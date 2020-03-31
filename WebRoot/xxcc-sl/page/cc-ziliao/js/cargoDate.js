/*
 * 
 */
//ajax更新货物资料的方法,传入地址、条件、数据类型这三个参数
function goodsContent(url, cond, type) {
   var data;
   layui.use('jquery', function() {
      var $ = layui.jquery;
      //获取数据
      $.ajax({
         type: 'post',
         url: url,
         async: false,
         data: cond,
         dataType: type,
         success: function(datas) {
            data = JSON.stringify(datas);
         },
         error: function() {
            data = 'error';
         }
      });
   });
   return data;
}