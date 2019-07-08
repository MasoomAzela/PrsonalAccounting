package com.uni.pa.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "cost_title")
public class CostTitle extends AbstractEntity {
    private int id;
    private String name;
    private String description;

    public CostTitle() {
    }

    public CostTitle(String name, String description) {
        this.name = name;
        this.description = description;
    }

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}