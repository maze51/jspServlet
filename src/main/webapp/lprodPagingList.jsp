<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>LPROD페이징리스트</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp" %>
</head>

<body>
	
	<!-- header.jsp -->
	<%@include file="/common/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">LPROD</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>LPROD_ID</th>
									<th>LPROD_GU</th>
									<th>LPROD_NM</th>
								</tr>
								
								<%
									List<LprodVo> lprodList = (List<LprodVo>)request.getAttribute("lprodList"); // controller에서 가져온 userList
								%>
								
								<c:forEach items="${lprodList }" var="lprod">
								<tr>
									<td>${lprod.lprod_id }</td>
									<td>${lprod.lprod_gu }</td>
									<td>${lprod.lprod_nm }</td>
								</tr>
								</c:forEach>
								
							</table>
						</div>
				
						<a class="btn btn-default pull-right">사용자 등록</a>
					
						<div class="text-center">
							<ul class="pagination">
								
								<%PageVo pageVo = (PageVo)request.getAttribute("pageVo");%>
								
								<%if(pageVo.getPage() == 1){ %>
									<li class="disabled"><span>«</span></li>
								<%} else {%>
									<li><a href="${pageContext.request.contextPath}/lprodPagingList?page=<%=pageVo.getPage()-1 %>&pageSize=<%=pageVo.getPageSize()%>">«</a></li>
								<%} %>
								
								<%	
									// 가져온 paginationSize를 활용해 화면에 나오는 page값을 고쳐준다 
									// + /userPagingList?page=1&pageSize=10 반영하기
									int paginationSize = (Integer)request.getAttribute("paginationSize");
									for(int i=1;i<=paginationSize;i++){ %>
										
										<%
										// 내가 현재 몇 번째 페이지에 있는가? pageVo에 저장되어 있다. 그걸 찾아서 li에 class="active"를 지정한다
										if(pageVo.getPage() == i){ %>
											<li class="active">
												<span><%=i %></span>
											</li>
											<%} else {%>
											<li>
												<a href="${pageContext.request.contextPath}/lprodPagingList?page=<%=i %>&pageSize=<%=pageVo.getPageSize()%>"><%=i %></a>
											</li>
											<%} %>
									<%}%>
									<%if(pageVo.getPage() == paginationSize){ %>
										<li class="disabled"><span>»</span></li>
									<%} else {%>
										<li><a href="${pageContext.request.contextPath}/lprodPagingList?page=<%=pageVo.getPage()+1 %>&pageSize=<%=pageVo.getPageSize()%>">»</a></li>
									<%} %>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
