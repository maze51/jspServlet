<%@tag import="kr.or.ddit.prod.dao.IProdDao"%>
<%@tag import="kr.or.ddit.prod.dao.ProdDaoImpl"%>
<%@tag import="java.sql.DriverManager"%>
<%@tag import="java.sql.ResultSet"%>
<%@tag import="java.sql.PreparedStatement"%>
<%@tag import="java.sql.Connection"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="prod" type="java.lang.String" required="true"%>
<br>
code : ${prod } <br>
<%
	String code = (String)jspContext.getAttribute("prod");
	IProdDao prodDao = new ProdDaoImpl();
	jspContext.setAttribute("prodList", prodDao.prodList(code));
	
	//jspContext.setAttribute("prodList", prodDao.prodList(code));
	// 이하 forEach돌려서 설정
	//---------------------------------------------------------------------
	//String code = (String)jspContext.getAttribute("prod");
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "PC10";
	String pass = "java";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pass);
		pstmt = conn.prepareStatement("select prod_id, prod_name from prod where prod_lgu = ?");
		pstmt.setString(1, prod);
		
		rs = pstmt.executeQuery();
		
		out.write("<select>");
		
		while(rs.next())
			out.write("<option value='" + rs.getString("prod_id") + "'>" + rs.getString("prod_name") + "</option>");
		
		out.write("</select>");
		
		conn.close();
	} catch(Exception e){
		e.printStackTrace();
	}
%>
