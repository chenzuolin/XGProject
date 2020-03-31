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
            queryChuKu();
            break;
         case "入库订单":
            //调用对应的入库订单的方法
            queryRuKu();
            break;
         case "盘库订单":
            //调用对应的盘库订单的方法
            queryPanKu();
            break;
         case "挪库订单":
            //调用对应的挪库订单的方法
            queryNuoKu();
            break;
         case "短倒订单":
            //调用对应的短倒订单的方法
            queryDuanDao();
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
            var cid = data.cid; //操作订单编号

            if(zid != undefined && zid != "" || zid != null) {
               //转换到相应出库详情页面
               document.location.href = "/XGProject/exportOperate.do?flag=ZhengZaiChuKu&eoperateId=" +
                  encodeURI(encodeURI(cid));
            } else {
               switch(id.substring(0, 1)) {
                  case "盘":
                     layer.alert("这是盘库订单，订单编号：" + id, {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4
                     });
                     break;
                  case "挪":
                     layer.alert("这是挪库订单，订单编号：" + id, {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4
                     });
                     break;
                  case "倒":
                     layer.alert("这是短倒订单，订单编号：" + id, {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4
                     });
                     break;
               }
            }

         }
         if(data.zongId != undefined && data.zongId != "" && data.zongId != null) {
            var id = data.zongId; //总订单编号
            var zid = data.inputSeedId; //子订单编号
            var cid = data.inputOperateId; //操作订单编号

            //转到相应的入库详情的页面
            document.location.href = "/XGProject/inputOperate.do?flag=selectBaoGuanChuli&caozuoid=" + cid;
         }
      }
   });
});
//当页面加载的时候默认调用出库的函数
queryChuKu();

//查询出库订单的方法
function queryChuKu() {
   layui.use(['jquery', 'form', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({
         type: "post",
         url: "/XGProject/exportOperate.do?flag=ZhunBiChuKu",
         data: {
            "interiorUserByEoperateStoreman": $("#dengluren").val()
         },
         dataType: "json",
         success: function(chukuData) {
            //          alert(JSON.stringify(chukuData));
            //判断数据是否为空
            if(chukuData == null || chukuData == undefined || chukuData == "" || chukuData[0].result == null) {
               layer.alert("无出库操作订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
            //出库订单表格渲染
            table.render({
               data: chukuData, //返回的json数据
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
                     sort: true, //是否产生排序
                     minWidth: 135,
                     fixed:true,
                     align: 'center'
                  }, {
                     field: 'huozhu',
                     title: '货主',
                     sort: false,
                     minWidth: 135,
                     align: 'center'
                  }, {
                     field: 'kehuhao',
                     title: '客户单号',
                     align: 'center'
                  }, {
                     field: 'chehao',
                     title: '车号',
                     minWidth: 110,
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'diaoduyuan',
                     title: '调度员',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'fenpeitime',
                     title: '分配时间',
                     sort: false,
                     minWidth: 160,
                     align: 'center'
                  }, {
                     field: 'fenpeiweight',
                     title: '分配重量',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'lisuan',
                     title: '过磅/理算',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'kuwei',
                     title: '库位',
                     minWidth: 80,
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'pinlei',
                     title: '品类',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'mingcheng',
                     title: '名称',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'guige',
                     title: '规格',
                     minWidth: 150,
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'caizhi',
                     title: '材质',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'shuxing',
                     title: '属性',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'chandi',
                     title: '产地',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'faqitime',
                     title: '发起时间',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'youxiao',
                     title: '有效期（天）',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'yunshu',
                     title: '运输方式',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'siji',
                     title: '司机姓名',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'sijitel',
                     title: '司机电话',
                     sort: false,
                     align: 'center'
                  }, {
                     field: 'remark',
                     title: '备注',
                     sort: false,
                     align: 'center'
                  }, {
                     fixed: 'right',
                     width: 130,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
         },
         error: function() {
            layer.alert("获取数据错误！", {
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

//查询入库订单的方法
function queryRuKu() {
   layui.use(['jquery', 'form', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({
         type: "post",
         url: "/XGProject/inputOperate.do?flag=selectBaoGuanInputOperatAjax",
         data: {
            "dengluName": $("#dengluren").val()
         },
         dataType: "json",
         success: function(rukuData) {
            //判断数据是否为空
            if(rukuData == null || rukuData == undefined || rukuData == "" || rukuData.length <= 0) {
               layer.alert("无入库操作订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
            //入库订单表格渲染
            table.render({
               data: rukuData, //返回的json数据
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
                     field: 'clientLoginName',
                     title: '货主',
                     minWidth: 135,
                     sort: false,
                  }, {
                     field: 'inputClientNumber',
                     title: '客户单号',
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
                     field: 'iseedShouldWeight',
                     title: '应入重量',
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
                     sort: false,
                  }, {
                     field: 'inputDefinedOne',
                     title: '有效期（天）',
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
         },
         error: function() {
            layer.alert("获取数据错误！", {
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

//查询盘库订单的方法
function queryPanKu() {
   layui.use(['jquery', 'form', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({
         type: "post",
         url: "/XGProject/checks.do?flag=getChecksJiHua&dates=" +
            new Date().getTime(),
         async: true,
         data: {
            "interiorUserByCheckHuman": $("#dengluren").val()
         },
         dataType: "json",
         success: function(pankuData) {
            //判断数据是否为空
            if(pankuData == null || pankuData == "" || pankuData == undefined || pankuData.length <= 0 || pankuData[0].result == null) {
               layer.alert("无盘库操作订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
            //盘库订单表格渲染
            table.render({
               data: pankuData, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-180',
               limit: 1000000, //默认显示的行数
               width:w,
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     width: 150, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'faqiren',
                     title: '发起人',
                     width: 130,
                     sort: false,
                  }, {
                     field: 'time',
                     title: '发起时间',
                     width: 200,
                  }, {
                     field: 'kuwei',
                     title: '库位',
                     width: 100,
                     sort: false
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     width: 100,
                     sort: false
                  }, {
                     field: 'mingcheng',
                     title: '货物名称',
                     sort: false
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     width: 200,
                     sort: false
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     width: 140,
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     width: 100,
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     width: 140,
                     sort: false
                  }, {
                     field: 'zhuangtai',
                     title: '订单状态',
                     width: 200,
                     sort: false,
                  }, {
                     fixed: 'right',
                     width: 200,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
         },
         error: function() {
            layer.alert("获取数据错误！", {
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

//查询挪库订单的方法
function queryNuoKu() {
   layui.use(['jquery', 'form', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({
         type: "post",
         url: "/XGProject/shift.do?flag=getZhengZaiFirst&dates=" +
            new Date().getTime(),
         async: true,
         data: {
            "interiorUserByShiftExecutor": $("#dengluren").val()
         },
         dataType: "json",
         success: function(nuokuData) {
            //判断数据是否为空
            if(nuokuData == null || nuokuData == "" || nuokuData == undefined || nuokuData.length <= 0 || nuokuData[0].result == null) {
               layer.alert("无挪库操作订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
            //挪库订单表格渲染
            table.render({
               data: nuokuData, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-180',
               limit: 1000000, //默认显示的行数
               width:w,
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     width: 150, //列的宽度
                     sort: true, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'yuankuwei',
                     title: '原库位',
                     width: 130,
                     sort: false,
                  }, {
                     field: 'xinkuwei',
                     title: '新库位',
                     width: 130,
                  }, {
                     field: 'faqiren',
                     title: '发起人',
                     width: 100,
                     sort: false
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     width: 100,
                     sort: false
                  }, {
                     field: 'mingcheng',
                     title: '货物名称',
                     width: 140,
                     sort: false
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     width: 150,
                     sort: false
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     width: 140,
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     width: 100,
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     width: 140,
                     sort: false
                  }, {
                     field: 'zhongliang',
                     title: '挪库重量',
                     width: 200,
                     sort: false,
                  }, {
                     field: 'jianshu',
                     title: '挪库件数',
                     width: 140,
                     sort: false
                  }, {
                     field: 'time',
                     title: '发起时间',
                     width: 200,
                     sort: false,
                  }, {
                     field: 'zhuangtai',
                     title: '订单状态',
                     width: 200,
                     sort: false,
                  }, {
                     fixed: 'right',
                     width: 200,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
         },
         error: function() {
            layer.alert("获取数据错误！", {
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

//查询短倒订单的方法
function queryDuanDao() {
   layui.use(['jquery', 'form', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({
         type: "post",
         url: "/XGProject/duanDaoAction.do?flag=getZhengZaiFirst&dates=" +
            new Date().getTime(),
         async: true,
         data: {
            "interiorUserByShiftExecutor": $("#dengluren").val()
         },
         dataType: "json",
         success: function(duandaoData) {
            //判断数据是否为空
            if(duandaoData == null || duandaoData == "" || duandaoData == undefined || duandaoData.length <= 0 || duandaoData[0].result == null) {
               layer.alert("无短倒操作订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
            //挪库订单表格渲染
            table.render({
               data: duandaoData, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-180',
               limit: 1000000, //默认显示的行数
               width:w,
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     width: 150, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'yuankuwei',
                     title: '原库位',
                     width: 130,
                     sort: false,
                  }, {
                     field: 'xinkuwei',
                     title: '新库位',
                     width: 130,
                  }, {
                     field: 'faqiren',
                     title: '发起人',
                     width: 100,
                     sort: false
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     width: 100,
                     sort: false
                  }, {
                     field: 'mingcheng',
                     title: '货物名称',
                     width: 140,
                     sort: false
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     width: 150,
                     sort: false
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     width: 140,
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     width: 100,
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     width: 140,
                     sort: false
                  }, {
                     field: 'zhongliang',
                     title: '挪库重量',
                     width: 200,
                     sort: false,
                  }, {
                     field: 'jianshu',
                     title: '挪库件数',
                     width: 140,
                     sort: false
                  }, {
                     field: 'time',
                     title: '发起时间',
                     width: 200,
                     sort: false,
                  }, {
                     field: 'zhuangtai',
                     title: '订单状态',
                     width: 200,
                     sort: false,
                  }, {
                     fixed: 'right',
                     width: 200,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
         },
         error: function() {
            layer.alert("获取数据错误！", {
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