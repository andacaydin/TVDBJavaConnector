package net.andac.aydin.tvdblibrary.datatypes;

/**
 * BannerType2<br>
 * <br>
 * This can be <b>text</b>, <b>graphical</b>, or <b>blank</b> for series
 * banners. It can be <b>season</b> or <b>seasonwide</b> for season banners. <br>
 * Blank banners will leave the title and show logo off the banner. <br>
 * Text banners will show the series name as plain text in an Arial font. <br>
 * Graphical banners will show the series name in the show's official font or
 * will display the actual logo for the show. <br>
 * Season banners are the standard DVD cover format while wide season banners
 * will be the same dimensions as the series banners. <br>
 * <br>
 * new: ResolutionSizes: 1920X1080, 1280x720, 680x1000<br>
 * 
 * @see http://www.thetvdb.com/wiki/index.php/API:banners.xml
 * @author Andac Aydin
 * 
 */
public enum BannerType2 {
	TEXT, GRAPHICAL, BLANK, SEASON, SEASONWIDE, RES1920X1080, RES680x1000, RES1280x720;

	public static BannerType2 fromString(String text) {

		if (Character.isDigit(text.charAt(0))) {
			text = "RES" + text;
		}

		if (text != null) {
			for (BannerType2 b : BannerType2.values()) {
				if (text.equalsIgnoreCase(b.name())) {
					return b;
				}
			}
		}
		return null;
	}

}
