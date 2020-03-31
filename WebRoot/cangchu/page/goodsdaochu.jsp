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

	<title>货物明细导出</title>
</head>
<c:if test="${glist==null }">
	<script>
		document.location.href="/XGProject/goods.do?flag=getAll";
	</script>
</c:if>
<body>
	<a href="#"
		onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});"
		id="buttonExportData">导出</a>
	<table id="table-name" width="100%" border="1" cellspacing="0"
		cellpadding="0" style="width: 1500px;">

		<tr>
			<td>编号</td>
			<td>货物品类</td>
			<td>货物名称</td>
			<td>货物助记符</td>
			<td>货物规格</td>
			<td>货物材质</td>
			<td>货物属性</td>
			<td>货物产地</td>
			<td>计件单位</td>
			<td>备注</td>
		</tr>
		<c:forEach items="${glist }" var="g" varStatus="gs">
			<tr>
				<td>${gs.index+1 }</td>
				<td>${g.goodsCategory.goodsCategoryName }</td>
				<td>${g.goodsName }</td>
				<td>${g.goodsSign }</td>
				<td>${g.goodsStandard.goodsStandardName }</td>
				<td>${g.goodsQuality.goodsQualityName }</td>
				<td>${g.goodsProperty.goodsPropertyName }</td>
				<td>${g.goodsYieldly.goodsYieldlyName }</td>
				<td>${g.goodsUnit.goodsUnitName }</td>
				<td>${g.goodsRemark }</td>
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