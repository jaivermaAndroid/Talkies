
package com.talkies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UserDetails implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("is_verified")
    @Expose
    private boolean isVerified;
    @SerializedName("is_profile_complete")
    @Expose
    private boolean isProfileComplete;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("user_type")
    @Expose
    private Object userType;
    @SerializedName("notification_enabled")
    @Expose
    private boolean notificationEnabled;
    @SerializedName("active_subscriptions")
    @Expose
    private Object activeSubscriptions;
    @SerializedName("preferred_video_quality")
    @Expose
    private PreferredVideoQuality preferredVideoQuality;
    @SerializedName("password_configured")
    @Expose
    private boolean passwordConfigured;
    private final static long serialVersionUID = 8410170659258737561L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean isIsProfileComplete() {
        return isProfileComplete;
    }

    public void setIsProfileComplete(boolean isProfileComplete) {
        this.isProfileComplete = isProfileComplete;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public Object getActiveSubscriptions() {
        return activeSubscriptions;
    }

    public void setActiveSubscriptions(Object activeSubscriptions) {
        this.activeSubscriptions = activeSubscriptions;
    }

    public PreferredVideoQuality getPreferredVideoQuality() {
        return preferredVideoQuality;
    }

    public void setPreferredVideoQuality(PreferredVideoQuality preferredVideoQuality) {
        this.preferredVideoQuality = preferredVideoQuality;
    }

    public boolean isPasswordConfigured() {
        return passwordConfigured;
    }

    public void setPasswordConfigured(boolean passwordConfigured) {
        this.passwordConfigured = passwordConfigured;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("mobileNumber");
        sb.append('=');
        sb.append(((this.mobileNumber == null)?"<null>":this.mobileNumber));
        sb.append(',');
        sb.append("countryCode");
        sb.append('=');
        sb.append(((this.countryCode == null)?"<null>":this.countryCode));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("isVerified");
        sb.append('=');
        sb.append(this.isVerified);
        sb.append(',');
        sb.append("isProfileComplete");
        sb.append('=');
        sb.append(this.isProfileComplete);
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("photo");
        sb.append('=');
        sb.append(((this.photo == null)?"<null>":this.photo));
        sb.append(',');
        sb.append("userType");
        sb.append('=');
        sb.append(((this.userType == null)?"<null>":this.userType));
        sb.append(',');
        sb.append("notificationEnabled");
        sb.append('=');
        sb.append(this.notificationEnabled);
        sb.append(',');
        sb.append("activeSubscriptions");
        sb.append('=');
        sb.append(((this.activeSubscriptions == null)?"<null>":this.activeSubscriptions));
        sb.append(',');
        sb.append("preferredVideoQuality");
        sb.append('=');
        sb.append(((this.preferredVideoQuality == null)?"<null>":this.preferredVideoQuality));
        sb.append(',');
        sb.append("passwordConfigured");
        sb.append('=');
        sb.append(this.passwordConfigured);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
