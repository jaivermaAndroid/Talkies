
package com.talkies.model.moredetails;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Result implements Serializable
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
    @SerializedName("certificates")
    @Expose
    private List<Certificate> certificates = null;
    @SerializedName("run_time")
    @Expose
    private Double runTime;
    @SerializedName("release_year")
    @Expose
    private Integer releaseYear;
    @SerializedName("pid")
    @Expose
    private Object pid;
    @SerializedName("sub_media_list")
    @Expose
    private List<Object> subMediaList = null;
    @SerializedName("season_title")
    @Expose
    private String seasonTitle;
    @SerializedName("episode_title")
    @Expose
    private String episodeTitle;
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
    @SerializedName("big_banner")
    @Expose
    private String bigBanner;
    private final static long serialVersionUID = 7158390014474887684L;

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

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public Double getRunTime() {
        return runTime;
    }

    public void setRunTime(Double runTime) {
        this.runTime = runTime;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public String getBigBanner() {
        return bigBanner;
    }

    public void setBigBanner(String bigBanner) {
        this.bigBanner = bigBanner;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(episodeTitle).append(thumbnail).append(languages).append(videoSectionType).append(banner).append(mediaRentalPlan).append(pid).append(shortDescription).append(title).append(seasonTitle).append(subMediaList).append(certificates).append(genres).append(bigBanner).append(continueWatching).append(episodeList).append(runTime).append(releaseYear).append(slug).toHashCode();
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
        return new EqualsBuilder().append(episodeTitle, rhs.episodeTitle).append(thumbnail, rhs.thumbnail).append(languages, rhs.languages).append(videoSectionType, rhs.videoSectionType).append(banner, rhs.banner).append(mediaRentalPlan, rhs.mediaRentalPlan).append(pid, rhs.pid).append(shortDescription, rhs.shortDescription).append(title, rhs.title).append(seasonTitle, rhs.seasonTitle).append(subMediaList, rhs.subMediaList).append(certificates, rhs.certificates).append(genres, rhs.genres).append(bigBanner, rhs.bigBanner).append(continueWatching, rhs.continueWatching).append(episodeList, rhs.episodeList).append(runTime, rhs.runTime).append(releaseYear, rhs.releaseYear).append(slug, rhs.slug).isEquals();
    }

}
