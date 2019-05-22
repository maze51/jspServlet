<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구구단</title>
<style>
	td{
		padding : 10px;
	}
</style>
</head>
<body>

	<%-- localhost/jsp/jsp/timesTables.jsp --%>
	
	<%
	String param = request.getParameter("i"); 
	String param2 = request.getParameter("j");
	%>
	
	<%-- 구구단 출력(2~9단) --%>
	
	<table border="1">
			<%for(int i=1;i<=Integer.parseInt(param2);i++){%>
		<tr>
				<% for(int j=2;j<=Integer.parseInt(param);j++){%>
			<td>
				<%= j + " * " + i + " = " + j*i %>
			</td>
				<% } %>
		</tr>	
			<% } %>
	</table>
</body>
</html>