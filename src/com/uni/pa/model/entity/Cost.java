package com.uni.pa.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cost")
public class Cost extends AbstractEntity {
    private int id;
    private Date insertDate;
    private long price;
    private Date buyDate;
    private User payer;
    private User addedBy;
    private String description;
    private CostTitle subject;

    public Cost() {
    }

    public Cost(CostTitle subject, long price, Date buyDate, User payer, User addedBy, String description) {
        //this.insertDate = insertDate;
        this.price = price;
        this.buyDate = buyDate;
        this.payer = payer;
        this.addedBy = addedBy;
        this.description = description;
        this.subject = subject;
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
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Basic
    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Basic
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "buy_date")
    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "added_by", referencedColumnName = "id")
    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @ManyToOne(targetEntity = CostTitle.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cost_title_id", referencedColumnName = "id")
    public CostTitle getCostSubject() {
        return subject;
    }

    public void setCostSubject(CostTitle costSubject) {
        this.subject = costSubject;
    }
}