
package com.talkies.model.faq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Faq implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    private final static long serialVersionUID = 5117496344505591003L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("description", description).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(description).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Faq) == false) {
            return false;
        }
        Faq rhs = ((Faq) other);
        return new EqualsBuilder().append(title, rhs.title).append(description, rhs.description).isEquals();
    }

}
