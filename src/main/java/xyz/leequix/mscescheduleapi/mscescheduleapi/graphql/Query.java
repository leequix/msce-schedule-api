package xyz.leequix.mscescheduleapi.mscescheduleapi.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.leequix.mscescheduleapi.mscescheduleapi.graphql.exception.DateParseException;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Day;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.DayPairs;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Group;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.DayService;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.GroupService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    private static final DateFormat graphqlDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    GroupService groupService;

    @Autowired
    DayService dayService;

    public List<Group> findAllGroups() {
        return groupService.findAll();
    }

    public Group findGroupByNumber(String number) {
        return groupService.findByNumber(number);
    }

    private static final Logger log = LoggerFactory.getLogger(Query.class);

    public List<Day> findAllDays() {
        return dayService.findAll();
    }

    public Day findDayByDate(String date) {
        try {
            return dayService.findByDate(graphqlDateFormat.parse(date));
        } catch (ParseException e) {
            throw new DateParseException("Can not parse this date, please use format dd.MM.yyyy", date);
        }
    }
}
