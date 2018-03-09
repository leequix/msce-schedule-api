package xyz.leequix.mscescheduleapi.mscescheduleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.leequix.mscescheduleapi.mscescheduleapi.model.Group;
import xyz.leequix.mscescheduleapi.mscescheduleapi.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Cacheable("groups")
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findByNumber(String number) {
        return groupRepository.findByNumber(number).orElse(null);
    }

    public Group findOrCreate(String groupNumber) {
        Group group = groupRepository.findByNumber(groupNumber).orElse(null);
        if (group == null) {
            group = new Group(groupNumber);
            groupRepository.save(group);
        }

        return group;
    }

    public void save(Group group) {
        groupRepository.save(group);
    }
}
