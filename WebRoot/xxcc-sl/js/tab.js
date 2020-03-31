/*
	@Author: 冯海军 石磊
	@Time: 2017-10
	@Tittle: tab
	@Description: 点击对应按钮添加新窗口
*/

var ses = []; //定义session,并保存为json数据
layui.use(['jquery', 'layer', 'element'], function() {
   var $ = layui.jquery,
      layer = layui.layer,
      element = layui.element;
   
   //对导航菜单的监听，并且添加新选项卡的方法
   element.on('nav(demo)', function(elem) {
      if($(elem).find('cite').text() == '注销系统') {
         $('.lockcms').click();
         return false;
      }
      if(window.sessionStorage.getItem('pageSession')) {
         ses = JSON.parse(sessionStorage.getItem('pageSession'));
      }

      //通过选项卡的内容判断选项卡是否存在
      var tabIndex = false;
      $(".layui-tab-title.topCard li").each(function() {
         if($(this).find('cite').text() == $(elem).find('cite').text()) {
            $(this).click();

            //如果页面已经打开，切换到相应的页面，并且跳出遍历循环。
            tabIndex = true;
            return;
         }
      });
      layer.tips($(elem).find('cite').text(), this, {
         tips: [4, '#1E9FFF'],
         time: 2000
      });
      //如果页面已打开，跳出此方法，不继续执行添加选项卡的方法
      if(tabIndex) {
         return false;
      }

      //如果选项卡打开了10个，弹出提示
      if($('.layui-tab-title li').length >= 10) {
         layer.open({
            type: 0,
            icon: 5,
            btnAlign: 'c',
            content: '不能再打开了，不然系统会卡啊！！！' //这里content是一个普通的String
         });
         return false;
      }
      //如果页面不存在，则根据菜单的相应内容添加新的选项卡
      var icon = $(elem).parent('dl').prev('a').find('i').attr('class');
      var url = $(elem).find('a').attr('data-url');
      var ids = new Date().getTime();
      element.tabAdd('bodyTab', {
         title: '<i class="' + icon + '"></i>' +
            '<cite>' + $(elem).find('cite').text() + '</cite>',
         content: "<iframe src='" + url + "' data-id=''></frame>",
         id: ids
      });
      $(this).click();

      //session创建步骤
      var curses = {
         "icon": icon,
         "title": $(elem).find('cite').text(),
         "href": url,
         "layId": ids
      };
      ses.push(curses);
      //创建全部选项卡的session并且把json格式转换为字符串，重置session方法名.stringify
      window.sessionStorage.setItem('pageSession', JSON.stringify(ses));
      //创建激活的选项卡的session
      window.sessionStorage.setItem('curSession', JSON.stringify(curses));
      element.init();
      $(".layui-tab-title.tabCard li").last().click();
   });

   //当刷新页面是获取session，以便再次创建原有的选项卡
   $(function() {
      if(window.sessionStorage.getItem('pageSession')) {
         //把json字符串转换为json格式,解析session。方法名.parse
         var sesdata = JSON.parse(window.sessionStorage.getItem('pageSession'));
         $.each(sesdata, function(i, obj) {
            element.tabAdd('bodyTab', {
               title: '<i class="' + obj.icon + '"></i>' +
                  '<cite>' + obj.title + '</cite>',
               content: "<iframe src='" + obj.href + "' data-id=''></frame>",
               id: obj.layId
            });
         });
         var curses = JSON.parse(window.sessionStorage.getItem('curSession'));
         element.tabChange('bodyTab', curses.layId);

      }

   });

   //	切换选项卡时监听激活的选项卡
   $(document).on("click", ".layui-tab-title.topCard li", function() {
      //    openNav($(this).find('cite').text());
      var curses = {
         "icon": $(this).children('i').first().attr('class'),
         "title": $(this).find('cite').text(),
         "href": $(this).parent('ul').next().next().children('div').eq($(this).index()).children('iframe').attr('src'),
         "layId": $(this).attr('lay-id')
      };
      window.sessionStorage.setItem('curSession', JSON.stringify(curses));

   });

   //	监听选项卡的关闭按钮,移除掉关闭的选项卡的session，并重置session
   $(document).on("click", ".layui-tab-close", function(e) {
      //		alert($(e).text());
      var sesdata = JSON.parse(window.sessionStorage.getItem('pageSession'));
      //		alert(sesdata.length);
      var sstitle = $(this).parent('li').children('cite').text();
      for(var i = 0; i < sesdata.length; i++) {
         if(sesdata[i].title == sstitle) {
            //				alert(obj.title == sstitle);
            sesdata.splice(i, 1);
            break;
         }
      }
      sessionStorage.setItem('pageSession', JSON.stringify(sesdata));
   });

   var pageClose = function(close) {
      this.close = close;
      switch(this.close) {
         case 'all':
            var len = $('.layui-tab-title li').length;
            for(var i = len; i > 0; i--) {
               element.tabDelete('bodyTab', $('.layui-tab-title li').eq(i).attr('lay-id'));
               element.init();
            }
            ses = [];
            sessionStorage.setItem('pageSession', JSON.stringify(ses));
            break;
            //=========================================================	
         case 'other':
            var len = $('.layui-tab-title li').length;
            var curses = JSON.parse(window.sessionStorage.getItem('curSession')); //重置session

            for(var i = len; i > 0; i--) {
               if($('.layui-tab-title li').eq(i).attr('lay-id') != curses.layId) {
                  element.tabDelete('bodyTab', $('.layui-tab-title li').eq(i).attr('lay-id'));
                  element.init();
               }
            }
            ses = [];
            var curs = {
               "icon": curses.icon,
               "title": curses.title,
               "href": curses.href,
               "layId": curses.layId
            };
            ses.push(curs);
            //				alert(ses[0].title);
            window.sessionStorage.setItem('pageSession', JSON.stringify(ses)); //重置session
            break;
      }
   };
   //	选项卡'全部关闭'的方法
   $('.allpage').click(function() {
      pageClose('all');
   });

   // 选项卡'关闭其他'方法
   $('.otherpage').click(function() {
      pageClose('other');

   });
   //退出
   $(".signOut").click(function() {
      window.sessionStorage.removeItem("pageSession");
      ses = [];
      window.sessionStorage.removeItem("curSession");
   });
});

function openNav(cite) {
   layui.use('jquery', function() {
      var $ = layui.$;
      $('#leftnav dd').each(function() {
         if($(this).find('cite').text() == cite) {
            $(this).parent('dl').prev('a').click();
            $(this).click();
         }
      })
   })
}