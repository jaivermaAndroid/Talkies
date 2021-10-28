
package com.talkies.model.connecttv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Device implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("device_id")
    @Expose
    private Object deviceId;
    @SerializedName("last_listen_time")
    @Expose
    private Integer lastListenTime;
    @SerializedName("currently_playing")
    @Expose
    private Object currentlyPlaying;
    private final static long serialVersionUID = 8076866058407259648L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getLastListenTime() {
        return lastListenTime;
    }

    public void setLastListenTime(Integer lastListenTime) {
        this.lastListenTime = lastListenTime;
    }

    public Object getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(Object currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("deviceId", deviceId).append("lastListenTime", lastListenTime).append("currentlyPlaying", currentlyPlaying).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(currentlyPlaying).append(id).append(deviceId).append(lastListenTime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Device) == false) {
            return false;
        }
        Device rhs = ((Device) other);
        return new EqualsBuilder().append(name, rhs.name).append(currentlyPlaying, rhs.currentlyPlaying).append(id, rhs.id).append(deviceId, rhs.deviceId).append(lastListenTime, rhs.lastListenTime).isEquals();
    }

}
