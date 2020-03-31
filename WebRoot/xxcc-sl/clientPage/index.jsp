<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

   <head>
   	<base href="<%=basePath%>">
      <meta charset="utf-8">
      <title>仓储管理系统</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta http-equiv="Access-Control-Allow-Origin" content="*">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <meta name="apple-mobile-web-app-status-bar-style" content="black">
      <meta name="apple-mobile-web-app-capable" content="yes">
      <meta name="format-detection" content="telephone=no">
      <link rel="icon" href="favicon.ico">
      <link rel="stylesheet" href="xxcc-sl/plugin/layui/css/layui.css">
      <link rel="stylesheet" href="xxcc-sl/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="xxcc-sl/css/index.css" />
      <link rel="stylesheet" href="xxcc-sl/css/public.css" />
      <style>
         #leftnav li a i {
            color: #1E9FFF;
            margin-right: 15px;
         }
         
         #leftnav cite {
            font-weight: 540;
         }
         
         #leftnav dd {
            padding-left: 15px;
         }
         
         #shouye i:last-child {
            display: none;
         }
         
         .marg0 {
            margin: 0;
         }
         
         .layui-body {
            bottom: 0 !important;
         }
         
         .full {
            position: fixed;
            align-content: center;
            width: 100%;
            height: 100%;
            overflow: auto;
         }
         
         .logo {
            color: #fff;
            float: left;
            line-height: 60px;
            font-size: 17px;
            padding: 0 25px;
            text-align: center;
            width: 150px;
         }
         
         .hideMenu {
            float: left;
            margin: 15px 15px 0 0;
            font-size: 17px;
            text-align: center;
            padding: 3px 5px;
            color: #fff;
            background-color: #1AA094;
         }
         
         .hideMenu:hover {
            color: #fff;
         }
         
         .weather {
            color: #fff;
            float: left;
            margin: 15px 0 0 50px;
         }
         
         .time {
            float: left;
            margin: 15px 15px 0 0;
            font-size: 14px;
            text-align: center;
            padding: 4px 5px;
            color: #ffffff;
         }
      </style>
   </head>

   <body class="layui-layout-body" id="cont">
      <div class="layui-layout layui-layout-admin">
         <!--头部内容-->
         <div class="layui-header header">
            <div class='layui-layou-left'>
               <a href="javascript:;" class="logo">鑫港库存管理系统</a>
               <i class="layui-icon layui-btn  layui-btn-small hideMenu">&#xe65a;</i>
               <!-- 天气信息 -->
               <div class="weather">
                  <div id="tp-weather-widget"></div>
                  <script>
                     (function(T, h, i, n, k, P, a, g, e) {
                        g = function() {
                           P = h.createElement(i);
                           a = h.getElementsByTagName(i)[0];
                           P.src = k;
                           P.charset = "utf-8";
                           P.async = 1;
                           a.parentNode.insertBefore(P, a)
                        };
                        T["ThinkPageWeatherWidgetObject"] = n;
                        T[n] || (T[n] = function() {
                           (T[n].q = T[n].q || []).push(arguments)
                        });
                        T[n].l = +new Date();
                        if(T.attachEvent) {
                           T.attachEvent("onload", g)
                        } else {
                           T.addEventListener("load", g, false)
                        }
                     }(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))
                  </script>
                  <script>
                     tpwidget("init", {
                        "flavor": "slim",
                        "location": "WX4FBXXFKE4F",
                        "geolocation": "enabled",
                        "language": "zh-chs",
                        "unit": "c",
                        "theme": "chameleon",
                        "container": "tp-weather-widget",
                        "bubble": "disabled",
                        "alarmType": "badge",
                        "color": "#FFFFFF",
                        "uid": "U9EC08A15F",
                        "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                     });
                     tpwidget("show");
                  </script>

               </div>
               <div class="time" id="now_date"></div>
            </div>
            <ul class="layui-nav  layui-layout-right top_menu">
               <li class="layui-nav-item lockcms" pc>
                  <a href="javascript:;">
                     <i class="fa fa-times-circle" aria-hidden="true" style="font-size: 1.2rem;"></i>&nbsp;
                     <cite>退出</cite>
                  </a>
               </li>
            </ul>
         </div>
         <!--左侧菜单-->
         <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll" id="leftnav">
               <div style=" width:195px; height:130px;text-align: center; margin-top: 10px; cursor: pointer;" title="点击查看个人资料" id="geren">
                  <img id="touxiang" src="xxcc-sl/img/man.png" style="border: 5px solid #009688;" class="layui-circle" width="80" height="80">
                  <p style="margin-top: 10px; overflow-x: hidden;">你好！<span style="color: #01AAED;"><%=request.getSession().getAttribute("loginName")%></span>, 欢迎登录</p>
               </div>
               <ul class="layui-nav layui-nav-tree" lay-filter="demo">
               </ul>
               <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            </div>
         </div>
         <!--内容区-->
         <div class="layui-body layui-form">
            <div class="layui-tab  marg0" lay-filter="bodyTab" lay-allowClose="true">
               <ul class="layui-tab-title  topCard">
                  <li class="layui-this" lay-id='' id="shouye">
                     <i class="fa fa-align-justify"></i>
                     <cite>后台首页</cite></li>
               </ul>
               <ul class="layui-nav closeBox layui-layout-right">
                  <li class="layui-nav-item">
                     <a href="javascript:;"><i class="fa fa-hand-pointer-o"></i><cite>页面操作</cite></a>
                     <dl class="layui-nav-child">
                        <dd>
                           <a href="javascript:;" class="otherpage"><i class="fa fa-times"></i><cite>关闭其他</cite></a>
                        </dd>
                        <dd>
                           <a href="javascript:;" class="allpage"><i class="fa fa-times-circle"></i><cite>关闭全部</cite></a>
                        </dd>
                     </dl>
                  </li>
               </ul>
               <div class="layui-tab-content frameWindow">
                  <div class="layui-tab-item layui-show">
                     <iframe src="xxcc-sl/clientPage/xiTongSheZhi/keHuXinXi_XiangQing.jsp"></iframe>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script src="xxcc-sl/plugin/layui/layui.js"></script>
      <script src="xxcc-sl/datas/clientNavs.js"></script>
      <script src="xxcc-sl/js/left-nav.js"></script>
      <script src="xxcc-sl/js/tab.js"></script>

      <script src='xxcc-sl/js/index.js'></script>
      <script>
         layui.use(['jquery', 'layer', 'element'], function() {
            var $ = layui.jquery,
               layer = layui.layer,
               element = layui.element;
            //当单机个人资料的时候触发
            $("#geren").click(function() {
               var tabIndex = false;
               $(".layui-tab-title.topCard li").each(function() {
                  if($(this).find('cite').text() == '用户资料') {
                     $(this).click();
                     //如果页面已经打开，切换到相应的页面，并且跳出遍历循环。
                     tabIndex = true;
                     return false;
                  }
               });
               if(tabIndex) {
                  return false;
               }
               var url = "/XGProject/xxcc-sl/clientPage/xiTongSheZhi/keHuXinXi_XiangQing.jsp";
               var ids = new Date().getTime();
               element.tabAdd('bodyTab', {
                  title: '<i class="layui-icon">&#xe612;</i><cite>用户资料</cite>',
                  content: "<iframe src='" + url + "' data-id=''></frame>",
                  id: ids
               });
               $(this).click();
            });
         });

         function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var day = date.getDay();
            if(month >= 1 && month <= 9) {
               month = "0" + month;
            }
            if(strDate >= 0 && strDate <= 9) {
               strDate = "0" + strDate;
            }

            var shi = date.getHours();
            if(parseInt(date.getHours()) < 10) {
               shi = "0" + date.getHours();
            }
            var fen = date.getMinutes();
            if(parseInt(date.getMinutes()) < 10) {
               fen = "0" + date.getMinutes();
            }
            var miao = date.getSeconds();
            if(parseInt(date.getSeconds()) < 10) {
               miao = "0" + date.getSeconds();
            }

            var currentdate = date.getFullYear() + "年" + month + "月" + strDate + "日 " +
               shi + "时" + fen + "分" + miao + "秒";

            var days = "";
            switch(day) {
               case 1:
                  days = "星期一";
                  break;
               case 2:
                  days = "星期二";
                  break;
               case 3:
                  days = "星期三";
                  break;
               case 4:
                  days = "星期四";
                  break;
               case 5:
                  days = "星期五";
                  break;
               case 6:
                  days = "星期六";
                  break;
               case 0:
                  days = "星期日";
                  break;
            }
            document.getElementById("now_date").innerHTML = currentdate + " " + days;
         }

         setInterval("getNowFormatDate()", 1000);
      </script>

   </body>

</html>