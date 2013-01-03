package net.andac.aydin.tvdblibrary.datatypes;

import java.util.ArrayList;
import java.util.Date;

public interface TvshowInterface {

	public abstract ArrayList<BannerInterface> getBanners();

	public abstract ArrayList<Actor> getActors();

	@Override
	public abstract String toString();

	public abstract Long getSeriesid();

	public abstract void setSeriesid(Long seriesid);

	public abstract String getSeriesName();

	public abstract void setSeriesName(String seriesName);

	public abstract String getOverview();

	public abstract void setOverview(String overview);

	public abstract Date getFirstAired();

	public abstract void setFirstAired(Date firstAired);

	public abstract ArrayList<EpisodeInterface> getEpisodes();

	public abstract Long getLastUpdated();

	public abstract void setLastUpdated(Long lastUpdated);

	public abstract Language getLanguage();

	public abstract void setLanguage(Language language);

	public abstract String writeGSON();

}