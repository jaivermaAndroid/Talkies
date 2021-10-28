
package com.talkies.model.searchitem;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RentalInfo implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("media_slug")
    @Expose
    private String mediaSlug;
    @SerializedName("marker")
    @Expose
    private Integer marker;
    @SerializedName("time_left")
    @Expose
    private Integer timeLeft;
    @SerializedName("is_cast_available")
    @Expose
    private Boolean isCastAvailable;
    private final static long serialVersionUID = 8403738402140434690L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaSlug() {
        return mediaSlug;
    }

    public void setMediaSlug(String mediaSlug) {
        this.mediaSlug = mediaSlug;
    }

    public Integer getMarker() {
        return marker;
    }

    public void setMarker(Integer marker) {
        this.marker = marker;
    }

    public Integer getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Boolean getIsCastAvailable() {
        return isCastAvailable;
    }

    public void setIsCastAvailable(Boolean isCastAvailable) {
        this.isCastAvailable = isCastAvailable;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isCastAvailable).append(id).append(mediaSlug).append(marker).append(timeLeft).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RentalInfo) == false) {
            return false;
        }
        RentalInfo rhs = ((RentalInfo) other);
        return new EqualsBuilder().append(isCastAvailable, rhs.isCastAvailable).append(id, rhs.id).append(mediaSlug, rhs.mediaSlug).append(marker, rhs.marker).append(timeLeft, rhs.timeLeft).isEquals();
    }

}
