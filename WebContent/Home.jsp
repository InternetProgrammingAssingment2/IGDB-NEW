<%@page import="com.igdb.stores.GameStore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - IGDB</title>
</head>
<body>
	<jsp:include page="PageHeader.jsp" />
	<%
	GameStore FarCry = (GameStore)request.getAttribute("farCry");
	GameStore NFS = (GameStore)request.getAttribute("NFS");	
	GameStore FIFA = (GameStore)request.getAttribute("FIFA");
	%>
	
	
	<div class="jumbotron">
      <div class="container">
        <h1>Welcome!</h1>
        <p>Welcome to International Games Database. This website allow you to search through thousands of games and review them. You can manage your profile, tell other people "what games you are interested in" and see other members favourite games.
        Enjoy!</p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2><a href="<%=request.getContextPath()%>/Game/<%=FarCry.getGameId()%>"><%=FarCry.getGameName() %></a></h2>
          <p><%=FarCry.getGameOverview() %></p>
          <p><a class="btn btn-default" href="<%=request.getContextPath()%>/Game/<%=FarCry.getGameId()%>" role="button">View More &raquo;</a></p>
        </div>
        <div class="col-md-4">
         <h2><a href="<%=request.getContextPath()%>/Game/<%=NFS.getGameId()%>"><%=NFS.getGameName() %></a></h2>
          <p><%=NFS.getGameOverview() %></p>
          <p><a class="btn btn-default" href="<%=request.getContextPath()%>/Game/<%=NFS.getGameId()%>" role="button">View More &raquo;</a></p>
       </div>
        <div class="col-md-4">
         <h2><a href="<%=request.getContextPath()%>/Game/<%=FIFA.getGameId()%>"><%=FIFA.getGameName() %></a></h2>
          <p><%=NFS.getGameOverview() %></p>
          <p><a class="btn btn-default" href="<%=request.getContextPath()%>/Game/<%=FIFA.getGameId()%>" role="button">View More &raquo;</a></p>
        </div>
      </div>

      <hr>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
	
</body>
</html>