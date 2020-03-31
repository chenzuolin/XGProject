/*
 * 
 * 
 */
//select便签选取客户名称的方法
function client(_this) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      //获取客户信息的方法

      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu',//client.do?flag=selectClient
         async: false,
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $(_this).append('<option value="">请选择客户</option>');
               }
               $(_this).append('<option value="' + obj.id + '">' + obj.jiancheng + " | " + (obj.sign).toLowerCase() + '</option>');
            });
         },
         error: function() {
            layer.alert('获取客户数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      form.render('select');
   });
}

//通过客户名称进行模糊查询
function clientName(_this) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery;
      var form = layui.form;
      var layer = layui.layer;
      //获取客户信息的方法

      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu',
         async: false,
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $(_this).append('<option value="">请选择客户</option>');
               }
               $(_this).append('<option value="' + obj.jiancheng + '">' + obj.jiancheng + " | " + (obj.sign).toLowerCase() + '</option>');
            });
         },
         error: function() {
            layer.alert('获取客户数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      form.render('select');
   });
}
//出库和过户时选取获取资料的查询方法。  资料中包含的字段相互间存在关联
function cargoPinlei(_this, clientId) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var form = layui.form;
      var $ = layui.jquery;
      var layer = layui.layer;

      if(clientId == null || clientId == undefined || clientId == "") {
         $(_this).html("<option value=''>无</option>");
         cargoName('#cargoName', clientId, $(_this).val());
         return false;
      }

      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=pinlei',
         async: false,
         data: {
            'clientBySstockClientId': clientId
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].pinleiId == result[j].pinleiId && i != j) {
                     data[i].pinleiId = "";
                  }
               }
            }
            $.each(data, function(i, obj) {
               if(obj.pinleiId != "") {
                  $(_this).append('<option value="' + obj.pinleiId + '">' + obj.pinlei + '</option>');
               }
            });
         },
         error: function() {
            layer.alert('获取货物品类数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      cargoName('#cargoName', clientId, $(_this).val());
   });
}
//查询货物名称
function cargoName(_this, clientId, pinlei) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      if(pinlei == null || pinlei == undefined || pinlei == "") {
         $(_this).html("<option value=''>无</option>");
         cargoGuige('#cargoGuige', clientId, pinlei, $(_this).val());
         return false;
      }
      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=mingcheng',
         async: false,
         data: {
            'clientBySstockClientId': clientId,
            'pinlei': pinlei
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].mingcheng == result[j].mingcheng &&
                     i != j) {
                     data[i].mingcheng = "";
                  }
               }
            }
            $.each(data, function(i, obj) {
               if(obj.mingcheng != "") {
                  $(_this).append('<option value="' + obj.mingcheng + '">' + obj.mingcheng + '</option>');
               }
            });
         },
         error: function() {
            layer.alert('获取货物名称数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      cargoGuige('#cargoGuige', clientId, pinlei, $(_this).val());
   });
}
//查询货物规格
function cargoGuige(_this, clientId, pinlei, goodsname) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      if(goodsname == null || goodsname == undefined || goodsname == "") {
         $(_this).html("<option value=''>无</option>");
         cargoCaizhi('#cargoCaizhi', clientId, pinlei, goodsname, $(_this).val());
         return false;
      }
      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=guige',
         async: false,
         data: {
            'clientBySstockClientId': clientId,
            'pinlei': pinlei,
            'goodsname': goodsname,
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].guigeId == result[j].guigeId && i != j) {
                     data[i].guigeId = "";
                  }
               }
            }
            $.each(data, function(i, obj) {
               if(obj.guigeId != "") {
                  $(_this).append('<option value="' + obj.guigeId + '">' + obj.gui + '</option>');
               }
            });
         },
         error: function() {
            layer.alert('获取货物规格数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      cargoCaizhi('#cargoCaizhi', clientId, pinlei, goodsname, $(_this).val());
   });
}
//查询货物材质
function cargoCaizhi(_this, clientId, pinlei, goodsname, guige) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      if(guige == null || guige == undefined || guige == "") {
         $(_this).html("<option value=''>无</option>");
         cargoShuxing('#cargoShuxing', clientId, pinlei, goodsname, guige, $(_this).val());
         return false;
      }
      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=caizhi',
         async: false,
         data: {
            'clientBySstockClientId': clientId,
            'pinlei': pinlei,
            'goodsname': goodsname,
            'guige': guige
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].caizhiId == result[j].caizhiId &&
                     i != j) {
                     data[i].caizhiId = "";
                  }
               }
            }
            $.each(data, function(i, obj) {
               if(obj.caizhiId != "") {
                  $(_this).append('<option value="' + obj.caizhiId + '">' + obj.caizhi + '</option>');
               }
            });
         },
         error: function() {
            layer.alert('获取货物材质数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      cargoShuxing('#cargoShuxing', clientId, pinlei, goodsname, guige, $(_this).val());
   });
}
//查询货物属性
function cargoShuxing(_this, clientId, pinlei, goodsname, guige, caizhi) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      if(caizhi == null || caizhi == undefined || caizhi == "") {
         $(_this).html("<option value=''>无</option>");
         cargoChandi('#cargoChandi', clientId, pinlei, goodsname, guige, caizhi, $(_this).val());
         return false;
      }
      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=shuxing',
         async: false,
         data: {
            'clientBySstockClientId': clientId,
            'pinlei': pinlei,
            'goodsname': goodsname,
            'guige': guige,
            'caizhi': caizhi
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].shuxingId == result[j].shuxingId && i != j) {
                     data[i].shuxingId = "";
                  }
               }
            }
            $.each(data, function(i, obj) {
               if(obj.shuxingId != "") {
                  $(_this).append('<option value="' + obj.shuxingId + '">' + obj.shuxing + '</option>');
               }
            });
         },
         error: function() {
            layer.alert('获取货物属性数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      cargoChandi('#cargoChandi', clientId, pinlei, goodsname, guige, caizhi, $(_this).val());
   });
}
//查询货物产地
function cargoChandi(_this, clientId, pinlei, goodsname, guige, caizhi, shuxing) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      if(shuxing == null || shuxing == undefined || shuxing == "") {
         $(_this).html("<option value=''>无</option>");
         return false;
      }
      //获取货物信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/shiftStockSeed.do?flag=getKeHuKuCun&ff=chandi',
         async: false,
         data: {
            'clientBySstockClientId': clientId,
            'pinlei': pinlei,
            'goodsname': goodsname,
            'guige': guige,
            'caizhi': caizhi,
            'shuxing': shuxing
         },
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $("#weight").html("");
            if(data == null || data == undefined || data.length <= 0 || data[0].result == null) {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            $.each(data, function(i, obj) {
               //zhongliang
               $(_this).append('<option value="' + obj.goodsId + '">' + obj.chandi + '</option>');
               $("#weight").append("<option value='" + obj.goodsId + "'>" + obj.zhongliang + "</option>");
            });
         },
         error: function() {
            layer.alert('获取货物产地数据错误！', {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
   });
}

//给input标签添加背景颜色
function inputBg() {
   layui.use('jquery', function() {
      var $ = layui.$;
      //给input标签添加背景颜色
      $(function() {
         $("input").focus(function() {
            $('input').css("background-color", "#fff");
            $(this).css("background-color", "#5FB878");
         });
         $("input").blur(function() {
            $(this).css("background-color", "#fff");
         });
      });
   });
}

//获取货物资料的方法  资料中包含的字段相互间没有关联
//查询入库品类
function RuKuPinLei(_this) {
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;
      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=selectGoodsPinlei",
         async: false,
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].pinleiId == result[j].pinleiId && i != j) {
                     data[i].pinleiId = "";
                  }
               }
            }
            for(var i = 0; i < data.length; i++) {
               if(data[i].pinleiId != "") {
                  $(_this).append("<option value='" + data[i].pinleiId + "'>" + data[i].pinlei + "</option>");
               }
            }
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      RuKugoodsName('#cargoName', $(_this).val());
   });
}

//查询入库货物名称
function RuKugoodsName(_this, pinlei) {
   if(pinlei == null || pinlei == undefined || pinlei == "") {
      $(_this).html("<option value=''>无</option>");
      RuKuGuiGe('#cargoGuige', pinlei, $(_this).val());
      return false;
   }
   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=xuanGoodsPinlei",
         async: false,
         data: {
            "pinlei": pinlei
         },
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }

            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].mingcheng == result[j].mingcheng && i != j) {
                     data[i].mingcheng = "";
                  }
               }
            }
            for(var i = 0; i < data.length; i++) {
               if(data[i].mingcheng != "") {
                  $(_this).append("<option value='" + data[i].mingcheng + "'>" + data[i].mingcheng + "</option>");
               }
            }
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      RuKuGuiGe('#cargoGuige', pinlei, $(_this).val());
   });
}

//查询入库货物规格
function RuKuGuiGe(_this, pinlei, goodsName) {
   if(goodsName == null || goodsName == undefined || goodsName == "") {
      $(_this).html("<option value=''>无</option>");
      RuKuCaiZhi('#cargoCaizhi', pinlei, goodsName, $(_this).val());
      return false;
   }

   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=xuanGoodsName",
         async: false,
         data: {
            "pinlei": pinlei,
            "goodsname": goodsName
         },
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].guigeId == result[j].guigeId && i != j) {
                     data[i].guigeId = "";
                  }
               }
            }
            for(var i = 0; i < data.length; i++) {
               if(data[i].guigeId != "") {
                  $(_this).append("<option value='" + data[i].guigeId + "'>" + data[i].gui + "</option>");
               }
            }
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      RuKuCaiZhi('#cargoCaizhi', pinlei, goodsName, $(_this).val());
   });
}

//查询入库货物材质
function RuKuCaiZhi(_this, pinlei, goodsName, guige) {
   if(guige == null || guige == undefined || guige == "") {
      $(_this).html("<option value=''>无</option>");
      RuKuShuXing('#cargoShuxing', pinlei, goodsName, guige, $(_this).val());
      return false;
   }

   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=xuanGuige",
         async: false,
         data: {
            "pinlei": pinlei,
            "goodsname": goodsName,
            "guige": guige
         },
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].caizhiId == result[j].caizhiId && i != j) {
                     data[i].caizhiId = "";
                  }
               }
            }
            for(var i = 0; i < data.length; i++) {
               if(data[i].caizhiId != "") {
                  $(_this).append("<option value='" + data[i].caizhiId + "'>" + data[i].caizhi + "</option>");
               }
            }
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      RuKuShuXing('#cargoShuxing', pinlei, goodsName, guige, $(_this).val());
   });
}

//查询入库货物属性
function RuKuShuXing(_this, pinlei, goodsName, guige, caizhi) {
   if(caizhi == null || caizhi == undefined || caizhi == "") {
      $(_this).html("<option value=''>无</option>");
      RuKuChanDi('#cargoChandi', pinlei, goodsName, guige, caizhi, $(_this).val());
      return false;
   }

   layui.use(['jquery', 'layer'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;

      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=xuanCaizhi",
         async: false,
         data: {
            "pinlei": pinlei,
            "goodsname": goodsName,
            "guige": guige,
            "caizhi": caizhi
         },
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }

            var result = data;
            for(var i = 0; i < data.length; i++) {
               for(var j = 0; j < result.length; j++) {
                  if(data[i].shuxingId == result[j].shuxingId && i != j) {
                     data[i].shuxingId = "";
                  }
               }
            }
            for(var i = 0; i < data.length; i++) {
               if(data[i].shuxingId != "") {
                  $(_this).append("<option value='" + data[i].shuxingId + "'>" + data[i].shuxing + "</option>");
               }
            }
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
      RuKuChanDi('#cargoChandi', pinlei, goodsName, guige, caizhi, $(_this).val());
   });
}

//查询入库货物产地
function RuKuChanDi(_this, pinlei, goodsName, guige, caizhi, shuxing) {
   if(shuxing == null || shuxing == undefined || shuxing == "") {
      $(_this).html("<option value=''>无</option>");
      return false;
   }

   layui.use(['jquery', 'layer', 'form'], function() {
      var $ = layui.jquery;
      var layer = layui.layer;
      var form = layui.form;

      $.ajax({
         type: "post",
         url: "/XGProject/input.do?flag=xuanShuxin",
         async: false,
         data: {
            "pinlei": pinlei,
            "goodsname": goodsName,
            "guige": guige,
            "caizhi": caizhi,
            "shuxing": shuxing
         },
         dataType: "json",
         success: function(data) {
            $(_this).html("");
            if(data == null || data == undefined || data == "") {
               $(_this).html("<option value=''>无</option>");
               return false;
            }
            for(var i = 0; i < data.length; i++) {
               $(_this).append("<option value='" + data[i].goodsId + "'>" + data[i].chandi + "</option>");
            }
            form.render('select'); //刷新select选择框渲染
         },
         error: function() {
            layer.alert("获取货物数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4
            });
         }
      });
   });
}
//==============================
//select标签选取部门名称的方法
function department(_this) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery,
         layer = layui.layer,
         form = layui.form;
      //获取客户信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/section.do?flag=selectSection',
         async: false,
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $(_this).append('<option value="">请选择部门</option>');
               }
               $(_this).append('<option value="' + obj.id + '">' + obj.department + '</option>');
            });
         },
         error: function() {
            layer.alert("获取部门数据错误！", {
               icon: 2,
               closeBtn: 2,
            });
         }
      });
      form.render('select');
   });
}
//select标签选取班组名称的方法
function team(_this) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery,
         layer = layui.layer,
         form = layui.form;
      //获取客户信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/classT.do?flag=selectclassT',
         async: false,
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $(_this).append('<option value="">请选择部门</option>');
               }
               $(_this).append('<option value="' + obj.id + '">' + obj.team + '</option>');
            });
         },
         error: function() {
            layer.alert("获取部门数据错误！", {
               icon: 2,
               closeBtn: 2,
            });
         }
      });
      form.render('select');
   });
}
//select标签选取职务名称的方法
function duty(_this) {
   layui.use(['jquery', 'form', 'layer'], function() {
      var $ = layui.jquery,
         layer = layui.layer,
         form = layui.form;
      //获取客户信息的方法
      $.ajax({
         type: 'post',
         url: '/XGProject/interiorUserDuty.do?flag=selectInteriorUserDuty&ff=ajax',
         async: false,
         dataType: 'json',
         success: function(data) {
            $(_this).html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $(_this).append('<option value="">请选择职务</option>');
               }
               $(_this).append('<option value="' + obj.id + '">' + obj.mingcheng + '</option>');
            });
         },
         error: function() {
            layer.alert("获取部门数据错误！", {
               icon: 2,
               closeBtn: 2,
            });
         }
      });
      form.render('select');
   });
}