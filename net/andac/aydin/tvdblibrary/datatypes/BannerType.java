package net.andac.aydin.tvdblibrary.datatypes;

/**
 * BannerType<br>
 * <br>
 * This can be <b>series</b> or <b>season</b>. <br>
 * Denotes if the banner applies to the series as a whole or to a specific
 * season. <br>
 * new: Fanart!<br>
 * 
 * @see http://www.thetvdb.com/wiki/index.php/API:banners.xml
 * @author Andac Aydin
 * 
 */
public enum BannerType {
	SERIES, SEASON, FANART, POSTER;
}
