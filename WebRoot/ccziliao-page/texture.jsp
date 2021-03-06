﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../css/bootstrap.css" />
    <link rel="stylesheet" href="../Css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../css/public.css" />
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../Js/bootstrap-datetimepicker.js"></script>
    <script src="../Js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>

<body>
    <!--内容起始处-->
    <div id="mainFrame" class="main">
        <div class="daohang">
            <ul><i>当前位置：</i> 
                <a style="cursor:pointer;">仓储资料</a> <span>></span>&nbsp;
                <a style="cursor:pointer;">货物材质</a> <span>></span>&nbsp;
            </ul>
        </div>
        <div class="container">
            <ul class="nav nav-tabs nav-pills" style="margin: 10px 2.5%; font-weight: bold;">
                <li class="active"><a data-toggle="tab" href="#home">货物材质列表</a></li>
                <li><a data-toggle="tab" href="#menu1">
                <span class="glyphicon glyphicon-th-list" aria-hidden="true" style="margin-right: 8px;">新增货物材质</a></li>
                <li><a data-toggle="tab" href="#menu2">
                <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;">编辑货物材质</a></li>
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
                            <tr>
                                <td>1</td>
                                <td>张三</td>
                                <td>答复</td>
                                <td>打发打发</td>
                                <td>
                                    <button class="btn btn-warning btn-xs"><a data-toggle="tab" href="#menu2">编辑</a></button>
                                    <button class="btn btn-danger btn-xs " data-toggle="modal" data-target="#disable">停用</button>
                                    <!-- 模态框（Modal） -->
                                    <div class="modal fade bs-example-modal-sm " id="disable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">
                    停用货物材质
                </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <h3>确定需要停用此货物材质？</h3>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                                    </button>
                                                    <button type="button" class="btn btn-primary">
                                                        提交更改
                                                    </button>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal -->
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>dsf</td>
                                <td>adsf</td>
                                <td>ads</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 新增货物材质 -->
                <div id="menu1" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">新增货物材质</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="zhanghao" class="col-sm-3 control-label">材质名称</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="帐号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inpassword" class="col-sm-3 control-label">描述</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control" id="inpassword">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="name">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-primary">
                                提交更改
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- 编辑货物材质 -->
                <div id="menu2" class="tab-pane fade">
                    <div class="modal-content" style="margin: auto 30%;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">编辑货物材质</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="zhanghao" class="col-sm-3 control-label">材质名称</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="帐号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inpassword" class="col-sm-3 control-label">描述</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control" id="inpassword">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="name">
                                    </div>
                                </div>
                            </form>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-primary">
                                提交更改
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
            </div>
        </div>
    </div>
</body>

</html>
