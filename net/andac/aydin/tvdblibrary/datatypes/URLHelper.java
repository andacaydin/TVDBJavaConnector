package net.andac.aydin.tvdblibrary.datatypes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class URLHelper {

	public static Map<String, String> getUrlParameters(String url)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		for (String param : url.split("&")) {
			String pair[] = param.split("=");
			String key = URLDecoder.decode(pair[0], "UTF-8");
			String value = "";
			if (pair.length > 1) {
				value = URLDecoder.decode(pair[1], "UTF-8");
				params.put(key, value);
			}
		}
		return params;
	}
}
