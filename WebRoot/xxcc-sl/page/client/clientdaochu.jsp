<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="renderer" content="webkit">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   <script src="/XGProject/xxcc-sl/js/DaoChu/jquery1.9.0.min.js"></script>
   <script src="/XGProject/xxcc-sl/js/DaoChu/tableExport.js"></script>
   <script src="/XGProject/xxcc-sl/js/DaoChu/jquery.base64.js"></script>
   <script src="/XGProject/xxcc-sl/js/DaoChu/jszip-utils.js"></script>
   <script src="/XGProject/xxcc-sl/js/DaoChu/xlsx.core.min.js"></script>

   <title>客户信息导出</title>
   </head>
   <c:if test="${clist==null }">
      <script>
         document.location.href = "/XGProject/client.do?flag=getAll";
      </script>
   </c:if>

   <body>
      <a href="#" onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});" id="buttonExportData">导出</a>
      <table id="table-name" width="100%" border="1" cellspacing="0" cellpadding="0" style="width: 2000px;">

         <tr>
            <td>编号</td>
            <td>单位简称</td>
            <td>单位全称</td>
            <td>助记符</td>
            <td>注册时间</td>
            <td>负责人</td>
            <td>联系电话</td>
            <td>联系邮箱</td>
            <td>身份证号码</td>
            <td>单位地址</td>
            <td>合同号</td>
            <td>合同起始日期</td>
            <td>合同结束日期</td>
            <td>结算方式</td>
            <td>折扣</td>
            <td>备注</td>
         </tr>
         <c:forEach items="${clist }" var="c" varStatus="cs">
            <tr>
               <td>${cs.index+1 }</td>
               <td>${c.clientAbbreviation }</td>
               <td>${c.clientFirmName }</td>
               <td>${c.clientSign }</td>
               <td>${c.clientCreateTime }</td>
               <td>${c.clientHuman }</td>
               <td>${c.clientTel }</td>
               <td>${c.clientEmail }</td>
               <td>${c.clientStatusNumber }</td>
               <td>${c.clientAddress }</td>
               <td>${c.clientContract }</td>
               <td>${c.clientStartTime }</td>
               <td>${c.clientFinishTime }</td>
               <td>${c.clientAccounts }</td>
               <td>${c.clientAgio }</td>
               <td>${c.clientRemark }</td>

            </tr>
         </c:forEach>
      </table>

      <script language="javascript">
         function get_radio_value(radioName) {
            var radios = document.getElementsByName(radioName);
            for(var i = 0; i < radios.length; i++) {
               if(radios[i].checked) {
                  return radios[i].value;
               }
            }
         }

         function to_json(workbook) {
            var result = {};
            workbook.SheetNames.forEach(function(sheetName) {
               var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
               if(roa.length > 0) {
                  result[sheetName] = roa;
               }
            });
            return result;
         }

         function to_csv(workbook) {
            var result = [];
            workbook.SheetNames.forEach(function(sheetName) {
               var csv = XLSX.utils.sheet_to_csv(workbook.Sheets[sheetName]);
               if(csv.length > 0) {
                  result.push("SHEET: " + sheetName);
                  result.push("");
                  result.push(csv);
               }
            });
            return result.join("\n");
         }

         function to_formulae(workbook) {
            var result = [];
            workbook.SheetNames.forEach(function(sheetName) {
               var formulae = XLSX.utils.get_formulae(workbook.Sheets[sheetName]);
               if(formulae.length > 0) {
                  result.push("SHEET: " + sheetName);
                  result.push("");
                  result.push(formulae.join("\n"));
               }
            });
            return result.join("\n");
         }

         var tarea = document.getElementById('b64data');

         function b64it() {
            var wb = XLSX.read(tarea.value, {
               type: 'base64'
            });
            process_wb(wb);
         }

         function process_wb(wb) {
            var output = "";
            switch(get_radio_value("format")) {
               case "json":
                  output = JSON.stringify(to_json(wb), 2, 2);
                  break;
               case "form":
                  output = to_formulae(wb);
                  break;
               default:
                  output = to_csv(wb);
            }
            if(out.innerText === undefined) out.textContent = output;
            else out.innerText = output;
         }

         var drop = document.getElementById('drop');

         function handleDrop(e) {
            e.stopPropagation();
            e.preventDefault();
            var files = e.dataTransfer.files;
            var i, f;
            for(i = 0, f = files[i]; i != files.length; ++i) {
               var reader = new FileReader();
               var name = f.name;
               reader.onload = function(e) {
                  var data = e.target.result;
                  //var wb = XLSX.read(data, {type: 'binary'});  
                  var arr = String.fromCharCode.apply(null, new Uint8Array(data));
                  var wb = XLSX.read(btoa(arr), {
                     type: 'base64'
                  });
                  process_wb(wb);
               };
               //reader.readAsBinaryString(f);  
               reader.readAsArrayBuffer(f);
            }
         }

         function handleDragover(e) {
            e.stopPropagation();
            e.preventDefault();
            e.dataTransfer.dropEffect = 'copy';
         }

         if(drop.addEventListener) {
            drop.addEventListener('dragenter', handleDragover, false);
            drop.addEventListener('dragover', handleDragover, false);
            drop.addEventListener('drop', handleDrop, false);
         }
      </script>
   </body>

</html>