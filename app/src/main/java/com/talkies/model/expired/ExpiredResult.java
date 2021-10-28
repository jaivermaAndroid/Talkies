
package com.talkies.model.expired;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExpiredResult implements Serializable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("current")
    @Expose
    private String current;
    private final static long serialVersionUID = -1704843528595271923L;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("count", count).append("next", next).append("previous", previous).append("results", results).append("current", current).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(count).append(next).append(current).append(previous).append(results).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExpiredResult) == false) {
            return false;
        }
        ExpiredResult rhs = ((ExpiredResult) other);
        return new EqualsBuilder().append(count, rhs.count).append(next, rhs.next).append(current, rhs.current).append(previous, rhs.previous).append(results, rhs.results).isEquals();
    }

}
