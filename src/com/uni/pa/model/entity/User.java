package com.uni.pa.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    private int id;
    private String fName;
    private String lName;
    private String subject;
    private long credit;
//    private List<Cost> cost;

    public User() {
    }

    public User(String fName, String lName, String subject, long credit) {
        this.fName = fName;
        this.lName = lName;
        this.subject = subject;
        this.credit = credit;
    }

    @Transient
    public String getFullName() {
        return this.fName + " " + this.lName;
    }

    /*@OneToMany(targetEntity = Cost.class, fetch = FetchType.EAGER, mappedBy = "payer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<Cost> getCost() {
        return cost;
    }

    public void setCost(List<Cost> cost) {
        this.cost = cost;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "l_name")
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "credit")
    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "f_name")
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

}