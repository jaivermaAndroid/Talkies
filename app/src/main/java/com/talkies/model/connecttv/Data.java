
package com.talkies.model.connecttv;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Data implements Serializable
{

    @SerializedName("device")
    @Expose
    private Device device;
    private final static long serialVersionUID = -2156945541317258146L;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("device", device).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(device).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return new EqualsBuilder().append(device, rhs.device).isEquals();
    }

}
