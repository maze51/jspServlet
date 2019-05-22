<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.write("application.getContextPath() : " + application.getContextPath() + "<br>");
	out.write("application.getServerInfo() : " + application.getServerInfo() + "<br>");
	out.write("application.getMajorVersion() : " + application.getMajorVersion() + "<br>");
	out.write("application.getMinorVersion() : " + application.getMinorVersion() + "<br>");
	
	// 초기화 파라미터. Spring설정할 때 사용된다.
	out.write("application.getInitParameter(\"ADMIN\") : " 
				+ application.getInitParameter("ADMIN") + "<br>");
	
	out.write("application.getRealPath(\"/res/190522.txt\") : " 
				+ application.getRealPath("/res/190522.txt"));
	
	// 파일 내용을 화면에 출력하기
	File file = new File("D:/190522.txt");
	BufferedReader br = new BufferedReader(new FileReader(file));
	String str = "";
	StringBuffer strr = new StringBuffer();
	
		strr.append("<br>");
	while(br.ready()){
		strr.append(br.readLine());
		strr.append("<br>");
	}
	str = strr.toString();
	
	out.write(str);
%>
</body>
</html>