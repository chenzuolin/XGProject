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
    <link rel="stylesheet" href="css/public.css" />
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/all.js"></script>
    <script src="js/goods.js"></script>
<script type="text/javascript">
    $(function(){
    	$(".closeOk").click(function(){
				document.location.href="${pageContext.request.contextPath}/goods.do?flag=goSelectGoods";
		});
    });
</script>
</head>

<body>
    <!--内容起始处-->
    <div id="mainFrame" class="main">
        <div class="daohang">
            <ul>
                <i>当前位置：</i>
                <a style="cursor:pointer;">仓储资料</a> <span>></span>&nbsp;
                <a style="cursor:pointer;">货物名称</a> <span>/</span>&nbsp;
            </ul>
        </div>
        <div class="container-fluid">
            <ul class="nav nav-tabs nav-pills" style="margin: 10px 2.5%; font-weight: bold;">
                <li class="active">
                    <a data-toggle="tab" href="#home">
                      <span class="glyphicon glyphicon-th-list" aria-hidden="true" style="margin-right: 8px;"></span>货物列表</a>
                </li>
                <li>
                    <a data-toggle="tab" href="#menu1" id="addGoods">
                     <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;"></span>新增货物</a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>货物名称</th>
                                <th>助记符</th>
                                <th>货物品类</th>
                                <th>规格</th>
                                <th>材质</th>
                                <th>属性</th>
                                <th>产地</th>
                                <th>计件单位</th>
                                <th>备注</th>
                                <th style="width: 100px;">编辑</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${goodslist==null }">
                        		<c:redirect url="/goods.do?flag=goSelectGoods" />
	                       	</c:if>
	                       	<c:forEach items="${goodslist }" var="lp" varStatus="v">
	                           <tr>
	                               <td>${v.index+1 }</td>
	                               <td>${lp.goodsName }</td>
	                               <td>${lp.goodsSign }</td>
	                               <td>${lp.goodsCategory.goodsCategoryName }</td>
	                               <td>${lp.goodsStandard.goodsStandardName }</td>
	                               <td>${lp.goodsQuality.goodsQualityName }</td>
	                               <td>${lp.goodsProperty.goodsPropertyName }</td>
	                               <td>${lp.goodsYieldly.goodsYieldlyName }</td>
	                               <td>${lp.goodsUnit.goodsUnitName }</td>
	                               <td>${lp.goodsRemark}</td>
	                               <td>
                                    <a class="btn btn-warning btn-xs aId" data-toggle="tab" href="#menu2">编辑</a>
                                    <button class="btn btn-danger btn-xs " data-toggle="modal" data-target="#disable${v.index }">停用</button>
                                    <!-- 模态框（Modal） -->
                                    <div class="modal fade bs-example-modal-sm " id="disable${v.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body">
                                                    <h3>确定需要停用此货物名称？</h3>
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
                                                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/goods.do?flag=stopGoods&id=${lp.goodsId }">确定</a>
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
                <!-- 新增货物名称 -->
                <div id="menu1" class="tab-pane fade">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">新增货物</h4>
                        </div>
                       <form  class="form-horizontal" 
                            method="post"  name="goodsForm" 
                            action="${pageContext.request.contextPath}/goods.do?flag=addGoods" onSubmit="return fun()">
                            <div class="modal-body">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="bianhao" class="col-sm-3 control-label">编号</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="bianhao" name="goodsId">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="col-sm-3 control-label">货物名称</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="name" name="goodsName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="zhujifu" class="col-sm-3 control-label">助记符</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="zhujifu" name="goodsSign">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="pinlei" class="col-sm-3 control-label">货物品类</label>
                                        <div class="col-sm-6" id="pinleiDiv">
                                            <select id="pinlei" name="goodsCategoryId">
                                                <option>xxxx</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group" id="guigeDiv">
                                        <label for="standard" class="col-sm-3 control-label">规格</label>
                                        <div class="col-sm-7 duoxuankuang" id="guigeDiv2">
                                           
                                        </div>
                                    </div>
                                    <div class="form-group" id="caizhiDiv">
                                        <label for="quality" class="col-sm-3 control-label">材质</label>
                                        <div class="col-sm-7 duoxuankuang" id="caizhiDiv2">
                                            
                                        </div>
                                    </div>
                                    <div class="form-group" id="shuxinDiv">
                                        <label for="property" class="col-sm-3 control-label">属性</label>
                                        <div class="col-sm-7 duoxuankuang" id="shuxinDiv2">
                                            
                                        </div>
                                    </div>
                                    <div class="form-group" id="chadiDiv">
                                        <label for="chandi" class="col-sm-3 control-label">产地</label>
                                        <div class="col-sm-7 duoxuankuang" id="chadiDiv2">
                                            
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="jjdw" class="col-sm-3 control-label">计件单位</label>
                                        <div class="col-sm-6" id="jijianDiv">
                                            <select id="jjdw" name="goodsUnitId">
                                                <option>xxxx</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                   <a class="btn btn-default closeOk">关闭</a>
                            		<input type="submit" class="btn btn-primary tijiao"  value="提交"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
                </div>
                <!-- 编辑货物名称 -->
                <div id="menu2" class="tab-pane fade">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">新增货物</h4>
                        </div>
                       <form class="form-horizontal"  name="goodsPropertyForm" 
                                method="post" action="${pageContext.request.contextPath}/goods.do?flag=updateGoods" onSubmit="return fun2()">
                            <div class="modal-body">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="bianhao1" class="col-sm-3 control-label">编号</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="bianhao1">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name1" class="col-sm-3 control-label">货物名称</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="name1">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="zhujifu1" class="col-sm-3 control-label">助记符</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="zhujifu1">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="pinlei1" class="col-sm-3 control-label">货物品类</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" list="pinlei1">
                                            <datalist id="pinlei1">
                                                <option>建材类</option>
                                                <option>板材类</option>
                                                <option>型材类</option>
                                                <option>木材类</option>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="standard1" class="col-sm-3 control-label">规格</label>
                                        <div class="col-sm-7 duoxuankuang">
                                            <input type="checkbox" value="guangpan" name="choose" id="guangpan">
                                            <label for="guangpan">HRB400</label>
                                            <input type="checkbox" value="kaiche" name="choose" id="kaiche">
                                            <label for="kaiche">HR400EB</label>
                                            <input type="checkbox" value="laiji" name="choose" id="laiji">
                                            <label for="laiji">HRB500</label>
                                            <input type="checkbox" value="baozhuang" name="choose" id="baozhuang">
                                            <label for="baozhuang">hrb500E</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>


                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="quality1" class="col-sm-3 control-label">材质</label>
                                        <div class="col-sm-7 duoxuankuang">
                                            <input type="checkbox" value="guangpan" name="choose" id="guangpan">
                                            <label for="guangpan">HRB400</label>
                                            <input type="checkbox" value="kaiche" name="choose" id="kaiche">
                                            <label for="kaiche">HRB400E</label>
                                            <input type="checkbox" value="laiji" name="choose" id="laiji">
                                            <label for="laiji">HRB500</label>
                                            <input type="checkbox" value="baozhuang" name="choose" id="baozhuang">
                                            <label for="baozhuang">HRB500E</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="property1" class="col-sm-3 control-label">属性</label>
                                        <div class="col-sm-7 duoxuankuang">
                                            <input type="checkbox" value="guangpan" name="choose" id="guangpan">
                                            <label for="guangpan">定尺9米</label>
                                            <input type="checkbox" value="kaiche" name="choose" id="kaiche">
                                            <label for="kaiche">定尺12米</label>
                                            <input type="checkbox" value="laiji" name="choose" id="laiji">
                                            <label for="laiji">16</label>
                                            <input type="checkbox" value="baozhuang" name="choose" id="baozhuang">
                                            <label for="baozhuang">18</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="chandi1" class="col-sm-3 control-label">产地</label>
                                        <div class="col-sm-7 duoxuankuang">
                                            <input type="checkbox" value="guangpan" name="choose" id="guangpan">
                                            <label for="guangpan">酒嘉</label>
                                            <input type="checkbox" value="kaiche" name="choose" id="kaiche">
                                            <label for="kaiche">酒榆</label>
                                            <input type="checkbox" value="laiji" name="choose" id="laiji">
                                            <label for="laiji">16</label>
                                            <input type="checkbox" value="baozhuang" name="choose" id="baozhuang">
                                            <label for="baozhuang">18</label>
                                            <input type="checkbox" value="guandeng" name="choose" id="guandeng">
                                            <label for="guandeng">30*2000*9-12</label>
                                            <input type="checkbox" value="yongshui" name="choose" id="yongshui">
                                            <label for="yongshui">30*2000*9-12</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="jjdw1" class="col-sm-3 control-label">计件单位</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" list="jjdw1">
                                            <datalist id="jjdw1">
                                                <option>件</option>
                                                <option>张</option>
                                            </datalist>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                     <a class="btn btn-primary closeOk">关闭</a>
                           			 <input class="btn btn-primary" type="submit" value="提交"/> 
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
function closes() {
    alert("确定要关闭吗？");
    window.location.href = "cargo-name.html";
}
$(function() {
    $(".aId").click(function() {
        //document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
        var td1 = $(this).parents("td").siblings("td").eq(0).text();
        var td2 = $(this).parents("td").siblings("td").eq(1).text();
        var td3 = $(this).parents("td").siblings("td").eq(2).text();
        var td4 = $(this).parents("td").siblings("td").eq(3).text();
        var td5 = $(this).parents("td").siblings("td").eq(4).text();
        var td6 = $(this).parents("td").siblings("td").eq(5).text();
        var td7 = $(this).parents("td").siblings("td").eq(6).text();
        var td8 = $(this).parents("td").siblings("td").eq(7).text();
        var td9 = $(this).parents("td").siblings("td").eq(8).text();

        $("#bianhao1").val(td1);
        $("#name1").val(td2);
        $("#zhujifu1").val(td3);
        $("#pinlei1").val(td4);
        $("#standard1").val(td5);
        $("#quality1").val(td6);
        $("#property1").val(td7);
        $("#chandi1").val(td8);
        $("#jjdw1").val(td9);
    });

});
</script>

</html>
