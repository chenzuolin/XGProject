<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>仓储管理系统-订单待处理</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" media="all">
   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset class="buttons">
               <legend>待处理订单</legend>
               <div class="layui-form-item" style="margin-bottom: 0;">
                  <div class="layui-inline">
                     <label class="layui-form-label">业务类型</label>
                     <div class="layui-input-block">
                        <select name="type" id="type" lay-search>
                           <option value="出库订单" selected>出库订单</option>
                           <option value="入库订单">入库订单</option>
                        </select>
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">客户名称</label>
                     <div class="layui-input-block">
                        <select name="client" id="client" lay-search>
                           <option value="">请选择客户</option>
                        </select>
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">司机车号</label>
                     <div class="layui-input-block">
                        <input type="text" name="number" id="number" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-inline">
                     <button class="layui-btn" lay-submit="" lay-filter="query" id="chaxun">立即提交</button>
                     <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                  </div>
               </div>
            </fieldset>
         </form>
         <!--显示主要内容的容器-->
         <table id="showContent" lay-filter="demo"></table>

         <!--工具条显示-->
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="edit"><span class="layui-icon">&#xe631;</span>操作</a>
         </script>
         <!--给出库订单状态添加颜色-->
         <script type="text/html" id="chukuTpl">
            {{# if(d.zhuangtai === '计划出库'){ }}
            <span style="color: red;">{{ d.zhuangtai }}</span> {{# } else { }}
            <span style="color: blue;">{{ d.zhuangtai }}</span> {{# } }}
         </script>
         <!--给入库订单状态添加颜色-->
         <script type="text/html" id="rukuTpl">
            {{# if(d.status === '计划入库'){ }}
            <span style="color: red;">{{ d.status }}</span> {{# } else { }}
            <span style="color: blue;">{{ d.status }}</span> {{# } }}
         </script>

         <!--分页显示容器-->
         <div id="paging" style="text-align: center;"></div>

         <!--<hr class="layui-bg-orange" />-->
      </div>

      <!--出库分配打开容器-->
      <div id="chukuOpen" style="display: none; margin: 10px;">
         <form class="layui-form layui-form-pane">
            <input type="hidden" value='<%=request.getSession().getAttribute("iulistId")%>' name="diaodu" />
            <div class="layui-form-item">
               <label class="layui-form-label">操作员</label>
               <div class="layui-input-block">
                  <select lay-verify="required" name="chukucaozuo" id="chukucaozuo"></select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">出库库位</label>
               <div class="layui-input-block">
                  <select lay-verify="required" lay-search lay-filter="chukukuwei" name="chukukuwei" id="chukukuwei"></select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">出库批次</label>
               <div class="layui-input-block" id="pici">

               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">过磅/理算</label>
               <div class="layui-input-block">
                  <input type="radio" value="过磅" title="过磅" name="chukuGuoLi" />
                  <input type="radio" value="理算" title="理算" checked="checked" name="chukuGuoLi" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">分配重量</label>
               <div class="layui-input-block">
                  <input type="text" lay-verify="required|number" name="chukufenpei" id="chukufenpei" placeholder="请输入分配重量" class="layui-input" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注</label>
               <div class="layui-input-block">
                  <input type="text" name="chukubeizhu" id="chukubeizhu" placeholder="请输入备注" class="layui-input" />
               </div>
            </div>
            <button class="layui-btn" lay-submit lay-filter="chukutijiao" style="display: none;" id="chukutijiao">立即提交</button>
         </form>
      </div>

      <!--入库待处理订单分配打开容器-->
      <div id="rukuOpen" style="display: none; margin: 10px;">
         <form class="layui-form layui-form-pane">
            <input type="hidden" value='<%=request.getSession().getAttribute("iulistId")%>' name="diaodu" />
            <div class="layui-form-item">
               <label class="layui-form-label">操作员</label>
               <div class="layui-input-block">
                  <select lay-verify="required" name="rukucaozuo" id="rukucaozuo"></select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">库区</label>
               <div class="layui-input-block">
                  <select lay-verify="required" lay-search lay-filter="rukuKuqu" name="rukuKuqu" id="rukuKuqu"></select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">入库库位</label>
               <div class="layui-input-block">
                  <select lay-verify="required" lay-search lay-filter="rukuKuwei" name="rukuKuwei" id="rukuKuwei"></select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">过磅/理算</label>
               <div class="layui-input-block">
                  <input type="radio" value="过磅" title="过磅" name="rukuGuoLi" />
                  <input type="radio" value="理算" title="理算" checked="checked" name="rukuGuoLi" />
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注</label>
               <div class="layui-input-block">
                  <input type="text" name="ioperateRemark" id="ioperateRemark" placeholder="请输入备注" class="layui-input" />
               </div>
            </div>
            <button class="layui-btn" lay-submit lay-filter="rukuTijiao" style="display: none;" id="rukuTijiao">立即提交</button>
         </form>
      </div>

      <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
      <script src="../../js/call-client.js" charset="utf-8"></script>

      <!--引入自写js-->
      <script src="js/dingDanDaiChuLi.js"></script>
   </body>

</html>