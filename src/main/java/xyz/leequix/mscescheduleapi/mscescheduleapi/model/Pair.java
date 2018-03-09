package xyz.leequix.mscescheduleapi.mscescheduleapi.model;

import java.util.List;

public class Pair {
    private Integer number;

    private List<SubgroupPair> subgroupPairs;

    public Pair(Integer number, List<SubgroupPair> subgroupPairs) {
        this.number = number;
        this.subgroupPairs = subgroupPairs;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<SubgroupPair> getSubgroupPairs() {
        return subgroupPairs;
    }

    public void setSubgroupPairs(List<SubgroupPair> subgroupPairs) {
        this.subgroupPairs = subgroupPairs;
    }
}
