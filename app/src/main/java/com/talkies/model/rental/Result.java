
package com.talkies.model.rental;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result implements Serializable
{

    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("rental_option")
    @Expose
    private RentalOption rentalOption;
    @SerializedName("amount_paid")
    @Expose
    private String amountPaid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payment_completed_on")
    @Expose
    private Integer paymentCompletedOn;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("is_expired")
    @Expose
    private Boolean isExpired;
    @SerializedName("expired_on")
    @Expose
    private Integer expiredOn;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price_distribution")
    @Expose
    private PriceDistribution priceDistribution;
    @SerializedName("receipt_url")
    @Expose
    private String receiptUrl;
    @SerializedName("is_gst_applicable")
    @Expose
    private Boolean isGstApplicable;
    @SerializedName("rental_type")
    @Expose
    private String rentalType;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("rent_activated_on")
    @Expose
    private Integer rentActivatedOn;
    @SerializedName("duration_sec")
    @Expose
    private Integer durationSec;
    @SerializedName("god_created_plan")
    @Expose
    private Boolean godCreatedPlan;
    private final static long serialVersionUID = 1466465392536344859L;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public RentalOption getRentalOption() {
        return rentalOption;
    }

    public void setRentalOption(RentalOption rentalOption) {
        this.rentalOption = rentalOption;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPaymentCompletedOn() {
        return paymentCompletedOn;
    }

    public void setPaymentCompletedOn(Integer paymentCompletedOn) {
        this.paymentCompletedOn = paymentCompletedOn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Integer getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Integer expiredOn) {
        this.expiredOn = expiredOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PriceDistribution getPriceDistribution() {
        return priceDistribution;
    }

    public void setPriceDistribution(PriceDistribution priceDistribution) {
        this.priceDistribution = priceDistribution;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public Boolean getIsGstApplicable() {
        return isGstApplicable;
    }

    public void setIsGstApplicable(Boolean isGstApplicable) {
        this.isGstApplicable = isGstApplicable;
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

    public Integer getRentActivatedOn() {
        return rentActivatedOn;
    }

    public void setRentActivatedOn(Integer rentActivatedOn) {
        this.rentActivatedOn = rentActivatedOn;
    }

    public Integer getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(Integer durationSec) {
        this.durationSec = durationSec;
    }

    public Boolean getGodCreatedPlan() {
        return godCreatedPlan;
    }

    public void setGodCreatedPlan(Boolean godCreatedPlan) {
        this.godCreatedPlan = godCreatedPlan;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(priceDistribution).append(paymentCompletedOn).append(orderId).append(currencySymbol).append(media).append(expiredOn).append(rentalOption).append(isGstApplicable).append(godCreatedPlan).append(amountPaid).append(rentalType).append(rentActivatedOn).append(currency).append(id).append(isExpired).append(receiptUrl).append(durationSec).append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(priceDistribution, rhs.priceDistribution).append(paymentCompletedOn, rhs.paymentCompletedOn).append(orderId, rhs.orderId).append(currencySymbol, rhs.currencySymbol).append(media, rhs.media).append(expiredOn, rhs.expiredOn).append(rentalOption, rhs.rentalOption).append(isGstApplicable, rhs.isGstApplicable).append(godCreatedPlan, rhs.godCreatedPlan).append(amountPaid, rhs.amountPaid).append(rentalType, rhs.rentalType).append(rentActivatedOn, rhs.rentActivatedOn).append(currency, rhs.currency).append(id, rhs.id).append(isExpired, rhs.isExpired).append(receiptUrl, rhs.receiptUrl).append(durationSec, rhs.durationSec).append(status, rhs.status).isEquals();
    }

}
