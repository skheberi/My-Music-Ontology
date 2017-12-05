<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>myMusic</title>
<link href="style.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<c:set var="tab1" value="${class1}" />
	<c:set var="tab2" value="${class2}" />
	<c:set var="tab3" value="${class3}" />
	<c:set var="tab4" value="${class4}" />
	<c:set var="tab5" value="${class5}" />

	<c:if
		test="${empty showTable1 && empty showTable2 && empty showTable3 && empty showTable4 && empty showTable5}">
		<c:set var="tab1"
			value="tab-pane fade in active container active show" />
		<c:set var="tab2" value="tab-pane fade container" />
		<c:set var="tab3" value="tab-pane fade container" />
		<c:set var="tab4" value="tab-pane fade container" />
		<c:set var="tab5" value="tab-pane fade container" />
		<c:set var="navLink1" value="active" />
	</c:if>
	<div
		style="margin-top: 10px; margin-left: 10px; font-size: 30px; margin-bottom: 20px; font-weight: bold;">
		<span>myMusic</span> <span><img align="right" height="60px"
			src="usc.jpg" style="margin-right: 10px" width="190px"></span>
	</div>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link ${navLink1}"
				data-toggle="tab" href="#q1">Upcoming Events</a></li>
			<li class="nav-item"><a class="nav-link ${navLink2}"
				data-toggle="tab" href="#q2">Social media URLs</a></li>
			<li class="nav-item"><a class="nav-link ${navLink3}"
				data-toggle="tab" href="#q3">Top 3 Albums</a></li>
			<li class="nav-item"><a class="nav-link ${navLink4}"
				data-toggle="tab" href="#q4">Popular Artists</a></li>
			<li class="nav-item"><a class="nav-link ${navLink5}"
				data-toggle="tab" href="#q5">Popular Genres</a></li>
		</ul>
		<div class="tab-content">
			<div class="${tab1}" id="q1">
				<br>
				<p>
				<h2>Find the events of the Artists who play the following
					instrument</h2>
				</p>
				<form method="POST" id="1" action="QueryServlet.do">
					<div class="form-group" style="width: 180px">
						<select class="form-control" name="instrument" id="instrument1">
							<option ${vocals} value="vocals">Vocals</option>
							<option ${drums} value="drums">Drums</option>
							<option ${guitar} value="guitar">Guitar</option>
							<option ${keyboards} value="keyboards">Keyboards</option>
							<option ${piano} value="piano">Piano</option>
							<option ${acoustic} value="acoustic">Acoustic</option>
						</select>
					</div>
					<input type="hidden" name="tabNumber" value="1">
					<center>
						<button type="submit" class="btn btn-primary">Submit</button>
					</center>
				</form>

				<c:if test="${showTable1 == 'yes'}">
					<div class="row" style="margin-top: 50px">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Event Name</th>
									<th scope="col">Event Date</th>
									<th scope="col">Performed By</th>
									<th scope="col">Arena</th>
									<th scope="col">Location</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="event" items="${eventOutput}"
									varStatus="theCount">
									<tr>
										<th scope="row">${theCount.index + 1}</th>
										<td>${event.eventName}</td>
										<td>${event.eventDate}</td>
										<td>${event.performedBy}</td>
										<td>${event.arena}</td>
										<td>${event.location}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br>
					<br>
					<div class="form-group">
						<label for="query">SPARQL Query</label>
						<textarea class="form-control" style="max-width: 60%" rows="12"
							id="query1">${queryPrefix}&#13;&#10;&#13;&#10;${mainQuery}</textarea>
					</div>
				</c:if>
			</div>
			<div class="${tab2}" id="q2">
				<br>
				<p>
				<h2>Find social media URLs of the award winning Artists in
					following genre</h2>
				</p>
				<form method="POST" id="2" action="QueryServlet.do">
					<div class="form-group" style="width: 80px">
						<select class="form-control" name="genre" id="genre">
							<option ${pop} value="pop">Pop</option>
							<option ${r_b} value="r&b">R&B</option>
							<option ${hip_hop} value="hip hop">Hip Hop</option>
							<option ${electro} value="electro">Electro</option>
						</select>
					</div>
					<input type="hidden" name="tabNumber" value="2">
					<center>
						<button type="submit" class="btn btn-primary">Submit</button>
					</center>
				</form>

				<c:if test="${showTable2 == 'yes' }">
					<div class="row" style="margin-top: 50px">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Artist</th>
									<th scope="col">Gender</th>
									<th scope="col">Image</th>
									<th scope="col">Social Link</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="urlRow" items="${urlOutput}"
									varStatus="theCount">
									<tr>
										<th scope="row">${theCount.index + 1}</th>
										<td>${urlRow.artistName}</td>
										<td>${urlRow.gender}</td>
										<td><img height="300" width="218"
											src="${urlRow.imageUrl}" /></td>
										<td><c:forEach var="urlValue" items="${urlRow.urls}">
												<a href="${urlValue}" target="_blank">${urlValue}</a>
												<br>
												<br>
											</c:forEach></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br>
					<br>
					<div class="form-group">
						<label for="query">SPARQL Query</label>
						<textarea class="form-control" style="max-width: 50%" rows="10"
							id="query1">${queryPrefix}&#13;&#10;&#13;&#10;${mainQuery}</textarea>
					</div>
				</c:if>

			</div>
			<div class="${tab3}" id="q3">
				<br>
				<p>
				<h2>Find top 3 albums of a particular label based on the
					playcount.</h2>
				</p>

				<form method="POST" id="3" action="QueryServlet.do">
					<div class="form-group" style="width: 180px">
						<select class="form-control" name="label" id="label1">
							<option ${rca} value="rca">RCA</option>
							<option ${def_jam} value="def jam">Def Jam</option>
							<option ${aftermath} value="aftermath">Aftermath</option>
							<option ${big_machine} value="big machine">Big Machine</option>
							<option ${interscope} value="interscope">Interscope</option>
							<option ${capitol} value="capitol">Capitol</option>
						</select>
					</div>
					<input type="hidden" name="tabNumber" value="3">
					<center>
						<button type="submit" class="btn btn-primary">Submit</button>
					</center>
				</form>

				<c:if test="${showTable3 == 'yes' }">
					<div class="row" style="margin-top: 50px">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Album</th>
									<th scope="col">Release Date</th>
									<th scope="col">Released By</th>
									<th scope="col">Play Count</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="label" items="${labelOutput}"
									varStatus="theCount">
									<tr>
										<th scope="row">${theCount.index + 1}</th>
										<td>${label.albumName}</td>
										<td>${label.releaseDate}</td>
										<td>${label.artistName}</td>
										<td>${label.playCount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br>
					<br>
					<div class="form-group">
						<label for="query">SPARQL Query</label>
						<textarea class="form-control" style="max-width: 50%" rows="10"
							id="query1">${queryPrefix}&#13;&#10;&#13;&#10;${mainQuery}</textarea>
					</div>
				</c:if>
			</div>
			<div class="${tab4}" id="q4">
				<br>
				<p>
				<h2>Show top Artists based on their Ratings and Followers</h2>
				</p>
				<br>
				<form method="POST" id="4" action="QueryServlet.do">
					<input type="hidden" name="tabNumber" value="4">
					<center>
						<button type="submit" class="btn btn-primary">Submit</button>
					</center>
				</form>
				<br> <br>
				<c:if test="${showTable4 == 'yes' }">
					<iframe id="chart" src="google_chart.html" height="600" width="1000"
						style="border-width: 0px;"></iframe>
					<div class="form-group">
						<label for="query">SPARQL Query</label>
						<textarea class="form-control" style="max-width: 50%" rows="10"
							id="query1">${queryPrefix}&#13;&#10;&#13;&#10;${mainQuery}</textarea>
					</div>
				</c:if>
			</div>
			<div class="${tab5}" id="q5">
				<br>
				<p>
				<h2>Which are the top 3 genres with the highest cumulative number of listeners</h2>
				</p>
				<br>
				<form method="POST" id="5" action="QueryServlet.do">
					<input type="hidden" name="tabNumber" value="5">
					<center>
						<button type="submit" class="btn btn-primary">Submit</button>
					</center>
				</form>
				<br> <br>
				<c:if test="${showTable5 == 'yes' }">
					<div class="row" style="margin-top: 50px">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Genre</th>
									<th scope="col">Listeners</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="genre" items="${genreOutput}"
									varStatus="theCount">
									<tr>
										<th scope="row">${theCount.index + 1}</th>
										<td>${genre.genreName}</td>
										<td>${genre.playCount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br>
					<br>
					<div class="form-group">
						<label for="query">SPARQL Query</label>
						<textarea class="form-control" style="max-width: 50%" rows="10"
							id="query1">${queryPrefix}&#13;&#10;&#13;&#10;${mainQuery}</textarea>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js">
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js">
		
	</script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js">
		
	</script>
	<script src="myscript.js">
	</script>
</body>
</html>