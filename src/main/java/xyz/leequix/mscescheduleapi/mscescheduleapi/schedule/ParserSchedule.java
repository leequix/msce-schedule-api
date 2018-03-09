package xyz.leequix.mscescheduleapi.mscescheduleapi.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.parser.StudentsScheduleParserService;

@Component
public class ParserSchedule {
    @Autowired
    StudentsScheduleParserService studentsScheduleParserService;

    @Scheduled(cron = "0 30 * * * *")
    public void parseStudentData() {
        studentsScheduleParserService.parseSchedule();
    }
}
