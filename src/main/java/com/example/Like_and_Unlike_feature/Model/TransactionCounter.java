package com.example.Like_and_Unlike_feature.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "TransactionCounter")
public class TransactionCounter
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long mailCounter;
    @Version
    @Column(nullable = false)
    public Long favouritesCounter;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate=new Date(System.currentTimeMillis());

    public TransactionCounter() {
    }

    public TransactionCounter(Long id, Long mailCounter, Long favouritesCounter, Date createDate) {
        this.id = id;
        this.mailCounter = mailCounter;
        this.favouritesCounter = favouritesCounter;
        this.createDate = createDate;
    }

    public TransactionCounter(Long favouritesCounter) {
        this.favouritesCounter = favouritesCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMailCounter() {
        return mailCounter;
    }

    public void setMailCounter(Long mailCounter) {
        this.mailCounter = mailCounter;
    }

    public Long getFavouritesCounter() {
        return favouritesCounter;
    }

    public void setFavouritesCounter(Long favouritesCounter) {
        this.favouritesCounter = favouritesCounter;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
