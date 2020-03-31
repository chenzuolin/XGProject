clientName("#client");
layui.use(['jquery', 'form', 'table'], function() {
   var $ = layui.jquery;
   var form = layui.form;
   var table = layui.table;

   //监听表单的提交
   form.on('submit(query)', function(data) {
      var type = data.field.type;
      switch(type) {
         case "出库订单":
            //调用对应的出库订单的方法
            $("#chehao").attr("placeholder", "请输入车号");
            $("#chehao").parent("div").prev("label").text("车号");
            queryChuKu(1, 20);
            break;
         case "入库订单":
            //调用对应的入库订单的方法
            $("#chehao").attr("placeholder", "请输入车号");
            $("#chehao").parent("div").prev("label").text("车号");
            queryRuKu(1, 20);
            break;
         case "过户订单":
            //调用对应的过户订单的方法
            $("#chehao").attr("placeholder", "请输入客户单号");
            $("#chehao").parent("div").prev("label").text("客户单号");
            queryGuoHu(1, 20);
            break;
      }
      return false;
   });

   //表格工具条的监听
   table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //点击了开始操作
         //对不同的id编号进行截取判断
         if(data.id != undefined && data.id != "" && data.id != null) {
            var id = data.id; //总订单编号
            var zid = data.zid; //子订单编号
            //转换到相应详情页面
            document.location.href = "/XGProject/shiftStockSeed.do?flag=getShenHeXiangXi&ssseedId=" + zid;
         }
         if(data.zongId != undefined && data.zongId != "" && data.zongId != null) {
            var id = data.zongId; //总订单编号
            var type = id.substring(0, 1);
            if(type == "出") {
               var zid = data.exportSeedId; //子订单编号
               var cid = data.exportOperateId; //操作订单编号

               document.location.href = "/XGProject/exportOperate.do?flag=getShenheChakai&caozuoid=" + cid + "&ziId=" + zid;

            } else if(type == "入") {
               var zid = data.inputSeedId; //子订单编号
               var cid = data.inputOperateId; //操作订单编号

               document.location.href = "/XGProject/inputOperate.do?flag=selectShenHeOperat&caozuoid=" + cid + "&ziId=" + zid;

            }
         }
      }
   });
});

//当页面加载的时候调用出库函数
queryChuKu(1, 20);

//出库查询函数,传入当前页和显示行数
function queryChuKu(curr, pageRow) {
   layui.use(['jquery', 'form', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;
      var laypage = layui.laypage;

      var chehao = $("#chehao").val(); //获得车号文本框中的值
      var client = $("#client").val(); //获得客户名称文本框中的值

      $.ajax({
         type: "post",
         url: "/XGProject/exportOperate.do?flag=getShenheChaxun",
         async: true,
         data: {
            "pageNow": curr,
            "danhao": chehao,
            "huozhu": client,
            "pageRow": pageRow
         },
         dataType: "json",
         success: function(data) {
            if(data == null || data == undefined || data == "" || data.length < 0 || data[0].qingkong == 'clean') {
               layer.alert("无出库审核订单记录！", {
                  icon: 2,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
            //出库订单表格渲染
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-145',
               width:w,
               cellMinWidth: 90,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'zongId', //列显示的值
                     title: '订单编号', //显示的标题
                     minWidth: 135, //列的宽度
                     sort: true, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'huozhu',
                     title: '货主',
                     minWidth: 135,
                     sort: false,
                  }, {
                     field: 'exportClientNumber',
                     title: '客户单号',
                     minWidth: 100,
                     sort: true
                  }, {
                     field: 'exportPlateNumber',
                     title: '车号',
                     minWidth: 110,
                     sort: false
                  }, {
                     field: 'diaodu',
                     title: '调度员',
                     sort: false
                  }, {
                     field: 'fenpeiTime',
                     title: '分配时间',
                     minWidth: 160,
                     sort: true
                  }, {
                     field: 'fenpeiWeight',
                     title: '分配重量',
                     sort: false
                  }, {
                     field: 'baoguan',
                     title: '保管员',
                     sort: false
                  }, {
                     field: 'shijiWeight',
                     title: '操作重量',
                     sort: false
                  }, {
                     field: 'shijiJianshu',
                     title: '操作件数',
                     sort: false
                  }, {
                     field: 'guoLi',
                     title: '过磅/理算',
                     sort: false
                  }, {
                     field: 'kuwei',
                     title: '库位',
                     minWidth: 80,
                     sort: false
                  }, {
                     field: 'goodsCategoryName',
                     title: '货物品类',
                     sort: false
                  }, {
                     field: 'goodsName',
                     title: '名称',
                     sort: false
                  }, {
                     field: 'goodsStandardName',
                     title: '规格',
                     minWidth: 150,
                     sort: false
                  }, {
                     field: 'goodsQualityName',
                     title: '材质',
                     sort: false
                  }, {
                     field: 'goodsPropertyName',
                     title: '属性',
                     sort: false
                  }, {
                     field: 'goodsYieldlyName',
                     title: '产地',
                     sort: false
                  }, {
                     field: 'exportCreateTime',
                     title: '发起时间',
                     minWidth: 160,
                     sort: false,
                  }, {
                     field: 'youxiao',
                     title: '有效期(天)',
                  }, {
                     field: 'exportCarryType',
                     title: '运输方式',
                     sort: false
                  }, {
                     field: 'exportDriverName',
                     title: '司机姓名',
                     sort: false
                  }, {
                     field: 'exportDriverTel',
                     title: '司机电话',
                     sort: false
                  }, {
                     field: 'beizhu',
                     title: '备注',
                     sort: false
                  }, {
                     fixed: 'right',
                     minWidth: 130,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });

            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(data[0].pageCount) * 20, //显示的总条数
                  limit: 20,
                  layout: ['prev', 'page', 'next', 'skip', 'limit'],
                  limits: [20, 40, 60, 80, 100],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        queryChuKu(obj.curr, obj.limit);
                     }
                  }
               });
            }

         },
         error: function() {
            layer.alert("数据请求错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

//入库查询函数,传入当前页和显示行数
function queryRuKu(curr, pageRow) {
   layui.use(['jquery', 'form', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;
      var laypage = layui.laypage;

      var chehao = $("#chehao").val(); //获得车号文本框中的值
      var client = $("#client").val(); //获得客户名称文本框中的值

      $.ajax({
         type: "post",
         url: "/XGProject/inputOperate.do?flag=selectShenheInputAjaxTj",
         async: true,
         data: {
            "danhao": chehao,
            "huozhu": client,
            "pageNow": curr,
            "pageRow": pageRow
         },
         dataType: "json",
         success: function(data) {
            if(data == null || data == undefined || data == "" || data.length < 0 || data[0].qingkong == 'clean') {
               layer.alert("无入库审核订单记录！", {
                  icon: 2,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
            //入库订单表格渲染
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-145',
               cellMinWidth: 90,
               width:w,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'zongId', //列显示的值
                     title: '订单编号', //显示的标题
                     minWidth: 135, //列的宽度
                     sort: true, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'clientLoginName',
                     title: '货主',
                     minWidth: 135,
                     sort: false,
                  }, {
                     field: 'inputClientNumber',
                     title: '客户单号',
                     minWidth: 100,
                     sort: true
                  }, {
                     field: 'inputPlateNumber',
                     title: '车号',
                     minWidth: 110,
                     sort: false
                  }, {
                     field: 'diaodu',
                     title: '调度员',
                     sort: false
                  }, {
                     field: 'fenpeiTime',
                     title: '分配时间',
                     minWidth: 160,
                     sort: false
                  }, {
                     field: "baoguan",
                     title: '保管员',
                     sort: false
                  }, {
                     field: 'shijiWeight',
                     title: '操作重量',
                     sort: false
                  }, {
                     field: "shijiJianshu",
                     title: '操作件数',
                     sort: false
                  }, {
                     field: 'guoLi',
                     title: '过磅/理算',
                     sort: false
                  }, {
                     field: 'kuwei',
                     title: '库位',
                     minWidth: 80,
                     sort: false
                  }, {
                     field: 'goodsCategoryName',
                     title: '货物品类',
                     sort: false
                  }, {
                     field: 'goodsName',
                     title: '名称',
                     sort: false
                  }, {
                     field: 'goodsStandardName',
                     title: '规格',
                     minWidth: 150,
                     sort: false
                  }, {
                     field: 'goodsQualityName',
                     title: '材质',
                     sort: false
                  }, {
                     field: 'goodsPropertyName',
                     title: '属性',
                     sort: false
                  }, {
                     field: 'goodsYieldlyName',
                     title: '产地',
                     sort: false
                  }, {
                     field: 'inputCreateTime',
                     title: '发起时间',
                     minWidth: 160,
                     sort: false,
                  }, {
                     field: 'inputDefinedOne',
                     title: '有效期(天)',
                     edit: false //是否可编辑
                  }, {
                     field: 'inputCarryType',
                     title: '运输方式',
                     sort: false
                  }, {
                     field: 'inputDriverName',
                     title: '司机姓名',
                     sort: false
                  }, {
                     field: 'inputDriverTel',
                     title: '司机电话',
                     sort: false
                  }, {
                     field: 'beizhu',
                     title: '备注',
                     sort: false
                  }, {
                     fixed: 'right',
                     minWidth: 130,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(data[0].pageCount) * 20, //显示的总条数
                  limit: 20,
                  layout: ['prev', 'page', 'next', 'skip', 'limit'],
                  limits: [20, 40, 60, 80, 100],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        queryRuKu(obj.curr, obj.limit);
                     }
                  }
               });
            }
         },
         error: function() {
            layer.alert("数据请求错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

function queryGuoHu(curr, pageRow) {
   layui.use(['jquery', 'form', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;
      var laypage = layui.laypage;

      var chehao = $("#chehao").val(); //获得车号文本框中的值
      var client = $("#client").val(); //获得客户名称文本框中的值

      $.ajax({
         type: "post",
         url: "/XGProject/shiftStockSeed.do?flag=getJiHuaShiftStock",
         async: true,
         data: {
            "tihuohao": chehao,
            "huozhu": client,
            "pageNow": curr,
            "pageRow": pageRow
         },
         dataType: "json",
         success: function(data) {
            if(data == null || data == undefined || data == "" || data.length < 0 || data.max == 'maxs' || data[0].result == null) {
               layer.alert("无过户审核订单记录！", {
                  icon: 2,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
            //入库订单表格渲染
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-145',
               width:w,
               cellMinWidth: 90,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     minWidth: 135, //列的宽度
                     sort: true, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'zhuanchu',
                     title: '转出单位',
                     minWidth: 150,
                     sort: false,
                  }, {
                     field: 'zhuanru',
                     title: '转入单位',
                     minWidth: 150,
                  }, {
                     field: 'kehudanhao',
                     title: '客户单号',
                     minWidth: 100,
                     sort: true
                  }, {
                     field: 'zhuangtai',
                     title: '订单状态',
                     minWidth: 100,
                     sort: true,
                  }, {
                     field: 'zhongliang',
                     title: '过户重量',
                     sort: false
                  }, {
                     field: 'time',
                     title: '过户时间',
                     minWidth: 160,
                     sort: false
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     sort: false
                  }, {
                     field: 'mingcheng',
                     title: '名称',
                     sort: false
                  }, {
                     field: 'guige',
                     title: '规格',
                     minWidth: 150,
                     sort: false
                  }, {
                     field: 'caizhi',
                     title: '材质',
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '属性',
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '产地',
                     sort: false
                  }, {
                     field: 'remark',
                     title: '备注',
                     sort: false
                  }, {
                     fixed: 'right',
                     width: 130,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(data[0].pageCount) * 20, //显示的总条数
                  limit: 20,
                  layout: ['prev', 'page', 'next', 'skip', 'limit'],
                  limits: [20, 40, 60, 80, 100],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        queryGuoHu(obj.curr, obj.limit);
                     }
                  }
               });
            }
         },
         error: function() {
            layer.alert("数据请求错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}