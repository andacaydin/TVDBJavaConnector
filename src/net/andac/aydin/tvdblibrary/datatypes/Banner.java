package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;

/**
 * Banner<br>
 * <br>
 * The banners.xml file holds a list of all of the series and season banners
 * associated with the series. <br>
 * This class represents this file.<br>
 * 
 * @see http://www.thetvdb.com/wiki/index.php/API:banners.xml
 * @author Andac Aydin
 * 
 */

@SuppressWarnings("serial")
public class Banner implements Serializable {

	private Long id;
	private String bannerPath;
	private BannerType bannerType;
	private BannerType2 bannerType2;
	private String language;
	private String season;
	private Long tvshowId;
	private Boolean defaultBanner;
	private Integer usageCount;

	public Long getTvshowId() {
		return tvshowId;
	}

	public void setTvshowId(Long tvshowId) {
		this.tvshowId = tvshowId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBannerPath() {
		return bannerPath;
	}

	public void setBannerPath(String bannerPath) {
		this.bannerPath = bannerPath;
	}

	public BannerType getBannerType() {
		return bannerType;
	}

	public void setBannerType(BannerType bannerType) {
		this.bannerType = bannerType;
	}

	public BannerType2 getBannerType2() {
		return bannerType2;
	}

	public void setBannerType2(BannerType2 bannerType2) {
		this.bannerType2 = bannerType2;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return tvshowId + "/" + language + "/" + this.bannerPath;
	}

	public Boolean getDefaultBanner() {
		return defaultBanner;
	}

	public void setDefaultBanner(Boolean defaultBanner) {
		this.defaultBanner = defaultBanner;
	}

	public Integer getUsageCount() {
		return usageCount;
	}

	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}
}
