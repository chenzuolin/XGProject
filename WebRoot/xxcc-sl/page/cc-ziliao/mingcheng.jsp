<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>layui</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" media="all">
      <link rel="stylesheet" href="../../plugin/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
   </head>

   <body>
      <div class="layui-fluid">
         <fieldset class="buttons">
            <legend>货物资料</legend>
            <form class="layui-form layui-form-pane" action="">
               <div class="layui-form-item" style="margin-bottom: 0;">
                  <div class="layui-inline">
                     <a class="layui-btn" id="ADD">
                       <i class="fa fa-plus" style="padding-right: 6px;"></i>
                       新增货物</a>
                  </div>
                  <div class="layui-inline" style="margin-left: 25rem;">
                     <label class="layui-form-label">
                       <i class="fa fa-search" style="padding-right: 6px;"></i>
                        货物资料</label>
                     <div class="layui-input-inline" style="width: 15rem;">
                        <input type="text" name="" class="layui-input" id="search" />
                     </div>
                  </div>
                  <div class="layui-inline">
                     <button class="layui-btn" lay-submit="" lay-filter="search" id="">立即提交</button>
                     <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                  </div>
               </div>
            </form>
         </fieldset>

         <!--表格内容-->
         <table id="demo" lay-filter="datatable"></table>
         <!--分页容器-->
         <div id="paging"></div>
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
         </script>
      </div>
      <!--编辑货物-->
      <div id="edit" style="display: none;">
         <form class="layui-form" action="">
            <input type="hidden" id="cargoid" name="cargoid" />
            <div class="layui-form-item">
               <label class="layui-form-label">货物名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="cargoName" lay-verify="required" class="layui-input" id="cargoName1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">助记符：</label>
               <div class="layui-input-block">
                  <input type="text" name="sign" lay-verify="required" class="layui-input" id="sign1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">货物类型：</label>
               <div class="layui-input-block">
                  <select name="pinlei" lay-verify="required" id="pinlei1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">规格：</label>
               <div class="layui-input-block">
                  <select name="guige" lay-verify="required" id="guige1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">材质：</label>
               <div class="layui-input-block">
                  <select name="caizhi" lay-verify="required" id="caizhi1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">属性：</label>
               <div class="layui-input-block">
                  <select name="shuxing" lay-verify="required" id="shuxing1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">产地：</label>
               <div class="layui-input-block">
                  <select name="chandi" lay-verify="required" id="chandi1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">计件单位：</label>
               <div class="layui-input-block">
                  <select name="danwei" lay-verify="required" id="danwei1" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">理论重量：</label>
               <div class="layui-input-block">
                  <input type="text" name="lilun1" class="layui-input" id="lilun1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注：</label>
               <div class="layui-input-block">
                  <input type="text" name="remark" class="layui-input" id="remark1">
               </div>
            </div>
            <div class="layui-form-item" style="display: none;">
               <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="formDemo1" id="formDemo1">立即提交</button>
               </div>
            </div>
         </form>
      </div>
      <!--添加货物名称-->
      <div id="add" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">货物名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="cargoName" lay-verify="required" class="layui-input" id="cargoName">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">助记符：</label>
               <div class="layui-input-block">
                  <input type="text" name="sign" lay-verify="required" class="layui-input" id="sign">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">货物类型：</label>
               <div class="layui-input-block">
                  <select name="pinlei" lay-verify="required" id="pinlei" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">规格：</label>
               <div class="layui-input-block">
                  <select name="guige" lay-verify="required" id="guige" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">材质：</label>
               <div class="layui-input-block">
                  <select name="caizhi" lay-verify="required" id="caizhi" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">属性：</label>
               <div class="layui-input-block">
                  <select name="shuxing" lay-verify="required" id="shuxing" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">产地：</label>
               <div class="layui-input-block">
                  <select name="chandi" lay-verify="required" id="chandi" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">计件单位：</label>
               <div class="layui-input-block">
                  <select name="danwei" lay-verify="required" id="danwei" lay-search>
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">理论重量：</label>
               <div class="layui-input-block">
                  <input type="text" name="lilun" class="layui-input" id="lilun">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注：</label>
               <div class="layui-input-block">
                  <input type="text" name="remark" class="layui-input" id="remark">
               </div>
            </div>
            <div class="layui-form-item" style="display: none;">
               <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="formDemo" id="formDemo">立即提交</button>
               </div>
            </div>
         </form>
      </div>
   </body>

   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js" charset="utf-8"></script>
   <script src="js/cargoDate.js"></script>
   <script>
      inputBg();
      layui.use(['table', 'form', 'laypage'], function() {
         var table = layui.table,
            form = layui.form,
            laypage = layui.laypage,
            $ = layui.jquery;
         //品类数据
         var pinlei = goodsContent('/XGProject/goodsCategory.do?flag=selectGoodsCategory', {}, 'json');
         $.each(JSON.parse(pinlei), function(i, obj) {
            $('#pinlei1').append('<option value="' + obj.id + '">' + obj.pinlei + '</option>');
            $('#pinlei').append('<option value="' + obj.id + '">' + obj.pinlei + '</option>');
         });
         //货物规格数据
         var guige = goodsContent('/XGProject/goodsStandard.do?flag=selectGoodsStandard', {}, 'json');
         $.each(JSON.parse(guige), function(i, obj) {
            $('#guige1').append('<option value="' + obj.id + '">' + obj.guige + '</option>');
            $('#guige').append('<option value="' + obj.id + '">' + obj.guige + '</option>');
         });
         //货物材质数据
         var caizhi = goodsContent('/XGProject/goodsQuality.do?flag=selectGoodsQuality', {}, 'json');
         $.each(JSON.parse(caizhi), function(i, obj) {
            $('#caizhi1').append('<option value="' + obj.id + '">' + obj.caizhi + '</option>');
            $('#caizhi').append('<option value="' + obj.id + '">' + obj.caizhi + '</option>');
         });
         //货物属性数据
         var shuxing = goodsContent('/XGProject/goodsProperty.do?flag=selectGoodsProperty', {}, 'json');
         $.each(JSON.parse(shuxing), function(i, obj) {
            $('#shuxing1').append('<option value="' + obj.id + '">' + obj.shuxing + '</option>');
            $('#shuxing').append('<option value="' + obj.id + '">' + obj.shuxing + '</option>');
         });
         //货物产地数据
         var chandi = goodsContent('/XGProject/goodsYieldly.do?flag=goAddgoodsYieldly', {}, 'json');
         $.each(JSON.parse(chandi), function(i, obj) {
            $('#chandi1').append('<option value="' + obj.id + '">' + obj.chandi + '</option>');
            $('#chandi').append('<option value="' + obj.id + '">' + obj.chandi + '</option>');
         });
         //货物计件单位数据
         var danwei = goodsContent('/XGProject/goodsUnit.do?flag=selectGoodsUnit', {}, 'json');
         $.each(JSON.parse(danwei), function(i, obj) {
            $('#danwei').append('<option value="' + obj.id + '">' + obj.danwei + '</option>');
            $('#danwei1').append('<option value="' + obj.id + '">' + obj.danwei + '</option>');
         });
         form.render('select');

         //监听表格工具条
         table.on('tool(datatable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            //停用货物方法
            if(layEvent === 'del') { //删除
               layer.confirm('确定停用此货物吗？', function(index) {
                  var stop = goodsContent("/XGProject/goods.do?flag=stopGoods", {
                     'id': data.id
                  }, 'text');
                  alert(stop);
                  if(stop == 'error') {
                     layer.alert('数据上传错误', {
                        icon: 2,
                        closeBtn: 2,
                        anim: 4,

                     })
                  } else {
                     if(stop.indexOf('停用成功') != -1) {
                        layer.alert("停用成功！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4
                        }, function(index) {
                           layer.close(index);
                           obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        });
                     } else {
                        layer.alert("停用失败！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4
                        });
                     }
                  }
                  layer.close(index);
                  //向服务端发送删除指令

               });
               //编辑货物方法
            } else if(layEvent === 'edit') {
               //获取对应行的数据并赋值
               $("#cargoid").val(data.id);
               $("#cargoName1").val(data.cargoName);
               $("#sign1").val(data.sign);
               $("#lilun1").val(data.lilunzhongliang);
               $("#remark1").val(data.remark);
               $("#pinlei1 option").each(function() {
                  if($(this).text() == data.pinlei) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               $("#guige1 option").each(function() {
                  if($(this).text() == data.guige) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               $("#caizhi1 option").each(function() {
                  if($(this).text() == data.caizhi) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               $("#shuxing1 option").each(function() {
                  if($(this).text() == data.shuxing) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               $("#chandi1 option").each(function() {
                  if($(this).text() == data.chandi) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               $("#danwei1 option").each(function() {
                  if($(this).text() == data.danwei) {
                     $(this).parent('select').val($(this).val());
                     return;
                  }
               });
               form.render('select');
               openindex = layer.open({
                  type: 1,
                  title: ['编辑货物名称', 'font-size:16px;'],
                  content: $('#edit'),
                  area: '550px',
                  closeBtn: 2,
                  anim: 4,
                  skin: 'layui-layer-blue',
                  btn: ['确定提交', '关闭'],
                  yes: function(index, layero) {
                     //按钮【按钮一】的回调
                     $('#formDemo1').click();

                  },

               });
            }
         });
         //货物资料查询的监听
         form.on('submit(search)', function(data) {
            showContent(1, 20);
            return false;
         })
         //编辑货物名称数据时第一验证、第二提交
         form.on('submit(formDemo1)', function(obj) {
            //用ajax的方式进行提交
            var datas = {
               'goodsId': $('#cargoid').val(),
               'goodsName': $('#cargoName1').val(),
               'goodsSign': $('#sign1').val(),
               'goodsCategoryId': $('#pinlei1').val(),
               'goodsStandardId': $('#guige1').val(),
               'goodsQualityId': $('#caizhi1').val(),
               'goodsPropertyId': $('#shuxing1').val(),
               'goodsYieldlyId': $('#chandi1').val(),
               'goodsUnitId': $('#danwei1').val(),
               'goodsAdjustment': $('#lilun1').val(),
               'goodsRemark': $('#remark1').val()
            };
            layer.confirm('确定编辑完成吗？', {
               iocn: 3,
               closeBtn: 2,
               anim: 3
            }, function() {
               var bianji = goodsContent("/XGProject/goods.do?flag=updateGoods", datas, 'text');
               var ok = bianji.indexOf('修改成功');
               if(ok != -1) {
                  layer.alert('修改成功', {
                     icon: 1,
                     closeBtn: 2
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert('修改失败', {
                     icon: 5,
                     closeBtn: 2

                  })
               }
            });
            return false;
         });
         //新增货物方法
         $('#ADD').click(function() {
            layer.open({
               type: 1,
               title: ['新增货物名称', 'font-size:16px;'],
               content: $('#add'),
               area: '550px',
               closeBtn: 2,
               anim: 4,
               skin: 'layui-layer-blue',
               btn: ['确定提交', '关闭'],
               yes: function(index, layero) {
                  //按钮【按钮一】的回调
                  $('#formDemo').click();
               },
            });

         });
         form.on('submit(formDemo)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            var datas = {
               'goodsName': data.cargoName,
               'goodsSign': data.sign,
               'goodsCategoryId': data.pinlei,
               'standardId': data.guige,
               'qualityId': data.caizhi,
               'propertyId': data.shuxing,
               'yieldlyId': data.chandi,
               'goodsUnitId': data.danwei,
               'goodsAdjustment': data.lilun,
               'goodsRemark': data.remark,
            };
            layer.confirm('确定添加货物吗？', {
               icon: 3,
               closeBtn: 2,
               anim: 4
            }, function() {
               var adds = goodsContent('/XGProject/goods.do?flag=addGoods', datas, 'text');
               if(adds == 'error') {
                  layer.alert('数据上传服务器错误！', {
                     icon: 2,
                     closeBtn: 2,
                     anim: 5,
                  });
                  return;
               }
               var ok = adds.indexOf('添加成功');
               if(ok != -1) {
                  layer.alert('添加货物成功', {
                     icon: 1,
                     closeBtn: 2
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert('添加货物失败', {
                     icon: 5,
                     closeBtn: 2

                  })
               }
            })

            return false;
         });

      });
      showContent(1, 20);

      function showContent(curr, pageRow) {
         layui.use(['table', 'laypage'], function() {
            var table = layui.table,
               laypage = layui.laypage,
               $ = layui.$;
            //从服务器获取数据，渲染表格数据
            var goodsdata = goodsContent('/XGProject/goods.do?flag=goSelectGoods', {
               'pageNow': curr,
               'pageRow': pageRow,
               'goodsNme':$('#search').val()
            }, 'json');
            if(goodsdata == 'error') {
               layer.alert('货物数据获取错误!', {
                  anim: 4,
                  closeBtn: 2,
                  icon: 2,
               })
            } else {
             var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度  
               table.render({
                  elem: '#demo',
                  height: 'full-180',
                  data: JSON.parse(goodsdata),
                  width:w,
                  cols: [
                     [ //标题栏
                        {
                           field: 'id',
                           title: '序号',
                           width: 60,
                           align: 'center'

                        }, {
                           field: 'cargoName',
                           title: '货物名称',
                           width: 150,
                           align: 'center'

                        }, {
                           field: 'sign',
                           title: '助记符',
                           width: 120,
                           align: 'center'

                        }, {
                           field: 'pinlei',
                           title: '货物类型',
                           width: 130,
                           align: 'center'

                        }, {
                           field: 'guige',
                           title: '规格',
                           width: 200,
                           align: 'center'

                        }, {
                           field: 'caizhi',
                           title: '材质',
                           width: 130,
                           align: 'center'

                        }, {
                           field: 'shuxing',
                           title: '属性',
                           width: 130,
                           align: 'center'

                        }, {
                           field: 'chandi',
                           title: '产地',
                           width: 130,
                           sort: true,
                           align: 'center'

                        }, {
                           field: 'danwei',
                           title: '计件单位',
                           width: 130,
                           align: 'center'

                        }, {
                           field: 'lilunzhongliang',
                           title: '理论重量',
                           width: 100,
                           align: 'center'

                        }, {
                           field: 'remark',
                           title: '备注',
                           width: 200,
                           align: 'center'

                        }, {
                           fixed: 'right',
                           width: 165,
                           align: 'center',
                           toolbar: '#barDemo'

                        }
                     ]
                  ],
                  initSort: {
                     field: 'id', //排序字段，对应 cols 设定的各字段名
                     type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
                  },
                  even: true,
                  limit: 1000000
               });
               if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
                  //执行一个laypage实例
                  laypage.render({
                     elem: 'paging', //注意，这里的 test1 是 ID，不用加 # 号
                     count: JSON.parse(goodsdata)[0].pageCount * pageRow,
                     limit: pageRow,
                     layout: ['prev', 'page', 'next', 'skip', 'limit'],
                     limits: [20, 30, 50, 100],
                     jump: function(obj, first) {
                        if(!first) {
                           showContent(obj.curr, obj.limit);
                        }
                     }
                  });
               }

            }
         });

      }
   </script>

</html>