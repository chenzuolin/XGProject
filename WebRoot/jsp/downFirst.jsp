<%@page import="java.io.InputStream"%>
<%@page import="com.sun.mail.iap.Response"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%><%@ page
	contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.Socket"%>
<%
	String name = "鑫港仓储.apk"; //request.getParameter("name");
	String fileName = name;//下载文件的名称加扩展名
	// String filePath = "E:\\StockProject\\LogisticsProject\\XGProject\\WebRoot\\img\\";//文件下载的相对的路径
	String filePath = getServletContext().getRealPath(
			"/App/" + fileName);////获取目标文件的绝对路径  
	//File file = new File(filePath,fileName);//创建文件
	//Thread.sleep(1000 * 5);  //延迟加载
	//设置文件MIME类型  
	response.setContentType(getServletContext().getMimeType(fileName));
	// response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
	//设置Content-Disposition  
	response.setHeader("Content-Disposition", "attachment;filename="
			+ fileName);
	//long len = file.length();//长度
	// FileInputStream fis = new FileInputStream(file);
	InputStream in = new FileInputStream(filePath);//z读取文件
	OutputStream os = response.getOutputStream();
	//byte[] buffer = new byte[1024 * 10];
	/* for (int read; (read = in.read(buffer)) != -1;) {
	    os.write(buffer, 0, read);
	} */
	int b;
	while ((b = in.read()) != -1) {
		os.write(b);
	}
	in.close();
	os.close();
	out.clear();
	out = pageContext.pushBody();
%>
