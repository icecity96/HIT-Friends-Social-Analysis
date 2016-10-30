<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Authorize</title>
	
	 <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet">
</head>
<body>
	<form id="login-form" action="HomePageSA" method="post" role="form" style="display: block;">
		<div class="form-group">
			<div class="text-center">				
				<div class="form-group">
					<input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="Username" required autofocus>
				</div>
				<div class="form-group">
					<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"required autofocus>
				</div>
				<div class="form-group">
					<input type="code" name="code" id="code" tabindex="3" class="form-control" value="${param.code }">
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Sign In">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
</body>
</html>