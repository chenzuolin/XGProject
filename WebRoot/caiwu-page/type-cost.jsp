﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--为移动设备添加 viewport-->
    <title>仓储管理系统登陆</title>
    <meta name="description" content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。致力于为广大商户提供快速、安全、便捷的仓储物流服务" />
    <meta name="keywords" content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。钢材、木材、百货、物流、汽车货运、铁路货运" />
    <meta name="author" content="">
    <meta name="format-detection" content="telephone=no" />
    <!--禁止数字识自动别为电话号码-->
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--禁止百度转码-->
    <link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
    <!--添加 favicon icon -->
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="css/public.css" />
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
  	$(function(){
  		$(".aId").click(function(){
  			//document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
		var td1 = $(this).parents("td").siblings("td").eq(0).find(".id").val();
		var td2 = $(this).parents("td").siblings("td").eq(1).text();
		var td3 = $(this).parents("td").siblings("td").eq(2).text();
		var td4 = $(this).parents("td").siblings("td").eq(3).text();
		
		$("#cdId").val(td1);
		$("#fylx1").val(td2);
		$("#fy1").val(td3);
		$("#beizhu1").val(td4);

  		});
	
		$(".closeOk").click(function(){
			document.location.href="${pageContext.request.contextPath}/costType.do?flag=getCostTypeAll";
		});	
  	});
  function fun(){
  	$reg=/^(\d*\.)?\d+$/;
	if($("#fylx").val()=="" || $("#fylx").val()==null){
		alert("费用类型不能为空！");
		return false;
	}
	if($("#fy").val()=="" || $("#fy").val()==null){
		alert("费用不能为空！");
		return false;
	}
	if(!$reg.test($("#fy").val())){
		alert("费用为正浮点数！");
		return false;
	}
	return true;			
}

function fun2(){
	$reg=/^(\d*\.)?\d+$/;
	if($("#fylx1").val()=="" || $("#fylx1").val()==null){
		alert("费用类型不能为空！");
		return false;
	}
	if($("#fy1").val()=="" || $("#fy1").val()==null){
		alert("费用不能为空！");
		return false;
	}
	if(!$reg.test($("#fy1").val())){
		alert("费用为正浮点数！");
		return false;
	}
	
	return true;	
}

  </script>
</head>

<body>
    <!--内容起始处-->
    <div id="mainFrame" class="main">
        <div class="daohang">
            <ul>
                <i>当前位置：</i>
                <a style="cursor:pointer;">财务管理</a> <span>/</span>&nbsp;
                <a href="javascript:document.location.reload();">费用类型</a> <span>/</span>&nbsp;
            </ul>
        </div>
        <div class="container">
            <ul class="nav nav-tabs nav-pills" style="margin: 10px 2.5%; font-weight: bold;">
                <li class="active">
                    <a data-toggle="tab" href="#home">
                        <span class="glyphicon glyphicon-list" aria-hidden="true" style="margin-right: 8px;"></span>费用类型列表</a>
                </li>
                <li>
                    <a data-toggle="tab" href="#menu1">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;"></span>新增费用类型</a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>费用类型</th>
                                <th>费用(元)</th>
                                <th>备注</th>
                                <th style="width: 100px;">编辑</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${ctlist==null }">
                			<c:redirect url="/costType.do?flag=getCostTypeAll"/>
	                	</c:if>
	                	<c:forEach items="${ctlist }" var="lp" varStatus="v">
	                    <tr>
	                        <td>${v.index+1 }<input type="hidden" class="id" value="${lp.ctypeId }"></td>
	                        <td>${lp.ctypeName }</td>
	                      	<td>${lp.ctypeCost }</td>
	                      	<td>${lp.ctypeRemark }</td>
                                <td>
                                 <a class="btn btn-warning btn-xs aId" data-toggle="tab" href="#menu2">编辑</a>
               					<button class="btn btn-danger btn-xs " data-toggle="modal" data-target="#disable${v.index }">停用</button>
                                    <!-- 模态框（Modal） -->
                                    <div class="modal fade  " id="disable${v.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title"> 停用费用类型</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <h3>确定需要停用此费用类型？</h3>
                                                </div>
                                                <div class="modal-footer">
                                                     <a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
                               			   			<a class="btn btn-primary" href="${pageContext.request.contextPath}/costType.do?flag=stopCostTypeAll&id=${lp.ctypeId }">确定</a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal -->
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="menu1" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">新增费用类型</h4>
                        </div>
                         <form  class="form-horizontal" 
                   method="post"  name="costTypeForm" 
                   action="${pageContext.request.contextPath}/costType.do?flag=saveCostType" onSubmit="return fun()">
                        <div class="modal-body">
         
                                <div class="form-group">
                                    <label for="fylx" class="col-sm-3 control-label">费用类型</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="fylx" name="ctypeName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="fy" class="col-sm-3 control-label">费用</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="fy" name="ctypeCost">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="beizhu" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="beizhu" name="ctypeRemark">
                                    </div>
                                </div>
                           
                        </div>
                        <!-- /.modal-content -->
                        <div class="modal-footer">
                            <a class="btn btn-primary closeOk">关闭</a>
                			<input class="btn btn-primary" type="submit" value="提交"/> 
                        </div>
                         </form>
                    </div>
                </div>
                <div id="menu2" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">编辑费用类型</h4>
                        </div>
                         <form class="form-horizontal"  name="costTypeForm" 
                       method="post" action="${pageContext.request.contextPath}/costType.do?flag=updateCostType" onSubmit="return fun2()">
                        <div class="modal-body">
 								<input type="hidden" id="cdId" name="ctypeId"/>
                               <!-- <div class="form-group">
                                    <label for="bh" class="col-sm-3 control-label">编号</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="bh" readonly name="ctypeName">
                                    </div>
                                </div> --> 
                                <div class="form-group">
                                    <label for="fylx1" class="col-sm-3 control-label">费用类型</label>
                                    <div class="col-sm-7">
                                        <input type="text" readonly="readonly" class="form-control" id="fylx1" name="ctypeName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="fy1" class="col-sm-3 control-label">费用</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="fy1" name="ctypeCost">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="beizhu1" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="beizhu1" name="ctypeRemark">
                                    </div>
                                </div>
                            
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary closeOk">关闭</a>
                			<input class="btn btn-primary" type="submit" value="提交"/> 
                        </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
            </div>
        </div>
    </div>
    </div>
</body>

</html>
