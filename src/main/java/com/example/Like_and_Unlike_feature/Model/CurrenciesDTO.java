package com.example.Like_and_Unlike_feature.Model;

public class CurrenciesDTO
{
    private String ticker;
    private String name;
    private String image;
    private boolean hasExternalId;
    private boolean isFiat;
    private boolean featured;
    private boolean isStable;
    private boolean supportsFixedRate;

    //"btc","Bitcoin",: "https://changenow.io/images/coins/btc.svg",: false,: false,: true,: false,: true


    public CurrenciesDTO() {
    }

    public CurrenciesDTO(String ticker, String name, String image, boolean hasExternalId, boolean isFiat,boolean featured,
                         boolean isStable, boolean supportsFixedRate) {
        this.ticker = ticker;
        this.name = name;
        this.image = image;
        this.hasExternalId = hasExternalId;
        this.isFiat = isFiat;
        this.featured = featured;
        this.isStable = isStable;
        this.supportsFixedRate = supportsFixedRate;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isHasExternalId() {
        return hasExternalId;
    }

    public void setHasExternalId(boolean hasExternalId) {
        this.hasExternalId = hasExternalId;
    }

    public boolean isFiat() {
        return isFiat;
    }

    public void setFiat(boolean fiat) {
        isFiat = fiat;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isStable() {
        return isStable;
    }

    public void setStable(boolean stable) {
        isStable = stable;
    }

    public boolean isSupportsFixedRate() {
        return supportsFixedRate;
    }

    public void setSupportsFixedRate(boolean supportsFixedRate) {
        this.supportsFixedRate = supportsFixedRate;
    }
}
