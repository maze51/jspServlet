package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;

@WebServlet("/userPagingList")
public class UserPagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IuserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		// page, pageSize 파라미터가 없을 경우 page=1, pageSize=10 기본값을 설정한다
		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		
		// page, pageSize 파라미터 받기 left.jsp에 주어진 /userPagingList?page=1&pageSize=10부분을 받는다. 위에서 개선
//		page = Integer.parseInt(request.getParameter("page"));
//		pageSize = Integer.parseInt(request.getParameter("pageSize"));

		// page, pageSize값을 pageVo에 담아 userPagingList의 매개변수로 보낸다
		PageVo pageVo = new PageVo(page, pageSize);
		
		// pageVo를 이용한 사용자 페이징 리스트 조회
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");

		// 객체를 속성으로 넣어 넘어갈 jsp페이지에서 참조할 수 있게 한다
		request.setAttribute("userList", userList);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("pageVo", pageVo);
		
		// 페이징리스트 화면을 만드는 jsp파일로 forward
		request.getRequestDispatcher("user/userPagingList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
