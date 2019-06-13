<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fmtDate</title>
</head>
<body>
<h2>formatDate</h2>
<c:set var="dt" value="<%=new Date() %>"/> <%--서블릿 객체 생성 대체 --%>
dt : ${dt } <br>
formatDate dt : <fmt:formatDate value="${dt }"/> <br>
formatDate dt : <fmt:formatDate value="${dt }" pattern="yyyy-MM-dd"/> <br> <%-- 역시 pattern지정 가능 --%>
<br>
formatDate dt full : <fmt:formatDate value="${dt }" type="date" dateStyle="FULL"/> <br>
formatDate dt medium : <fmt:formatDate value="${dt }" type="date" dateStyle="MEDIUM"/> <br>
formatDate dt short : <fmt:formatDate value="${dt }" type="date" dateStyle="SHORT"/> <br>
<br>
formatDate dt : <fmt:formatDate value="${dt }" type="time"/> <br>
formatDate dt : <fmt:formatDate value="${dt }" type="both"/> <br>

<h3>ja</h3>
<fmt:setLocale value="ja"/>
formatDate dt(ja) : <fmt:formatDate value="${dt }"/> <br>

<h3>de</h3>
<fmt:setLocale value="de"/>
formatDate dt(de) : <fmt:formatDate value="${dt }"/> <br>

<h3>en</h3>
<fmt:setLocale value="en_US"/>
formatDate dt(en_US) : <fmt:formatDate value="${dt }"/> <br>

<hr>

<fmt:setLocale value="ko_KR"/>
<h2>parseDate (String => date / 2019.6.13 => date(날짜타입))</h2>
<c:set value="2019.6.13" var="dtStr"/>
dtStr : ${dtStr } <br>
parseDate dtStr : <fmt:parseDate value="${dtStr }" pattern="yyyy.MM.dd"/> <br>

</body>
</html>