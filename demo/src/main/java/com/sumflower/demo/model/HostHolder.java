package com.sumflower.demo.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<StudentLogin> students = new ThreadLocal<>();
    private static ThreadLocal<ExpertLogin> experts = new ThreadLocal<>();
    private static ThreadLocal<CommitteeLogin> committees = new ThreadLocal<>();
    private static ThreadLocal<LoginTicket> loginTicket = new ThreadLocal<>();

    public StudentLogin getStudent() { return students.get();}
    public ExpertLogin getExpert() { return experts.get();}
    public CommitteeLogin getCommittee() { return committees.get();}
    public LoginTicket getLoginTicket() { return loginTicket.get();}

    public void setStudents(StudentLogin student) {students.set(student);}
    public void setExperts(ExpertLogin expert) {experts.set(expert);}
    public void setCommittees(CommitteeLogin committee) {committees.set(committee);}
    public void setLoginTicket(LoginTicket ticket) {loginTicket.set(ticket);}

    public void clear() {
        students.remove();
        experts.remove();
        committees.remove();
        loginTicket.remove();
    }


}
