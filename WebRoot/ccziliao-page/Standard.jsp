<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="<%=basePath%>">
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
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="css/public.css" />
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".aId").click(function(){
    			//document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
				var td1 = $(this).parents("td").siblings("td").eq(0).find(".id").val();
				var td2 = $(this).parents("td").siblings("td").eq(1).text();
				var td3 = $(this).parents("td").siblings("td").eq(2).text();
				var td4 = $(this).parents("td").siblings("td").eq(3).text();
				
				$("#cdId").val(td1);
				$("#chandiName").val(td2);
				$("#miaoshu").val(td3);
				$("#beizhu").val(td4);
				
    		});
			
			$(".closeOk").click(function(){
				document.location.href="${pageContext.request.contextPath}/goodsStandard.do?flag=selectGoodsStandard";
			});
			
    	});
    	
    	function fun(){
			if($(".ggName").val()=="" || $(".ggName").val()==null){
				alert("规格名称不能为空！");
				return false;
			}			
		}
		function fun2(){
			if($("#chandiName").val()=="" || $("#chandiName").val()==null){
				alert("规格名称不能为空！");
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
            <ul><i>当前位置：</i> 
                <a style="cursor:pointer;">仓储资料</a> <span>></span>&nbsp;
                <a style="cursor:pointer;">货物规格</a> <span>></span>&nbsp;
            </ul>
        </div>
        <div class="container">
            <ul class="nav nav-tabs nav-pills" style="margin: 10px 2.5%; font-weight: bold;">
                <li class="active"><a data-toggle="tab" href="#home">
                <span class="glyphicon glyphicon-th-list" aria-hidden="true" style="margin-right: 8px;"></span>货物规格列表</a></li>
                <li><a data-toggle="tab" href="#menu1">
                <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;"></span>新增货物规格</a></li>
               <!--   <li><a data-toggle="tab" href="#menu2">编辑货物规格</a></li>-->
            </ul>
            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr >
                                <th>编号</th>
                                <th>规格名称</th>
                                <th>描述</th>
                                <th>备注</th>
                                <th style="width: 100px;">编辑</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${listGoodsStandard==null }">
                        		<c:redirect url="/goodsStandard.do?flag=selectGoodsStandard"/>
                        	</c:if>
                        	<c:forEach items="${listGoodsStandard}" var="gs" varStatus="v">
                            <tr>
                               <td>${v.index+1 }<input type="hidden" class="id" value="${gs.goodsStandardId }"></td>
                                <td>${gs.goodsStandardName }</td>
                                <td>${gs.goodsStandardDefinedTwo }</td>
                                <td>${gs.goodsStandardRemark }</td>
                                <td>
                                    <a class="btn btn-warning btn-xs aId" data-toggle="tab" href="#menu2">编辑</a>
                                    <a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#disable${v.index }">停用</a>
                                    <!-- 模态框（Modal） -->
                                    <div class="modal fade bs-example-modal-sm " id="disable${v.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">停用货物规格</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <h3>确定需要停用此货物规格？</h3>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default closeOk" data-dismiss="modal">关闭
                                                    </button>
                                                    <a class="btn btn-primary" href="/XGProject/goodsStandard.do?flag=stopGoodsStandard&id=${gs.goodsStandardId}">提交</a>
                                              
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
                <!-- 新增货物规格 -->
                <div id="menu1" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">新增货物规格</h4>
                        </div>
                        
                        <form class="form-horizontal" name="goodsStandardForm" 
                         method="post" action="${pageContext.request.contextPath}/goodsStandard.do?flag=addGoodsStandard" onSubmit="return fun()">
	                       <div class="modal-body">
	                             <div class="form-group">
	                                 <label for="zhanghao" class="col-sm-3 control-label">规格名称</label>
	                                 <div class="col-sm-5">
	                                     <input type="text" class="form-control ggName" name="goodsStandardName">
	                                 </div>
	                             </div>
	                             <div class="form-group">
	                                 <label for="inpassword" class="col-sm-3 control-label" >描述</label>
	                                 <div class="col-sm-5">
	                                     <input type="text" class="form-control" id="inpassword" name="goodsStandardDefinedTwo">
	                                 </div>
	                             </div>
	                             <div class="form-group">
	                                 <label for="name" class="col-sm-3 control-label">备注</label>
	                                 <div class="col-sm-5">
	                                     <input type="text" class="form-control"  name="goodsStandardRemark">
	                                 </div>
	                             </div>
	                       </div>
	                       <div class="modal-footer">
	                            <a class="btn btn-default closeOk">关闭</a>
	                           <input class="btn btn-primary" type="submit" value="提交"/> 
	                       </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- 编辑货物规格 -->
                <div id="menu2" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">编辑货物规格</h4>
                        </div>
                        <form class="form-horizontal"  name="goodsStandardForm" 
                         method="post" action="${pageContext.request.contextPath}/goodsStandard.do?flag=updateGoodsStandard"
                         onSubmit="return fun2()">
                        <div class="modal-body">
                        		<input type="hidden" id="cdId" name="goodsStandardId" />
                              <div class="form-group">
                                  <label for="zhanghao" class="col-sm-3 control-label">规格名称</label>
                                  <div class="col-sm-5">
                                      <input type="text" class="form-control" id="chandiName" name="goodsStandardName">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="inpassword" class="col-sm-3 control-label">描述</label>
                                  <div class="col-sm-5">
                                      <input type="text" class="form-control" id="miaoshu" name="goodsStandardDefinedTwo">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="name" class="col-sm-3 control-label">备注</label>
                                  <div class="col-sm-5">
                                      <input type="text" class="form-control" id="beizhu" name="goodsStandardRemark">
                                  </div>
                              </div>
                        </div>
                        <div class="modal-footer">
                             <a class="btn btn-default closeOk">关闭</a>
                            <input class="btn btn-primary" type="submit" value="提交"/> 
                        </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
            </div>
        </div>
    </div>
</body>

</html>
