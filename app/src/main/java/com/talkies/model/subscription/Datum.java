
package com.talkies.model.subscription;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Datum implements Serializable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("long_description")
    @Expose
    private String longDescription;
    @SerializedName("is_cast_available")
    @Expose
    private Boolean isCastAvailable;
    @SerializedName("subscription_type")
    @Expose
    private String subscriptionType;
    @SerializedName("subscription_duration_sec")
    @Expose
    private Integer subscriptionDurationSec;
    @SerializedName("subscription_duration")
    @Expose
    private Integer subscriptionDuration;
    @SerializedName("is_gst_applicable")
    @Expose
    private Boolean isGstApplicable;
    @SerializedName("price_distribution")
    @Expose
    private PriceDistribution priceDistribution;
    @SerializedName("is_auto_renewal_available")
    @Expose
    private Boolean isAutoRenewalAvailable;
    @SerializedName("actual_price")
    @Expose
    private String actualPrice;
    @SerializedName("is_recommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("icon")
    @Expose
    private Object icon;
    @SerializedName("rental_type")
    @Expose
    private String rentalType;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("card_title")
    @Expose
    private String cardTitle;
    private final static long serialVersionUID = -4723655910249181106L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Boolean getIsCastAvailable() {
        return isCastAvailable;
    }

    public void setIsCastAvailable(Boolean isCastAvailable) {
        this.isCastAvailable = isCastAvailable;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Integer getSubscriptionDurationSec() {
        return subscriptionDurationSec;
    }

    public void setSubscriptionDurationSec(Integer subscriptionDurationSec) {
        this.subscriptionDurationSec = subscriptionDurationSec;
    }

    public Integer getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(Integer subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    }

    public Boolean getIsGstApplicable() {
        return isGstApplicable;
    }

    public void setIsGstApplicable(Boolean isGstApplicable) {
        this.isGstApplicable = isGstApplicable;
    }

    public PriceDistribution getPriceDistribution() {
        return priceDistribution;
    }

    public void setPriceDistribution(PriceDistribution priceDistribution) {
        this.priceDistribution = priceDistribution;
    }

    public Boolean getIsAutoRenewalAvailable() {
        return isAutoRenewalAvailable;
    }

    public void setIsAutoRenewalAvailable(Boolean isAutoRenewalAvailable) {
        this.isAutoRenewalAvailable = isAutoRenewalAvailable;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(longDescription).append(code).append(priceDistribution).append(actualPrice).append(icon).append(currencySymbol).append(cardTitle).append(isGstApplicable).append(isAutoRenewalAvailable).append(isCastAvailable).append(subscriptionDurationSec).append(subscriptionType).append(rentalType).append(isRecommended).append(currency).append(id).append(subscriptionDuration).append(desc).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return new EqualsBuilder().append(longDescription, rhs.longDescription).append(code, rhs.code).append(priceDistribution, rhs.priceDistribution).append(actualPrice, rhs.actualPrice).append(icon, rhs.icon).append(currencySymbol, rhs.currencySymbol).append(cardTitle, rhs.cardTitle).append(isGstApplicable, rhs.isGstApplicable).append(isAutoRenewalAvailable, rhs.isAutoRenewalAvailable).append(isCastAvailable, rhs.isCastAvailable).append(subscriptionDurationSec, rhs.subscriptionDurationSec).append(subscriptionType, rhs.subscriptionType).append(rentalType, rhs.rentalType).append(isRecommended, rhs.isRecommended).append(currency, rhs.currency).append(id, rhs.id).append(subscriptionDuration, rhs.subscriptionDuration).append(desc, rhs.desc).isEquals();
    }

}
