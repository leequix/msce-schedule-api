package xyz.leequix.mscescheduleapi.mscescheduleapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Group;

import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, String> {
    Optional<Group> findByNumber(String number);
}
