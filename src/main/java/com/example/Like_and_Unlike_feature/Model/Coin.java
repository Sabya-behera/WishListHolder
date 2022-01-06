package com.example.Like_and_Unlike_feature.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coin {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Id;

    private String shortName;
    private String FullName;
    private String icon;
    @Enumerated(EnumType.STRING)
    private CoinType coinType;

    public enum CoinType {
        OTHERS, ERC20, ERC721;
    }

    @Enumerated(EnumType.STRING)
    private Favourites.Status status;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'NOT_AVAILABLE'")
    private ExchangeStatus exchangeStatus;

    public enum ExchangeStatus {
        NOT_AVAILABLE, AVAILABLE;
    }

    private String nftArtWorkUrl;
    private String nftArtWorkIcon;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }

    public Favourites.Status getStatus() {
        return status;
    }

    public void setStatus(Favourites.Status status) {
        this.status = status;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getNftArtWorkUrl() {
        return nftArtWorkUrl;
    }

    public void setNftArtWorkUrl(String nftArtWorkUrl) {
        this.nftArtWorkUrl = nftArtWorkUrl;
    }

    public String getNftArtWorkIcon() {
        return nftArtWorkIcon;
    }

    public void setNftArtWorkIcon(String nftArtWorkIcon) {
        this.nftArtWorkIcon = nftArtWorkIcon;
    }
}
