var huowu; //定义货物全局变量
var chukuZid; //定义出库订单子订单编号全局变量
var rukuZid; //定义入库子订单编号全局变量
layui.use(['jquery', 'form', 'table', 'layer'], function() {
   var $ = layui.jquery;
   var form = layui.form;
   var table = layui.table;
   var layer = layui.layer;

   //查询对应的客户
   clientName('#client');
   show($("#type").val());

   //监听表单的提交
   form.on('submit(query)', function(data) {
      var type = data.field.type;
      show(type);
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
            //转换到相应的出库界面详情页面
            document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&eseedId=" +
               encodeURI(encodeURI(zid));
         }
         if(data.zongId != undefined && data.zongId != "" && data.zongId != null) {
            var id = data.zongId; //总订单编号
            var zid = data.iseedId; //子订单编号

            //转换到相应的入库详情界面
            document.location.href = "/XGProject/inputSeed.do?flag=getXiangQing&iseedId=" + encodeURI(encodeURI(zid));
         }
      } else if(layEvent === 'edit') {
         //点击了操作
         //当点击操作的时候查询相应的操作人员和库位，批次
         if(data.id != undefined && data.id != "" && data.id != null) {
            var id = data.id; //总订单编号
            var zid = data.zid; //子订单编号
            //调用查询出库操作员的函数
            zhixingchuku();
            //调用查询对应出库库位的函数
            selectchukukuwei(data.goodsId);
            huowu = data.goodsId;
            chukuZid = zid;
            //这是对应的出库点击
            layer.open({
               type: 1,
               content: $("#chukuOpen"),
               area: ["600px", "450px"],
               title: ['出库待处理操作', 'font-size:16px;'],
               skin: 'layui-layer-blue',
               closeBtn: 2,
               anim: 4,
               btn: ['立即提交', '关闭'],
               yes: function(index, obj) {
                  $("#chukutijiao").click();
               },
               btn2: function(index, obj) {
                  layer.close(index);
               }
            });
         }
         if(data.zongId != undefined && data.zongId != "" && data.zongId != null) {
            var id = data.zongId; //总订单编号
            var zid = data.iseedId; //子订单编号
            //加载显示库区的函数
            QueryKuQu();
            //加载入库执行人函数
            selectBaoGuan();
            rukuZid = zid;
            //入库待处理订单分配
            layer.open({
               type: 1,
               content: $("#rukuOpen"),
               area: ["600px", "400px"],
               title: ['入库待处理操作', 'font-size:16px;'],
               skin: 'layui-layer-blue',
               closeBtn: 2,
               anim: 4,
               btn: ['立即提交', '关闭'],
               yes: function(index, obj) {
                  $("#rukuTijiao").click();
               },
               btn2: function(index, obj) {
                  layer.close(index);
               }
            });
         }
      }
   });

   //当出库选择的下拉框切换的时候查询对应的批次
   form.on('select(chukukuwei)', function(obj) {
      selectPici(huowu, obj.value);
   });

   //出库form表单提交
   form.on('submit(chukutijiao)', function(obj) {
      var data = obj.field;

      var check = $("#chukuOpen .layui-form-checkbox");
      var len = check.length;
      var picis = [];
      var x = 0;
      for(var i = 0; i < len; i++) {
         if(check.eq(i).hasClass("layui-form-checked")) {
            picis[x] = check.eq(i).prev("input[type=checkbox]").val();
            x++;
         }
      }
      if(x == 0) {
         layer.msg("请选择出库批次！", {
            icon: 2,
            time: 3000,
            type: 0
         });
         return false;
      }
      var kuweis = [];
      kuweis[0] = data.chukukuwei;
      layer.confirm("确定提交吗？", {
         icon: 3,
         closeBtn: 2,
         anim: 4,
         title: ['系统提示', 'font-size:16px;'],
         skin: 'layui-layer-blue',
      }, function() {
         //用ajax的方式进行提交
         $.ajax({
            type: "post",
            url: "/XGProject/exportSeed.do?flag=DiaoDuFenPei",
            async: false,
            data: {
               "eseedId": chukuZid,
               "baoguan": data.chukucaozuo,
               "kuwei": kuweis,
               "pici": picis,
               "lisuan": data.chukuGuoLi,
               "fenpeizhongliang": data.chukufenpei,
               "beizhu": data.chukubeizhu,
               "diaodu": data.diaodu
            },
            dataType: "text",
            success: function(data) {
               var ok = data.indexOf("提交成功");
               if(ok != -1) {
                  layer.alert("提交成功！", {
                     icon: 1,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert("提交失败！", {
                     icon: 1,
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
      return false;
   });

   //当出库选择的下拉框切换的时候查询对应的批次
   form.on('select(rukuKuqu)', function(obj) {
      selectKuwei(obj.value);
   });

   //入库form表单提交
   form.on('submit(rukuTijiao)', function(obj) {
      var data = obj.field;
      var kuweis = [];
      kuweis[0] = data.rukuKuwei;
      layer.confirm("确定提交吗？", {
         icon: 3,
         closeBtn: 2,
         anim: 4,
         title: ['系统提示', 'font-size:16px;'],
         skin: 'layui-layer-blue',
      }, function() {
         //用ajax的方式进行提交
         $.ajax({
            type: "post",
            url: "/XGProject/inputOperate.do?flag=addInputOperat",
            async: false,
            data: {
               "inputSeed": rukuZid,
               "interiorUserByIoperateStoremanId": data.rukucaozuo,
               "kuwei": kuweis,
               "ioperatePonderationTrue": data.rukuGuoLi,
               "ioperateRemark": data.ioperateRemark,
            },
            dataType: "text",
            success: function(data) {
               var ok = data.indexOf("分配成功");
               if(ok != -1) {
                  layer.alert("提交成功！", {
                     icon: 1,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert("提交失败！", {
                     icon: 1,
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
      return false;
   });
});

function show(type) {
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
}

// 查询入库执行人
function selectBaoGuan() {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;

      $.ajax({ // ajax提交方式
         type: "post",
         url: "/XGProject/input.do?flag=selectBaoguanAjax",
         dataType: "json",
         success: function(baoguan) {
            $("#rukucaozuo").html("");

            if(baoguan.result == "null" || baoguan.length <= 0) {
               $("#rukucaozuo").append("<option value=''>无在线人员</option> ");
               return false;
            }
            $.each(baoguan, function(i, item) {
               $("#rukucaozuo").append("<option value=" + item.id + ">" + item.name + "</option> ");
            });
            form.render('select');
         },
         error: function() {
            layer.alert("获取入库执行人数据错误！", {
               icon: 2,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

// 查询对应的库区
function QueryKuQu() {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/bursary.do?flag=getKuQuDaiChuLi",
         async: false,
         data: {
            "time": new Date().getTime()
         },
         dataType: "json",
         success: function(obj) {
            $("#rukuKuqu").html("");
            if(obj != null) {
               for(var i = 0; i < obj.length; i++) {
                  $("#rukuKuqu").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
               }
            } else {
               $("#rukuKuqu").append("<option value=''>无库区</option>");
            }
            selectKuwei($("#rukuKuqu").val());
            form.render('select');
         },
         error: function() {
            layer.alert("获取库区数据错误！", {
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

//查询对应的入库库位//通过库区进行查询
function selectKuwei(kuqu) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      if(kuqu == null || kuqu == undefined || kuqu == "") {
         $("#rukuKuwei").html("<option value=''>无库位</option>");
         form.render('select');
         return false;
      }

      $.ajax({ // ajax提交方式
         type: "post",
         url: "/XGProject/input.do?flag=selectKuweiAjax",
         async: false,
         data: {
            "kuqu": kuqu
         },
         dataType: "json",
         success: function(kuwei) {
            $("#rukuKuwei").html("");
            if(kuwei == null || kuwei.length <= 0 || kuwei == "" || kuwei[0].result == null) {
               $("#rukuKuwei").html("<option value=''>无库位</option>")
               form.render('select');
               return false;
            }

            for(var i = 0; i < kuwei.length; i++) {
               $("#rukuKuwei").append("<option value='" + kuwei[i].id + "'>" + kuwei[i].name + "</option>");
            }
            form.render('select');
         },
         error: function() {
            layer.alert("获取入库库位数据错误！", {
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

// 查询对应的出库的执行人
function zhixingchuku() {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/checks.do?flag=getChuKuCZY&ff=zhixing",
         async: false,
         data: {
            "time": new Date().getTime()
         },
         dataType: "json",
         success: function(obj) {
            $("#chukucaozuo").html("");
            if(obj != null) {
               if(obj[0].result != null && obj[0].id != null) {
                  for(var i = 0; i < obj.length; i++) {
                     $("#chukucaozuo").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
                  }
               } else {
                  $("#chukucaozuo").append("<option value=''>无在线人员</option>");
               }
            } else {
               $("#chukucaozuo").append("<option value=''>无在线人员</option>");
            }
            form.render('select');
         },
         error: function() {
            layer.alert("获取出库操作员数据错误！", {
               icon: 2,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

// 当出库的时候通过货物进行查询对应的库位
function selectchukukuwei(huowu) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/tarehouseGoods.do?flag=selectChuKuKuWei",
         async: true,
         data: {
            "goodsId": huowu,
            "time": new Date().getTime()
         },
         dataType: "json",
         success: function(obj) {
            $("#chukukuwei").html("");
            if(obj == null || obj[0].result == null) {
               $("#chukukuwei").append("<option value=''>无库位</option>");
               return false;
            }
            for(var i = 0; i < obj.length; i++) {
               $("#chukukuwei").append("<option value='" + obj[i].id + "'>" + obj[i].name + "    | 剩余重量：" + obj[i].weight + "吨，剩余件数：" + obj[i].number + obj[i].unit + "</option>");
            }
            form.render('select');
            selectPici(huowu, $("#chukukuwei").val());
         },
         error: function() {
            layer.alert("获取出库库存数据错误！", {
               icon: 2,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

//通过库位和货物进行查询批次
function selectPici(goods, kuwei) {

   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;

      $.ajax({ // ajax提交方式
         type: "post",
         url: "/XGProject/tarehouseDetail.do?flag=getChuKuPiCi",
         dataType: "json",
         data: {
            "goods": goods,
            "kuwei": kuwei
         },
         success: function(obj) {
            if(obj == null || obj[0].result == null) {
               return false;
            }
            for(var i = 0; i < obj.length; i++) {
               $("#pici").html("");
               $("#pici").append("<input type='checkbox' name='pici' data-content='剩余重量：" + (parseFloat(obj[i].weight) - parseFloat(obj[i].Eweight)) + "吨,剩余件数:" + (parseFloat(obj[i].number) - parseFloat(obj[i].Enumber)) + obj[i].unit + "' value='" + obj[i].id + "' title='" + obj[i].id + "'>");
            }
            form.render('checkbox');
            $("#pici .layui-form-checkbox").on('click', function() {
               if($(this).hasClass("layui-form-checked")) {
                  layer.tips($(this).prev("input[type=checkbox]").attr("data-content"), this, {
                     tips: 1,
                     time: 4000
                  });
               }
            });
         },
         error: function() {
            layer.alert("获取出库批次数据错误！", {
               icon: 2,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

//出库查询函数,传入当前页和显示行数
function queryChuKu(curr, pageRow) {
   layui.use(['element', 'jquery', 'form', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;
      var laypage = layui.laypage;
      var element = layui.element;

      var chehao = $("#number").val(); //获得车号文本框中的值
      var client = $("#client").val(); //获得客户名称文本框中的值
      var loadIndex = layer.load(1); //风格1的加载
      $.ajax({
         type: "post",
         url: "/XGProject/exportSeed.do?flag=getDaiChuLi",
         async: true,
         data: {
            "pageNow": curr,
            "kehubianhao": chehao,
            "jiancheng": client,
            "pageRow": pageRow
         },
         dataType: "json",
         success: function(data) {
            layer.close(loadIndex);
            if(data == null || data == undefined || data == "" || data.length < 0 || data[0].result == null) {
               layer.alert("无出库待处理订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w = $(parent.window).width() - 234; //获取浏览器的宽，减去侧边栏的宽度  
            //出库订单表格渲染
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-155',
               width: w,
               limit: 10000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     sort: true, //是否产生排序
                     minWidth: 135,
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'huozhu',
                     title: '货主',
                     sort: false,
                     minWidth: 135,
                     fixed: true
                  }, {
                     field: 'faqitime',
                     title: '发起时间',
                     sort: true,
                     minWidth: 160
                  }, {
                     field: 'zhuangtai',
                     title: '订单状态',
                     sort: true,
                     minWidth: 100,
                     templet: '#chukuTpl'
                  }, {
                     field: 'youxiao',
                     title: '有效期(天)',
                     minWidth: 100
                  }, {
                     field: 'kehudanhao',
                     title: '客户单号',
                     sort: true,
                     minWidth: 100
                  }, {
                     field: 'zhongliang',
                     title: '出库重量',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'yunshu',
                     title: '运输方式',
                     sort: false,
                     minWidth: 90
                  }, {
                     field: 'chehao',
                     title: '车号',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'siji',
                     title: '司机姓名',
                     sort: false,
                     minWidth: 90
                  }, {
                     field: 'sijitel',
                     title: '司机电话',
                     sort: false,
                     minWidth: 125
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'mingcheng',
                     title: '名称',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'guige',
                     title: '规格',
                     sort: false,
                     minWidth: 150
                  }, {
                     field: 'caizhi',
                     title: '材质',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'shuxing',
                     title: '属性',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'chandi',
                     title: '产地',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'remark',
                     title: '备注',
                     sort: false,
                     minWidth: 100
                  }, {
                     fixed: 'right',
                     toolbar: '#barDemo',
                     minWidth: 180
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
            layer.close(loadIndex);
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
   layui.use(['element', 'jquery', 'form', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      var table = layui.table;
      var laypage = layui.laypage;
      var element = layui.element;

      var chehao = $("#number").val(); //获得车号文本框中的值
      var client = $("#client").val(); //获得客户名称文本框中的值
      var loadIndex = layer.load(1); //风格1的加载
      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=selectPlanInputDZAjax",
         async: true,
         data: {
            "danhao": chehao,
            "huozhu": client,
            "pageNow": curr,
            "pageRow": pageRow
         },
         dataType: "json",
         success: function(data) {
            layer.close(loadIndex);
            if(data == null || data == undefined || data == "" || data.length < 0 || data[0].result == null) {
               layer.alert("无入库待处理订单记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w = $(parent.window).width() - 234; //获取浏览器的宽，减去侧边栏的宽度  
            //入库订单表格渲染
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               height: 'full-155',
               cellMinWidth: 90,
               width: w,
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
                     fixed: true
                  }, {
                     field: 'inputCreateTime',
                     title: '发起时间',
                     sort: true,
                     minWidth: 160
                  }, {
                     field: "status",
                     title: '订单状态',
                     sort: true,
                     minWidth: 100,
                     templet: '#rukuTpl'
                  }, {
                     field: 'inputDefinedOne',
                     title: '有效期(天)',
                     minWidth: 100
                  }, {
                     field: 'inputClientNumber',
                     title: '客户单号',
                     sort: true,
                     minWidth: 100
                  }, {
                     field: 'iseedShouldWeight',
                     title: '入库重量',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'inputCarryType',
                     title: '运输方式',
                     sort: false,
                     minWidth: 90
                  }, {
                     field: 'inputPlateNumber',
                     title: '车号',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'inputDriverName',
                     title: '司机姓名',
                     sort: false,
                     minWidth: 90
                  }, {
                     field: 'inputDriverTel',
                     title: '司机电话',
                     sort: false,
                     minWidth: 125
                  }, {
                     field: 'goodsCategoryName',
                     title: '货物品类',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'goodsName',
                     title: '名称',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'goodsStandardName',
                     title: '规格',
                     sort: false,
                     minWidth: 150
                  }, {
                     field: 'goodsQualityName',
                     title: '材质',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'goodsPropertyName',
                     title: '属性',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'goodsYieldlyName',
                     title: '产地',
                     sort: false,
                     minWidth: 100
                  }, {
                     field: 'remark',
                     title: '备注',
                     sort: false,
                     minWidth: 100
                  }, {
                     fixed: 'right',
                     minWidth: 180,
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
            layer.close(loadIndex);
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