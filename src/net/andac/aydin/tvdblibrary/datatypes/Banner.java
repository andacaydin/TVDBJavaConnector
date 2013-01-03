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
public class Banner implements Serializable, BannerInterface {

	private Long id;
	private String bannerPath;
	private BannerType bannerType;
	private BannerType2 bannerType2;
	private String language;
	private String season;
	private Long tvshowId;

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getTvshowId()
	 */
	@Override
	public Long getTvshowId() {
		return tvshowId;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setTvshowId(java.lang.Long)
	 */
	@Override
	public void setTvshowId(Long tvshowId) {
		this.tvshowId = tvshowId;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getBannerPath()
	 */
	@Override
	public String getBannerPath() {
		return bannerPath;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setBannerPath(java.lang.String)
	 */
	@Override
	public void setBannerPath(String bannerPath) {
		this.bannerPath = bannerPath;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getBannerType()
	 */
	@Override
	public BannerType getBannerType() {
		return bannerType;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setBannerType(net.andac.aydin.tvdblibrary.datatypes.BannerType)
	 */
	@Override
	public void setBannerType(BannerType bannerType) {
		this.bannerType = bannerType;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getBannerType2()
	 */
	@Override
	public BannerType2 getBannerType2() {
		return bannerType2;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setBannerType2(net.andac.aydin.tvdblibrary.datatypes.BannerType2)
	 */
	@Override
	public void setBannerType2(BannerType2 bannerType2) {
		this.bannerType2 = bannerType2;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getLanguage()
	 */
	@Override
	public String getLanguage() {
		return language;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setLanguage(java.lang.String)
	 */
	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#getSeason()
	 */
	@Override
	public String getSeason() {
		return season;
	}

	/* (non-Javadoc)
	 * @see net.andac.aydin.tvdblibrary.datatypes.BannerInterface#setSeason(java.lang.String)
	 */
	@Override
	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {

		return tvshowId + "/" + language + "/" + this.bannerPath;
	}
}
