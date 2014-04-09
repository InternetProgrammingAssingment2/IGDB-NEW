<%@page import="java.util.*"%>
<%@page import="com.igdb.stores.*"%>
<%@page import="com.igdb.lib.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Games List - Test(10)</title>
</head>
<body>
	<jsp:include page="PageHeader.jsp" />
	<h3>Games...</h3>

	<%
		LinkedList<GameStore> gameList = (LinkedList<GameStore>) request
				.getAttribute("TenGames");
		if (gameList != null || gameList.isEmpty()) {
			Iterator<GameStore> iterator;
			iterator = gameList.iterator();
			while (iterator.hasNext()) {
				GameStore gs = (GameStore) iterator.next();
	%>
	<a href="<%=request.getContextPath()%>/Game/<%=gs.getGameId()%>"><%=gs.getGameName()%></a>
	<br>

	<%
		}
		} else {
	%>
	<h4>Something Wrong, No Games Returned!</h4>

	<%
		}
	%>


</body>
</html>