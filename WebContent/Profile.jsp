<%@page import="com.igdb.stores.ReviewStore"%>
<%@page import="java.util.*"%>
<%@page import="com.igdb.stores.UserStore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
.pic {
	margin-top: 50px;
	width: 120px;
	margin-left: 50px;
	margin-bottom: -60px;
}

.panel {
	background-image:
		url("http://autoimagesize.com/wp-content/uploads/2014/01/rainbow-aurora-background-wallpaper-colour-images-rainbow-background.jpg");
}

.name {
	position: absolute;
	padding-left: 200px;
	font-size: 30px;
}

.dropdown {
	position: absolute;
}

.change {
	position: relative;
	bottom: 20px;
	padding: 1px;
	color: white;
	text-decoration: none;
}

.change:hover {
	text-decoration: none;
	background-color: black;
	color: white;
}

textarea {
	resize: none;
}
</style>

</style>
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="/js/jasny-bootstrap.js"></script>

<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/buttons.css"
	rel="stylesheet">
	
	
<%
		UserStore logedUser = (UserStore) session.getAttribute("user");
			UserStore user = (UserStore) request.getAttribute("user");
			LinkedList<ReviewStore> ReviewList = (LinkedList<ReviewStore>) request.getAttribute("reviewList");
	%>
<title>Profile - <%=user.getFirstName() %></title>

</head>
<body>
	<jsp:include page="PageHeader.jsp" />
	<br>


	



	<div class="container">
		<div class="row well">

			<div class="col-md-12">
				<div class="panel">
					<img class="pic img-circle"
						src="http://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/twDq00QDud4/s120-c/photo.jpg"
						alt="...">
					<div class="name">
						<small><%=user.getFirstName()%> <%=user.getLastName()%></small>
					</div>
				</div>

				<br> <br> <br>
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#activities" data-toggle="tab"><i
							class="fa fa-comments"></i> Activities</a></li>
					<li><a href="#about" data-toggle="tab"><i
							class="fa fa-user"></i> About</a></li>
					<li><a href="#photos" data-toggle="tab"><i
							class="fa fa-user"></i> Photos</a></li>
				</ul>

				<div class="tab-content">

					<div class="tab-pane active" id="activities">
						<br>
						<%
							System.out.println("Reviews in render");
											if (ReviewList == null || ReviewList.isEmpty()) {
						%>

						<p>No Reviews found</p>
						<%
							} else {
						%>
						<%
							Iterator<ReviewStore> iterator;

							iterator = ReviewList.iterator();
							while (iterator.hasNext()) {
							ReviewStore rs = (ReviewStore) iterator.next();
						%>
						Reviewed <a
							href="<%=request.getContextPath()%>/Game/<%=rs.getGame_id()%>"><%=rs.getGame_name()%></a>
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


					<div class="tab-pane" id="about">
						<br>
						<h4>
							Username:
							<%=user.getUsername()%><br> <br> Email:
							<%=user.getEmail()%><br> <br> Country:
							<%=user.getCountry()%><br> <br>
						</h4>
					</div>

				</div>

			</div>
		</div>


	</div>

	<script type="text/javascript">
		$(function() {
			$('#myTab a:first').tab('show')
		})
	</script>

</body>
</html>