package xyz.leequix.mscescheduleapi.mscescheduleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Day;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.DayPairs;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Group;
import xyz.leequix.mscescheduleapi.mscescheduleapi.repository.DayRepository;

import java.util.Optional;

@Service
public class DayPairsService {
    @Autowired
    DayRepository dayRepository;

    public Optional<DayPairs> findGroupPairsOnDay(Day day, Group group) {
        for(DayPairs dayPairs : day.getDayPairs()) {
            if (dayPairs.getGroup().equals(group)) {
                return Optional.of(dayPairs);
            }
        }
        return Optional.empty();
    }
}
