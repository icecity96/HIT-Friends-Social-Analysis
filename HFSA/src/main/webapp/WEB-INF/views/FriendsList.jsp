<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FriendsList</title>
	<link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet">
</head>
<body class="gray-by">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			 <c:forEach items="${friendList}" var="friend">
		 		<div class="col-sm-6">
		        	<div class="contact-box">
						<div class="pull-right social-action dropdown">
							<button data-toggle="dropdown" class="dropdown-toggle btn-white">
								<i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu m-t-xs">
								<li><a href="delFriends?id=${userLogin.id }&name=${friend.friendname }">删除好友</a></li>
							</ul>
						</div>
						<a href="SpecificFriend?id=${userLogin.id }&friendName=${friend.friendname }">
		                    <div class="col-sm-4">
		                        <div class="text-center">
		                            <img alt="image" class="img-circle m-t-xs img-responsive" src="img/a3.jpg">
		                            <div class="m-t-xs font-bold">${friend.friendname }</div>
		                        </div>
		                    </div>
		                    <div class="col-sm-8">
		                        <h3><strong>${friend.id }</strong></h3>
		                        Weibo:<a href="${friend.friendweibo }" target="_Blank">${friend.friendweibo }</a><br>
		                        TianYa:<a href="${friend.friendtianya }" target="_Blank">${friend.friendtianya }</a><br>
		                    </div>
		                    <div class="clearfix"></div>
		                </a>
		            </div>
		        </div>
		  	</c:forEach>    
		</div>
	</div>
	

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/content.min.js"></script>
    <script>
        $(document).ready(function(){$(".contact-box").each(function(){animationHover(this,"pulse")})});
    </script>
</body>
</html>