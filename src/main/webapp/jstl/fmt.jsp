<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fmt</title>
</head>
<body>

<h2>ko</h2>
<fmt:setLocale value="ko"/> <%-- 설정할 locale 지정 --%>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="GREETING"/> <br>
	<fmt:message key="VISITOR">
		<fmt:param value="brown"/> <br> <%--인자값을 받는 경우 이렇게 넣어줄 수 있다 --%>
	</fmt:message>
</fmt:bundle>

<h2>en</h2>
<fmt:setLocale value="en"/>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="GREETING"/> <br>
	<fmt:message key="VISITOR">
		<fmt:param value="james"/> <br>
	</fmt:message>
</fmt:bundle>

<h2>ja</h2>
<fmt:setLocale value="ja"/>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="GREETING"/> <br>
	<fmt:message key="VISITOR">
		<fmt:param value="cony"/> <br>
	</fmt:message>
</fmt:bundle>

<h2>setBundle</h2>
<fmt:setBundle basename="kr.or.ddit.msg.msg" var="message"/> <%--var에는 담아줄 속성명을 쓴다 --%>
<fmt:message key="GREETING" bundle="${message}"/> <br> <%--bundle에 위에서 정한 속성명을 쓴다 --%>
<fmt:message key="VISITOR" bundle="${message}">
	<fmt:param value="brown"/>
</fmt:message>

</body>
</html>