package com.example.Like_and_Unlike_feature.Model;

import javax.persistence.*;

@Entity
@Table(name = "favourites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  private String favouriteStatus;
  private String fromCoin;
  private String toCoin;
  private Long userDetailsId;
  public  enum Status {ACTIVE,INACTIVE};
  @Enumerated(EnumType.STRING)
  private Status status;

    public Favourites() {
    }

    public Favourites(String favouriteStatus, String fromCoin, Long id, String toCoin, Long userDetailsId) {
        this.favouriteStatus = favouriteStatus;
        this.fromCoin = fromCoin;
        this.id = id;
        this.toCoin = toCoin;
        this.userDetailsId = userDetailsId;
    }

    public Favourites(Status status) {
        this.status = status;
    }

    public Favourites(Long id, String fromCoin, String toCoin, Status status) {
        this.id = id;
        this.fromCoin = fromCoin;
        this.toCoin = toCoin;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFavouriteStatus() {
        return favouriteStatus;
    }

    public void setFavouriteStatus(String favouriteStatus) {
        this.favouriteStatus = favouriteStatus;
    }

    public String getFromCoin() {
        return fromCoin;
    }

    public void setFromCoin(String fromCoin) {
        this.fromCoin = fromCoin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToCoin() {
        return toCoin;
    }

    public void setToCoin(String toCoin) {
        this.toCoin = toCoin;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
    }
}

