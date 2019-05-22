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
		System.out.println("userId : " + request.getParameter("userId"));
		RequestDispatcher rd = request
				.getRequestDispatcher("/jsp/requestDispatchTarget.jsp");
		// 여기는 서버 안이라 경로를 알아서 찾는다. request.getContextPath()할 필요가 없음

		rd.forward(request, response);
	%>
</body>
</html>