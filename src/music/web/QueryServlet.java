package music.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.daos.event.EventDAO;
import music.daos.genre.GenreDAO;
import music.daos.label.LabelDAO;
import music.daos.urls.URLsDAO;
import music.queries.QueryProcessor;

public class QueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String tabNumber = request.getParameter("tabNumber");
		String labelQueryPrefix = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX artist: <http://myMusic.org/music/artist#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX song: <http://myMusic.org/music/song#>" + "PREFIX label:<http://myMusic.org/music/label#>"
				+ "PREFIX album: <http://myMusic.org/music/album#>";

		String eventQueryPrefix = "PREFIX music: <http://mymusic/>"
				+ "PREFIX artist: <http://myMusic.org/music/artist#> "
				+ "PREFIX event: <http://myMusic.org/music/event#>"
				+ "PREFIX location: <http://myMusic.org/music/location#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

		String urlQueryPrefix = "PREFIX music: <http://mymusic/>" + "PREFIX artist: <http://myMusic.org/music/artist#> "
				+ "PREFIX award: <http://myMusic.org/music/award#> "
				+ "PREFIX album: <http://myMusic.org/music/album#> " + "PREFIX event: <http://myMusic.org/music/event#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX song: <http://myMusic.org/music/song#>";

		String genreQueryPrefix = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + "PREFIX music: <http://mymusic/>"
				+ "PREFIX artist: <http://myMusic.org/music/artist#> "
				+ "PREFIX album: <http://myMusic.org/music/album#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX song: <http://myMusic.org/music/song#>";

		if ("1".equalsIgnoreCase(tabNumber)) {
			request.setAttribute("class1", "tab-pane fade in active container active show");
			request.setAttribute("class2", "tab-pane fade container");
			request.setAttribute("class3", "tab-pane fade container");
			request.setAttribute("class4", "tab-pane fade container");
			request.setAttribute("class5", "tab-pane fade container");
			request.setAttribute("navLink1", "active");
			request.setAttribute("navLink2", "");
			request.setAttribute("navLink3", "");
			request.setAttribute("navLink4", "");
			request.setAttribute("navLink5", "");

			String instrument = request.getParameter("instrument");

			String mainQuery = "select ?ename ?art_name ?date ?arena ?lcity ?lstate ?lcountry where{"
					+ "?description event:arena ?arena. " + "?description event:date ?date."
					+ "?description event:name ?ename." + "?description event:location ?loc ."
					+ "?loc location:city ?lcity ." + "?loc location:state ?lstate ."
					+ "?loc location:country ?lcountry ." + "?description event:performed_by ?aname."
					+ "?aname artist:name ?art_name ." + "?aname artist:instruments ?instr ."
					+ "?instr ?prop ?s. FILTER (lcase(?s)=" + "\"" + instrument + "\""
					+ "&& (strstarts(str(?prop), str(rdf:_))))}";

			String queryString = eventQueryPrefix + mainQuery;
			List<EventDAO> eventOutput = new QueryProcessor().getEeventQueryOutput((queryString));
			request.setAttribute("eventOutput", eventOutput);
			request.setAttribute(instrument.replaceAll(" ", "_"), "selected");
			request.setAttribute("showTable1", "yes");
			setParameters(request, eventQueryPrefix, mainQuery);
		}

		if ("2".equalsIgnoreCase(tabNumber)) {
			request.setAttribute("class1", "tab-pane fade container");
			request.setAttribute("class2", "tab-pane fade in active container active show");
			request.setAttribute("class3", "tab-pane fade container");
			request.setAttribute("class4", "tab-pane fade container");
			request.setAttribute("class5", "tab-pane fade container");
			request.setAttribute("navLink1", "");
			request.setAttribute("navLink2", "active");
			request.setAttribute("navLink3", "");
			request.setAttribute("navLink4", "");
			request.setAttribute("navLink5", "");

			String genre = request.getParameter("genre");

			String mainQuery = "select ?art_name ?urls ?gender ?image where{" + "?description award:song ?songz ."
					+ "?songz song:genre ?sg . " + "FILTER(lcase(?sg) =" + "\"" + genre + "\"" + ") ."
					+ "?songz song:title ?name ." + "?songz song:artist ?artist ." + "?artist artist:name ?art_name ."
					+ "?artist artist:image_urls ?image ." + "?artist artist:gender ?gender ."
					+ "?artist artist:urls ?art_url ."
					+ "?art_url ?prop ?urls. FILTER (strstarts(str(?prop), str(rdf:_)))}";

			String queryString = urlQueryPrefix + mainQuery;
			List<URLsDAO> urlOutput = new QueryProcessor().getUrlQueryOutput((queryString));
			request.setAttribute("urlOutput", urlOutput);
			request.setAttribute(genre.replaceAll(" ", "_").replaceAll("&", "_"), "selected");
			request.setAttribute("showTable2", "yes");
			setParameters(request, urlQueryPrefix, mainQuery);
		}

		if ("3".equalsIgnoreCase(tabNumber)) {
			request.setAttribute("class3", "tab-pane fade in active container active show");
			request.setAttribute("class1", "tab-pane fade container");
			request.setAttribute("class2", "tab-pane fade container");
			request.setAttribute("class4", "tab-pane fade container");
			request.setAttribute("class5", "tab-pane fade container");
			request.setAttribute("navLink3", "active");
			request.setAttribute("navLink1", "");
			request.setAttribute("navLink2", "");
			request.setAttribute("navLink4", "");
			request.setAttribute("navLink5", "");

			String label = request.getParameter("label");
			String mainQuery = "select ?aname ?pc ?rd ?art_name where{" + "?description artist:name ?art_name ."
					+ "?description artist:album ?abm ." + "?abm album:name ?aname ." + "?abm album:play_count ?pc ."
					+ "?abm album:label ?lbl." + "?abm album:release_date ?rd. " + "?lbl label:name ?lname ."
					+ "FILTER(lcase(?lname) =" + "\"" + label + "\"" + ") ."
					+ "?lbl label:country ?cnt} order by desc(xsd:integer(?pc)) Limit 3";
			String queryString = labelQueryPrefix + mainQuery;

			List<LabelDAO> labelOutput = new QueryProcessor().getLableQueryOutput(queryString);
			request.setAttribute("labelOutput", labelOutput);
			request.setAttribute(label.replaceAll(" ", "_"), "selected");
			request.setAttribute("showTable3", "yes");
			setParameters(request, labelQueryPrefix, mainQuery);
		}

		if ("4".equalsIgnoreCase(tabNumber)) {
			request.setAttribute("class3", "tab-pane fade container");
			request.setAttribute("class1", "tab-pane fade container");
			request.setAttribute("class2", "tab-pane fade container");
			request.setAttribute("class4", "tab-pane fade in active container active show");
			request.setAttribute("class5", "tab-pane fade container");
			request.setAttribute("navLink3", "");
			request.setAttribute("navLink1", "");
			request.setAttribute("navLink2", "");
			request.setAttribute("navLink4", "active");
			request.setAttribute("navLink5", "");

			request.setAttribute("showTable4", "yes");

			String queryPrefix = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
					+ "PREFIX artist: <http://myMusic.org/music/artist#> "
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "PREFIX song: <http://myMusic.org/music/song#>";

			String mainQuery = "select ?aname ?pop ?foll where{" + "?description artist:name ?aname ."
					+ "?description artist:popularity ?pop ." + "?description artist:followers ?foll ."
					+ "} order by desc(?pop) LIMIT 6";
			setParameters(request, queryPrefix, mainQuery);
		}

		if ("5".equalsIgnoreCase(tabNumber)) {
			request.setAttribute("class3", "tab-pane fade container");
			request.setAttribute("class1", "tab-pane fade container");
			request.setAttribute("class2", "tab-pane fade container");
			request.setAttribute("class5", "tab-pane fade in active container active show");
			request.setAttribute("class4", "tab-pane fade container");
			request.setAttribute("navLink3", "");
			request.setAttribute("navLink1", "");
			request.setAttribute("navLink2", "");
			request.setAttribute("navLink5", "active");
			request.setAttribute("navLink4", "");

			request.setAttribute("showTable5", "yes");

			String mainQuery = "select ?cnt ?genre where{" + "SELECT ?genre (sum(xsd:integer(?lst)) AS ?cnt) where {"
					+ "?description song:genre ?genre ." + "?description song:listners ?lst ."
					+ "} Group by ?genre} order by DESC(xsd:integer(?cnt)) LIMIT 3";

			String queryString = genreQueryPrefix + mainQuery;
			List<GenreDAO> genreOutput = new QueryProcessor().getGenreQueryOutput(queryString);
			request.setAttribute("genreOutput", genreOutput);
			request.setAttribute("showTable5", "yes");
			setParameters(request, genreQueryPrefix, mainQuery);
		}
		RequestDispatcher view = request.getRequestDispatcher("music.jsp");
		view.forward(request, response);
	}

	/**
	 * @param request
	 * @param queryPrefix
	 * @param mainQuery
	 */
	private void setParameters(HttpServletRequest request, String queryPrefix, String mainQuery) {
		request.setAttribute("queryPrefix", queryPrefix);
		request.setAttribute("mainQuery", mainQuery);
	}
}
