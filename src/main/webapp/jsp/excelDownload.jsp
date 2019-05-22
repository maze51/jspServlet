<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-Disposition", "attachment; filename=character.xls");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>이름</th>
			<th>영문명</th>
		</tr>
		
		<tr>
			<td>라이언</td>
			<td>ryan</td>
		</tr>		
		<tr>
			<td>무지</td>
			<td>muzi</td>
		</tr>		
		<tr>
			<td>어피치</td>
			<td>appach</td>
		</tr>		
	</table>
</body>
</html>