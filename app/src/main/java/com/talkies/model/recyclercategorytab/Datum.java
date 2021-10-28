
package com.talkies.model.recyclercategorytab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("run_time")
    @Expose
    private Double runTime;
    @SerializedName("pid")
    @Expose
    private Object pid;
    @SerializedName("sub_media_list")
    @Expose
    private List<Object> subMediaList = null;
    @SerializedName("season_title")
    @Expose
    private Object seasonTitle;
    @SerializedName("episode_title")
    @Expose
    private Object episodeTitle;
    @SerializedName("media_rental_plan")
    @Expose
    private MediaRentalPlan mediaRentalPlan;
    @SerializedName("video_section_type")
    @Expose
    private VideoSectionType videoSectionType;
    @SerializedName("continue_watching")
    @Expose
    private Object continueWatching;
    @SerializedName("episode_list")
    @Expose
    private List<Object> episodeList = null;
    private final static long serialVersionUID = 5383906832810045456L;

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

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getRunTime() {
        return runTime;
    }

    public void setRunTime(Double runTime) {
        this.runTime = runTime;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }

    public List<Object> getSubMediaList() {
        return subMediaList;
    }

    public void setSubMediaList(List<Object> subMediaList) {
        this.subMediaList = subMediaList;
    }

    public Object getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(Object seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public Object getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(Object episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public MediaRentalPlan getMediaRentalPlan() {
        return mediaRentalPlan;
    }

    public void setMediaRentalPlan(MediaRentalPlan mediaRentalPlan) {
        this.mediaRentalPlan = mediaRentalPlan;
    }

    public VideoSectionType getVideoSectionType() {
        return videoSectionType;
    }

    public void setVideoSectionType(VideoSectionType videoSectionType) {
        this.videoSectionType = videoSectionType;
    }

    public Object getContinueWatching() {
        return continueWatching;
    }

    public void setContinueWatching(Object continueWatching) {
        this.continueWatching = continueWatching;
    }

    public List<Object> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Object> episodeList) {
        this.episodeList = episodeList;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(episodeTitle).append(thumbnail).append(languages).append(videoSectionType).append(banner).append(mediaRentalPlan).append(pid).append(shortDescription).append(title).append(seasonTitle).append(subMediaList).append(genres).append(continueWatching).append(episodeList).append(runTime).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return new EqualsBuilder().append(episodeTitle, rhs.episodeTitle).append(thumbnail, rhs.thumbnail).append(languages, rhs.languages).append(videoSectionType, rhs.videoSectionType).append(banner, rhs.banner).append(mediaRentalPlan, rhs.mediaRentalPlan).append(pid, rhs.pid).append(shortDescription, rhs.shortDescription).append(title, rhs.title).append(seasonTitle, rhs.seasonTitle).append(subMediaList, rhs.subMediaList).append(genres, rhs.genres).append(continueWatching, rhs.continueWatching).append(episodeList, rhs.episodeList).append(runTime, rhs.runTime).append(slug, rhs.slug).isEquals();
    }

}
