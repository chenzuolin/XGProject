<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>仓储管理系统-客户注册</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" type="text/css" href="../../css/public.css" />

   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane">
            <fieldset>
               <legend>客户信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">登录名</label>
                     <div class="layui-input-block">
                        <input type="text" name="loginName" placeholder="请输入登录名" id="loginName" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">登录密码</label>
                     <div class="layui-input-block">
                        <input type="password" name="loginPwd" placeholder="请输入登录密码" id="loginPwd" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">确认密码</label>
                     <div class="layui-input-block">
                        <input type="password" name="querenPwd" placeholder="请输入确认密码" id="querenPwd" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">负责人</label>
                     <div class="layui-input-block">
                        <input type="text" name="fuzeren" placeholder="请输入负责人" id="fuzeren" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">联系电话</label>
                     <div class="layui-input-block">
                        <input type="text" name="tel" placeholder="请输入联系电话" id="tel" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">邮箱</label>
                     <div class="layui-input-block">
                        <input type="text" name="email" placeholder="请输入邮箱地址" id="email" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <br />
            <fieldset>
               <legend>企业信息</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">单位名称</label>
                     <div class="layui-input-block">
                        <input type="text" name="mingcheng" placeholder="请输入单位名称" id="mingcheng" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">单位简称</label>
                     <div class="layui-input-block">
                        <input type="text" name="jiancheng" placeholder="请输入单位简称" id="jiancheng" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">单位助记符</label>
                     <div class="layui-input-block">
                        <input type="text" name="zhujifu" placeholder="请输入单位助记符" id="zhujifu" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">合同号</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetonghao" placeholder="请输入合同号" id="hetonghao" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">合同起始日期</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetongBegin" placeholder="请输入合同起始日期" id="hetongBegin" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">合同结束日期</label>
                     <div class="layui-input-block">
                        <input type="text" name="hetongFinish" placeholder="请输入合同结束日期" id="hetongFinish" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">单位地址</label>
                     <div class="layui-input-block">
                        <input type="text" name="addres" placeholder="请输入单位地址" id="addres" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">折扣</label>
                     <div class="layui-input-block">
                        <input type="text" name="zhekou" placeholder="请输入折扣,默认为1.0" value="1.0" id="zhekou" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
                     <label class="layui-form-label">信用额度</label>
                     <div class="layui-input-block">
                        <input type="text" name="xinyong" placeholder="请输入信用度,默认为1.0" value="1.0" id="xiyong" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
            </fieldset>
            <br />
            <div>
               <div class="layui-block" style="width: 100%; text-align: center;">
                  <button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即提交</button>
                  <button type="reset" class="layui-btn layui-btn-primary" id="daochu">重置</button>
               </div>
            </div>
         </form>
      </div>
   </body>
   <!--layui js-->
   <script src="../../plugin/layui/layui.js"></script>
   <script src="../../js/call-client.js"></script>
   <script type="text/javascript">
      inputBg();
   </script>

   <!--自写js-->
   <script src="js/keHuZhuCe.js"></script>

</html>