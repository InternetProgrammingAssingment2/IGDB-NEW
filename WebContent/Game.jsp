<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.igdb.stores.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="<%=request.getContextPath()%>/Js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/Js/bootstrap.min.js"></script>
<style type="text/css">
.text {
	resize: none;
}
</style>
<%
			System.out.println("Still Working?");
			GameStore gs = (GameStore) request.getAttribute("game");
			System.out.println("Still Working?");
		%>

<title>About Game - <%=gs.getGameName() %></title>
</head>
<body>
	<jsp:include page="PageHeader.jsp" />
	<div class="container">
		<br>
		

		Game Title:
		<%=gs.getGameName()%><br>
		<%
			System.out.println("Got Name");
		%>
		Game Genre: 	
		<%
		for(String b : gs.getGenres()){
			%>
			<%=b %>,
		<% } %>
		
		<br>
		Game Platform:	
		<%
		for(String s : gs.getPlatforms()){
			%>
			<%=s %>,
		<% }
		
		%> 
		<br>
		
		No of Players:
		<%=gs.getPlayersNo()%><br>
		<%
			System.out.println("Got Players");
		%>
		Co-Op:
		<%=gs.getCoOP()%><br> Game Publisher:
		<%=gs.getPublisher()%><br> Game Developer:
		<%=gs.getDeveloper()%><br> Release Date:
		<%=gs.getRealeaseDate()%><br>
		<hr>
		Overview:
		<p><%=gs.getGameOverview()%>
		</p>
		<hr>
		<div class="col-md-7 col-md-offset-1">
			<form action="<%=request.getContextPath()%>/Game/<%=gs.getGameId()%>"
				method="post">
				<textarea class="form-control text" placeholder="Review this Game!"
					rows="4" name="review"></textarea>
				<button class="btn btn-success pull-right" type="submit">Review!</button>
			</form>
		</div>



		<div class="message-wrap col-md-9 col-md-offset-1">
			<br>

			<h3>
				<u>Reviews: </u>
			</h3>
			<div class="msg-wrap" style="overflow: auto; height: 420px;">

				<%
					System.out.println("Reviews in render");
					List<ReviewStore> ReviewList = (List<ReviewStore>) request
							.getAttribute("reviewList");
					if (ReviewList == null || ReviewList.isEmpty()) {
				%>

				<p>Be the First one to Review this game! :)</p>
				<%
					} else {
				%>
				<%
					Iterator<ReviewStore> iterator;

						iterator = ReviewList.iterator();
						while (iterator.hasNext()) {
							ReviewStore rs = (ReviewStore) iterator.next();
							String userName;
				%>
				<h5>
					<a
						href="<%=request.getContextPath()%>/Profile/<%=rs.getUser_name()%>"><%=rs.getUser_name()%>:</a><br>
					<p>"<%=rs.getReview()%>"</p>

				</h5>
				<h6>
					On:
					<%=rs.getDate_time()%>
				</h6>
				<hr>

				<%
					}
					}
				%>


			</div>

		</div>



	</div>



</body>
</html>