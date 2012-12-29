package net.andac.aydin.tvdblibrary.connector;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.andac.aydin.tvdblibrary.connector.exceptions.TVDBOutboundConnectionException;
import net.andac.aydin.tvdblibrary.datatypes.Actor;
import net.andac.aydin.tvdblibrary.datatypes.Banner;
import net.andac.aydin.tvdblibrary.datatypes.Episode;
import net.andac.aydin.tvdblibrary.datatypes.Language;
import net.andac.aydin.tvdblibrary.datatypes.TVDBFile;
import net.andac.aydin.tvdblibrary.datatypes.Tvshow;
import net.andac.aydin.tvdblibrary.datatypes.UpdateIntervalType;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TVDBConnector {
	private static final Logger log = Logger.getLogger(TVDBConnector.class
			.getName());

	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

	static private final String TVDATABASE_URL = "http://www.thetvdb.com";

	// TODO Enter your API Key here
	static private final String API_KEY = " ... ";
	static private final String THETVDB_UPDATED_TVSHOWS_DAILY_URL = "http://www.thetvdb.com/api/"
			+ API_KEY + "/updates/updates_day.zip";
	static private final String THETVDB_UPDATED_TVSHOWS_WEEKLY_URL = "http://www.thetvdb.com/api/"
			+ API_KEY + "/updates/updates_week.zip";
	static private final String THETVDB_UPDATED_TVSHOWS_MONTHLY_URL = "http://www.thetvdb.com/api/"
			+ API_KEY + "/updates/updates_month.zip";
	static private final String THETVDB_UPDATED_TVSHOWS_ALL_URL = "http://www.thetvdb.com/api/"
			+ API_KEY + "/updates/updates_all.zip";

	private static final String TAG = "TVDBConnector";

	private static TVDBConnector instance;

	public static TVDBConnector getInstance() {
		if (instance == null) {

			instance = new TVDBConnector();
		}
		return instance;
	}

	public TVDBConnector() {
	}

	/**
	 * Returns Arraylist of TvShowIds which have been updated in last
	 * UpdateInterval<br>
	 * 
	 * @param UpdateIntervalType
	 * @return List of Longs
	 * @throws IOException
	 */
	public List<Long> getUpdatedTvShows(UpdateIntervalType intervalType)
			throws TVDBOutboundConnectionException {
		byte[] zipFile = null;
		List<Long> response = new ArrayList<Long>();
		try {
			switch (intervalType) {
			case DAILY:
				zipFile = loadFileFromUrl(THETVDB_UPDATED_TVSHOWS_DAILY_URL);
				break;
			case WEEKLY:
				zipFile = loadFileFromUrl(THETVDB_UPDATED_TVSHOWS_WEEKLY_URL);
				break;
			case MONTHLY:
				zipFile = loadFileFromUrl(THETVDB_UPDATED_TVSHOWS_MONTHLY_URL);
				break;
			case ALL:
				zipFile = loadFileFromUrl(THETVDB_UPDATED_TVSHOWS_ALL_URL);
				break;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "loadFileFromUrl() Error", e);
			throw new TVDBOutboundConnectionException(e);
		}
		ArrayList<TVDBFile> unzippedFiles = unzipFile(zipFile);

		// only one zipfile expected, according to thetvdb contract

		try {
			String str = new String(unzippedFiles.get(0).getBytearray());
			response.addAll(extractUpdatedTvShowIdsFromXML(str));
		} catch (IndexOutOfBoundsException e) {
			log.log(Level.SEVERE, "ParseError:ZipFile Empty!", e);
			throw new TVDBOutboundConnectionException(e);
		} catch (ParseException e) {
			log.log(Level.SEVERE, "ParseError:getUpdatedTvShows", e);
			throw new TVDBOutboundConnectionException(e);
		}
		return response;
	}

	private Tvshow extractTvShowFromXML(String xmldata) throws ParseException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(xmldata));
		Document document;
		NodeList tvshowNodes = null;
		NodeList episodesNodes = null;
		try {
			document = factory.newDocumentBuilder().parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();
			// Extract the TV SHOW
			XPathExpression showExpression = xpath.compile("Data/Series");
			tvshowNodes = (NodeList) showExpression.evaluate(document,
					XPathConstants.NODESET);
			// Extract EPISODES
			XPathExpression episodesExpression = xpath.compile("Data/Episode");
			episodesNodes = (NodeList) episodesExpression.evaluate(document,
					XPathConstants.NODESET);

		} catch (SAXException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		}
		// Fill up series-information
		Node seriesNode = tvshowNodes.item(0);
		Tvshow tvshow = TVDBMapper.getInstance().mapTvshow(seriesNode);

		// Fill up episode information
		for (int i = 0; i < episodesNodes.getLength(); i++) {
			Node episodeNode = episodesNodes.item(i);
			Episode episode = TVDBMapper.getInstance().mapEpisode(episodeNode);
			episode.setTvshowId(tvshow.getSeriesid());
			tvshow.getEpisodes().add(episode);
		}
		return tvshow;
	}

	private ArrayList<Banner> extractBannersFromXML(String xmldata,
			Long tvshowId) throws ParseException {
		ArrayList<Banner> bannerList = new ArrayList<Banner>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(xmldata));
		Document document;
		NodeList bannerNodes = null;
		try {
			document = factory.newDocumentBuilder().parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();
			// Extract the Banners
			XPathExpression showExpression = xpath.compile("Banners/Banner");

			bannerNodes = (NodeList) showExpression.evaluate(document,
					XPathConstants.NODESET);
		} catch (SAXException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		}
		// Fill up series-information
		for (int j = 0; j < bannerNodes.getLength(); j++) {
			Node seriesNode = bannerNodes.item(j);
			Banner banner = TVDBMapper.getInstance().mapBanner(seriesNode);
			banner.setTvshowId(tvshowId);
			bannerList.add(banner);
		}
		return bannerList;
	}

	private ArrayList<Actor> extractActorsFromXML(String xmldata, Long tvshowId)
			throws ParseException {

		ArrayList<Actor> actorList = new ArrayList<Actor>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(xmldata));
		Document document;
		NodeList actorsNodes = null;
		try {
			document = factory.newDocumentBuilder().parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();
			// Extract the Banners
			XPathExpression showExpression = xpath.compile("Actors/Actor");
			actorsNodes = (NodeList) showExpression.evaluate(document,
					XPathConstants.NODESET);
		} catch (SAXException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "ParseError:extractTvShowFromXML", e);
		}
		// Fill up series-information
		for (int j = 0; j < actorsNodes.getLength(); j++) {
			Node seriesNode = actorsNodes.item(j);
			Actor actor = TVDBMapper.getInstance().mapActor(seriesNode);
			actor.setTvshowId(tvshowId);
			actorList.add(actor);
		}
		return actorList;
	}

	private List<Long> extractUpdatedTvShowIdsFromXML(String xmldata)
			throws ParseException {
		List<Long> responseList = new ArrayList<Long>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(xmldata));
		Document document;
		NodeList tvshowNodes = null;

		try {
			document = factory.newDocumentBuilder().parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression showExpression = xpath.compile("Data/Series");
			tvshowNodes = (NodeList) showExpression.evaluate(document,
					XPathConstants.NODESET);

		} catch (SAXException e) {
			log.log(Level.SEVERE, "ParseError:extractUpdatedTvShowIdsFromXML",
					e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "ParseError:extractUpdatedTvShowIdsFromXML",
					e);
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "ParseError:extractUpdatedTvShowIdsFromXML",
					e);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "ParseError:extractUpdatedTvShowIdsFromXML",
					e);
		}

		for (int i = 0; i < tvshowNodes.getLength(); i++) {
			Node seriesNode = tvshowNodes.item(i);
			responseList.add(TVDBMapper.getInstance().mapTvshowTimestamp(
					seriesNode));
		}
		return responseList;
	}

	private List<Tvshow> extractSearchResultsFromXML(String xmldata)
			throws ParseException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(xmldata));
		Document document;
		List<Tvshow> tvshowList = new ArrayList<Tvshow>();
		NodeList tvshowNodes = null;

		try {
			document = factory.newDocumentBuilder().parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();

			// Extract the TV SHOW
			XPathExpression showExpression = xpath.compile("Data/Series");

			tvshowNodes = (NodeList) showExpression.evaluate(document,
					XPathConstants.NODESET);

		} catch (SAXException e) {
			log.log(Level.SEVERE, "ParseError:extractSearchResultsFromXML", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "ParseError:extractSearchResultsFromXML", e);
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, "ParseError:extractSearchResultsFromXML", e);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "ParseError:extractSearchResultsFromXML", e);
		}

		if (tvshowNodes == null) {
			return tvshowList;
		}

		// Fill up series-information
		for (int j = 0; j < tvshowNodes.getLength(); j++) {
			Node seriesNode = tvshowNodes.item(j);
			Tvshow tvshow = TVDBMapper.getInstance().mapTvshow(seriesNode);
			tvshowList.add(tvshow);
		}

		return tvshowList;
	}

	/**
	 * Loads a Full TvShow-Entity from thetvdb with all its propertys.
	 * 
	 * @param seriesId
	 * @param languageType
	 * @return
	 * @throws IOException
	 */
	public Tvshow retrieveFullTvshowFromTVDB(Long seriesId,
			Language languageType) throws TVDBOutboundConnectionException {

		byte[] loadFileFromUrl;
		String uri = TVDATABASE_URL + "/api/" + API_KEY + "/series/" + seriesId
				+ "/all/" + languageType.getId() + ".zip";
		try {
			loadFileFromUrl = loadFileFromUrl(uri);
		} catch (IOException e) {
			throw new TVDBOutboundConnectionException(e);
		}
		ArrayList<TVDBFile> unzipFile = unzipFile(loadFileFromUrl);

		Tvshow tvShow = null;
		for (TVDBFile tvdbFile : unzipFile) {

			// TVSHOW.XML
			if (tvdbFile.getFilename().equals(languageType.getId() + ".xml")) {
				try {
					tvShow = extractTvShowFromXML(new String(
							tvdbFile.getBytearray(), UTF8_CHARSET));
				} catch (ParseException e) {
					log.log(Level.SEVERE,
							"ParseError:retrieveTvshowFromTVDB->TvShow", e);
				}
			}

			// BANNER.XML
			if (tvdbFile.getFilename().equals("banners.xml")) {
				try {
					ArrayList<Banner> banners = extractBannersFromXML(
							new String(tvdbFile.getBytearray(), UTF8_CHARSET),
							tvShow.getSeriesid());
					tvShow.getBanners().clear();
					tvShow.getBanners().addAll(banners);
				} catch (ParseException e) {
					log.log(Level.SEVERE,
							"ParseError:retrieveTvshowFromTVDB->Banners", e);
				}
			}
			// ACTORS.XML
			if (tvdbFile.getFilename().equals("actors.xml")) {
				try {
					ArrayList<Actor> actors = extractActorsFromXML(new String(
							tvdbFile.getBytearray(), UTF8_CHARSET),
							tvShow.getSeriesid());
					tvShow.getActors().addAll(actors);
				} catch (ParseException e) {
					log.log(Level.SEVERE,
							"ParseError:retrieveTvshowFromTVDB->Actors", e);
				}
			}
		}

		return tvShow;
	}

	/**
	 * Retrieves searchresult with a marginal and therefore performant dataset<br>
	 * see example:
	 * http://www.thetvdb.com/api/GetSeries.php?seriesname=lost&language=en <br>
	 * 
	 * @param searchstring
	 * @param languageType
	 * @return
	 * @throws BusinessLogicException
	 * @throws IOException
	 */
	public List<Tvshow> searchSeriesInTVDB(String searchstring,
			Language languageType) throws TVDBOutboundConnectionException {
		List<Tvshow> tvShowList = null;
		String uri = TVDATABASE_URL + "/api/GetSeries.php?seriesname="
				+ searchstring + "&language=" + languageType.getId();
		try {
			StringBuilder xmldata = loadDataFromUrl(uri);
			tvShowList = extractSearchResultsFromXML(xmldata.toString());
		} catch (ParseException e) {
			log.log(Level.SEVERE, "ParseError:searchSeriesInTVDB", e);
			throw new TVDBOutboundConnectionException(e);
		} catch (IOException e) {
			log.log(Level.WARNING,
					"SearchError:searchSeriesInTVDB. Load from URL terminated:"
							+ uri, e);
			throw new TVDBOutboundConnectionException(e);
		}
		return tvShowList;
	}

	/**
	 * loads data from any uri
	 * 
	 * @return
	 * @throws IOException
	 */
	private StringBuilder loadDataFromUrl(String uri) throws IOException {
		StringBuilder payload = new StringBuilder();
		try {
			URL tvdatabase = new URL(uri);
			URLConnection yc = tvdatabase.openConnection();
			yc.setReadTimeout(0); // no timeout
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(), UTF8_CHARSET));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				payload.append(inputLine);
			in.close();
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "LoadFromUrl: MalformedURLException", e);
			throw new IOException(e);
		}
		return payload;
	}

	/**
	 * loads a file from any uri
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private byte[] loadFileFromUrl(String uri) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		log.info("loading file from url:" + uri);
		try {
			URL url = new URL(uri);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(0); // no timeout
			urlConnection.setConnectTimeout(0);
			urlConnection.connect();

			InputStream inputstream = url.openStream();
			byte[] buffer = new byte[1000];

			int ze;
			while ((ze = inputstream.read(buffer)) > 0) {
				out.write(buffer, 0, ze);
			}
			out.flush();
			inputstream.close();

		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "LoadFileFromUrl: MalformedURLException", e);
			throw new IOException(e);
		}
		if (out.toString().contains("404 Not Found")) {
			log.log(Level.SEVERE, "uri not loadable:" + uri);
			throw new IOException("404 file not found! " + uri);
		}
		return out.toByteArray();
	}

	/**
	 * Unzips a single file and returns contents as byte array.
	 * 
	 * @return
	 */
	private ArrayList<TVDBFile> unzipFile(byte[] data) {

		ArrayList<TVDBFile> byteArray = new ArrayList<TVDBFile>();

		try {
			final InputStream input = new ByteArrayInputStream(data);

			final byte[] buffer = new byte[1024];
			final ZipInputStream zipInputStream = new ZipInputStream(input);
			int anzahl = 0;

			ZipEntry zipentry = zipInputStream.getNextEntry();
			while (zipentry != null) {
				TVDBFile file = new TVDBFile();
				ByteArrayOutputStream out = new ByteArrayOutputStream(
						data.length);
				while ((anzahl = zipInputStream.read(buffer)) != -1) {
					out.write(buffer, 0, anzahl);
				}
				file.setFilename(zipentry.getName());
				file.setBytearray(out.toByteArray());
				byteArray.add(file);
				out.close();
				zipInputStream.closeEntry();
				zipentry = zipInputStream.getNextEntry();
			}
			zipInputStream.close();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error occured while unzipping file!", e);
		}

		return byteArray;
	}

	/**
	 * Loads all banners for a tvshow and returns list of banners containing all
	 * the files.
	 * 
	 * @param tvShow
	 * @return
	 * @throws IOException
	 */
	public List<TVDBFile> getTvShowBanners(Tvshow tvShow)
			throws TVDBOutboundConnectionException {
		log.log(Level.INFO,
				TAG + " Gathering tvshow banners (" + tvShow.getSeriesid()
						+ ")");
		ArrayList<TVDBFile> allFiles = new ArrayList<TVDBFile>();

		// load all banners
		ArrayList<Banner> banners = tvShow.getBanners();
		for (Banner banner : banners) {
			TVDBFile file = new TVDBFile();
			try {
				byte[] loadFileFromUrl = loadFileFromUrl(TVDATABASE_URL
						+ "/banners/" + banner.getBannerPath());
				file.setBytearray(loadFileFromUrl);
				file.setFilename("banners/" + banner.getBannerPath());
			} catch (IOException e) {
				log.log(Level.SEVERE, "Banner " + banner.getBannerPath()
						+ " not loadable! Skipping this one...");
				continue;
			}
			// only add file, if file is not empty!
			if (file.getBytearray().length > 0) {
				allFiles.add(file);
			}
		}
		return allFiles;
	}

	/**
	 * Loads all banners, actor images and tvshow-data and returns a zip file
	 * containing all the files.
	 * 
	 * @param tvShow
	 * @return
	 * @throws IOException
	 */
	public TVDBFile getTvShowZipped(Tvshow tvShow)
			throws TVDBOutboundConnectionException {
		log.log(Level.INFO,
				TAG + " Generating zipped tvshow (" + tvShow.getSeriesid()
						+ ")");
		ArrayList<TVDBFile> allFiles = new ArrayList<TVDBFile>();

		// general tvshow info
		TVDBFile tvshowFile = new TVDBFile();
		tvshowFile.setFilename("tvshow.json");
		tvshowFile.setBytearray(tvShow.writeGSON().getBytes());
		allFiles.add(tvshowFile);

		// load all banners
		ArrayList<Banner> banners = tvShow.getBanners();
		for (Banner banner : banners) {
			TVDBFile file = new TVDBFile();
			String uri = TVDATABASE_URL + "/banners/" + banner.getBannerPath();
			try {
				file.setBytearray(loadFileFromUrl(uri));
			} catch (IOException e) {
				log.log(Level.SEVERE, "Error loading banner: " + uri
						+ " skipping...", e);
				continue;
			}
			file.setFilename("banners/" + banner.getBannerPath());
			// only add file, if file is not empty!
			if (file.getBytearray().length > 0) {
				allFiles.add(file);
			}
		}

		// load actor images
		ArrayList<Actor> actors = tvShow.getActors();
		for (Actor actor : actors) {
			if (actor.getImage() != null) {
				TVDBFile file = new TVDBFile();
				String uri = TVDATABASE_URL + "/banners/" + actor.getImage();
				try {
					file.setBytearray(loadFileFromUrl(uri));
				} catch (IOException e) {
					log.log(Level.SEVERE, "Error loading actor image: " + uri
							+ " skipping...", e);
					continue;
				}
				file.setFilename("banners/" + actor.getImage());
				// only add file, if file is not empty!
				if (file.getBytearray().length > 0) {
					allFiles.add(file);
				}
			} else {
				log.info("Actor " + actor.getName() + " has no picture");
			}
		}

		String filename = tvShow.getSeriesid() + ".zip";
		TVDBFile zippedResultFile;
		try {
			zippedResultFile = zipFileList(allFiles, filename);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error zipping file: " + filename);
			throw new TVDBOutboundConnectionException(e);
		}

		return zippedResultFile;
	}

	private TVDBFile zipFileList(ArrayList<TVDBFile> allFiles, String filename)
			throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(out);
		for (TVDBFile tvdbFile : allFiles) {
			ZipEntry entry = new ZipEntry(tvdbFile.getFilename());
			entry.setSize(tvdbFile.getBytearray().length);
			zip.putNextEntry(entry);
			zip.write(tvdbFile.getBytearray(), 0,
					tvdbFile.getBytearray().length);
			zip.closeEntry();
		}
		zip.flush();
		zip.close();
		out.close();
		TVDBFile resultFile = new TVDBFile();
		resultFile.setBytearray(out.toByteArray());
		resultFile.setFilename(filename);
		return resultFile;
	}
}
