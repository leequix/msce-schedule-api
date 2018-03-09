package xyz.leequix.mscescheduleapi.mscescheduleapi.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.*;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.DayPairsService;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.DayService;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.GroupService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentsScheduleParserService implements ScheduleParserService {
    @Autowired
    private DayService dayService;

    @Autowired
    DayPairsService dayPairsService;

    @Autowired
    GroupService groupService;

    private static final String MSCE_SCHEDULE_PAGE_URL = "http://mgke.minsk.edu.by/ru/main.aspx?guid=3831";

    private static final Logger log = LoggerFactory.getLogger(StudentsScheduleParserService.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Day getDayFromMSCEDate(String msceDate) throws ParseException {
        return dayService.findOrCreate(dateFormat.parse(msceDate.split(", ")[1]));
    }

    public DayPairs getPairsOnDayForGroup(Day day, Group group) {
        DayPairs dayPairs = dayPairsService.findGroupPairsOnDay(day, group).orElse(null);

        if (dayPairs == null) {
            dayPairs = new DayPairs(group, Collections.emptyList());
            day.getDayPairs().add(dayPairs);
            dayService.save(day);
        }

        return dayPairs;
    }

    public List<SubgroupPair> parseMSCEPair(String pairSource, String audienceSource) {
        final Matcher subgroupsMatcher = Pattern.compile("(\\d.)?\\D+").matcher(pairSource);
        final Matcher audiencesMatcher = Pattern.compile("[\\d\\-]+([а-яА-Яa-zA-Z\\s()]+)?").matcher(audienceSource);
        List<String> subgroups = new ArrayList<>();
        List<String> audiences = new ArrayList<>();
        while (subgroupsMatcher.find()) {
            subgroups.add(subgroupsMatcher.group(0));
        }
        while (audiencesMatcher.find()) {
            audiences.add(audiencesMatcher.group(0));
        }

        List<SubgroupPair> subgroupPairs = new ArrayList<>();
        for (Integer subgroupIndex = 0; subgroupIndex < subgroups.size(); subgroupIndex++) {
            subgroupPairs.add(new SubgroupPair(subgroups.get(subgroupIndex), audiences.get(subgroupIndex)));
        }
        return subgroupPairs;
    }

    @Override
    public void parseSchedule() {
        log.info("Getting schedule from MSCE site");
        Document msceSchedulePage;
        try {
            msceSchedulePage = Jsoup.connect(MSCE_SCHEDULE_PAGE_URL).get();
        } catch (IOException e) {
            log.error("Can not get and parse document from URL {}", MSCE_SCHEDULE_PAGE_URL);
            return;
        }
        Elements scheduleTables = msceSchedulePage.select("table");
        for (Element table : scheduleTables) {
            Elements rows = table.select("tbody tr");
            Element header = rows.first().selectFirst("td[width=100%]");
            Day day;
            try {
                day = getDayFromMSCEDate(header.text());
            } catch (ParseException e) {
                log.error("Can not parse date from source: {}", header.text());
                continue;
            }
            Elements groupHeaders = rows.get(1).select("td[colspan=2]");

            Integer pairsCount = rows.size() - 3;

            for (Integer groupIndex = 0; groupIndex < groupHeaders.size(); groupIndex++) {
                Group group = groupService.findOrCreate(groupHeaders.get(groupIndex).text());
                DayPairs dayPairs = getPairsOnDayForGroup(day, group);
                List<Pair> pairs = new ArrayList<>();
                for (Integer pairIndex = 0; pairIndex < pairsCount; pairIndex++) {
                    Elements row = rows.get(pairIndex + 3).select("td");
                    String pairSource = row.get(2 * groupIndex + 1).text();
                    String audienceSource = row.get(2 * groupIndex + 2).text();
                    List<SubgroupPair> subgroupPairs = parseMSCEPair(pairSource, audienceSource);
                    if (subgroupPairs.isEmpty()) {
                        continue;
                    }
                    pairs.add(new Pair(pairIndex + 1, subgroupPairs));
                }
                dayPairs.setPairs(pairs);
            }
            dayService.save(day);
        }
    }
}
