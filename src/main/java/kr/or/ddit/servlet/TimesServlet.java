package kr.or.ddit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimesServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	//// logger를 쓰기 위해 선언 및 생성
	//// Logger 임포트 시 slf4j를 선택해 줄 것
	private Logger logger = LoggerFactory.getLogger(TimesServlet.class); // 맨 뒤 괄호는 현재 클래스명
	//괄호 안은 kr.or.ddit.servlet.TimesServlet
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		// localhost/jsp/timesTables?param=6&param2=7
		// http://localhost/jsp/TimesTablesServlet?i=6&j=7
		
		// String 타입 인수를 받아 String으로 반환
		String param = req.getParameter("i"); // 얻어오는 파라미터값을 매칭
		
		//// logger에서 사용하는 메서드. 바뀐다고 로그 출력 방법이 달라지는 것은 아니다
		//// trace / debug / info / warn / error
		logger.debug("param: {}, {}", param, "test");
		
		String param2 = req.getParameter("j");
		logger.debug("param2: {}", param2);
		
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
