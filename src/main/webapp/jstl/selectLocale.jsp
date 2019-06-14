<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fmt</title>
<%@include file="/common/basicLib.jsp" %>
<script>
$(document).ready(function(){
	$("#localeSelect").val("${locale}"); // option부분에 <c:if test="${locale == 'ko' }">selected</c:if>설정하는 것 보다 간단하다
	
	$("#localeSelect").on("change", function(){
		$("#frm").submit();
	})
})
</script>
</head>
<body>

<h2>select locale</h2>
<form id="frm" action="${cp }/selectLocale" method="post">
	<%-- post로 보내지 않고 다시 get으로 보내도 동일하게 처리할 수 있다 --%>
	<select id="localeSelect" name="locale">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	</select>
</form>
<br><br>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="GREETING"/> <br>
	<fmt:message key="VISITOR">
		<fmt:param value="brown"/> <br>
	</fmt:message>
</fmt:bundle>

</body>
</html>