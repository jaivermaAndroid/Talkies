
package com.talkies.model.rental;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RentalOption implements Serializable
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
    @SerializedName("is_recommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("icon")
    @Expose
    private Object icon;
    @SerializedName("section")
    @Expose
    private Object section;
    @SerializedName("card_title")
    @Expose
    private Object cardTitle;
    private final static long serialVersionUID = 176526194328747691L;

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

    public Object getSection() {
        return section;
    }

    public void setSection(Object section) {
        this.section = section;
    }

    public Object getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(Object cardTitle) {
        this.cardTitle = cardTitle;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(longDescription).append(isCastAvailable).append(code).append(isRecommended).append(icon).append(section).append(id).append(cardTitle).append(desc).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RentalOption) == false) {
            return false;
        }
        RentalOption rhs = ((RentalOption) other);
        return new EqualsBuilder().append(longDescription, rhs.longDescription).append(isCastAvailable, rhs.isCastAvailable).append(code, rhs.code).append(isRecommended, rhs.isRecommended).append(icon, rhs.icon).append(section, rhs.section).append(id, rhs.id).append(cardTitle, rhs.cardTitle).append(desc, rhs.desc).isEquals();
    }

}
