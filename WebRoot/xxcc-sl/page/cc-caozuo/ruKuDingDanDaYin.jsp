﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>${is.input.client.clientFirmName}入库订单</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
      <style type="text/css">
         * {
            color: #000;
         }
         
         .layui-table.title td.tit {
            padding: 5px;
            width: 6%;
            text-align: right;
            border: 0;
         }
         
         .layui-table.title td.con {
            padding: 5px;
            width: 16%;
            text-align: left;
            border: 0;
            font-weight: 600;
         }
         
         .layui-table,
         .layui-table th,
         .layui-table td {
            margin: 0 auto;
            border: 2px solid #000;
         }
         
         .layui-table th {
            text-align: center;
            font-weight: 600;
            height: 18px;
         }
         
         h1 {
            text-align: center;
            margin-bottom: 2rem;
         }
         
         .zhuyi {
            font-size: 1rem;
            padding-bottom: 0.2rem;
         }
      </style>
   </head>

   <body>
      <div class="layui-container">
         <div class="layui-form-item">
            <a onclick="printPage()" class="layui-btn layui-btn-normal layui-btn" id="printImg">打印</a>
            <a href="javascript:window.history.go(-1);" class="layui-btn layui-btn-normal layui-btn" id="houtui">后退</a>
         </div>
         <h1>物联邦入库订单</h1>

         <table class="layui-table title" style="border: 0;">
            <tr>
               <td class="tit">货主单位：</td>
               <td class="con">${is.input.client.clientFirmName}</td>
               <td class="tit">仓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库：</td>
               <td class="con">物联邦（鑫港库）</td>
               <td class="tit">打印时间：</td>
               <td class="con" id="printTime"></td>
            </tr>
            <tr>
               <td class="tit">提货单位：</td>
               <td class="con"></td>
               <td class="tit">客户单号：</td>
               <td class="con">${is.input.inputClientNumber}</td>
               <td class="tit">订单编号：</td>
               <td class="con">${is.input.inputId}</td>
            </tr>
            <tr>
               <td class="tit">车&nbsp;牌&nbsp;号：</td>
               <td class="con">${is.input.inputPlateNumber}</td>
               <td class="tit">司机姓名：</td>
               <td class="con">${is.input.inputDriverName}</td>
               <td class="tit"></td>
               <td class="con"></td>
            </tr>
         </table>

         <table class="layui-table">
            <thead>
               <tr>
                  <th rowspan="2 ">库房</th>
                  <th rowspan="2 ">货物名称</th>
                  <th rowspan="2 ">规格</th>
                  <th rowspan="2 ">材质</th>
                  <th rowspan="2 ">属性</th>
                  <th rowspan="2 ">产地</th>
                  <th rowspan="2 ">计量方式</th>
                  <th colspan="2 ">应发</th>
                  <th colspan="2 ">实发</th>
               </tr>
               <tr>
                  <th>数量(件)</th>
                  <th>重量(吨)</th>
                  <th>数量(件)</th>
                  <th>重量(吨)</th>
               </tr>
            </thead>
            <tbody>
               <c:if test="${list!=null }">
                  <c:forEach items="${iolist }" var="ios">
                     <tr>
                        <td>鑫港库</td>
                        <td>${is.goods.goodsName }</td>
                        <td>${is.goods.goodsStandard.goodsStandardName }</td>
                        <td>${is.goods.goodsQuality.goodsQualityName }</td>
                        <td>${is.goods.goodsProperty.goodsPropertyName }</td>
                        <td>${is.goods.goodsYieldly.goodsYieldlyName }</td>
                        <td>${ios.ioperatePonderationTrue}</td>
                        <td></td>
                        <td class="theory_w">${is.iseedShouldWeight}</td>
                        <td class="reality_j">
                           ${ios.ioperateRealityNumber==null?0.0:ios.ioperateRealityNumber}</td>
                        <td class="reality_w">
                           ${ios.ioperateRealityWeight==null?0.0:ios.ioperateRealityWeight}</td>
                     </tr>
                  </c:forEach>
               </c:if>
               <c:if test="${list==null }">
                  <tr>
                     <td>鑫港库</td>
                     <td>${is.goods.goodsName }</td>
                     <td>${is.goods.goodsStandard.goodsStandardName }</td>
                     <td>${is.goods.goodsQuality.goodsQualityName }</td>
                     <td>${is.goods.goodsProperty.goodsPropertyName }</td>
                     <td>${is.goods.goodsYieldly.goodsYieldlyName }</td>
                     <td>${io.ioperatePonderationTrue}</td>
                     <td></td>
                     <td class="theory_w">${is.iseedShouldWeight}</td>
                     <td class="reality_j">
                        ${io.ioperateRealityNumber==null?0.0:io.ioperateRealityNumber}</td>
                     <td class="reality_w">
                        ${io.ioperateRealityWeight==null?0.0:io.ioperateRealityWeight}</td>
                  </tr>
                  </tr>
               </c:if>
               <tr>
                  <td>&nbsp;</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
               </tr>
               <tr>
                  <td>&nbsp;</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
               </tr>
               <tr height="50px ">
                  <td colspan="7 ">备注</td>
                  <td></td>
                  <td id="theoryTotal_w">${is.iseedShouldWeight}</td>
                  <td id="realityTotal_j"></td>
                  <td id="realityTotal_w"></td>
               </tr>
               <tr height="100px ">
                  <td colspan="11 ">
                     <blockquote class="zhuyi">
                        <span>注意事项：</span>
                        <span>1、本单据未签字、缺联、涂改均为无效。</span>
                        <span>2、提货单位当面核对货物并签字后生效。 </span>
                        <span>3、本单据需加盖提货专用章方才生效。</span>
                     </blockquote>
                     <blockquote style="margin-left: 82px;" class="zhuyi">
                        <span>4、特殊情况单据需要手写必须加盖提货专用章；否则视为无效。 </span>
                        <span>5、货物出库后，本单位对该货物概不负责。</span>
                        <span>6、本单据当天有效。</span>
                     </blockquote>
                     <blockquote class="zhuyi">地址：甘肃省兰州市西固区环行西路6号（鑫港物流园） 联系电话：0931-1111111
                     </blockquote>
                     <blockquote class="zhuyi">本单一式五联：第一联（白）开单员、第二联（蓝）财务、第三联（红）保管、第四联（黄）客户、第五联（绿）门卫</blockquote>
                  </td>
               </tr>
            </tbody>
         </table>
         <table class="layui-table title" style="border: 0;">
            <tr>
               <td class="tit">开票人：</td>
               <td class="con">
                  <%=((InteriorUser)request.getSession().getAttribute("iulist")).getIuserName()%>
               </td>
               <td class="tit">联系电话:</td>
               <td class="con">${es.export.exportDriverTel}</td>
               <td class="tit">签字：</td>
               <td class="con"></td>
            </tr>
         </table>
   </body>
   <script src="/XGProject/xxcc-sl/plugin/layui/layui.js"></script>
   <script>
      layui.use('table', function() {
         var table = layui.table,
            $ = layui.$;
         $('input[type=text]').attr('readonly', 'readonly');
         var date = new Date();
         var seperator1 = "-";
         var seperator2 = ":";
         var month = date.getMonth() + 1;
         var strDate = date.getDate();
         if(month >= 1 && month <= 9) {
            month = "0" + month;
         }
         if(strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
         }

         var shi = date.getHours();
         if(parseInt(date.getHours()) < 10) {
            shi = "0" + date.getHours();
         }
         var fen = date.getMinutes();
         if(parseInt(date.getMinutes()) < 10) {
            fen = "0" + date.getMinutes();
         }
         var miao = date.getSeconds();
         if(parseInt(date.getSeconds()) < 10) {
            miao = "0" + date.getSeconds();
         }

         var currentdate = date.getFullYear() + "年" + month + "月" + strDate + "日 " +
            shi + "时" + fen + "分" + miao + "秒";

         $('#printTime').text(currentdate);
         //统计订单中理论和实际的件数和重量
         var reality_j = 0.0;
         var reality_w = 0.0;
         for(var i = 0; i < $('.reality_j').length; i++) {
            reality_j += parseFloat($('.reality_j').eq(i).text());
            reality_w += parseFloat($('.reality_w').eq(i).text());
         }
         $('#realityTotal_j').text(reality_j.toFixed(3));
         $('#realityTotal_w').text(reality_w.toFixed(3));
      });
   </script>
   <script>
      function printPage() {
         layui.use('jquery', function() {
            var $ = layui.jquery;
            $("#printImg").css("display", "none");
            $("#houtui").css("display", "none");
            window.print();
            $("#printImg").css("display", "");
            $("#houtui").css("display", "");
            return false;
         });
      }
   </script>

</html>