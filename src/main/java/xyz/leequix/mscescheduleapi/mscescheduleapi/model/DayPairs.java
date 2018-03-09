package xyz.leequix.mscescheduleapi.mscescheduleapi.model;


import java.util.List;

public class DayPairs {
    private Group group;

    private List<Pair> pairs;

    public DayPairs(Group group, List<Pair> pairs) {
        this.group = group;
        this.pairs = pairs;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }
}
