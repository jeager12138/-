package com.sumflower.demo.model;

public class Project {
    private int id;
    private String projectName;  //作品名称
    private String college;    //院系
    private int competitionType;    //竞赛类别 0是科技发明制作，1是调查报告和学术论文
    private String studentName;
    private String studentNumber;
    private String birthDay;
    private String education;    //学历
    private String major;
    private String entryYear;
    private String projectFullName;  //作品全称
    private String address;
    private String phone;
    private String email;
    private String friends;    //合作者情况
    private int projectType;   //作品分类,0~5对应A~F
    private String details;    //作品总体情况说明
    private String invention;  //创新点
    private String keywords;
    private String picUrl;
    private String docUrl;
    private String videoUrl;
    private double averageScore;
    private int submitStatus;     //提交状态 0已经提交 1还可修改 2通过 3拒绝
    private int studentId;
    private int competitionId;
    private String showForm;
    private String searchWay;
    private int judgeNum;

    public int getJudgeNum() {
        return judgeNum;
    }

    public void setJudgeNum(int judgeNum) {
        this.judgeNum = judgeNum;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Project(){}

    public Project(int id,String projectName, String college, int competitionType, String studentName,
                   String studentNumber, String birthDay, String education, String major,
                   String entryYear, String projectFullName, String address, String phone,
                   String email, String friends, int projectType, String details, String invention,
                   String keywords, String picUrl, String docUrl, String videoUrl,
                   double averageScore, int submitStatus,int studentId){
        this.id = id;
        this.projectName = projectName;
        this.college = college;
        this.competitionType = competitionType;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.birthDay = birthDay;
        this.education = education;
        this.major = major;
        this.entryYear = entryYear;
        this.projectFullName = projectFullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.friends = friends;
        this.projectType = projectType;
        this.details = details;
        this.invention = invention;
        this.keywords = keywords;
        this.picUrl = picUrl;
        this.docUrl = docUrl;
        this.videoUrl = videoUrl;
        this.averageScore = averageScore;
        this.submitStatus = submitStatus;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", college='" + college + '\'' +
                ", competitionType=" + competitionType +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", entryYear='" + entryYear + '\'' +
                ", projectFullName='" + projectFullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", friends='" + friends + '\'' +
                ", projectType=" + projectType +
                ", details='" + details + '\'' +
                ", invention='" + invention + '\'' +
                ", keywords='" + keywords + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", docUrl='" + docUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", averageScore=" + averageScore +
                ", submitStatus=" + submitStatus +
                ", studentId=" + studentId +
                '}';
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(int competitionType) {
        this.competitionType = competitionType;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public String getProjectFullName() {
        return projectFullName;
    }

    public void setProjectFullName(String projectFullName) {
        this.projectFullName = projectFullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInvention() {
        return invention;
    }

    public void setInvention(String invention) {
        this.invention = invention;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
