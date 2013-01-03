package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import net.andac.aydin.tvdblibrary.gson.GsonCreator;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class Tvshow implements Serializable, TvshowInterface {
	private Long seriesid;
	private String seriesName;
	private String overview;
	private Date firstAired;
	private ArrayList<Episode> episodes;
	private Long lastUpdated;
	private Language language;
	private ArrayList<Banner> banners;
	private ArrayList<Actor> actors;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getBanners()
	 */
	@Override
	public ArrayList<Banner> getBanners() {
		if (this.banners == null) {
			banners = new ArrayList<Banner>();
		}
		return banners;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getActors()
	 */
	@Override
	public ArrayList<Actor> getActors() {
		if (this.actors == null) {
			actors = new ArrayList<Actor>();
		}
		return actors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#toString()
	 */
	@Override
	public String toString() {
		return seriesName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getSeriesid()
	 */
	@Override
	public Long getSeriesid() {
		return seriesid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setSeriesid(java
	 * .lang.Long)
	 */
	@Override
	public void setSeriesid(Long seriesid) {
		this.seriesid = seriesid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getSeriesName()
	 */
	@Override
	public String getSeriesName() {
		return seriesName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setSeriesName(java
	 * .lang.String)
	 */
	@Override
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getOverview()
	 */
	@Override
	public String getOverview() {
		return overview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setOverview(java
	 * .lang.String)
	 */
	@Override
	public void setOverview(String overview) {
		this.overview = overview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getFirstAired()
	 */
	@Override
	public Date getFirstAired() {
		return firstAired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setFirstAired(java
	 * .util.Date)
	 */
	@Override
	public void setFirstAired(Date firstAired) {
		this.firstAired = firstAired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getEpisodes()
	 */
	@Override
	public ArrayList<Episode> getEpisodes() {
		if (this.episodes == null) {
			episodes = new ArrayList<Episode>();
		}
		return episodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getLastUpdated()
	 */
	@Override
	public Long getLastUpdated() {
		return lastUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setLastUpdated(
	 * java.lang.Long)
	 */
	@Override
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#getLanguage()
	 */
	@Override
	public Language getLanguage() {
		return language;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#setLanguage(net
	 * .andac.aydin.tvdblibrary.datatypes.Language)
	 */
	@Override
	public void setLanguage(Language language) {
		this.language = language;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.TvshowInterface#writeGSON()
	 */
	@Override
	public String writeGSON() {
		Gson gson = GsonCreator.createGson();
		return gson.toJson(this);
	}

}
