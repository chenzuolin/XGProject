<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>仓储管理系统-角色权限</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="../../css/public.css" />
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>

      <!--自写js-->
      <script src="js/jiaoSeQuanXian.js"></script>

      <style>
         /*修改打开容器样式*/
         #updateOpen,
         #insertOpen {
            width: 880px;
            height: 580px;
            text-align: right;
            padding: 10px;
            position: relative;
            display: none;
            overflow-x: hidden;
         }
      </style>
   </head>

   <body>
      <div class="layui-fluid">
         <fieldset>
            <legend>系统日志</legend>
            <a href="javascript:;" class="layui-btn" id="insert"> <i class="layui-icon">&#xe608;</i> 添加职务</a>
         </fieldset>
         <!--显示内容容器-->
         <table id="showContent" lay-filter="table" style="display: none;"></table>
         <!--table导航工具条的demo-->
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe642;</span>编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del"><span class="layui-icon">&#xe640;</span>停用</a>
         </script>
          <hr class="layui-bg-red" />
      </div>
      <!--修改打开容器-->
      <div id="updateOpen">
         <form class="layui-form layui-form-pane">
            <input type="hidden" name="zhiwuid" value="" id="zhiwuid" />
            <div class="layui-form-item">
               <label class="layui-form-label">职务名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="zhiwuname" id="zhiwuname" placeholder="请输入职务名称" class="layui-input" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">职务描述：</label>
               <div class="layui-input-block">
                  <input type="text" name="zhiwumiaoshu" id="zhiwumiaoshu" placeholder="请输入职务描述" class="layui-input" />
               </div>
            </div>

            <hr class="layui-bg-orange" />

            <!--职务选项卡-->
            <div class="layui-tab">
               <ul class="layui-tab-title">
                  <li class="layui-this">系统管理</li>
                  <li>客户管理</li>
                  <li>数据中心</li>
                  <li>财务管理</li>
                  <li>仓储资料</li>
                  <li>仓储业务</li>
                  <li>首页权限</li>
               </ul>
               <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">
                     <table id="xitongguanli" lay-filter="xitongguanli">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200,height:50}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="kehuguanli" lay-filter="kehuguanli">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="shujuzhongxin" lay-filter="shujuzhongxin">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="caiwuguanli" lay-filter="caiwuguanli">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="cangchuziliao" lay-filter="cangchuziliao">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="cangchuyewu" lay-filter="cangchuyewu">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="shouyequanxian" lay-filter="shouyequanxian">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
               </div>
            </div>
         </form>
      </div>

      <!--添加打开容器-->
      <div id="insertOpen">
         <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
               <label class="layui-form-label">职务名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="zhiwuname" id="zhiwunameIn" placeholder="请输入职务名称" class="layui-input" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">职务描述：</label>
               <div class="layui-input-block">
                  <input type="text" name="zhiwumiaoshu2" id="zhiwumiaoshu2" placeholder="请输入职务描述" class="layui-input" />
               </div>
            </div>

            <hr class="layui-bg-orange" />

            <!--职务选项卡-->
            <div class="layui-tab">
               <ul class="layui-tab-title">
                  <li class="layui-this">系统管理</li>
                  <li>客户管理</li>
                  <li>数据中心</li>
                  <li>财务管理</li>
                  <li>仓储资料</li>
                  <li>仓储业务</li>
                  <li>首页权限</li>
               </ul>
               <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">
                     <table id="xitongguanli2" lay-filter="xitongguanli2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200,height:50}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="kehuguanli2" lay-filter="kehuguanli2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="shujuzhongxin2" lay-filter="shujuzhongxin2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="caiwuguanli2" lay-filter="caiwuguanli2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="cangchuziliao2" lay-filter="cangchuziliao2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="cangchuyewu2" lay-filter="cangchuyewu2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
                  <div class="layui-tab-item">
                     <table id="shouyequanxian2" lay-filter="shouyequanxian2">
                        <thead>
                           <tr>
                              <th lay-data="{field:'name', width:200}">权限名称</th>
                              <th lay-data="{field:'miaoshu', width:530, sort:false}">权限描述</th>
                           </tr>
                        </thead>
                        <tbody></tbody>
                     </table>
                  </div>
               </div>
            </div>
         </form>
      </div>
   </body>

</html>