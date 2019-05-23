<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		// (여기는 화면 출력용이 아닌, 로직만 담당하는 부분)
		
		// 4가지 파라미터를 받아서, 4개의 영역에 속성으로 넣어준다
		// 속성 저장 : scope객체.setAttribute("속성명", 속성(or객체) );
		// scope 객체 : pageContext, request, session, application
		
		
		pageContext.setAttribute("pageAttribute", request.getParameter("pageParam"));
		request.setAttribute("requestAttribute", request.getParameter("requestParam"));
		session.setAttribute("sessionAttribute", request.getParameter("sessionParam"));
		application.setAttribute("applicationAttribute", request.getParameter("applicationParam"));
		
		request.getRequestDispatcher("/scope/scopePrint.jsp").forward(request, response);
		
	%>
