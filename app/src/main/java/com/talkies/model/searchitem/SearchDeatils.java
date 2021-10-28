
package com.talkies.model.searchitem;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.talkies.model.searchitem.Cast;
import com.talkies.model.searchitem.Certificate;
import com.talkies.model.searchitem.Director;
import com.talkies.model.searchitem.ExtraFlags;
import com.talkies.model.searchitem.Genre;
import com.talkies.model.searchitem.Language;
import com.talkies.model.searchitem.MediaRentalPlan;
import com.talkies.model.searchitem.OtherCrewMember;
import com.talkies.model.searchitem.ProductionHouse;
import com.talkies.model.searchitem.RentalInfo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchDeatils implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("trailer_link")
    @Expose
    private String trailerLink;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("production_house")
    @Expose
    private ProductionHouse productionHouse;
    @SerializedName("producers")
    @Expose
    private List<Object> producers = null;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("writers")
    @Expose
    private List<Writer> writers = null;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
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
    @SerializedName("rental_info")
    @Expose
    private RentalInfo rentalInfo;
    @SerializedName("is_available_for_play")
    @Expose
    private Boolean isAvailableForPlay;
    @SerializedName("media_url")
    @Expose
    private String mediaUrl;
    @SerializedName("is_paid")
    @Expose
    private Boolean isPaid;
    @SerializedName("certificates")
    @Expose
    private List<Certificate> certificates = null;
    @SerializedName("run_time")
    @Expose
    private Double runTime;
    @SerializedName("release_year")
    @Expose
    private Integer releaseYear;
    @SerializedName("other_crew_members")
    @Expose
    private List<OtherCrewMember> otherCrewMembers = null;
    @SerializedName("deep_link_url")
    @Expose
    private Object deepLinkUrl;
    @SerializedName("marker")
    @Expose
    private Integer marker;
    @SerializedName("marker_per")
    @Expose
    private Integer markerPer;
    @SerializedName("tokens")
    @Expose
    private Object tokens;
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
    @SerializedName("trailer")
    @Expose
    private Object trailer;
    @SerializedName("views_count")
    @Expose
    private Integer viewsCount;
    @SerializedName("continue_watching")
    @Expose
    private Object continueWatching;
    @SerializedName("episode_list")
    @Expose
    private List<Object> episodeList = null;
    @SerializedName("chromecast_media_url")
    @Expose
    private Object chromecastMediaUrl;
    @SerializedName("extra_flags")
    @Expose
    private ExtraFlags extraFlags;
    private final static long serialVersionUID = 2079121177411705051L;

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public ProductionHouse getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(ProductionHouse productionHouse) {
        this.productionHouse = productionHouse;
    }

    public List<Object> getProducers() {
        return producers;
    }

    public void setProducers(List<Object> producers) {
        this.producers = producers;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
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

    public RentalInfo getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(RentalInfo rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public Boolean getIsAvailableForPlay() {
        return isAvailableForPlay;
    }

    public void setIsAvailableForPlay(Boolean isAvailableForPlay) {
        this.isAvailableForPlay = isAvailableForPlay;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
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

    public List<OtherCrewMember> getOtherCrewMembers() {
        return otherCrewMembers;
    }

    public void setOtherCrewMembers(List<OtherCrewMember> otherCrewMembers) {
        this.otherCrewMembers = otherCrewMembers;
    }

    public Object getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(Object deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
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

    public Object getTokens() {
        return tokens;
    }

    public void setTokens(Object tokens) {
        this.tokens = tokens;
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

    public Object getTrailer() {
        return trailer;
    }

    public void setTrailer(Object trailer) {
        this.trailer = trailer;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
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

    public Object getChromecastMediaUrl() {
        return chromecastMediaUrl;
    }

    public void setChromecastMediaUrl(Object chromecastMediaUrl) {
        this.chromecastMediaUrl = chromecastMediaUrl;
    }

    public ExtraFlags getExtraFlags() {
        return extraFlags;
    }

    public void setExtraFlags(ExtraFlags extraFlags) {
        this.extraFlags = extraFlags;
    }

    @Override
    public String toString() {
        return "Talkies";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(directors).append(videoSectionType).append(pid).append(title).append(trailerLink).append(isAvailableForPlay).append(deepLinkUrl).append(seasonTitle).append(trailer).append(cast).append(rentalInfo).append(productionHouse).append(markerPer).append(genres).append(tokens).append(runTime).append(otherCrewMembers).append(releaseYear).append(slug).append(episodeTitle).append(thumbnail).append(languages).append(mediaUrl).append(viewsCount).append(banner).append(mediaRentalPlan).append(shortDescription).append(synopsis).append(writers).append(chromecastMediaUrl).append(producers).append(isPaid).append(subMediaList).append(certificates).append(marker).append(continueWatching).append(extraFlags).append(episodeList).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchDeatils) == false) {
            return false;
        }
        SearchDeatils rhs = ((SearchDeatils) other);
        return new EqualsBuilder().append(directors, rhs.directors).append(videoSectionType, rhs.videoSectionType).append(pid, rhs.pid).append(title, rhs.title).append(trailerLink, rhs.trailerLink).append(isAvailableForPlay, rhs.isAvailableForPlay).append(deepLinkUrl, rhs.deepLinkUrl).append(seasonTitle, rhs.seasonTitle).append(trailer, rhs.trailer).append(cast, rhs.cast).append(rentalInfo, rhs.rentalInfo).append(productionHouse, rhs.productionHouse).append(markerPer, rhs.markerPer).append(genres, rhs.genres).append(tokens, rhs.tokens).append(runTime, rhs.runTime).append(otherCrewMembers, rhs.otherCrewMembers).append(releaseYear, rhs.releaseYear).append(slug, rhs.slug).append(episodeTitle, rhs.episodeTitle).append(thumbnail, rhs.thumbnail).append(languages, rhs.languages).append(mediaUrl, rhs.mediaUrl).append(viewsCount, rhs.viewsCount).append(banner, rhs.banner).append(mediaRentalPlan, rhs.mediaRentalPlan).append(shortDescription, rhs.shortDescription).append(synopsis, rhs.synopsis).append(writers, rhs.writers).append(chromecastMediaUrl, rhs.chromecastMediaUrl).append(producers, rhs.producers).append(isPaid, rhs.isPaid).append(subMediaList, rhs.subMediaList).append(certificates, rhs.certificates).append(marker, rhs.marker).append(continueWatching, rhs.continueWatching).append(extraFlags, rhs.extraFlags).append(episodeList, rhs.episodeList).isEquals();
    }

}
