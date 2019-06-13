<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fmtNumber</title>
</head>
<body>
<%--
	아래 set은 pageContext.setAttribute("num",1000000); 와 동일
 --%>
 
<h2>formatNumber (number => string / 1000000 => 1,000,000)</h2>
<c:set value="1000000.123123" var="num"/>
num : ${num } <br>

<fmt:setLocale value="ko_KR"/>
ko : <fmt:formatNumber value="${num }"/> <br>
ko : <fmt:formatNumber value="${num }" type="currency"/> <br>
ko : <fmt:formatNumber value="${num }" type="percent"/> <br>
<%-- type : number, currency, or percent --%>

<fmt:setLocale value="de_DE"/>
de : <fmt:formatNumber value="${num }"/> <br>
de : <fmt:formatNumber value="${num }" type="currency"/> <br>
de : <fmt:formatNumber value="${num }" type="percent"/> <br>

<%--formatting된 문자열을 숫자로 변환 --%>
<h2>parseNumber (string => number / 1,000,000 => 1000000)</h2>
<fmt:setLocale value="ko"/>
<c:set value="1,000,000" var="numStr"/>
numStr = ${numStr } <br>
parseNumber numStr = <fmt:parseNumber value="${numStr }" pattern="0,000"/> <br>
<%--locale설정을 무시하고 pattern을 줄 수도 있다 --%>


</body>
</html>