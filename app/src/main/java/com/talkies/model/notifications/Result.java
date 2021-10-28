
package com.talkies.model.notifications;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("call_of_action")
    @Expose
    private Object callOfAction;
    @SerializedName("action_label")
    @Expose
    private Object actionLabel;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("notification_create_on")
    @Expose
    private Integer notificationCreateOn;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -979701673974356577L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Object getCallOfAction() {
        return callOfAction;
    }

    public void setCallOfAction(Object callOfAction) {
        this.callOfAction = callOfAction;
    }

    public Object getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(Object actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNotificationCreateOn() {
        return notificationCreateOn;
    }

    public void setNotificationCreateOn(Integer notificationCreateOn) {
        this.notificationCreateOn = notificationCreateOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(notificationCreateOn).append(callOfAction).append(shortDescription).append(id).append(title).append(type).append(slug).append(actionLabel).append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(notificationCreateOn, rhs.notificationCreateOn).append(callOfAction, rhs.callOfAction).append(shortDescription, rhs.shortDescription).append(id, rhs.id).append(title, rhs.title).append(type, rhs.type).append(slug, rhs.slug).append(actionLabel, rhs.actionLabel).append(status, rhs.status).isEquals();
    }

}
