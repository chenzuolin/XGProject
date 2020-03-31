layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
   var laydate = layui.laydate;
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;

   //执行一个laydate实例
   laydate.render({
      elem: '#begin', //指定元素，开始时间
      type: "datetime"
   });
   laydate.render({
      elem: '#finish', //指定元素，结束时间
      type: "datetime"
   });

   //form监听提交,当单机【立即提交的时候调用】
   form.on('submit(formDemo)', function(data) {
      showContent(1);
      return false;
   });

   //在客户名称下拉框中当键盘按下弹起的时候，将该文本框的值改变为小写
   $(".layui-form-select .layui-select-title input").keyup(function() {
      $(this).val($(this).val().toLowerCase());
   });

});

//当页面加载的时候调用显示内容的方法
showContent(1);

function showContent(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var goods = $("#goods").val(); //获得货物资料中的值
      var goodsPinLei = $("#goodsPinLei").val(); //获得货物品类文本框中的值

      //调用开始加载函数
      var loadIndex = layer.load(1); //风格1的加载

      $.ajax({
         type: "post",
         url: "/XGProject/tidingsAction.do?flag=TongJiXGLSKC",
         async: true,
         data: {
            "pageNow": curr,
            "time": new Date().getTime(),
            "begin": begin,
            "finish": finish,
            "goodsName": goods,
            "pinlei": goodsPinLei
         },
         dataType: "json",
         success: function(jdata) {
            layer.close(loadIndex);
            if(jdata == null || jdata == "" || jdata == undefined) {
               layer.alert("无历史库存记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            $.each(jdata, function(i, objs) {
               objs.xuhao = (i + 1);
            });

            var sumJson = {
               "chandi": "当页合计：",
               "qichu": jdata[jdata.length - 1].QCZH,
               "rukuheji": jdata[jdata.length - 1].RKZH,
               "chukuheji": jdata[jdata.length - 1].CKZH,
               "qimo": jdata[jdata.length - 1].QMZH
            };
            jdata.push(sumJson);
            var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度 
            table.render({
               data: jdata, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width:w,
               height: 'full-210',
               limit: 1000000, //默认显示的行数
               cellMinWidth: 60,
               even: true,
               cols: [
                  [{
                     field: 'xuhao', //列显示的值
                     title: '序号', //显示的标题
                     width: 60, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     sort: true
                  }, {
                     field: 'mingcheng',
                     title: '货物名称',
                     sort: true
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     sort: true
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     sort: true
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     sort: true
                  }, {
                     field: 'qichu',
                     title: '期初库存',
                     sort: false
                  }, {
                     field: 'rukuheji',
                     title: '入库合计',
                     sort: false
                  }, {
                     field: 'chukuheji',
                     title: '出库合计',
                     sort: false
                  }, {
                     field: 'qimo',
                     title: '期末库存',
                     sort: false
                  }]
               ], //设置表头
               done: function() {
                  $(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
                     "font-weight": "bold",
                     "color": "red"
                  });
               }
            });
            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(jdata[0].pageCount) * 30, //显示的总条数
                  limit: 30,
                  layout: ['prev', 'page', 'next', 'skip'],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        showContent(obj.curr);
                     }

                  }
               });
            }
         },
         error: function() {
            layer.close(loadIndex);
            layer.alert("请求数据错误！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}