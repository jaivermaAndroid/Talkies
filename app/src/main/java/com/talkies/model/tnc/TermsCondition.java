
package com.talkies.model.tnc;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TermsCondition implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("tnc")
    @Expose
    private List<Tnc> tnc = null;
    private final static long serialVersionUID = 6714399308507136022L;

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

    public List<Tnc> getTnc() {
        return tnc;
    }

    public void setTnc(List<Tnc> tnc) {
        this.tnc = tnc;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tnc).append(title).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TermsCondition) == false) {
            return false;
        }
        TermsCondition rhs = ((TermsCondition) other);
        return new EqualsBuilder().append(tnc, rhs.tnc).append(title, rhs.title).append(slug, rhs.slug).isEquals();
    }

}
