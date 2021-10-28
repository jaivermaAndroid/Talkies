
package com.talkies.model.announcements;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Anouncement implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("title")
    @Expose
    private String title;
    private final static long serialVersionUID = -6323712043698144689L;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  "Announcements";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(data).append(title).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Anouncement) == false) {
            return false;
        }
        Anouncement rhs = ((Anouncement) other);
        return new EqualsBuilder().append(data, rhs.data).append(title, rhs.title).isEquals();
    }

}
