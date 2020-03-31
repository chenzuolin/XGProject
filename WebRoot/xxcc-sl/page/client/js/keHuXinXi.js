//对应table的工具条进行监听
layui.use(['table', 'layer', 'form'], function() {
   var table = layui.table,
      form = layui.form,
      layer = layui.layer;
   table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //查看详情
         document.location.href = "/XGProject/client.do?flag=goChakanClient&id=" + data.id;
      } else if(layEvent === 'del') {
         layer.confirm("确定停用吗？", {
            icon: 3,
            title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
            closeBtn: 2,
            anim: 4
         }, function(index) {
            //用ajax的方式停用客户
            $.ajax({
               type: "post",
               url: "/XGProject/client.do?flag=stopClient",
               async: false,
               data: {
                  "time": new Date().getTime(),
                  "id": data.id
               },
               dataType: 'text',
               success: function(data) {
                  var ok = data.indexOf("停用成功");
                  if(ok != -1) {
                     layer.alert("停用成功！", {
                        icon: 1,
                        closeBtn: 2,
                        anim: 4,
                        title: ['系统提示', 'font-size:16px;'],
                        skin: 'layui-layer-blue',
                     }, function(index) {
                        document.location.reload();
                        layer.close(index);
                     });
                  } else {
                     layer.alert("停用失败！", {
                        icon: 2,
                        closeBtn: 2,
                        anim: 4,
                        title: ['系统提示', 'font-size:16px;'],
                        skin: 'layui-layer-blue',
                     });
                  }
               },
               error: function() {
                  layer.alert("停用失败！", {
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
   });
   //表单提交监听
   form.on('submit(search)', function(data) {
      showContent(1, 30);
      return false;
   });

});

//当页面加载的时候调用显示内容的方法
showContent(1, 30);

//定义显示内容函数
function showContent(curr, pageRow) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;

      //ajax获取数据
      $.ajax({
         type: "post",
         url: "/XGProject/client.do?flag=selectClient",
         async: true,
         data: {
            "time": new Date().getTime(),
            "pageNow": curr, //当前页
            "pageRow": pageRow,
            "clientAbbreviation": $("#search").val(),
            "ff": "ajax" //定义的方式为ajax访问
         },
         dataType: 'json',
         success: function(jdata) {
            $("#showContent tbody").html("");
            $.each(jdata, function(i, obj) {
               $("#showContent tbody").append("<tr><td>" + obj.id + "</td><td>" + obj.loginName + "</td>" +
                  "<td>" + obj.createTime + "</td><td>" + obj.tel + "</td><td>" + obj.quanping + "</td>" +
                  "<td>" + obj.hetonghao + "</td><td>" + obj.fuzeren + "</td><td>" + obj.addres + "</td></tr>");
            });
            //把返回的数据渲染到对应的表格中
            var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度 
            var tableObj = table.render({
               data: jdata, //返回的json数据
               elem: '#showContent', //显示数据的容器
               widht:w,
               height: 'full-155',
               cellMinWidth: 90,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '客户编号', //显示的标题
                     width: 90,
                     fixed: false //是否是固定列宽
                  }, {
                     field: 'loginName',
                     title: '登录名',
                     sort: true,
                  }, {
                     field: 'createTime',
                     title: '注册时间',
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'tel',
                     title: '联系电话',
                     sort: false
                  }, {
                     field: 'quanping',
                     title: '单位名称',
                     width: 200,
                     sort: false
                  }, {
                     field: 'hetonghao',
                     title: '合同号',
                     sort: true
                  }, {
                     field: 'fuzeren',
                     title: '负责人',
                     sort: false
                  }, {
                     field: 'addres',
                     title: '单位地址',
                     width: 200,
                     sort: false
                  }, {
                     fixed: 'right',
                     minWidth: 180,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });

            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1 && parseInt(pageRow) == 30) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(jdata[0].pageCount) * parseInt(pageRow), //显示的总条数
                  limit: 30,
                  layout: ['prev', 'page', 'next', 'skip', 'limit'],
                  limits: [20, 40, 60, 100, 300, 500], //选择显示的行数
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        showContent(obj.curr, obj.limit);
                     }

                  }
               });
            }
         },
         error: function() { //当ajax访问出错的时候调用的方法
            jieshujiazai();
            //layui中layer.alert的标准写法
            layer.alert("请求数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            }, function(index) {
               layer.close(index);
            });
         }
      });
   });
}