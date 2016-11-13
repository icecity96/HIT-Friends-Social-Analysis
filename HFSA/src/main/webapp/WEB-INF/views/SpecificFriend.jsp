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
                        <h5>动态量</h5>
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
                        	var weekmov6 = ${weekMov[6] };
                        	var weekmov5 = ${weekMov[5] };
                        	var weekmov4 = ${weekMov[4] };
                        	var weekmov3 = ${weekMov[3] };
                        	var weekmov2 = ${weekMov[2] };
                        	var weekmov1 = ${weekMov[1] };
                        	var weekmov0 = ${weekMov[0] };
                        
							var myChart = echarts.init(document.getElementById('echarts-line-chart'));
							
							var option ={
								title : {
								},
								tooltip : {
									trigger : "axis"
								},
								legend : {
									data : [ "动态量"]
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
									data : [ "六天前", "五天前", "四天前", "大前天", "前天", "昨天", "当天" ]
								} ],
								yAxis : [ {
									type : "value",
									axisLabel : {
										formatter : "{value} 条"
									}
								} ],
								series : [ {
									name : "动态量",
									type : "line",
									data : [ weekmov6, weekmov5, weekmov4, weekmov3, weekmov2, weekmov1, weekmov0 ],
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
                        <h5>兴趣分析</h5>
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
						<div class="echarts" id="echarts-pie-chart"></div>
						<script type="text/javascript">
							var topic9 = ${frendtopic[9] };
							var topic8 = ${frendtopic[8] };
							var topic7 = ${frendtopic[7] };
							var topic6 = ${frendtopic[6] };
							var topic5 = ${frendtopic[5] };
							var topic4 = ${frendtopic[4] };
							var topic3 = ${frendtopic[3] };
							var topic2 = ${frendtopic[2] };
							var topic1 = ${frendtopic[1] };
							var topic0 = ${frendtopic[0] };
							
							var myChart = echarts.init(document.getElementById('echarts-pie-chart'));
							
							var option ={
									title : {
									},
									tooltip : {
										trigger : "item",
										formatter : "{a} <br/>{b} : {c} ({d}%)"
									},
									legend : {
										orient : "vertical",
										x : "left",
										data : [ "体育类", "旅行摄影美食类", "军事和时事类", "校园和职场类", "汽车", "财经类", "星座时尚语录", "娱乐", "科技", "健康和养生" ]
									},
									calculable : !0,
									series : [ {
										name : "兴趣分类",
										type : "pie",
										radius : "55%",
										center : [ "50%", "60%" ],
										data : [ {
											value : topic0,
											name : "体育类"
										}, {
											value : topic1,
											name : "旅行摄影美食类"
										}, {
											value : topic2,
											name : "军事和时事类"
										}, {
											value : topic3,
											name : "校园和职场类"
										}, {
											value : topic4,
											name : "汽车"
										}, {
											value : topic5,
											name : "财经类"
										}, {
											value : topic6,
											name : "星座时尚语录"
										}, {
											value : topic7,
											name : "娱乐"
										}, {
											value : topic8,
											name : "科技"
										}, {
											value : topic9,
											name : "健康和养生"
										}]
									} ]
							};
							myChart.setOption(option);
						</script>
						<div class="echarts" id="echarts-radar-chart"></div>
                        <script type="text/javascript">
	                        var topic9 = ${frendtopic[9] };
							var topic8 = ${frendtopic[8] };
							var topic7 = ${frendtopic[7] };
							var topic6 = ${frendtopic[6] };
							var topic5 = ${frendtopic[5] };
							var topic4 = ${frendtopic[4] };
							var topic3 = ${frendtopic[3] };
							var topic2 = ${frendtopic[2] };
							var topic1 = ${frendtopic[1] };
							var topic0 = ${frendtopic[0] };
							var maxNum = ${maxNum };
							
							var myChart = echarts.init(document.getElementById('echarts-radar-chart'));
							
							var option ={
									title : {},
									tooltip : {},
									legend : {
										orient : "vertical",
										x : "right",
										y : "bottom",
										data : [ "相关动态量"]
									},
									radar : {
										indicator : [ 
											{ name : "体育类", max : maxNum},
											{ name : "旅行摄影美食类", max : maxNum},
											{ name : "军事和时事类", max : maxNum},
											{ name : "校园和职场类", max : maxNum},
											{ name : "汽车", max : maxNum},
											{ name : "财经类", max : maxNum},
											{ name : "星座时尚语录", max : maxNum},
											{ name : "娱乐", max : maxNum},
											{ name : "科技", max : maxNum},
											{ name : "健康和养生", max : maxNum}
										]
									},
									series : [ {
										name : "相关动态量",
										type : "radar",
										data : [ {
											value : [ topic0, topic1, topic2, topic3, topic4, topic5, topic6, topic7, topic8, topic9 ],
											name : "相关动态量"
										} ]
									} ]
							};
							myChart.setOption(option);
						</script>
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