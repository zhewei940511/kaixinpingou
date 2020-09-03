package com.laojiashop.laojia.entity;

public class MyteamBean {


    /**
     * totalNumberTeams : 0
     * numberTeamAddedToday : 0
     * myTeamPerformance : 0
     * teamNewPerformanceToday : 0
     */

    private int totalNumberTeams;
    private int numberTeamAddedToday;
    private String myTeamPerformance;
    private String teamNewPerformanceToday;

    public int getTotalNumberTeams() {
        return totalNumberTeams;
    }

    public void setTotalNumberTeams(int totalNumberTeams) {
        this.totalNumberTeams = totalNumberTeams;
    }

    public int getNumberTeamAddedToday() {
        return numberTeamAddedToday;
    }

    public void setNumberTeamAddedToday(int numberTeamAddedToday) {
        this.numberTeamAddedToday = numberTeamAddedToday;
    }

    public String getMyTeamPerformance() {
        return myTeamPerformance;
    }

    public void setMyTeamPerformance(String myTeamPerformance) {
        this.myTeamPerformance = myTeamPerformance;
    }

    public String getTeamNewPerformanceToday() {
        return teamNewPerformanceToday;
    }

    public void setTeamNewPerformanceToday(String teamNewPerformanceToday) {
        this.teamNewPerformanceToday = teamNewPerformanceToday;
    }
}
