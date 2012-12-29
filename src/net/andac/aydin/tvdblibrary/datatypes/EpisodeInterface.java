package net.andac.aydin.tvdblibrary.datatypes;

import java.util.Date;

public interface EpisodeInterface {

	public abstract Long getTvshowId();

	public abstract void setTvshowId(Long tvshowId);

	public abstract String toString();

	public abstract String getEpisodeName();

	public abstract void setEpisodeName(String episodeName);

	public abstract Long getEpisodeId();

	public abstract void setEpisodeId(Long episodeId);

	public abstract Long getEpisodeNumber();

	public abstract void setEpisodeNumber(Long episodeNumber);

	public abstract String getOverview();

	public abstract void setOverview(String overview);

	public abstract Date getFirstAired();

	public abstract void setFirstAired(Date firstAired);

	public abstract Long getSeasonNumber();

	public abstract void setSeasonNumber(Long seasonNumber);

	public abstract void setLastUpdated(Long lastUpdated);

	public abstract Long getLastUpdated();

	public abstract String writeGSON();

}