
package com.talkies.model.faq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class FAQResponseModel implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("faqs")
    @Expose
    private List<Faq> faqs = null;
    private final static long serialVersionUID = -8090564549273204539L;

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

    public List<Faq> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<Faq> faqs) {
        this.faqs = faqs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("slug", slug).append("faqs", faqs).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(faqs).append(title).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FAQResponseModel) == false) {
            return false;
        }
        FAQResponseModel rhs = ((FAQResponseModel) other);
        return new EqualsBuilder().append(faqs, rhs.faqs).append(title, rhs.title).append(slug, rhs.slug).isEquals();
    }

}
