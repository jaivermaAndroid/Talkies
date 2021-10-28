package com.talkies.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AlertNotification implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("action_label")
    @Expose
    private String actionLabel;
    @SerializedName("call_of_action")
    @Expose
    private String callOfAction;
    @SerializedName("alert_type")
    @Expose
    private String alertType;
    @SerializedName("is_dismissed")
    @Expose
    private Boolean isDismissed;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -2871470577988103204L;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getCallOfAction() {
        return callOfAction;
    }

    public void setCallOfAction(String callOfAction) {
        this.callOfAction = callOfAction;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public Boolean getIsDismissed() {
        return isDismissed;
    }

    public void setIsDismissed(Boolean isDismissed) {
        this.isDismissed = isDismissed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("description", description).append("icon", icon).append("actionLabel", actionLabel).append("callOfAction", callOfAction).append("alertType", alertType).append("isDismissed", isDismissed).append("id", id).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isDismissed).append(alertType).append(icon).append(description).append(callOfAction).append(id).append(title).append(actionLabel).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AlertNotification) == false) {
            return false;
        }
        AlertNotification rhs = ((AlertNotification) other);
        return new EqualsBuilder().append(isDismissed, rhs.isDismissed).append(alertType, rhs.alertType).append(icon, rhs.icon).append(description, rhs.description).append(callOfAction, rhs.callOfAction).append(id, rhs.id).append(title, rhs.title).append(actionLabel, rhs.actionLabel).isEquals();
    }

}
