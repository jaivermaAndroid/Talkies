
package com.talkies.model.moredetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VideoSectionType implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    private final static long serialVersionUID = 2834601359566999988L;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VideoSectionType) == false) {
            return false;
        }
        VideoSectionType rhs = ((VideoSectionType) other);
        return new EqualsBuilder().append(title, rhs.title).append(slug, rhs.slug).isEquals();
    }

}