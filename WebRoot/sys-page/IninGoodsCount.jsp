<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--为移动设备添加 viewport-->
<title>仓储管理系统登陆</title>
<meta name="description"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。致力于为广大商户提供快速、安全、便捷的仓储物流服务" />
<meta name="keywords"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。钢材、木材、百货、物流、汽车货运、铁路货运" />
<meta name="author" content="">
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->
<link rel="stylesheet" href="/XGProject/css/bootstrap.css" />
<link rel="stylesheet" href="/XGProject/css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="/XGProject/js/bootstrap.min.js"></script>
<script src="/XGProject/js/jquery1.9.0.min.js"></script>

<script src="/XGProject/cangchu/d/tableExport.js"></script>
<script src="/XGProject/cangchu/d/jquery.base64.js"></script>
<script src="/XGProject/cangchu/d/jszip-utils.js"></script>
<script src="/XGProject/cangchu/d/xlsx.core.min.js"></script>
<script src="/XGProject/cangchu/jsPDF/jspdf.min.js"></script>
<script src="/XGProject/cangchu/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>

<script type="text/javascript">
	function show() {
		document.location.href = "/XGProject/initClientGoodsAction.do?flag=getAll&type=client";
	}
</script>
</head>
<c:if test="${cglist==null }">
	<script>
		show();
	</script>
</c:if>
<script>
	$(function() {
		$("#xuanze")
				.click(
						function() {
							if ($(this).prop("checked")) {
								document.location.href = "/XGProject/initClientGoodsAction.do?flag=getAll&type=all";
							} else {
								document.location.href = "/XGProject/initClientGoodsAction.do?flag=getAll&type=all";
							}
						});
	});
</script>
<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a href="">系统管理</a>
				<span>/</span>&nbsp;
				<a href="">货物初始化</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>客户库存统计</a></li>
				<!--  <li><a data-toggle="tab" href="#menu2">编辑班组</a></li> -->
			</ul>
			<form class="form-inline" role="form">
				<div class="form-group col-sm-5">
					<label for="name">客户名称</label> <input type="text"
						class="form-control" id="name" placeholder="请输入名称">
				</div>

				<div class="checkbox col-sm-5">
					<label> <input type="checkbox" id="xuanze">统计鑫港货物重量</label>
					<a class="btn btn-default"
						onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});"
						id="buttonExportData">导出</a>
				</div>

			</form>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover" id="table-name"
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>编号</th>
								<th>客户id</th>
								<th>客户名称</th>
								<th>货物id</th>
								<th>货物名称</th>
								<th>规格</th>
								<th>材质</th>
								<th>属性</th>
								<th>产地</th>
								<th>总量合计</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cglist }" varStatus="v" var="cg">
								<tr>
									<td>${v.index+1 }</td>
									<td>${cg.clientId }</td>
									<td>${cg.icgClient==null?"无":cg.icgClient }</td>
									<td>${cg.goodsId }</td>
									<td>${cg.icgName }</td>
									<td>${cg.icgGuige }</td>
									<td>${cg.icgCaizhi }</td>
									<td>${cg.icgShuxing }</td>
									<td>${cg.icgChandi }</td>
									<td>${cg.icgWeight }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
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

</html>
