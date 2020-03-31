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

   //表格工具条的监听
   table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //查看
         //id的字符串进行截取然后判断
         var type = (data.id).substring(0, 1);
         switch(type) {
            case "入":
               document.location.href = "/XGProject/inputSeed.do?flag=getXiangQing&iseedId=" +
                  encodeURI(encodeURI(data.zid));
               break;
            case "出":
               document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&eseedId=" +
                  encodeURI(encodeURI(data.zid));
               break;
            case "转":
               document.location.href = "/XGProject/shiftStockSeed.do?flag=getXiangQing&ssseedId=" +
                  encodeURI(encodeURI(data.zid));
               break;
         }
      }
   });
   clientName("#client");
});

//当页面加载的时候调用显示内容的方法
showContent(1);

//根据不同的订单类型加载页面数据，curr为当前页
function showContent(curr) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery,
         layer = layui.layer;
      var type = $("#indent-type").val();
      switch(type) {
         case '出库作废订单':
            queryChuKuZuoFei(curr);
            break;
         case '出库订单':
            queryChuKu(curr);
            break;
         case '入库作废订单':
            queryRuKuZuoFei(curr);
            break;
         case '入库订单':
            queryRuKu(curr);
            break;
         case '过户订单':
            queryGuoHu(curr);
            break;
      }
   });
}

//查询对应的入库订单的函数
function queryRuKu(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var indent_number = $("#indent-number").val(); //获取订单编号文本框中的值
      var client_number = $("#client-number").val(); //获得客户单号文本框中的值
      var goods = $("#goods").val(); //获得货物资料中的值

      if(!client) {
         client = "";
      }
      var loadIndex = layer.load(1); //风格1的加载
      var url = "/XGProject/inputSeed.do?flag=getRuKuLiShi";
      var data = ajaxQuery(url, begin, finish, client, indent_number, client_number, goods, curr);
      layer.close(loadIndex);
      if(data != "error") {
         var jdata = JSON.parse(data);
         if(jdata == null || jdata[0].result == null) {
            layer.alert("无入库订单记录！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
         var shiruSum = 0.0;
         var yingruSum = 0.0;
         $.each(jdata, function(i, obj) {
            obj.xuhao = (i + 1);
            if(obj.result != null) {
               shiruSum += parseFloat(obj.shichuweight);
               yingruSum += parseFloat(obj.zhongliang);
            }
         });
         var sumJson = {
            'xuhao': "",
            'id': "当页合计：",
            'zid': "",
            'huozhu': "",
            'yunshu': "",
            'huowu': "",
            'kehudanhao': "",
            'faqishijian': "",
            'zhongliang': yingruSum.toFixed(3),
            'shichuweight': shiruSum.toFixed(3),
            'zhuangtai': "",
         };
         jdata.push(sumJson);
         var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
         table.render({
            data: jdata, //返回的json数据
            elem: '#showContent', //显示数据的容器
            height: 'full-215',
            width: w,
            limit: 1000000, //默认显示的行数
            cellMinWidth: 100,
            even: true,
            cols: [
               [{
                  field: 'xuhao', //列显示的值
                  title: '序号', //显示的标题
                  width: 60, //列的宽度
                  sort: false, //是否产生排序
                  fixed: true //是否是固定列宽
               }, {
                  field: 'id',
                  title: '订单编号',
                  sort: true
               }, {
                  field: 'zhuangtai',
                  title: '订单状态',
                  sort: true,
                  align: 'center',
                  templet: '#titleTp1'
               }, {
                   field: 'faqiren',
                   title: '发起人',
                   sort: true,
                   align: 'center',
                   width:100
                },{
                  field: 'huozhu',
                  title: '货主名称',
                  sort: true,
                  edit: false //是否可编辑
               }, {
                  field: 'yunshu',
                  title: '运输方式',
                  sort: true
               }, {
                  field: 'huowu',
                  title: '货物名称',
                  sort: true
               }, {
                  field: 'kehudanhao',
                  title: '客户单号',
                  sort: false
               }, {
                  field: 'zhongliang',
                  title: '应收重量',
                  sort: false
               }, {
                  field: 'shichuweight',
                  title: '实收重量',
                  sort: false
               }, {
                  field: 'faqishijian',
                  title: '发起时间',
                  sort: false
               }, {
                  fixed: 'right',
                  width: 130,
                  align: 'right',
                  toolbar: '#barDemo'
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
                     queryRuKu(obj.curr);
                  }

               }
            });
         }
      }
   });

}

//查询对应的出库订单的函数
function queryChuKu(curr) {
   layui.use(['table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var indent_number = $("#indent-number").val(); //获取订单编号文本框中的值
      var client_number = $("#client-number").val(); //获得客户单号文本框中的值
      var goods = $("#goods").val(); //获得货物资料中的值

      if(!client) {
         client = "";
      }
      var loadIndex = layer.load(1); //风格1的加载
      var url = "/XGProject/exportSeed.do?flag=getChuKuLiShi";
      var data = ajaxQuery(url, begin, finish, client, indent_number, client_number, goods, curr);
      layer.close(loadIndex);
      if(data != "error") {
         var jdata = JSON.parse(data);
         if(jdata == null || jdata[0].result == null) {
            layer.alert("无出库订单记录！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
         var shichuSum = parseFloat(jdata[jdata.length - 1].scsum); //实出的总和
         var yingchuSum = parseFloat(jdata[jdata.length - 1].ycsum); //应出的总和
         $.each(jdata, function(i, obj) {
            obj.xuhao = (i + 1);
         });
         var sumJson = {
            "id": "当页合计：",
            "zhongliang": yingchuSum.toFixed(3),
            "shichuweight": shichuSum.toFixed(3),
            "zhuangtai": ''
         };
         jdata.push(sumJson);
         var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
         table.render({
            data: jdata, //返回的json数据
            elem: '#showContent', //显示数据的容器
            width: w,
            height: 'full-215',
            cellMinWidth: 100,
            limit: 1000000, //默认显示的行数
            even: true,
            cols: [
               [{
                  field: 'xuhao', //列显示的值
                  title: '序号', //显示的标题
                  width: 60, //列的宽度
                  sort: false, //是否产生排序
                  fixed: true //是否是固定列宽
               }, {
                  field: 'id',
                  title: '订单编号',
                  sort: true
               },{
                  field: 'zhuangtai',
                  title: '订单状态',
                  sort: true,
                  align: 'center',
                  templet: '#titleTp2'
               },{
                   field: 'faqiren',
                   title: '发起人',
                   sort: true,
                   align: 'center',
                   width:100
                }, {
                  field: 'huozhu',
                  title: '货主名称',
                  sort: true,
                  edit: false //是否可编辑
               }, {
                  field: 'yunshu',
                  title: '运输方式',
                  sort: true
               }, {
                  field: 'huowu',
                  title: '货物名称',
                  sort: true
               }, {
                  field: 'kehudanhao',
                  title: '客户单号',
                  sort: false
               }, {
                  field: 'zhongliang',
                  title: '应出重量',
                  sort: false
               }, {
                  field: 'shichuweight',
                  title: '实出重量',
                  sort: false
               }, {
                  field: 'faqishijian',
                  title: '发起时间',
                  sort: false
               }, {
                  fixed: 'right',
                  width: 130,
                  align: 'right',
                  toolbar: '#barDemo'
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
                     queryChuKu(obj.curr);
                  }

               }
            });
         }
      }
   });
}

//查询对应的过户订单的函数
function queryGuoHu(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var indent_number = $("#indent-number").val(); //获取订单编号文本框中的值
      var client_number = $("#client-number").val(); //获得客户单号文本框中的值
      var goods = $("#goods").val(); //获得货物资料中的值

      if(!client) {
         client = "";
      }
      var loadIndex = layer.load(1); //风格1的加载
      var url = "/XGProject/shiftStockSeed.do?flag=getShiftStockAll";
      var data = ajaxQuery(url, begin, finish, client, indent_number, client_number, goods, curr);
      layer.close(loadIndex);
      if(data != "error") {
         var jdata = JSON.parse(data);
         if(jdata == null || jdata[0].result == null) {
            layer.alert("无过户订单记录！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
         var shichuSum = jdata[jdata.length - 1].sum; //实出的总和
         $.each(jdata, function(i, obj) {
            obj.xuhao = (i + 1);
         });
         var sumJson = {
            "id": "当页合计：",
            "zhongliang": shichuSum,
            "zhuangtai": ""
         };
         jdata.push(sumJson);
         var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
         table.render({
            data: jdata, //返回的json数据
            elem: '#showContent', //显示数据的容器
            width: w,
            height: 'full-215',
            cellMinWidth: 100,
            limit: 1000000, //默认显示的行数
            even: true,
            cols: [
               [{
                  field: 'xuhao', //列显示的值
                  title: '序号', //显示的标题
                  width: 60, //列的宽度
                  sort: false, //是否产生排序
                  fixed: true //是否是固定列宽
               }, {
                  field: 'id',
                  title: '订单编号',
                  sort: false,
               }, {
                  field: 'zhuangtai',
                  title: '订单状态',
                  sort: true,
                  templet: '#titleTp3'
               },  {
                   field: 'faqiren',
                   title: '发起人',
                   sort: true,
                   width:100
                },{
                  field: 'zhuanchu',
                  title: '转出单位',
               }, {
                  field: 'zhuanru',
                  title: '转入单位',
                  sort: false,
                  edit: false //是否可编辑
               }, {
                  field: 'jieguo',
                  title: '审核结果',
                  sort: false
               }, {
                  field: 'huowu',
                  title: '货物',
                  sort: false
               }, {
                  field: 'zhongliang',
                  title: '转出重量',
                  sort: false
               }, {
                  field: 'kehudanhao',
                  title: '客户单号',
                  sort: false
               }, {
                  field: 'faqishijian',
                  title: '发起时间',
                  sort: false
               }, {
                  fixed: 'right',
                  width: 130,
                  align: 'right',
                  toolbar: '#barDemo'
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
                     queryGuoHu(obj.curr);
                  }

               }
            });
         }
      }
   });
}

//查询对应的入库作废订单的函数
function queryRuKuZuoFei(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var indent_number = $("#indent-number").val(); //获取订单编号文本框中的值
      var client_number = $("#client-number").val(); //获得客户单号文本框中的值
      var goods = $("#goods").val(); //获得货物资料中的值

      if(!client) {
         client = "";
      }
      var loadIndex = layer.load(1); //风格1的加载
      var url = "/XGProject/inputSeed.do?flag=getRuKuZuoFei";
      var data = ajaxQuery(url, begin, finish, client, indent_number, client_number, goods, curr);
      layer.close(loadIndex);
      if(data != "error") {
         var jdata = JSON.parse(data);
         if(jdata == null || jdata[0].result == null) {
            layer.alert("无入库作废订单记录！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
         var shiruSum = 0.0;
         var yingruSum = 0.0;
         $.each(jdata, function(i, obj) {
            obj.xuhao = (i + 1);
            shiruSum += parseFloat(obj.shichuweight);
            yingruSum += parseFloat(obj.zhongliang);
         });
         var sumJson = {
            "id": "当页合计：",
            "zhongliang": yingruSum.toFixed(3),
            "shichuweight": shiruSum.toFixed(3)
         };
         jdata.push(sumJson);
        var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
         table.render({
            data: jdata, //返回的json数据
            elem: '#showContent', //显示数据的容器
            width: w,
            height: 'full-215',
            limit: 1000000, //默认显示的行数
            even: true,
            cols: [
               [{
                  field: 'xuhao', //列显示的值
                  title: '序号', //显示的标题
                  width: 80, //列的宽度
                  sort: false, //是否产生排序
                  fixed: true //是否是固定列宽
               }, {
                  field: 'id',
                  title: '订单编号',
                  width: 160,
                  sort: false,
               },{
                  field: 'huozhu',
                  title: '货主',
                  width: 140,
                  sort: false,
                  edit: false //是否可编辑
               }, {
                  field: 'yunshu',
                  title: '运输方式',
                  width: 140,
                  sort: false
               }, {
                  field: 'huowu',
                  title: '货物',
                  width: 140,
                  sort: false
               }, {
                  field: 'kehudanhao',
                  title: '客户单号',
                  width: 140,
                  sort: false
               }, {
                  field: 'faqishijian',
                  title: '发起时间',
                  width: 180,
                  sort: false
               }, {
                  field: 'zhongliang',
                  title: '应收重量',
                  width: 140,
                  sort: false
               }, {
                  field: 'shichuweight',
                  title: '实收重量',
                  width: 140,
                  sort: false
               }, {
                  field: 'zhuangtai',
                  title: '订单状态',
                  width: 140,
                  sort: false,
               }, {
                  fixed: 'right',
                  width: 120,
                  align: 'center',
                  toolbar: '#barDemo'
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
                     queryRuKuZuoFei(obj.curr);
                  }

               }
            });
         }
      }
   });
}

//查询对应的出库作废订单的函数
function queryChuKuZuoFei(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var indent_number = $("#indent-number").val(); //获取订单编号文本框中的值
      var client_number = $("#client-number").val(); //获得客户单号文本框中的值
      var goods = $("#goods").val(); //获得货物资料中的值

      if(!client) {
         client = "";
      }
      var loadIndex = layer.load(1); //风格1的加载
      var url = "/XGProject/exportSeed.do?flag=getChuKuLiShiZuoFei";
      var data = ajaxQuery(url, begin, finish, client, indent_number, client_number, goods, curr);
      layer.close(loadIndex);
      if(data != "error") {
         var jdata = JSON.parse(data);
         if(jdata == null || jdata[0].result == null) {
            layer.alert("无出库作废订单记录！", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
         var shichuSum = 0.0; //实出的总和
         var yingchuSum = 0.0; //应出的总和
         $.each(jdata, function(i, obj) {
            obj.xuhao = (i + 1);
            shichuSum += parseFloat(obj.shichuweight);
            yingchuSum += parseFloat(obj.zhongliang);
         });
         var sumJson = {
            "id": "当页合计：",
            "zhongliang": yingchuSum.toFixed(3),
            "shichuweight": shichuSum.toFixed(3)
         };
         jdata.push(sumJson);
         var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
         table.render({
            data: jdata, //返回的json数据
            elem: '#showContent', //显示数据的容器
            width: w,
            height: 'full-215',
            limit: 1000000, //默认显示的行数
            even: true,
            cols: [
               [{
                  field: 'xuhao', //列显示的值
                  title: '序号', //显示的标题
                  width: 80, //列的宽度
                  sort: false, //是否产生排序
                  fixed: true //是否是固定列宽
               }, {
                  field: 'id',
                  title: '订单编号',
                  width: 160,
                  sort: false,
               },{
                  field: 'huozhu',
                  title: '货主',
                  width: 140,
                  sort: false,
                  edit: false //是否可编辑
               }, {
                  field: 'yunshu',
                  title: '运输方式',
                  width: 140,
                  sort: false
               }, {
                  field: 'huowu',
                  title: '货物',
                  width: 140,
                  sort: false
               }, {
                  field: 'kehudanhao',
                  title: '客户单号',
                  width: 140,
                  sort: false
               }, {
                  field: 'faqishijian',
                  title: '发起时间',
                  width: 180,
                  sort: false
               }, {
                  field: 'zhongliang',
                  title: '应出重量',
                  width: 140,
                  sort: false
               }, {
                  field: 'shichuweight',
                  title: '实出重量',
                  width: 140,
                  sort: false
               }, {
                  field: 'zhuangtai',
                  title: '订单状态',
                  width: 140,
                  sort: false,
               }, {
                  fixed: 'right',
                  width: 120,
                  align: 'center',
                  toolbar: '#barDemo'
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
                     queryChuKuZuoFei(obj.curr);
                  }

               }
            });
         }
      }
   });
}

//对应的ajax访问的封装方法,u：路径,b:起始日期，f，结束日期，c：客户简称,i:订单编号,cn:客户单号,g:货物资料,p;当前页
function ajaxQuery(u, b, f, c, i, cn, g, p) {
   var data;
   layui.use('jquery', function() {
      var $ = layui.jquery;
      var type = $("#indent-type").val();
      var dataPar;
      switch(type) {
         case "出库订单":
         case "出库作废订单":
            dataPar = {
               "time": new Date().getTime(),
               "begin": b,
               "finish": f,
               "jiancheng": c,
               "export": i,
               "kehubianhao": cn,
               "goodsName": g,
               "pageNow": p
            }
            break;
         case "过户订单":
            dataPar = {
               "time": new Date().getTime(),
               "begin": b,
               "finish": f,
               "jiancheng": c,
               "shiftStock": i,
               "kehudanhao": cn,
               "goodsname": g,
               "pageNow": p
            }
            break;
         case "入库订单":
         case "入库作废订单":
            dataPar = {
               "time": new Date().getTime(),
               "begin": b,
               "finish": f,
               "clientName": c,
               "input": i,
               "clientNumber": cn,
               "goodsName": g,
               "pageNow": p
            }
            break;
      }
      $.ajax({
         type: "post",
         url: u,
         async: false,
         data: dataPar,
         dataType: "json",
         success: function(obj) {
            data = JSON.stringify(obj);
         },
         error: function() {
            data = "error";
         }
      });
   });
   return data;
}