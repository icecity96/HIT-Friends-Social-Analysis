<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
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
	<form id="login-form" action="" method="post" role="form" style="display: block;">
		<div class="form-group">
			<div class="text-center">				
				<div class="form-group">
					<input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="FriendName" required autofocus>
				</div>
				<div class="form-group">
					<input type="微博昵称" name="WeiBoNickName" id="WBnickname" tabindex="2" class="form-control" placeholder="微博昵称"required autofocus>
				</div>
				<div class="form-group">
					<input type="天涯主页" name="TianYaUrl" id="TianYaUrl" tabindex="3" class="form-control" value="天涯主页">
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="添加">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>