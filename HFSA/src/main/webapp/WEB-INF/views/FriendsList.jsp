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
			 <div class="col-sm-4">
                <div class="contact-box">
                    <a href="SpecificFriend">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="img/a2.jpg">
                                <div class="m-t-xs font-bold">CTO</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>奔波儿灞</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 甘肃·兰州</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="default.htm">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="SpecificFriend">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="img/a1.jpg">
                                <div class="m-t-xs font-bold">营销总监</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>灞波儿奔</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 四川·成都</p>
                            <address>
                            <strong>Taobao, Inc.</strong><br>
                            E-mail:xxx@taobao.com<br>
                            Weibo:<a href="default.htm">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="SpecificFriend">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="img/a3.jpg">
                                <div class="m-t-xs font-bold">Marketing manager</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>Monica Smith</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="default.htm">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
		</div>
	</div>
<%-- 	<c:forEach items="${FriendsList}" var="friend"> --%>
<!--  		<div class="col-sm-4"> -->
<!--         	<div class="contact-box"> -->
<!--                 <a href=""> -->
<!--                     <div class="col-sm-4"> -->
<!--                         <div class="text-center"> -->
<!--                             <img alt="image" class="img-circle m-t-xs img-responsive" src="img/a3.jpg"> -->
<%--                             <div class="m-t-xs font-bold">${friend.name }</div> --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="col-sm-8"> -->
<%--                         <h3><strong>${friend.realname }</strong></h3> --%>
<%--                         Weibo:<a href="default.htm">${friend.WeiBoNickName }</a><br> --%>
<%--                         TianYa:<a href="default.htm">${friend.TianYaUrl }</a><br> --%>
<!--                     </div> -->
<!--                     <div class="clearfix"></div> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->
<%--   	</c:forEach> --%>

	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/content.min.js"></script>
    <script>
        $(document).ready(function(){$(".contact-box").each(function(){animationHover(this,"pulse")})});
    </script>
</body>
</html>