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

	<script src="/XGProject/js/jquery1.9.0.min.js"></script>
	<script src="/XGProject/cangchu/d/tableExport.js"></script>
	<script src="/XGProject/cangchu/d/jquery.base64.js"></script>
	<script src="/XGProject/cangchu/d/jszip-utils.js"></script>
	<script src="/XGProject/cangchu/d/xlsx.core.min.js"></script>

	<title>收费记录导出</title>
</head>
<c:if test="${alist==null }">
	<script>
		document.location.href="/XGProject/accounts.do?flag=getAll";
	</script>
</c:if>
<body>
	<a href="#"
		onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});"
		id="buttonExportData">导出</a>
	<table id="table-name" width="100%" border="1" cellspacing="0"
		cellpadding="0" style="width: 3000px;">

		<tr>
			<td>客户名称</td>
			<td>起始日期</td>
			<td>结束日期</td>
			<td>汽运入库费</td>
			<td>火运入库费</td>
			<td>汽运出库费</td>
			<td>火运出库费</td>
			<td>下站费（出）</td>
			<td>过户费</td>
			<td>下站费（过）</td>
			<td>仓储费</td>
			<td>轧账费用合计</td>
			<td>滞纳金起征时间</td>
			<td>滞纳金结束时间</td>
			<td>滞纳金费率</td>
			<td>滞纳金合计</td>
			<td>总费用合计</td>
			<td>结算人</td>
			<td>结算时间</td>
			<td>收费人</td>
			<td>收费时间</td>
			<td>缴费人</td>
			<td>支付方式</td>
			<td>票据类型</td>
			<td>实收费用</td>
			<td>备注</td>
		</tr>
		<c:forEach items="${alist }" var="ac">
			<tr>
				<td>${ac.client.clientAbbreviation}</td>
				<td>${ac.accountsCreateTime }</td>
				<td>${ac.accountsFinishTime}</td>

				<td>${ac.rukuCost }元</td>
				<td>${ac.zidingyiFour }元</td>

				<td>${ac.zidingyiFive }元</td>
				<td>${ac.zidingyiSix }元</td>

				<td>${ac.chukumaxtime }元</td>
				<td>${ac.guohuCost }元</td>

				<td>${ac.zhuanchukumaxtime }元</td>
				<td>${ac.cangchuCost }元</td>

				<td>${ac.accountsExpensesSeed }元</td>
				<td>${ac.shoufeiqixian }</td>

				<td>${ac.accountsCollectTime==null?"无":ac.accountsCollectTime }</td>
				<td>${ac.zhinaFeilv }元</td>

				<td>${ac.accountsSeed==null?0.0:ac.accountsSeed }元</td>
				<td>${ac.accountsSeed+ac.accountsExpensesSeed }元</td>

				<td>${ac.jiesuanren.iuserName }</td>
				<td>${ac.jiesuantime }</td>

				<td>${ac.shoufeiren==null?"无":ac.shoufeiren.iuserName }</td>

				<td>${ac.accountsCollectTime==null?"无":ac.accountsCollectTime }</td>

				<td>${ac.jiaofeiren==null?"无":ac.jiaofeiren }</td>
				<td>${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionName
					}</td>

				<td>${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionReceipt
					}</td>
				<td>${ac.shishouCost==null?0:ac.shishouCost }元</td>
				<td>${ac.accountsRemark }</td>
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