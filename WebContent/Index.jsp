<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">

<title>Login - IGDB</title>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please sign in</h3>
					</div>
					<div class="panel-body">
						<form accept-charset="UTF-8" action="Home" role="form"
							method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="User Name"
										name="username" type="text" value="Rajitha">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="1">
								</div>
								<input class="btn btn-lg btn-success btn-block" type="submit"
									value="Login">
								<center>
									<h4>Or</h4>
								</center>
								<input class="btn btn-lg btn-primary btn-block" type="submit"
									value="Register">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>