package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;
import java.util.Date;

import net.andac.aydin.tvdblibrary.gson.GsonCreator;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class Episode implements Serializable {

	protected Long episodeId;
	protected Long episodeNumber;
	protected String overview;
	protected String episodeName;
	protected Date firstAired;
	protected Long seasonNumber;
	protected Long lastUpdated;
	protected Long tvshowId;
	private Boolean isWatched;

	public Long getTvshowId() {
		return tvshowId;
	}

	public void setTvshowId(Long tvshowId) {
		this.tvshowId = tvshowId;
	}

	@Override
	public String toString() {

		String string = new String();

		if (this.seasonNumber != null) {
			string += this.seasonNumber;
		}
		if (this.episodeNumber != null) {
			string += this.episodeNumber;
		}
		if (this.episodeName != null) {
			string += this.episodeName;
		}
		if (this.firstAired != null) {
			string += this.firstAired;
		}
		if (this.overview != null) {
			string += this.overview;
		}
		return string;

	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public Long getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Long episodeId) {
		this.episodeId = episodeId;
	}

	public Long getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Long episodeNumber) {
		this.episodeNumber = episodeNumber;
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

	public Long getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(Long seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;

	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public String writeGSON() {
		Gson gson = GsonCreator.createGson();
		return gson.toJson(this);
	}

	public Boolean getIsWatched() {
		if (isWatched == null) {
			isWatched = false;
		}
		return isWatched;
	}

	public void setIsWatched(Boolean isWatched) {
		this.isWatched = isWatched;
	}

}
