
package com.talkies.model.continuewatching;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubMediaList implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("big_banner")
    @Expose
    private Object bigBanner;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("season_title")
    @Expose
    private String seasonTitle;
    @SerializedName("episode_title")
    @Expose
    private String episodeTitle;
    @SerializedName("pid")
    @Expose
    private Object pid;
    private final static long serialVersionUID = 5574850150402748718L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Object getBigBanner() {
        return bigBanner;
    }

    public void setBigBanner(Object bigBanner) {
        this.bigBanner = bigBanner;
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

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("banner", banner).append("bigBanner", bigBanner).append("shortDescription", shortDescription).append("slug", slug).append("seasonTitle", seasonTitle).append("episodeTitle", episodeTitle).append("pid", pid).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(seasonTitle).append(episodeTitle).append(bigBanner).append(banner).append(pid).append(shortDescription).append(title).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SubMediaList) == false) {
            return false;
        }
        SubMediaList rhs = ((SubMediaList) other);
        return new EqualsBuilder().append(seasonTitle, rhs.seasonTitle).append(episodeTitle, rhs.episodeTitle).append(bigBanner, rhs.bigBanner).append(banner, rhs.banner).append(pid, rhs.pid).append(shortDescription, rhs.shortDescription).append(title, rhs.title).append(slug, rhs.slug).isEquals();
    }

}
