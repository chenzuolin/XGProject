<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>仓储管理系统-客户信息详情</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
      <style>
         #bianji {
            text-align: right;
            padding: 10px;
            position: relative;
            display: none;
            margin: 0 5%;
         }
      </style>
   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" id="showC">
            <input type="hidden" value="${client.clientId }" name="clientId" class="layui-input" />
            <fieldset>
               <legend>客户信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">登录名</label>
                     <div class="layui-input-block">
                        <input type="text" value="${client.clientLoginName }" name="loginName" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">负责人</label>
                     <div class="layui-input-block">
                        <input type="text" name="fuzeren" value="${client.clientHuman }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">邮箱</label>
                     <div class="layui-input-block">
                        <input type="text" name="email" value="${client.clientEmail }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">联系电话</label>
                     <div class="layui-input-block">
                        <input type="text" name="tel" value="${client.clientTel }" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <br />
            <hr />
            <fieldset>
               <legend>合同信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">注册时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetonghao" value="${client.clientCreateTime }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">合同号</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetonghao" value="${client.clientContract }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">合同起始日期</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetongBegin" value="${client.clientStartTime }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">合同结束日期</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetongFinish" value="${client.clientFinishTime }" class="layui-input">
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">折扣</label>
                     <div class="layui-input-block">
                        <input type="text" name="zhekou" value="${client.clientAgio }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">信用度</label>
                     <div class="layui-input-block">
                        <input type="text" name="zhekou" value="${client.clientCredit }" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <br />
            <hr />
            <fieldset>
               <legend>企业信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">单位名称</label>
                     <div class="layui-input-block">
                        <input type="text" name="mingcheng" value="${client.clientFirmName }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">单位简称</label>
                     <div class="layui-input-block">
                        <input type="text" name="jiancheng" value="${client.clientAbbreviation }" class="layui-input">
                     </div>
                  </div>

                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">单位助记符</label>
                     <div class="layui-input-block">
                        <input type="text" name="zhujifu" value="${client.clientSign }" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">单位地址</label>
                     <div class="layui-input-block">
                        <input type="text" name="address" value="${client.clientAddress }" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <br />
            <div>
               <div class="layui-inline" style="width: 100%; text-align: left;">
                  <button class="layui-btn" lay-submit="" lay-filter="formDemo" id="query">编辑</button>
                 <!--  <a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color:black;">返回</a> -->
               </div>
            </div>
         </form>
      </div>

      <!--编辑弹出窗口-->
      <div id="bianji">
         <form class="layui-form layui-form-pane" id="bianjiForm">
            <input type="hidden" name="clientId" value="" id="clientId" />
            <div class="layui-form-item">
               <label class="layui-form-label">负责人</label>
               <div class="layui-input-block">
                  <input type="text" name="clientHuman" id="fuzeren" class="layui-input" layui-verify="required" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">邮箱</label>
               <div class="layui-input-block">
                  <input type="text" name="clientAbbreviation" id="email" class="layui-input" layui-verify="email" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">联系电话</label>
               <div class="layui-input-block">
                  <input type="text" name="clientSign" id="tel" class="layui-input" layui-verify="phone" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">单位地址</label>
               <div class="layui-input-block">
                  <input type="text" name="clientSign" id="address" class="layui-input" layui-verify="required" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">验证码</label>
               <div class="layui-input-inline">
                  <input type="text" name="clientSign" id="zhujifu" class="layui-input" layui-verify="number" />
               </div>
               <a class="layui-btn" id="code" style="float: right;">获取验证码</a>
            </div>
         </form>
         <hr class="layui-bg-orange" />
      </div>
   </body>

   <!--layui js-->
   <script src="/XGProject/xxcc-sl/plugin/layui/layui.js"></script>
   <script src="/XGProject/xxcc-sl/js/call-client.js"></script>
   <script>
      inputBg();
      layui.use(['jquery', 'form', 'layer', 'laydate'], function() {
         var $ = layui.jquery;
         var form = layui.form;
         var layer = layui.layer;
         var laydate = layui.laydate;

         laydate.render({
            elem: '#begin' //指定元素，开始时间
         });
         laydate.render({
            elem: '#finish' //指定元素，结束时间
         });
         //将显示内容中的文本框设置为只读
         $("#showC input").attr("readonly", 'readonly');

         form.on('submit(formDemo)', function(data) {
            var obj = data.field;
            $("#clientId").val(obj.clientId);
            $("#fuzeren").val(obj.fuzeren);
            $("#email").val(obj.email);
            $("#tel").val(obj.tel);
            $("#address").val(obj.address);

            layer.open({
               type: 1,
               title: ['编辑客户信息', 'font-size:16px;'],
               skin: 'layui-layer-blue',
               closeBtn: 2,
               anim: 4,
               content: $('#bianji'),
               area: ['500px', '400px'],
               btn: ['立即提交', '关闭'],
               yes: function(index, obj) {
                  //点击立交提交的时候触发
                  var fuzeren = $("#fuzeren").val();
                  var email = $("#email").val();
                  var tel = $("#tel").val();
                  var address = $("#address").val();
                  var code = $('#code').val();

                  layer.confirm("确定提交吗？", {
                     icon: 3,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  }, function(index) {
                     $.ajax({
                        type: "post",
                        url: "/XGProject/client.do?flag=updateClient",
                        data: {
                           "clientId": clientId,
                           "clientHuman": fuzeren,
                           "clientEmail": email,
                           "clientTel": tel,
                           "clientAddress": address,
                           "": code,
                        },
                        async: false,
                        dataType: "text",
                        success: function(data) {
                           alert(data);
                           var ok = data.indexOf("修改成功！");
                           if(ok != -1) {
                              layer.alert("修改成功！", {
                                 icon: 1,
                                 closeBtn: 2,
                                 anim: 4,
                                 title: ['系统提示', 'font-size:16px;'],
                                 skin: 'layui-layer-blue',
                              }, function(index) {
                                 document.location.href = "/XGProject/xxcc-sl/page/client/keHuXinXi.jsp"
                                 layer.close(index);
                              });
                           } else {
                              layer.alert("修改失败！", {
                                 icon: 1,
                                 closeBtn: 2,
                                 anim: 4,
                                 title: ['系统提示', 'font-size:16px;'],
                                 skin: 'layui-layer-blue',
                              });
                           }
                        },
                        error: function() {
                           layer.alert("数据上传错误，修改失败！", {
                              icon: 2,
                              closeBtn: 2,
                              anim: 4,
                              title: ['系统提示', 'font-size:16px;'],
                              skin: 'layui-layer-blue',
                           });
                        }
                     });
                     layer.close(index);
                  });
               },
               btn2: function(index, obj) {
                  //点击关闭的时候触发
                  layer.close(index);
               }
            });
            return false;
         });
      });
   </script>

</html>