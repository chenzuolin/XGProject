<%@page import="java.sql.Driver"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html; charset=gb2312" %>
<%@page import="java.io.InputStream"%>
<%@page import="com.sun.mail.iap.Response"%>
<%@page import="java.util.Date" %>
<%@page import="java.io.*"%>
<html>
	<head>
		<title>ȡ���ļ�����</title>
	</head>
	<body>
		<img alt="����ļ�����" src="img/caidan.png" />
		<center><font size="5" color="blue">ȡ���ļ�����</font></center>
		<hr /> <p></p>
		<%
			String path = request.getRealPath("/");//ȡ�õ�ǰĿ¼�ڷ������˵�ʵ��λ��
			File f = new File(path,"File.txt");//����File���������趨��f������������
			//�ڴ˴��жϸ��ļ��Ƿ���ڣ����������ɾ���������������ô�ʹ������ļ�
			String message = "";
			if(f.exists()){
				//�ļ�����
				/* boolean ok = f.delete();//����ɾ��
				if(ok){
					message = "�ļ����ڣ������ɾ����";
				}else{
					message = "�ļ����ڣ�ɾ��ʧ�ܣ�";
				} */
			}else{
				boolean ok = f.createNewFile();
				if(ok){
					message = "�ļ������ڣ�����ɴ���";	
				}else{
					message="�ļ������ڣ�����ʧ�ܣ�";
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");//��ʱ����и�ʽ��
		 %>
		 <font color=blue><%=f.getName() %></font>���������£�<br />
		 <strong><%=message %>������·���ǣ�<%=path %></strong>
		 <font color=red>
		 	<ul>
		 		<li>�ļ��ĳ���Ϊ��<%=f.length() %></li>
		 		<li><%=(f.isFile())?"���ļ�":"�����ļ�" %></li>
		 		<li><%=(f.isDirectory())?"��Ŀ¼":"����Ŀ¼" %></li>
		 		<li><%=(f.canRead())?"�ɶ�ȡ":"���ɶ�ȡ" %></li>
		 		<li><%=(f.canWrite())?"��д��":"����д��" %></li>
		 		<li><%=(f.isHidden())?"�������ļ�":"���������ļ�" %></li>
		 		<li>����޸ĵ�����Ϊ��<%=df.format(new Date(f.lastModified())) %></li>
		 	</ul>
		 </font>
		 
		 <%
		 	String mess = "";
		 	//���ļ���Ŀ¼�Ĵ���
		 	path = path +"\\Test";//������Ŀ¼��
		 	File DriverName = new File(path);//��������TestĿ¼��file����
		 	if(DriverName.exists()){//�жϸ�Ŀ¼�Ƿ����
		 		//�жϵĽ��������ڵ�ʱ�򣬽���ɾ��
		 		boolean ok = DriverName.delete();//����ɾ��
			 	if(ok){
			 		mess = "TestĿ¼���ڣ�ɾ���ɹ���";	
			 	}else{
			 		mess = "TestĿ¼���ڣ�ɾ��ʧ�ܣ�";
			 	}
		 	}else{
		 		//�ж�Ϊ�����ڵ�ʱ����д���Ŀ¼
		 		boolean ok = DriverName.mkdir();//���д���Ŀ¼
		 		if(ok){
		 			mess = "TestĿ¼�����ڣ������ɹ���";
		 		}else{
		 			mess = "TestĿ¼�����ڣ�����ʧ�ܣ�";
		 		}
		 	}
		  %>
		  
		  
		  <b>����Ŀ¼�����<%=mess %></b>
	</body>
</html>