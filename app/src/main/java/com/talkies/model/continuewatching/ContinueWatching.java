
package com.talkies.model.continuewatching;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ContinueWatching implements Serializable
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
    private List<Object> genres = null;
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
    private List<Object> certificates = null;
    @SerializedName("run_time")
    @Expose
    private Double runTime;
    @SerializedName("release_year")
    @Expose
    private Integer releaseYear;
    @SerializedName("marker")
    @Expose
    private Integer marker;
    @SerializedName("marker_per")
    @Expose
    private Integer markerPer;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("sub_media_list")
    @Expose
    private List<SubMediaList> subMediaList = null;
    @SerializedName("season_title")
    @Expose
    private Object seasonTitle;
    @SerializedName("episode_title")
    @Expose
    private Object episodeTitle;
    @SerializedName("media_rental_plan")
    @Expose
    private Object mediaRentalPlan;
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
    private final static long serialVersionUID = 450039633094567155L;

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

    public List<Object> getGenres() {
        return genres;
    }

    public void setGenres(List<Object> genres) {
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

    public List<Object> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Object> certificates) {
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

    public Integer getMarker() {
        return marker;
    }

    public void setMarker(Integer marker) {
        this.marker = marker;
    }

    public Integer getMarkerPer() {
        return markerPer;
    }

    public void setMarkerPer(Integer markerPer) {
        this.markerPer = markerPer;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<SubMediaList> getSubMediaList() {
        return subMediaList;
    }

    public void setSubMediaList(List<SubMediaList> subMediaList) {
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

    public Object getMediaRentalPlan() {
        return mediaRentalPlan;
    }

    public void setMediaRentalPlan(Object mediaRentalPlan) {
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
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("shortDescription", shortDescription).append("languages", languages).append("genres", genres).append("slug", slug).append("banner", banner).append("thumbnail", thumbnail).append("certificates", certificates).append("runTime", runTime).append("releaseYear", releaseYear).append("marker", marker).append("markerPer", markerPer).append("pid", pid).append("subMediaList", subMediaList).append("seasonTitle", seasonTitle).append("episodeTitle", episodeTitle).append("mediaRentalPlan", mediaRentalPlan).append("videoSectionType", videoSectionType).append("continueWatching", continueWatching).append("episodeList", episodeList).append("bigBanner", bigBanner).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(episodeTitle).append(thumbnail).append(languages).append(videoSectionType).append(banner).append(mediaRentalPlan).append(pid).append(shortDescription).append(title).append(seasonTitle).append(subMediaList).append(certificates).append(markerPer).append(genres).append(marker).append(bigBanner).append(continueWatching).append(episodeList).append(runTime).append(releaseYear).append(slug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ContinueWatching) == false) {
            return false;
        }
        ContinueWatching rhs = ((ContinueWatching) other);
        return new EqualsBuilder().append(episodeTitle, rhs.episodeTitle).append(thumbnail, rhs.thumbnail).append(languages, rhs.languages).append(videoSectionType, rhs.videoSectionType).append(banner, rhs.banner).append(mediaRentalPlan, rhs.mediaRentalPlan).append(pid, rhs.pid).append(shortDescription, rhs.shortDescription).append(title, rhs.title).append(seasonTitle, rhs.seasonTitle).append(subMediaList, rhs.subMediaList).append(certificates, rhs.certificates).append(markerPer, rhs.markerPer).append(genres, rhs.genres).append(marker, rhs.marker).append(bigBanner, rhs.bigBanner).append(continueWatching, rhs.continueWatching).append(episodeList, rhs.episodeList).append(runTime, rhs.runTime).append(releaseYear, rhs.releaseYear).append(slug, rhs.slug).isEquals();
    }

}
