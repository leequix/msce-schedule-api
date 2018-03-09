package xyz.leequix.mscescheduleapi.mscescheduleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Day;
import xyz.leequix.mscescheduleapi.mscescheduleapi.repository.DayRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DayService {
    @Autowired
    DayRepository dayRepository;

    public Day findOrCreate(Date date) {
        Day day = findByDate(date);
        if (day == null) {
            day = new Day(date, new ArrayList<>());
            dayRepository.save(day);
        }
        return day;
    }

    public Day findByDate(Date date) {
        return dayRepository.findByDate(date).orElse(null);
    }

    public List<Day> findAll() {
        return dayRepository.findAll();
    }


    public void save(Day day) {
        dayRepository.save(day);
    }
}
