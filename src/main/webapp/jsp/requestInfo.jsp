<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 스크립틀릿 <% %> : 자바 로직을 작성하는 공간 --%>
	<%-- 표현식 <%= %> : 결과물을 출력 --%>
	
	<%
		String msg = "test";
		msg += "_append";
		
		// jsp의 내장객체, 묵시적 객체(implict) (이미 선언되어 있기 때문에
		// 별도의 선언 없이 사용 가능한 객체의 하나)
	%>
		request.getRemoteAddr() : <%=request.getRemoteAddr() %><br>  
		request.getLocalAddr() : <%=request.getLocalAddr() %><br>
		<%-- 서버/클라의 ip. 지금은 둘이 같아서 같은 값이 뜬다 --%>
		  
		request.getContentType() : <%=request.getContentType() %><br>
		  
		request.getContextPath() : <%=request.getContextPath() %><br>
		<%-- 요청을 보낸 웹 어플리케이션의 이름. 첫 /jsp부분
		파일을 요청할 때 실제 경로 대신 이걸 넣으면 서버에 지정된 경로가 바뀌어도 알아서 대응할 수 있다
		--%>
		  
		request.getMethod() : <%=request.getMethod() %><br>
		
		request.getRequestURI() : <%=request.getRequestURI() %><br>
		<%-- 사용자가 어떤 url로 요청을 보냈는지 확인하는 부분 --%>
		
		request.getProtocol() : <%=request.getProtocol() %><br>
		
		<img src="<%=request.getContextPath() %>/img/sally.png">
		  
</body>
</html>