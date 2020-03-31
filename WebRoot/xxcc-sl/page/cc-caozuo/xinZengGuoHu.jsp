<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>仓储管理系统-新增过户</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" />
   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset>
               <legend>客户信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">转出客户</label>
                     <div class="layui-input-block">
                        <select name="clientOut" lay-verify="required" id="clientOut" lay-filter="clientOut" lay-search>

                        </select>
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">转入客户</label>
                     <div class="layui-input-block">
                        <select name="clientIn" lay-verify="required" id="clientIn" lay-search>

                        </select>
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">客户单号</label>
                     <div class="layui-input-block">
                        <input type="text" name="clientNum" lay-verify="required" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">订单类型</label>
                     <div class="layui-input-block">
                        <select name="shiftType" lay-verify="">
                           <option value="正常转库" selected="">正常转库</option>
                           <option value="直提转库">直提转库</option>
                           <option value="其他转库">其他转库</option>
                        </select>
                     </div>
                  </div>
               </div>
            </fieldset>
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
            <select id="weight" lay-ignore style="display: none; opacity: 0;"></select>
         </form>
      </div>
      <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
      <script src="../../js/call-client.js" charset="utf-8"></script>
      <%
		if (request.getSession().getAttribute("loginName") == null
			|| request.getSession().getAttribute("iulist") == null
			|| request.getSession().getAttribute("power") == null) {
		%>
      <script type="text/javascript">
         layui.use('jquery', function() {
            var $ = layui.jquery;
            $("body").append("<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
         });
      </script>
      <%
		}
		%>
      <%
			int x = 0;
			int chaofa = 0;
								
			List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
			for(int i=0; i<power.size(); i++){
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增过户")){
					x++;
				}
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("无库存超发")){
					chaofa++;
				}
			}
		if(x==0){
		%>
      <script type="text/javascript">
         layui.use('jquery', function() {
            var $ = layui.jquery;
            $("body").append("<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
         });
      </script>
      <%
		}
		%>
      <input type="hidden" value="<%=chaofa%>" id="wukucun_chaofa" />
   </body>
   <script>
      inputBg();
      client('#clientIn');
      client('#clientOut');

      layui.use(['jquery', 'form', 'layer'], function() {
         var $ = layui.jquery;
         var form = layui.form;
         var layer = layui.layer;
         //更新货物信息
         form.on('select(clientOut)', function(data) {
            cargoPinlei('#cargoPinlei', data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoPinlei)', function(data) {
            cargoName('#cargoName', $('#clientOut').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoName)', function(data) {
            cargoGuige('#cargoGuige', $('#clientOut').val(), $('#cargoPinlei').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoGuige)', function(data) {
            cargoCaizhi('#cargoCaizhi', $('#clientOut').val(), $('#cargoPinlei').val(), $('#cargoName').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoCaizhi)', function(data) {
            cargoShuxing('#cargoShuxing', $('#clientOut').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });
         form.on('select(cargoShuxing)', function(data) {
            cargoChandi('#cargoChandi', $('#clientOut').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), data.value);
            form.render('select'); //刷新select选择框渲染
         });

         //当单机提交的时候，用ajax的方式进行提交
         form.on('submit(demo1)', function(obj) {
            var data = obj.field;
            //判断是否具有无库存超发权限
            var chaofa = $("#wukucun_chaofa").val();
            //如果不具有权限的时候，不能超发
            if(chaofa == "0") {
               var yes = false;
               $("#weight option").each(function() {
                  if($(this).val() == data.cargoChandi) {
                     if(parseFloat(data.cargoWeight) > parseFloat($(this).text())) {
                        yes = true;
                     }
                     return;
                  }
               });
               if(yes) {
                  layer.alert("过户重量不能大于库存重量！", {
                     icon: 2,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  });
                  return false;
               }
            } else {
               var yes = false;
               $("#weight option").each(function() {
                  if($(this).val() == data.cargoChandi) {
                     if(parseFloat(data.cargoWeight) > parseFloat($(this).text())) {
                        yes = true;
                     }
                     return;
                  }
               });
               if(yes) {
                  layer.confirm("客户现有库存量小于过户重量，是否超发？", {
                     icon: 3,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                     btn: ['是', '否'],
                     yes: function(index, layero) {
                        layer.close(index);
                        tijiao(data);
                     },
                     btn2: function(index, layero) {
                        layer.close(index);
                     }
                  });
                  return false;
               }
            }
            tijiao(data);
            return false;
         });
      });

      function tijiao(data) {
         layui.use(['jquery', 'form', 'layer'], function() {
            var $ = layui.jquery;
            var form = layui.form;
            var layer = layui.layer;

            layer.confirm("确定提交吗？", {
               icon: 3,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            }, function() {
               $.ajax({
                  type: "post",
                  url: "/XGProject/shiftStock.do?flag=saveShiftStock",
                  async: false,
                  data: {
                     "clientBySstockClientId": data.clientOut, //对应转出客户
                     "clientBySstockShiftToFirm": data.clientIn, //对应转入客户
                     "sstockClientNumber": data.clientNum, //客户单号
                     "sstockDefinedOne": data.shiftType, //转库类型
                     "goodss": data.cargoChandi, //货物
                     "weight": data.cargoWeight, //重量
                     "remark": data.remark, //备注
                  },
                  dataType: "text",
                  success: function(data) {
                     var ok = data.indexOf("提交成功");
                     if(ok != -1) {
                        layer.alert("过户发起成功！", {
                           icon: 1,
                           anim: 4,
                           closeBtn: 2,
                           title: ['系统提示', 'font-size:16px;'],
                           skin: 'layui-layer-blue',
                        });
                        cargoChandi('#cargoChandi', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), $("#cargoShuxing").val());
                        form.render('select'); //刷新select选择框渲染
                     } else {
                        layer.alert("过户发起失败！", {
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