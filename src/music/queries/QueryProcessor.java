package music.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

import music.daos.event.EventDAO;
import music.daos.genre.GenreDAO;
import music.daos.label.LabelDAO;
import music.daos.urls.URLsDAO;

public class QueryProcessor {

	public List<LabelDAO> getLableQueryOutput(String queryString) {
		FileManager.get().addLocatorClassLoader(QueryProcessor.class.getClassLoader());
		Model model = FileManager.get().loadModel("FinalRdf.rdf");

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			List<LabelDAO> labelOutput = new ArrayList<LabelDAO>();

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				LabelDAO labelDao = new LabelDAO();
				Literal name = soln.getLiteral("aname");
				Literal artistName = soln.getLiteral("art_name");
				Literal releaseDate = soln.getLiteral("rd");
				Literal pc = soln.getLiteral("pc");
				labelDao.setAlbumName(name.toString());
				labelDao.setPlayCount(pc.toString());
				labelDao.setReleaseDate(releaseDate.toString());
				labelDao.setArtistName(artistName.toString());
				labelOutput.add(labelDao);
			}
			return labelOutput;
		} finally {
			qexec.close();
		}
	}

	public List<EventDAO> getEeventQueryOutput(String queryString) {
		FileManager.get().addLocatorClassLoader(QueryProcessor.class.getClassLoader());
		Model model = FileManager.get().loadModel("FinalRdf.rdf");

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			List<EventDAO> eventOutput = new ArrayList<EventDAO>();

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				EventDAO eventDao = new EventDAO();
				Literal art_name = soln.getLiteral("art_name");
				Literal eventName = soln.getLiteral("ename");
				Literal date = soln.getLiteral("date");
				Literal arena = soln.getLiteral("arena");
				Literal city = soln.getLiteral("lcity");
				Literal state = soln.getLiteral("lstate");
				Literal country = soln.getLiteral("lcountry");
				eventDao.setEventName(eventName.toString());
				eventDao.setEventDate(date.toString());
				eventDao.setPerformedBy(art_name.toString());
				eventDao.setArena(arena.toString());
				eventDao.setLocation(city.toString() + ", " + state.toString() + ", " + country.toString());
				eventOutput.add(eventDao);
			}
			return eventOutput;
		} finally {
			qexec.close();
		}
	}

	public List<URLsDAO> getUrlQueryOutput(String queryString) {
		FileManager.get().addLocatorClassLoader(QueryProcessor.class.getClassLoader());
		Model model = FileManager.get().loadModel("FinalRdf.rdf");

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			List<URLsDAO> urlOutput = new ArrayList<URLsDAO>();
			Map<String, URLsDAO> artistMap = new HashMap<>();

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal art_name = soln.getLiteral("art_name");
				URLsDAO urlDao;
				if (!artistMap.isEmpty() && artistMap.containsKey(art_name.toString())) {
					urlDao = artistMap.get(art_name.toString());
					urlDao.getUrls().add(soln.getLiteral("urls").toString());
				} else {
					urlDao = new URLsDAO();
					List<String> urls = new ArrayList<>();
					urls.add(soln.getLiteral("urls").toString());
					urlDao.setUrls(urls);
					urlDao.setArtistName(art_name.toString());
					urlDao.setGender(soln.getLiteral("gender").toString());
					urlDao.setImageUrl(soln.getLiteral("image").toString());
					artistMap.put(art_name.toString(), urlDao);
					urlOutput.add(urlDao);
				}
			}
			return urlOutput;
		} finally {
			qexec.close();
		}
	}

	public List<GenreDAO> getGenreQueryOutput(String queryString) {
		// TODO Auto-generated method stub
		FileManager.get().addLocatorClassLoader(QueryProcessor.class.getClassLoader());
		Model model = FileManager.get().loadModel("FinalRdf.rdf");

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			List<GenreDAO> genreOutput = new ArrayList<GenreDAO>();

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				GenreDAO genreDao = new GenreDAO();
				genreDao.setGenreName(soln.getLiteral("genre").toString());
				genreDao.setPlayCount(String.valueOf(soln.getLiteral("cnt").getInt()));
				genreOutput.add(genreDao);
			}
			return genreOutput;
		} finally {
			qexec.close();
		}
	}
}
