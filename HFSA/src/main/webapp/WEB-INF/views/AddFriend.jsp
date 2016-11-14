<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加好友</title>
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
	<div class="form-group">
		<div class="form-horizontal" style="margin-top: 10%">
			<div class="form-group">
				<label class="col-sm-3 control-label">好友名：</label>

				<div class="col-sm-6">
					<input type="text" name="name" id="name" tabindex="1"
						class="form-control" placeholder="FriendName" required autofocus />
					<span class="help-block m-b-none">你可以为你的好友起一个名字</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">微博主页url：</label>

				<div class="col-sm-6">
					<input type="text" name="weibourl" id="weibourl" tabindex="2"
						class="form-control" placeholder="微博主页url" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">天涯主页url：</label>

				<div class="col-sm-6">
					<input type="text" name="tianyaurl" id="tianyaurl" tabindex="3"
						class="form-control" placeholder="天涯主页url" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<input type="submit" name="add-submit" id="add-submit" tabindex="4"
						class="form-control btn btn-login" value="添加"
						onclick="ajaxTest();" />
				</div>
			</div>
		</div>
	</div>

	
	<script type="text/javascript">
		function ajaxTest() {
			var id = ${userLogin.id };
			$.ajax({
				type : "post",
				url : "tianjia",
				data : {
					id : id,
					name : $("#name").val(),
					weibourl : $("#weibourl").val(),
					tianyaurl : $("#tianyaurl").val()
				},
				dataType : "json",
				error : function() {
					window.location.href = "FriendsList?id=" + id;
				}
			})
		}
	</script>
	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/hplus.min.js"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
</body>
</html>