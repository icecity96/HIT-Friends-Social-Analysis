<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpecificFriend</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css" rel="stylesheet">
<script src="js/echarts.min.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>折线图</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="graph_flot.html#">选项1</a>
                                </li>
                                <li><a href="graph_flot.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="echarts" id="echarts-line-chart"></div>
                        <script type="text/javascript">
							var myChart = echarts.init(document.getElementById('echarts-line-chart'));
							
							var option ={
								title : {
									text : "未来一周气温变化"
								},
								tooltip : {
									trigger : "axis"
								},
								legend : {
									data : [ "最高气温", "最低气温" ]
								},
								grid : {
									x : 40,
									x2 : 40,
									y2 : 24
								},
								calculable : !0,
								xAxis : [ {
									type : "category",
									boundaryGap : !1,
									data : [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ]
								} ],
								yAxis : [ {
									type : "value",
									axisLabel : {
										formatter : "{value} °C"
									}
								} ],
								series : [ {
									name : "最高气温",
									type : "line",
									data : [ 11, 11, 15, 13, 12, 13, 10 ],
									markPoint : {
										data : [ {
											type : "max",
											name : "最大值"
										}, {
											type : "min",
											name : "最小值"
										} ]
									},
									markLine : {
										data : [ {
											type : "average",
											name : "平均值"
										} ]
									}
								}, {
									name : "最低气温",
									type : "line",
									data : [ 1, -2, 2, 5, 3, 2, 0 ],
									markPoint : {
										data : [ {
											name : "周最低",
											value : -2,
											xAxis : 1,
											yAxis : -1.5
										} ]
									},
									markLine : {
										data : [ {
											type : "average",
											name : "平均值"
										} ]
									}
								} ]
							};
							myChart.setOption(option);
						</script>
                    </div>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>柱状图</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="graph_flot.html#">选项1</a>
                                </li>
                                <li><a href="graph_flot.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="echarts" id="echarts-bar-chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-10">
				<c:forEach items="${specificFriend }" var="friendstatus">
					<div class="social-feed-separated">
						<div class="social-avatar">
							<a href="SpecificFriend"> <img alt="image" src="img/a8.jpg">
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
								<a href="SpecificFriend">${friendstatus.name }</a> <small class="text-muted">${friendstatus.statu.time }</small>
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