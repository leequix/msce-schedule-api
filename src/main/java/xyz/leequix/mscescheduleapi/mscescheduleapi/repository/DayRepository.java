package xyz.leequix.mscescheduleapi.mscescheduleapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Day;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.DayPairs;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Group;

import java.util.Date;
import java.util.Optional;

public interface DayRepository extends MongoRepository<Day, String> {
    Optional<Day> findByDate(Date date);
}
