package net.andac.aydin.tvdblibrary.datatypes;

public interface BannerInterface {

	public abstract Long getTvshowId();

	public abstract void setTvshowId(Long tvshowId);

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract String getBannerPath();

	public abstract void setBannerPath(String bannerPath);

	public abstract BannerType getBannerType();

	public abstract void setBannerType(BannerType bannerType);

	public abstract BannerType2 getBannerType2();

	public abstract void setBannerType2(BannerType2 bannerType2);

	public abstract String getLanguage();

	public abstract void setLanguage(String language);

	public abstract String getSeason();

	public abstract void setSeason(String season);

}