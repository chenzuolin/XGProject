<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>仓储管理系统-新增入库</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" />
      <style type="text/css">

      </style>
   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset>
               <legend>客户信息</legend>
               <div class="layui-form-item">
                  <label class="layui-form-label">客户名称</label>
                  <div class="layui-input-block" style="width: 30%;">
                     <select name="clientIn" lay-verify="required" id="client" lay-filter="client" lay-search>
                     </select>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">客户单号</label>
                     <div class="layui-input-block">
                        <input type="text" name="clientNum" class="layui-input" lay-verify="required">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">订单有效期</label>
                     <div class="layui-input-block">
                        <input type="text" name="indate" class="layui-input" lay-verify="required">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">运输方式</label>
                     <div class="layui-input-block">
                        <select name="transport" lay-verify="required">
                           <option value="汽运">汽运</option>
                           <option value="火运">火运</option>
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">车号</label>
                     <div class="layui-input-block">
                        <input type="text" name="truckNum" lay-verify="required" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">司机姓名</label>
                     <div class="layui-input-block">
                        <input type="text" name="d-name" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">司机电话</label>
                     <div class="layui-input-block">
                        <input type="text" name="d-tel" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <!--货物资料-->
            <fieldset>
               <legend>货物信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物品类</label>
                     <div class="layui-input-block">
                        <select name="cargoPinlei" lay-verify="required" id="cargoPinlei" lay-filter="cargoPinlei">
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物名称</label>
                     <div class="layui-input-block">
                        <select name="cargoName" lay-verify="required" id="cargoName" lay-filter="cargoName" lay-search>
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物规格</label>
                     <div class="layui-input-block">
                        <select name="cargoGuige" lay-verify="required" id="cargoGuige" lay-filter="cargoGuige" lay-search>
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物材质</label>
                     <div class="layui-input-block">
                        <select name="cargoCaizhi" lay-verify="required" id="cargoCaizhi" lay-filter="cargoCaizhi" lay-search>
                        </select>
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物属性</label>
                     <div class="layui-input-block">
                        <select name="cargoShuxing" lay-verify="required" id="cargoShuxing" lay-filter="cargoShuxing" lay-search>
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物产地</label>
                     <div class="layui-input-block">
                        <select name="cargoChandi" lay-verify="required" id="cargoChandi" lay-filter="cargoChandi" lay-search>
                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">货物重量</label>
                     <div class="layui-input-block">
                        <input type="text" name="cargoWeight" lay-verify="required|number" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
                     <label class="layui-form-label">备注</label>
                     <div class="layui-input-block">
                        <input type="text" name="remark" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <div class="layui-form-item">
               <div class="layui-input-block">
                  <div class="layui-layout-right" style="margin-top: 20px;">
                     <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                     <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
   </body>
   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js"></script>
   <script>
      inputBg();
      client('#client');

      layui.use(['form', 'jquery', 'layer'], function() {
         var $ = layui.jquery;
         var form = layui.form;
         var layer = layui.layer;

         RuKuPinLei('#cargoPinlei');
         //更新货物信息
         form.on('select(cargoPinlei)', function(data) {
            RuKugoodsName('#cargoName', data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoName)', function(data) {
            RuKuGuiGe('#cargoGuige', $('#cargoPinlei').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoGuige)', function(data) {
            RuKuCaiZhi('#cargoCaizhi', $('#cargoPinlei').val(), $('#cargoName').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoCaizhi)', function(data) {
            RuKuShuXing('#cargoShuxing', $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoShuxing)', function(data) {
            RuKuChanDi('#cargoChandi', $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('submit(demo1)', function(obj) {
            tijiao(obj.field);
            return false;

         })
      });

      function tijiao(data) {
         layui.use(['jquery', 'form', 'layer'], function() {
            var $ = layui.jquery;
            var form = layui.form;
            var layer = layui.layer;
            alert(JSON.stringify(data));
            layer.confirm("确定提交吗？", {
               icon: 3,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            }, function() {
               $.ajax({
                  type: "post",
                  url: "/XGProject/input.do?flag=planInput",
                  async: false,
                  data: {
                     "client": data.clientIn, //对应客户
                     "inputClientNumber": data.clientNum, //客户单号
                     "inputDefinedOne": data.indate, //订单有效期
                     "inputCarryType": data.transport, //运输方式
                     //                   "exportRemark": data.peisong, //是否配送
                     "inputPlateNumber": data.truckNum, //车号
                     "inputDriverName": data.driverName, //司机姓名
                     "inputDriverTel": data.driverTel, //司机电话
                     "goodss": data.cargoChandi, //货物
                     "iseedShouldWeights": data.cargoWeight, //重量
                     "iseedRemarks": data.remark //备注
                  },
                  dataType: "text",
                  success: function(data) {
                     var ok = data.indexOf("计划入库成功");
                     if(ok != -1) {
                        layer.alert("入库发起成功！", {
                           icon: 1,
                           anim: 4,
                           closeBtn: 2,
                           title: ['系统提示', 'font-size:16px;'],
                           skin: 'layui-layer-blue',
                        });
                        cargoChandi('#cargoChandi', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), $("#cargoShuxing").val());
                        form.render('select'); //刷新select选择框渲染
                     } else {
                        layer.alert("入库发起失败！", {
                           icon: 5,
                           anim: 4,
                           closeBtn: 2,
                           title: ['系统提示', 'font-size:16px;'],
                           skin: 'layui-layer-blue',
                        });
                     }
                  },
                  error: function() {
                     layer.alert("数据上传错误！", {
                        icon: 2,
                        anim: 4,
                        closeBtn: 2,
                        title: ['系统提示', 'font-size:16px;'],
                        skin: 'layui-layer-blue',
                     });
                  }
               });
            });
         });
      }
   </script>

</html>