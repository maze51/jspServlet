<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<%-- 구구단 출력 --%>
	<table border="1">
			
			<c:forEach begin="1" end="${param.b == null ? 9 : param.b }" var="i">
				<tr>
					<c:forEach begin="2" end="${param.a == null ? 9 : param.a }" var="j">
						<td>
							${j } * ${i } = ${j*i }
						</td>				
					</c:forEach>
				</tr>
			</c:forEach>

	</table>
</body>
</html>