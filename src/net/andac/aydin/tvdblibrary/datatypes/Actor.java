package net.andac.aydin.tvdblibrary.datatypes;

import java.io.Serializable;

/**
 * Actor<br>
 * <br>
 * The actors.xml file holds a list of all of the series actors.<br>
 * This class represents this file.<br>
 * 
 * @see http://www.thetvdb.com/wiki/index.php/API:actors.xml
 * @author Andac Aydin
 * 
 */
@SuppressWarnings("serial")
public class Actor implements Serializable {

	/**
	 * Can be appended to <mirrorpath>/banners/ to determine the actual location
	 * of the artwork.
	 */
	private String image;

	/**
	 * The actors real name.
	 */
	private String name;

	/**
	 * The name of the actors character in the series.
	 */
	private String role;

	/**
	 * An integer from 0-3. 1 being the most important actor on the show and 3
	 * being the third most important actor. 0 means they have no special sort
	 * order. Duplicates of 1-3 aren't suppose to be allowed but currently are
	 * so the field isn't perfect but can still be used for basic sorting.
	 */
	private Integer sortorder;

	private Long tvshowId;

	public Long getTvshowId() {
		return tvshowId;
	}

	public void setTvshowId(Long tvshowId) {
		this.tvshowId = tvshowId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getSortorder() {
		return sortorder;
	}

	public void setSortorder(Integer sortorder) {
		this.sortorder = sortorder;
	}

}
