<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LoginPage</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="signinpanel">
				<div class="col-sm-6">
	                <div class="signin-info">
	                    <div class="logopanel m-b">
	                        <h1>HFSA</h1>
	                    </div>
	                    <div class="m-b"></div>
	                    <h3>欢迎使用 <strong>HFSA</strong></h3>
	                    <ul class="m-b">
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 多社交平台集中管理</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 大数据更懂你的好友</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 文字表格多种展示</li>
	                    </ul>
	                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
	                </div>
	                <div class="signup-footer">
	            		<div class="pull-left">
	                		&copy; 2016 All Rights Reserved. HFSA
	            		</div>
	       			</div>
	            </div>
				<div class="col-sm-6">
					<div class="panel panel-login">
		                <div class="panel-heading">
			                <div class="row">
			                	<div class="col-xs-6">
									<a href="#" class="active" id="login-form-link">登录</a>
								</div>
								<div class="col-xs-6">
									<a href="#" id="register-form-link">注册</a>
								</div>
							</div>
		                </div>
		                <div class="panel-body">
		               		<div class="row">
								<div class="col-lg-12">
									<form id="login-form" action="HomePage" method="post" role="form" style="display: block;">
										<div class="form-group">
											<div class="text-center">
												${msg }
											</div>
										</div>
										<div class="form-group">
											<input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="Username" required autofocus>
										</div>
										<div class="form-group">
											<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"required autofocus>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="登录">
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-lg-12">
													<div class="text-center">
														<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>
													</div>
												</div>
											</div>
										</div>
									</form>
									<form id="register-form" action="register" method="post" role="form" style="display: none;">
										<div class="form-group">
											<input type="text" name="nickname" id="nickname" tabindex="1" class="form-control" placeholder="Username" required autofocus>
										</div>
										<div class="form-group">
											<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" required autofocus>
										</div>
										<div class="form-group">
											<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required autofocus>
										</div>
										<div class="form-group">
											<input type="password" name="confirmPassword" id="confirmPassword" tabindex="2" class="form-control" placeholder="Confirm Password" required autofocus>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="注册">
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
		            </div>
		    	</div>
	        </div>
	    </div>
	</div>
<script type="text/javascript">
$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

</script>

</body>
</html>
