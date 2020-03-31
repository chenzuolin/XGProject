<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>仓储管理系统-订单待处理</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" />

   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset class="buttons">
               <legend>配送订单</legend>
               <div class="layui-form-item" style="margin-bottom: 0;">
                  <div class="layui-inline">
                     <label class="layui-form-label">业务类型</label>
                     <div class="layui-input-inline">
                        <select name="city" lay-search>
                           <option value="010" selected>出库订单</option>
                           <option value="021">入库订单</option>
                        </select>
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">客户名称</label>
                     <div class="layui-input-inline" style="width: 15rem;">
                        <select name="client" id="client" lay-search>
                        	<option value="">请选择客户</option>
                        </select>
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">客户单号</label>
                     <div class="layui-input-block">
                        <input type="text" name="number" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-inline" style="right:-100px;">
                     <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                     <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                  </div>
               </div>
            </fieldset>
         </form>
         <table id="demo"></table>
      </div>
      <script type="text/html" id="barDemo">
         <a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看</a>
         <!--<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="edit"><span   class="layui-icon">&#xe631;</span>操作</a>-->
         <!--<a class="layui-btn layui-btn-danger layui-btn-small" lay-event="del">删除</a>-->
      </script>
   </body>
   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js" charset="utf-8"></script>
   <script>
      client('#client');
      var huowu; //定义货物全局变量
      var chukuZid; //定义出库订单子订单编号全局变量
      var rukuZid; //定义入库子订单编号全局变量
      layui.use(['table', 'form'], function() {
         var table = layui.table,
            form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
         //展示已知数据
         $.ajax({
            type: "post",
            url: "/XGProject/exportSeed.do?flag=getPeiSong",
            async: true,
            dataType: 'json',
            success: function(data) {
               if(data == null || data == undefined || data == "" || data.length < 0 || data[0].result == null) {
                  layer.alert("无配送订单！", {
                     icon: 5,
                     closeBtn: 2,
                     anim: 4,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  });
               }
               var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
               table.render({
                  elem: '#demo',
                  height: 'full-125',
                  cellMinWidth: 80,
                  width:w,
                  data: data,
                  cols: [
                     [ //标题栏
                        {
                           field: 'id',
                           title: '订单编号',
                           sort: true,
                           align: 'center'

                        }, {
                           field: 'huozhu',
                           title: '客户名称',
                           align: 'center'

                        }, {
                           field: 'kehudanhao',
                           title: '客户单号',
                           align: 'center'

                        }, {
                           field: 'faqitime',
                           title: '订单时间',
                           align: 'center'

                        }, {
                           field: 'zhuangtai',
                           title: '订单状态',
                           align: 'center'

                        }, {
                           field: 'tel',
                           title: '联系电话',
                           align: 'center'

                        }, {
                           field: 'mingcheng',
                           title: '货物名称',
                           align: 'center'

                        }, {
                           field: 'guige',
                           title: '规格',
                           align: 'center'

                        }, {
                           field: 'caizhi',
                           title: '材质',
                           align: 'center'

                        }, {
                           field: 'shuxing',
                           title: '属性',
                           align: 'center'

                        }, {
                           field: 'chandi',
                           title: '产地',
                           align: 'center'

                        }, {
                           field: 'zhongliang',
                           title: '重量',
                           align: 'center'

                        }, {
                           fixed: 'right',
                           align: 'center',
                           toolbar: '#barDemo'

                        }
                     ]
                  ],
                  initSort: {
                     field: 'faqitime', //排序字段，对应 cols 设定的各字段名
                     type: 'null' //排序方式  asc: 升序、desc: 降序、null: 默认排序
                  },
                  even: true,
                  page: true, //是否显示分页
                  limits: [20, 30, 50, 100],
                  limit: 20, //每页默认显示的数量
                  loading: true

               });
            }

         })

         //监听工具条
         table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'detail') { //点击了开始操作
               //对不同的id编号进行截取判断
               if(data.id != undefined && data.id != "" && data.id != null) {
                  var id = data.id; //总订单编号
                  var zid = data.zid; //子订单编号
                  //转换到相应的出库界面详情页面
                  document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&eseedId=" +
                     encodeURI(encodeURI(zid));
               }
            } else if(layEvent === 'del') { //删除
               layer.confirm('真的删除行么', function(index) {
                  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                  layer.close(index);
                  //向服务端发送删除指令
               });
            } else if(layEvent === 'edit') { //编辑
               //do something

               //同步更新缓存对应的值
               obj.update({
                  username: '123',
                  title: 'xxx'
               });
            }
         });

         //监听表单的提交
         form.on('submit(query)', function(data) {
            var type = data.field.type;
            switch(type) {
               case "出库订单":
                  //调用对应的出库订单的方法
                  queryChuKu(1, 20);
                  break;
               case "入库订单":
                  //调用对应的入库订单的方法
                  queryRuKu(1, 20);
                  break;
            }
            return false;
         });
      });
   </script>

</html>