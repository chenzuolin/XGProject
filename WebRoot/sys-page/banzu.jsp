<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function() {
        $(".aId").click(function() {
            //document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
            var td1 = $(this).parents("td").siblings("td").eq(0).find(".id").val();
            var td2 = $(this).parents("td").siblings("td").eq(1).text();
            var td3 = $(this).parents("td").siblings("td").eq(2).text();
            var td4 = $(this).parents("td").siblings("td").eq(3).text();
					
            $("#cdId").val(td1);
            $("#banzuName1").val(td2);
            $("#fuzeren1").val(td3);
            $("#buzhu1").val(td4);
            
            //调用部门类;
            selectSection();

        });
        
        $(".closeOk").click(function(){
				document.location.href="${pageContext.request.contextPath}/classT.do?flag=selectclassT";
		});
		
		
		$("#classTAdd").click(function() {
			//掉用部门方法
       		selectSection();
		});
    });
    
   	function fun(){
		if($("#banzuName").val()=="" || $("#banzuName").val()==null){
			alert("班组名称不能为空！");
			return false;
		}
		if($("#fuzeren").val()=="" || $("#fuzeren").val()==null){
			alert("负责人不能为空！");
			return false;
		}
		if($("#yanzhenBumen").val()=="" || $("#yanzhenBumen").val()==null){
			alert("部门不能为空！");
			return false;
		}
		return true;			
	}
	
	function fun2(){
		if($("#banzuName1").val()=="" || $("#banzuName1").val()==null){
			alert("班组名称不能为空！");
			return false;
		}
		if($("#fuzeren1").val()=="" || $("#fuzeren1").val()==null){
			alert("负责人不能为空！");
			return false;
		}
		if($("#yanzhenBumen2").val()=="" || $("#yanzhenBumen2").val()==null){
			alert("部门不能为空！");
			return false;
		}
		return true;	
	}


	function selectSection(){
		$.ajax({//ajax提交方式
				 type:"post",
				 url:"section.do?flag=selectSectionAjax",
				 dataType: "json",
				 success:function(section){				
					 var $dataObj=eval(section);	
					 if($dataObj.length>0){
						 $(".bumen").html("");//先清空，再添加;					
						 var $select=$(".bumen");
						 $.each($dataObj, function (i, item) { 
								$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
								
					     });  	
					  
					 }		 
				  },
				  error:function(){
					  alert("数据错误！");
				  }
		});
	}


</script>
</head>

<body>
    <!--内容起始处-->
    <div id="mainFrame" class="main">
        <div class="daohang">
            <ul>
                <i>当前位置：</i>
                <a style="cursor:pointer;">系统管理</a> <span>/</span>&nbsp;
                <a href="javascript:document.location.reload();">班组管理</a> <span>/</span>&nbsp;
            </ul>
        </div>
        <div class="container">
            <ul class="nav nav-tabs nav-pills" style="margin: 10px 2.5%; font-weight: bold;">
                <li class="active">
                    <a data-toggle="tab" href="#home">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true" style="margin-right: 8px;"></span>班组列表</a>
                </li>
                <li>
                    <a data-toggle="tab" href="#menu1" id="classTAdd">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;"></span>新增班组</a>
                </li>
                <!--  <li><a data-toggle="tab" href="#menu2">编辑班组</a></li> -->
            </ul>
            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>班组名称</th>
                                <th>部门</th>
                                <th>班组负责人</th>
                                <th>备注</th>
                                <th style="width: 100px;">编辑</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${listClassT==null }">
                        		<c:redirect url="/classT.do?flag=selectclassT" />
                        	</c:if>
                        	<c:forEach items="${listClassT }" var="lp" varStatus="v">
                            <tr>
                                <td>${v.index+1 }<input type="hidden" class="id" value="${lp.classId }"></td>
                                <td>${lp.className }</td>
                                <td>${lp.section.sectionName}</td>
                                <td>${lp.classHuman }</td>
                                <td>${lp.classRemark }</td>
                                <td>
                                    <a class="btn btn-warning btn-xs aId" data-toggle="tab" href="#menu2">编辑</a>
                                    <a class="btn btn-danger btn-xs " data-toggle="modal" data-target="#disable${v.index }">停用</a>
                                    <!-- 模态框（Modal） -->
                                    <div class="modal fade bs-example-modal-sm " id="disable${v.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="myModalLabel">停用班组</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <h3>确定需要停用此班组？</h3>
                                                </div>
                                                <div class="modal-footer">
                                                   <a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
                                                   <a class="btn btn-primary" href="${pageContext.request.contextPath}/classT.do?flag=stopClassT&id=${lp.classId }">确定</a>
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
                    <div class="modal-content" style="margin: auto 20%;">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">新增班组</h4>
                        </div>
                        <form  class="form-horizontal" 
                            method="post"  name="classTForm" 
                            action="${pageContext.request.contextPath}/classT.do?flag=addClassT" onSubmit="return fun()">
                        <div class="modal-body">
                                <div class="form-group">
                                    <label for="bumen-name" class="col-sm-3 control-label">班组名称</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="className" id="banzuName" placeholder="班组名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="charge" class="col-sm-3 control-label">班组负责人</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="classHuman" id="fuzeren" placeholder="班组负责人">
                                    </div>
                                </div>
                                <div class="form-group">
                                   <label for="shangjibumen" class="col-sm-3 control-label">上级部门</label>
                                   <div class="col-sm-5">
                                       <select class="form-control bumen" id="yanzhenBumen" name="section">
                                           <option style='display: none' value=''></option>
                                           
                                       </select>
                                   </div>
                             	</div>
                                <div class="form-group">
                                    <label for="beizhu" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="classRemark" id="buzhu" placeholder="备注">
                                    </div>
                                </div>
                        </div>
                        <div class="modal-footer">
                           	<a class="btn btn-default closeOk">关闭</a>
                            <input type="submit" class="btn btn-primary tijiao"  value="提交"/>
                        </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <div id="menu2" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 20%;">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">编辑班组</h4>
                        </div>
                         <form class="form-horizontal"  name="classTForm" 
                                method="post" action="${pageContext.request.contextPath}/classT.do?flag=updateClassT" onSubmit="return fun2()">
                        <div class="modal-body">
                        	 <input type="hidden" id="cdId" name="classId"/>             
                             <div class="form-group">
                                 <label for="bumen-name1" class="col-sm-3 control-label">班组名称</label>
                                 <div class="col-sm-5">
                                     <input type="text" class="form-control" name="className" id="banzuName1" placeholder="班组名称">
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label for="charge1" class="col-sm-3 control-label">班组负责人</label>
                                 <div class="col-sm-5">
                                     <input type="text" class="form-control" name="classHuman" id="fuzeren1" placeholder="班组负责人">
                                 </div>
                             </div>
                             <div class="form-group">
                                   <label for="shangjibumen" class="col-sm-3 control-label">上级部门</label>
                                   <div class="col-sm-5">
                                       <select class="form-control bumen" id="yanzhenBumen2" name="section">
                                           <option style='display: none' value=''></option>
                                           <option>建材类</option>
                                           <option>板材类</option>
                                           <option>型材类</option>
                                           <option>木材类</option>
                                       </select>
                                   </div>
                             </div>
                             <div class="form-group">
                                 <label for="beizhu1" class="col-sm-3 control-label">备注</label>
                                 <div class="col-sm-5">
                                     <input type="text" class="form-control" name="classRemark" id="buzhu1" placeholder="备注">
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
</body>

</html>
