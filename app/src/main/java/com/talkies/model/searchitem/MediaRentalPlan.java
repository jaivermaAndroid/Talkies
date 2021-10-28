
package com.talkies.model.searchitem;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MediaRentalPlan implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("is_recommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("logo")
    @Expose
    private String logo;
    private final static long serialVersionUID = -7722480408301610108L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(icon).append(isRecommended).append(logo).append(title).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MediaRentalPlan) == false) {
            return false;
        }
        MediaRentalPlan rhs = ((MediaRentalPlan) other);
        return new EqualsBuilder().append(icon, rhs.icon).append(isRecommended, rhs.isRecommended).append(logo, rhs.logo).append(title, rhs.title).append(slug, rhs.slug).isEquals();
    }

}
