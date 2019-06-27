package com.sumflower.demo.model;

public class Judge {
    private int id;
    private int projectId;
    private int score;
    private String suggestion;
    private int judgeStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(int judgeStatus) {
        this.judgeStatus = judgeStatus;
    }
}
