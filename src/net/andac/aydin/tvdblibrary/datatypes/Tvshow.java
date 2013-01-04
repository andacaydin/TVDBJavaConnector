package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


import com.google.gson.Gson;

@SuppressWarnings("serial")
public class Tvshow implements Serializable {
	private Long seriesid;
	private String seriesName;
	private String overview;
	private Date firstAired;
	private ArrayList<Episode> episodes;
	private Long lastUpdated;
	private Language language;
	private ArrayList<Banner> banners;
	private ArrayList<Actor> actors;

	public ArrayList<Banner> getBanners() {
		if (this.banners == null) {
			banners = new ArrayList<Banner>();
		}
		return banners;
	}

	public ArrayList<Actor> getActors() {
		if (this.actors == null) {
			actors = new ArrayList<Actor>();
		}
		return actors;
	}

	public String toString() {
		return seriesName;
	}

	public Long getSeriesid() {
		return seriesid;
	}

	public void setSeriesid(Long seriesid) {
		this.seriesid = seriesid;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Date getFirstAired() {
		return firstAired;
	}

	public void setFirstAired(Date firstAired) {
		this.firstAired = firstAired;
	}

	public ArrayList<Episode> getEpisodes() {
		if (this.episodes == null) {
			episodes = new ArrayList<Episode>();
		}
		return episodes;
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String writeGSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
