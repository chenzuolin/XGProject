<%@page import="java.sql.Driver"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html; charset=gb2312" %>
<%@page import="java.io.InputStream"%>
<%@page import="com.sun.mail.iap.Response"%>
<%@page import="java.util.Date" %>
<%@page import="java.io.*"%>
<html>
	<head>
		<title>取得文件属性</title>
	</head>
	<body>
		<img alt="获得文件属性" src="img/caidan.png" />
		<center><font size="5" color="blue">取得文件属性</font></center>
		<hr /> <p></p>
		<%
			String path = request.getRealPath("/");//取得当前目录在服务器端的实际位置
			File f = new File(path,"File.txt");//建立File变量，并设定由f变量变数引用
			//在此处判断该文件是否存在，如果存在则删除，如果不存在那么就创建该文件
			String message = "";
			if(f.exists()){
				//文件存在
				/* boolean ok = f.delete();//进行删除
				if(ok){
					message = "文件存在，已完成删除！";
				}else{
					message = "文件存在，删除失败！";
				} */
			}else{
				boolean ok = f.createNewFile();
				if(ok){
					message = "文件不存在，已完成创建";	
				}else{
					message="文件不存在，创建失败！";
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");//对时间进行格式化
		 %>
		 <font color=blue><%=f.getName() %></font>的属性如下：<br />
		 <strong><%=message %>创建的路径是：<%=path %></strong>
		 <font color=red>
		 	<ul>
		 		<li>文件的长度为：<%=f.length() %></li>
		 		<li><%=(f.isFile())?"是文件":"不是文件" %></li>
		 		<li><%=(f.isDirectory())?"是目录":"不是目录" %></li>
		 		<li><%=(f.canRead())?"可读取":"不可读取" %></li>
		 		<li><%=(f.canWrite())?"可写入":"不可写入" %></li>
		 		<li><%=(f.isHidden())?"是隐藏文件":"不是隐藏文件" %></li>
		 		<li>最后修改的日期为：<%=df.format(new Date(f.lastModified())) %></li>
		 	</ul>
		 </font>
		 
		 <%
		 	String mess = "";
		 	//对文件夹目录的创建
		 	path = path +"\\Test";//创建的目录名
		 	File DriverName = new File(path);//创建代表Test目录的file变量
		 	if(DriverName.exists()){//判断该目录是否存在
		 		//判断的结果如果存在的时候，进行删除
		 		boolean ok = DriverName.delete();//进行删除
			 	if(ok){
			 		mess = "Test目录存在，删除成功！";	
			 	}else{
			 		mess = "Test目录存在，删除失败！";
			 	}
		 	}else{
		 		//判断为不存在的时候进行创建目录
		 		boolean ok = DriverName.mkdir();//进行创建目录
		 		if(ok){
		 			mess = "Test目录不存在，创建成功！";
		 		}else{
		 			mess = "Test目录不存在，创建失败！";
		 		}
		 	}
		  %>
		  
		  
		  <b>创建目录结果：<%=mess %></b>
	</body>
</html>