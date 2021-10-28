
package com.talkies.model.recyclercategorytab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class RecyclerCategoryList implements Serializable
{

    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("data")
    @Expose
    private List<List<Datum>> data = null;
    private final static long serialVersionUID = -2792725012810707255L;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List<Datum>> getData() {
        return data;
    }

    public void setData(List<List<Datum>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(data).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RecyclerCategoryList) == false) {
            return false;
        }
        RecyclerCategoryList rhs = ((RecyclerCategoryList) other);
        return new EqualsBuilder().append(title, rhs.title).append(data, rhs.data).append(slug, rhs.slug).isEquals();
    }

}
