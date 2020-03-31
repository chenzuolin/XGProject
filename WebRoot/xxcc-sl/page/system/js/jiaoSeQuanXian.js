layui.use(['form', 'jquery', 'table', 'layer'], function() {
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;

   var updateOpen; //打开的修改的弹出层定义
   var insertOpen; //开的的添加职务的弹出层定义

   //table工具条监听
   table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //点击修改
         //对应的赋值
         $("#zhiwuid").val(data.id);
         $("#zhiwuname").val(data.mingcheng);
         $("#zhiwumiaoshu").val(data.miaoshu);

         //当点击修改的时候查询对应的权限
         queryPower(data.id, "update");
         //打开对应的修改弹出层
         updateOpen = layer.open({
            type: 1,
            title: "职务修改",
            closeBtn: 2,
            anim: 4,
            content: $('#updateOpen'),
            area: ['900px', '700px'],
            btn: ['立即提交', '关闭'],
            yes: function(index, obj) {
               //点击立交提交的时候触发
               var zhiwu = $("#zhiwuname").val(); //获得修改职务文本框中额值
               var miaoshu = $("#zhiwumiaoshu").val(); //获得职务描述文本框中的值

               //判断职务名称是否为空
               if(zhiwu == null || zhiwu == undefined || zhiwu == "") {
                  layer.alert("职务名称不能为空！", {
                     icon: 2,
                     closeBtn: 2,
                     anim: 4
                  });
                  return false;
               }

               //判断职务的描述是否为空
               if(miaoshu == null || miaoshu == undefined || miaoshu == "") {
                  layer.alert("职务描述不能为空！", {
                     icon: 2,
                     closeBtn: 2,
                     anim: 4
                  });
                  return false;
               }

               var check = $("#updateOpen .layui-form-checkbox"); //
               var len = check.length;
               var functionId = [];
               var x = 0;
               for(var i = 0; i < len; i++) {
                  if(check.eq(i).hasClass("layui-form-checked")) {
                     functionId[x] = check.eq(i).prev("input[type=checkbox]").val();
                     x++;
                  }
               }
               var loadIndex = layer.load(1); //风格1的加载
               //用ajax的方式提交权限的修改
               $.ajax({
                  type: "post",
                  url: "/XGProject/interiorUserDuty.do?flag=updateInteriorUserDuty",
                  data: {
                     "interiorUserDutyName": zhiwu,
                     "interiorUserDutyId": $('#zhiwuid').val(),
                     "interiorUserDutyRemark": miaoshu,
                     "functionId": functionId
                  },
                  async: false,
                  dataType: "text",
                  success: function(data) {
                     layer.close(loadIndex);
                     var ok = data.indexOf("修改成功");
                     if(ok != -1) {
                        layer.alert("修改成功！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4
                        }, function(index) {
                           document.location.reload();
                           layer.close(index);
                        });
                     } else {
                        layer.alert("修改失败！", {
                           icon: 5,
                           closeBtn: 2,
                           anim: 4
                        });
                     }
                  },
                  error: function() {
                     layer.close(loadIndex);
                     layer.alert("数据上传错误！", {
                        icon: 2,
                        closeBtn: 2,
                        anim: 4
                     });
                  }
               });
            },
            btn2: function(index, obj) {
               //点击关闭的时候触发
               layer.close(index);
            }
         });
      } else if(layEvent === 'del') {
         layer.confirm("确定停用吗？", {
            icon: 3,
            title: "系统提示",
            closeBtn: 2,
            anim: 4,
         }, function(index) {
            //用ajax的方式停用职务
            $.ajax({
               type: "post",
               url: "/XGProject/interiorUserDuty.do?flag=stopInteriorUserDuty",
               data: {
                  "id": data.id
               },
               async: false,
               dataType: "text",
               success: function(data) {
                  var ok = data.indexOf("停用成功");
                  if(ok != -1) {
                     layer.alert("停用成功！", {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4
                     }, function(index) {
                        document.location.reload();
                        layer.close(index);
                     });
                  } else {
                     layer.alert("停用失败！", {
                        icon: 5,
                        closeBtn: 2,
                        anim: 4
                     });
                  }
               },
               error: function() {
                  layer.alert("数据上传错误！", {
                     icon: 2,
                     closeBtn: 2,
                     anim: 4
                  });
               }
            });
         });
      }
   });

   //当点击添加职务的时候打开相应的弹出层
   $("#insert").click(function() {
      queryPower("wu", "insert");
      insertOpen = layer.open({
         type: 1,
         title: "添加职务",
         closeBtn: 2,
         anim: 4,
         content: $('#insertOpen'),
         area: ['900px', '700px'],
         btn: ['立即提交', '关闭'],
         yes: function(index, obj) {
            //点击立交提交的时候触发
            //用ajax的方式提交新提价的职务
            var zhiwu = $("#zhiwunameIn").val(); //获得修改职务文本框中额值
            var miaoshu = $("#zhiwumiaoshu2").val(); //获得职务描述文本框中的值

            //判断职务名称是否为空
            if(zhiwu == null || zhiwu == undefined || zhiwu == "") {
               layer.alert("职务名称不能为空！", {
                  icon: 2,
                  closeBtn: 2,
                  anim: 4
               });
               return false;
            }

            //判断职务的描述是否为空
            if(miaoshu == null || miaoshu == undefined || miaoshu == "") {
               layer.alert("职务描述不能为空！", {
                  icon: 2,
                  closeBtn: 2,
                  anim: 4
               });
               return false;
            }

            var check = $("#insertOpen .layui-form-checkbox"); //
            var len = check.length;
            var functionId = [];
            var x = 0;
            for(var i = 0; i < len; i++) {
               if(check.eq(i).hasClass("layui-form-checked")) {
                  functionId[x] = check.eq(i).prev("input[type=checkbox]").val();
                  x++;
               }
            }
            var loadIndex = layer.load(1); //风格1的加载
            //用ajax的方式提交权限的添加
            $.ajax({
               type: "post",
               url: "/XGProject/interiorUserDuty.do?flag=addinteriorUserDuty",
               data: {
                  "interiorUserDutyName": zhiwu,
                  "interiorUserDutyRemark": miaoshu,
                  "functionId": functionId
               },
               async: false,
               dataType: "text",
               success: function(data) {
                  layer.close(loadIndex);
                  var ok = data.indexOf("添加成功");
                  if(ok != -1) {
                     layer.alert("添加成功！", {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4
                     }, function(index) {
                        document.location.reload();
                        layer.close(index);
                     });
                  } else {
                     layer.alert("添加失败！", {
                        icon: 5,
                        closeBtn: 2,
                        anim: 4
                     });
                  }
               },
               error: function() {
                  layer.alert("数据上传错误！", {
                     icon: 2,
                     closeBtn: 2,
                     anim: 4
                  });
               }
            });
         },
         btn2: function(index, obj) {
            //点击关闭的时候触发
            layer.close(index);
         }
      });
   });

});

//当页面加载的时候调用显示内容函数
showContent();

//显示职务内容函数
function showContent() {
   layui.use(['element', 'table', 'jquery', 'layer'], function() {
      var table = layui.table,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;

      //用ajax请求显示的职务的数据(指全部的职务)
      $.ajax({
         type: "post",
         url: "/XGProject/interiorUserDuty.do?flag=selectInteriorUserDuty&ff=ajax",
         async: true,
         dataType: "json",
         success: function(data) {
            if(data == null || data == "" || data == undefined) {
               layer.alert("无职务记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
               });
            }
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度  
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width: w,
               height: 'full-125',
               limit: 1000000, //默认显示的行数
               even: true,
               page: true, //是否显示分页
               limits: [10, 20, 50],
               limit: 10, //每页默认显示的数量
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '职务编号', //显示的标题
                     width: 100, //列的宽度
                     sort: false, //是否产生排序
                     fixed: false //是否是固定列宽
                  }, {
                     field: 'mingcheng',
                     title: '职务名称',
                     width: 150,
                     sort: false,
                  }, {
                     field: 'miaoshu',
                     title: '职务描述',
                     width: 450,
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     fixed: "right",
                     width: 200,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
         },
         error: function() {
            layer.alert("请求数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
   });
}

//用ajax查询所有的权限
function queryPower(zhiwuid, type) {
   layui.use(['jquery', 'layer', 'table'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;
      var table = layui.table;

      $.ajax({ // ajax提交方式
         type: "post",
         url: "/XGProject/functions.do?flag=goSelectFunctions",
         dataType: "json",
         success: function(data) {
            var xitongguanli = []; //权限类别为系统管理的数据
            var kehuguanli = []; //客户管理的数据
            var shujuzhongxin = []; //数据中心数据
            var caiwuguanli = []; //财务管理数据
            var cangchuziliao = []; //仓储资料数据
            var cangchuyewu = []; //仓储业务数据
            var shouyequanxian = []; //首页权限数据

            //遍历返回的数据追加到不同的类别数据中
            $.each(data, function(i, obj) {
               if(obj.leibei == "系统管理") {
                  xitongguanli.push(obj);
               }
               if(obj.leibei == "客户管理") {
                  kehuguanli.push(obj);
               }
               if(obj.leibei == "数据中心") {
                  shujuzhongxin.push(obj);
               }
               if(obj.leibei == "财务管理") {
                  caiwuguanli.push(obj);
               }
               if(obj.leibei == "仓储资料") {
                  cangchuziliao.push(obj);
               }
               if(obj.leibei == "仓储业务") {
                  cangchuyewu.push(obj);
               }
               if(obj.leibei == "首页权限") {
                  shouyequanxian.push(obj);
               }
            });

            if(type == "update") {
               //调用相应的加载dom的方法
               TableDomLoad("#xitongguanli", xitongguanli);
               TableDomLoad("#kehuguanli", kehuguanli);
               TableDomLoad("#shujuzhongxin", shujuzhongxin);
               TableDomLoad("#caiwuguanli", caiwuguanli);
               TableDomLoad("#cangchuziliao", cangchuziliao);
               TableDomLoad("#cangchuyewu", cangchuyewu);
               TableDomLoad("#shouyequanxian", shouyequanxian);
            } else {
               //调用相应的加载dom的方法
               TableDomLoad("#xitongguanli2", xitongguanli);
               TableDomLoad("#kehuguanli2", kehuguanli);
               TableDomLoad("#shujuzhongxin2", shujuzhongxin);
               TableDomLoad("#caiwuguanli2", caiwuguanli);
               TableDomLoad("#cangchuziliao2", cangchuziliao);
               TableDomLoad("#cangchuyewu2", cangchuyewu);
               TableDomLoad("#shouyequanxian2", shouyequanxian);
            }

            //调用对应的判断是否具有此权限的方法
            if(zhiwuid == "wu") {
               $("#insertOpen form .layui-tab .layui-tab-content table").each(function() {
                  table.init($(this).attr("lay-filter"), {
                     width: 880,
                     height: 'full',
                     limit: 1000000
                  });
               });
            } else {
               TableCheckBox("xitongguanli,kehuguanli,shujuzhongxin,caiwuguanli,cangchuziliao,cangchuyewu,shouyequanxian", zhiwuid);
            }
         },
         error: function() {
            layer.alert("请求权限数据错误！", {
               icon: 2
            });
         }
      });
   });
}

//渲染对应的数据表格，加载相应的dom结构,id:为加载的容器的id,data:相应的json数据
function TableDomLoad(id, data) {
   layui.use(['jquery', 'table'], function() {
      var $ = layui.jquery;
      var table = layui.table;
      //先将对应的内容清空再进行追加
      $(id).children("tbody").html("");
      $.each(data, function(i, obj) {
         $(id).children("tbody").append("<tr><td><input type='checkbox' name='functionId' value='" + obj.id +
            "' id='td" + obj.id + "' lay-skin='primary' title='" + obj.name + "' />" +
            "</td><td>" + obj.beizhu + "</td>< /tr>");
      });
   });
}

//判断某个职务是否具有此权限，如果具有则选中
function TableCheckBox(id, zhiwuid) {
   layui.use(['element', 'jquery', 'layer', 'form', 'table'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;
      var form = layui.form;
      var element = layui.element;
      var table = layui.table;
      $.ajax({ // ajax提交方式
         type: "post",
         url: "/XGProject/powers.do?flag=selectPowersAll",
         data: {
            "id": zhiwuid
         },
         dataType: "json",
         async: false,
         success: function(data) {
            if(data.length > 0) {
               $.each(data, function(i, item) {
                  $("#updateOpen input[type='checkbox']").each(function() {
                     // 比较，如果与数据库中数据相同就赋值为true
                     if($(this).val() == item.id) {
                        $(this).attr("checked", true);
                     }
                  });

               });
            }
            $("#updateOpen form .layui-tab .layui-tab-content table").each(function() {
               table.init($(this).attr("lay-filter"), {
                  width: 880,
                  height: 'full',
                  limit: 1000000
               });
            });
         },
         error: function() {
            layer.alert("获取权限判断数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
            });
         }
      });
   });
}