package kr.or.ddit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		// localhost/jsp/timesTables?param=6&param2=7
		// http://localhost/jsp/TimesTablesServlet?i=6&j=7
		
		// String 타입 인수를 받아 String으로 반환
		String param = req.getParameter("i"); // 얻어오는 파라미터값을 매칭
		String param2 = req.getParameter("j");
		
//		System.out.println("param : " + param);
		
		String g = "";
		g += "<html>";
		g += "	<head>";
		g += "		<style>";
		g += "			td{";
		g += "			padding : 8px;";
		g += "			}";
		g += "		</style>";
		g += "	</head>";
		g += "	<body>";
		g += "		<table border='1'>";
		for (int i = 1; i <= Integer.parseInt(param2); i++) {
			g += "<tr>";
			for (int j = 2; j <= Integer.parseInt(param); j++) {
				g += "<td>" + (j + " * " + i + " = " + j*i) + "</td>";
			}
			g += "</tr>";
		}
		g += "		</table>";
		g += "	</body>";
		g += "</html>";
		
//		for (int i = 1; i < 10; i++) {
//			for (int j = 2; j < 10; j++) {
//				g += j + " * " + i + " = " + j*i;
//				g += "\t";
//			}
//			g += "\n";
//		}
		
		pw.write(g);
		
		pw.close();
		
	}
}
