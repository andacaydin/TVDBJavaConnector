package net.andac.aydin.tvdblibrary.gson;

import net.andac.aydin.tvdblibrary.datatypes.TvshowInterface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCreator {

	public static Gson createGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(TvshowInterface.class,
				new TvshowInterfaceAdapter());
		return builder.create();
	}
}
