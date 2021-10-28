
package com.talkies.model.searchitem;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExtraFlags implements Serializable
{

    @SerializedName("is_released")
    @Expose
    private Boolean isReleased;
    @SerializedName("releasing_on_timestamp")
    @Expose
    private Integer releasingOnTimestamp;
    @SerializedName("expiring_on_timestamp")
    @Expose
    private Integer expiringOnTimestamp;
    private final static long serialVersionUID = 7139708061496435152L;

    public Boolean getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(Boolean isReleased) {
        this.isReleased = isReleased;
    }

    public Integer getReleasingOnTimestamp() {
        return releasingOnTimestamp;
    }

    public void setReleasingOnTimestamp(Integer releasingOnTimestamp) {
        this.releasingOnTimestamp = releasingOnTimestamp;
    }

    public Integer getExpiringOnTimestamp() {
        return expiringOnTimestamp;
    }

    public void setExpiringOnTimestamp(Integer expiringOnTimestamp) {
        this.expiringOnTimestamp = expiringOnTimestamp;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(releasingOnTimestamp).append(expiringOnTimestamp).append(isReleased).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExtraFlags) == false) {
            return false;
        }
        ExtraFlags rhs = ((ExtraFlags) other);
        return new EqualsBuilder().append(releasingOnTimestamp, rhs.releasingOnTimestamp).append(expiringOnTimestamp, rhs.expiringOnTimestamp).append(isReleased, rhs.isReleased).isEquals();
    }

}
