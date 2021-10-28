
package com.talkies.model.subscription;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SubscriptionResult implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("is_recommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    private final static long serialVersionUID = -3226662007966068480L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(icon).append(isRecommended).append(logo).append(title).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SubscriptionResult) == false) {
            return false;
        }
        SubscriptionResult rhs = ((SubscriptionResult) other);
        return new EqualsBuilder().append(icon, rhs.icon).append(isRecommended, rhs.isRecommended).append(logo, rhs.logo).append(title, rhs.title).append(data, rhs.data).isEquals();
    }

}
