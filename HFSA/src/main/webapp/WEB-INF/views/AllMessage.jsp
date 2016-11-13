<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全部动态</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css" rel="stylesheet">
</head>
<body>
<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-10">
				<c:forEach items="${friendsStatus}" var="friendstatus">
					<div class="social-feed-separated">
						<div class="social-avatar">
							<a href="SpecificFriend?id=${userLogin.id }&friendName=${friendstatus.name }"> <img alt="image" src="img/a8.jpg">
							</a>
						</div>
						<div class="social-feed-box">
							<div class="pull-right social-action dropdown">
								<button data-toggle="dropdown" class="dropdown-toggle btn-white">
									<i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu m-t-xs">
									<li><a href="#">设置</a></li>
								</ul>
							</div>
							<div class="social-avatar">
								<a href="SpecificFriend?id=${userLogin.id }&friendName=${friendstatus.name }">${friendstatus.name }</a> <small class="text-muted">${friendstatus.statu.time }</small>
							</div>
							<div class="social-body">
								<p>${friendstatus.statu.context }</p>
							</div>
							<div class="social-footer">
								<small class="text-muted">${friendstatus.statu.type }</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>



	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/content.min.js"></script>
</body>
</html>