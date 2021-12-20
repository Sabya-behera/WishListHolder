package com.example.Like_and_Unlike_feature.Model;

import javax.persistence.*;

@Entity
@Table(name = "exchangepair")
public class ExchangePair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromCoin;
    @Column
    private String toCoin;
    public enum Status {ACTIVE, INACTIVE};
    @Enumerated(EnumType.STRING)
    private Status status;

    public ExchangePair() {
    }

    public ExchangePair(Long id, String fromCoin, String toCoin, Status status) {
        this.id = id;
        this.fromCoin = fromCoin;
        this.toCoin = toCoin;
        this.status = status;
    }

    public ExchangePair(String fromCoin, String toCoin, Status status) {
        this.fromCoin = fromCoin;
        this.toCoin = toCoin;
        this.status = status;
    }

    public ExchangePair(String fromCoin, String toCoin) {
        this.fromCoin = fromCoin;
        this.toCoin = toCoin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCoin() {
        return fromCoin;
    }

    public void setFromCoin(String fromCoin) {
        this.fromCoin = fromCoin;
    }

    public String getToCoin() {
        return toCoin;
    }

    public void setToCoin(String toCoin) {
        this.toCoin = toCoin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}