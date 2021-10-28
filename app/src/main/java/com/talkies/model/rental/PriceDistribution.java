
package com.talkies.model.rental;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PriceDistribution implements Serializable
{

    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("gst_amount")
    @Expose
    private Double gstAmount;
    @SerializedName("base_amount")
    @Expose
    private Double baseAmount;
    private final static long serialVersionUID = -3017169399229651754L;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(Double gstAmount) {
        this.gstAmount = gstAmount;
    }

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(totalAmount).append(gstAmount).append(baseAmount).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PriceDistribution) == false) {
            return false;
        }
        PriceDistribution rhs = ((PriceDistribution) other);
        return new EqualsBuilder().append(totalAmount, rhs.totalAmount).append(gstAmount, rhs.gstAmount).append(baseAmount, rhs.baseAmount).isEquals();
    }

}
