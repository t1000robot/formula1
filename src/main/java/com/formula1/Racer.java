package com.formula1;

public class Racer {
    private String abbreviation;
    private String name;
    private String team;
    private long startTime;
    private long endTime;
    private long resultTime;

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public void setResultTime(long resultTime) {
        this.resultTime = resultTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public Long getStartTime() {
        return startTime;
    }
    public Long getEndTime() {
        return endTime;
    }
    public Long getResultTime() {
        return resultTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Racer racer = (Racer) obj;
        if (!racer.abbreviation.equals(this.abbreviation))
            return false;
        if (!racer.name.equals(this.name))
            return false;
        if (!racer.team.equals(this.team))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 31*abbreviation.hashCode() + name.hashCode() + team.hashCode();
    }
}
