package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;
import java.util.Date;

import net.andac.aydin.tvdblibrary.gson.GsonCreator;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class Episode implements Serializable, EpisodeInterface {

	protected Long episodeId;
	protected Long episodeNumber;
	protected String overview;
	protected String episodeName;
	protected Date firstAired;
	protected Long seasonNumber;
	protected Long lastUpdated;
	protected Long tvshowId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getTvshowId()
	 */
	@Override
	public Long getTvshowId() {
		return tvshowId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setTvshowId(java
	 * .lang.Long)
	 */
	@Override
	public void setTvshowId(Long tvshowId) {
		this.tvshowId = tvshowId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#toString()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getEpisodeName()
	 */
	@Override
	public String getEpisodeName() {
		return episodeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setEpisodeName
	 * (java.lang.String)
	 */
	@Override
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getEpisodeId()
	 */
	@Override
	public Long getEpisodeId() {
		return episodeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setEpisodeId(java
	 * .lang.Long)
	 */
	@Override
	public void setEpisodeId(Long episodeId) {
		this.episodeId = episodeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getEpisodeNumber()
	 */
	@Override
	public Long getEpisodeNumber() {
		return episodeNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setEpisodeNumber
	 * (java.lang.Long)
	 */
	@Override
	public void setEpisodeNumber(Long episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getOverview()
	 */
	@Override
	public String getOverview() {
		return overview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setOverview(java
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
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getFirstAired()
	 */
	@Override
	public Date getFirstAired() {
		return firstAired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setFirstAired(
	 * java.util.Date)
	 */
	@Override
	public void setFirstAired(Date firstAired) {
		this.firstAired = firstAired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getSeasonNumber()
	 */
	@Override
	public Long getSeasonNumber() {
		return seasonNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setSeasonNumber
	 * (java.lang.Long)
	 */
	@Override
	public void setSeasonNumber(Long seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#setLastUpdated
	 * (java.lang.Long)
	 */
	@Override
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#getLastUpdated()
	 */
	@Override
	public Long getLastUpdated() {
		return lastUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.andac.aydin.tvdblibrary.datatypes.EpisodeInterface#writeGSON()
	 */
	@Override
	public String writeGSON() {
		Gson gson = GsonCreator.createGson();
		return gson.toJson(this);
	}

}
