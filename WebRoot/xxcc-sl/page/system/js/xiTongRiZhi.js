layui.use(['laydate', 'form', 'jquery'], function() {
   var laydate = layui.laydate;
   var form = layui.form;
   var $ = layui.jquery;

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
      showContent(1, 30);
      return false;
   });

});

//当页面加载的时候调用显示内容的方法
showContent(1, 30);

//显示页面主要内容的函数
function showContent(curr, pageRow) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;

      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var loginName = $("#loginName").val(); //获得登录名文本框中的值
      var content = $("#content").val(); //获得操作内容文本框中的值

      //用ajax请求数据
      $.ajax({
         type: "post",
         url: "/XGProject/loginLog.do?flag=getAllLLog&ff=ajax",
         data: {
            "begin": begin,
            "finish": finish,
            "llogName": loginName,
            "llogRemark": content,
            "time": new Date().getTime(),
            "pageNow": curr,
            "pageRow": pageRow
         },
         async: true,
         dataType: "json",
         success: function(data) {
            if(data == null || data == "" || data == undefined) {
               layer.alert("无系统日志记录！", {
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
               height: 'full-210',
               cellMinWidth: 200,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '日志编号', //显示的标题
                     width: 135, //列的宽度
                     sort: false, //是否产生排序
                     fixed: false //是否是固定列宽
                  }, {
                     field: 'time',
                     title: '登录时间',
                     width: 180,
                     sort: true,
                  }, {
                     field: 'loginName',
                     title: '登录名',
                     width: 120,
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     field: 'ip',
                     title: 'IP地址',
                     width: 160,
                     sort: false
                  }, {
                     field: 'type',
                     title: '操作类型',
                     width: 160,
                     sort: false
                  }, {
                     field: 'remark',
                     title: '操作内容',
                     minWidth: 300,
                     sort: false
                  }]
               ] //设置表头
            });

            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1 && parseInt(pageRow) == 30) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(data[0].pageCount) * parseInt(pageRow), //显示的总条数
                  limit: 30,
                  layout: ['prev', 'page', 'next', 'skip', 'limit'],
                  limits: [30, 50, 80, 100, 300, 500], //选择显示的行数
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
         error: function() {
            layer.alert("数据请求错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
            });
         }

      });
   });
}