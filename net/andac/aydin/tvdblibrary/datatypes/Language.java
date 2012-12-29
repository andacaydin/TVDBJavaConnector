package net.andac.aydin.tvdblibrary.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * From Thetvdb: Here is a list of all 23 avaliable languages on our site.<br>
 * We do have plans to support more languages in the future but unfortunately
 * due to technical issues <br>
 * we will not be adding anymore languages until we have finished the new
 * version of the site. <br>
 * http://thetvdb.com/wiki/index.php/Multi_Language
 * 
 * @author Andac Aydin
 * 
 */
public enum Language {

	ENGLISH("en"), SVENSKA("sv"), NORSK("no"), DANSK("da"), SUOMEKSI("fi"), NEDERLANDS(
			"nl"), DEUTSCH("de"), ITALIANO("it"), FRANÇAIS("fr"), POLSKI("pl"), MAGYAR(
			"hu"), GREEK("el"), TURKISH("tr"), RUSSIAN("ru"), HEBREW("he"), JAPANESE(
			"ja"), PORTUGUESE("pt"), CHINESE("zh"), CZECH("cs"), SLOVENIAN("sl"), CROATIAN(
			"hr"), KOREAN("ko");

	private String id;

	private Language(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public static Language valueById(String id) {
		for (Language lang : Language.values()) {
			if (lang.id.equals(id)) {
				return lang;
			}
		}
		return null;
	}

	public static List<Language> getValues() {
		ArrayList<Language> languageList = new ArrayList<Language>();
		for (Language lang : Language.values()) {
			languageList.add(lang);
		}
		return languageList;
	}

	public static List<String> getIdValuesAsStringList() {
		ArrayList<String> languageList = new ArrayList<String>();
		for (Language lang : Language.values()) {
			languageList.add(lang.id);
		}
		return languageList;
	}

	public static List<String> getValuesAsStringList() {
		ArrayList<String> languageList = new ArrayList<String>();
		for (Language lang : Language.values()) {
			languageList.add(lang.name());
		}
		return languageList;

	}
}
