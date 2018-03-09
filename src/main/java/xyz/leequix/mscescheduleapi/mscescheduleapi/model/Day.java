package xyz.leequix.mscescheduleapi.mscescheduleapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Day {
    @Id
    private String id;

    private Date date;

    private List<DayPairs> dayPairs;

    public Day(Date date, List<DayPairs> dayPairs) {
        this.date = date;
        this.dayPairs = dayPairs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DayPairs> getDayPairs() {
        return dayPairs;
    }

    public void setDayPairs(List<DayPairs> dayPairs) {
        this.dayPairs = dayPairs;
    }
}
