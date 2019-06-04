package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/userModify")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class UserModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(UserModifyController.class);
	
	private IuserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UserModifyController doGet()");
		
		String userId = request.getParameter("sendId");
		logger.debug("sendId : " + userId);
		UserVo userVo = userService.getUser(userId);
		request.setAttribute("userVo", userVo);
		
		request.getRequestDispatcher("/user/userModify.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		logger.debug("UserModifyController doPost()");
		
		String userId = request.getParameter("userId");
		String name   = request.getParameter("name");
		String alias  = request.getParameter("alias");
		String addr1  = request.getParameter("addr1");
		String addr2  = request.getParameter("addr2");
		String zipcd  = request.getParameter("zipcd");
		String birth  = request.getParameter("birth");
		
		// 사용자가 보낸 평문 비밀번호 데이터
		String pass   = request.getParameter("pass");
		// 암호화
		pass = KISA_SHA256.encrypt(pass);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//sdf.parse(birth); // 문자열을 입력받아 Date로 치환
		
		UserVo userVo = null;
		try {
			userVo = new UserVo(name, userId, alias, 
					pass, addr1, addr2, zipcd, sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 사진파일 처리 시작-------------------------------------------------
		// profile 파일 업로드 처리
		Part profile = request.getPart("profile");
		// 사용자가 파일을 업로드한 경우
		if(profile.getSize() > 0){
			
			String contentDisposition = profile.getHeader("content-disposition");
			String filename = PartUtil.getFileName(contentDisposition); // 실제 파일명
			String ext = PartUtil.getExt(filename);
			
			String uploadPath = PartUtil.getUploadPath();
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
			// 파일 디스크에 쓰기
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVo.setPath(filePath);
			userVo.setFilename(filename);
			
			profile.write(filePath);
			profile.delete(); // 디스크 임시 공간에 저장된 파일이 있다면 삭제한다.
			}
		}
		
		int updateCnt = userService.updateUser(userVo);
			
		if(updateCnt == 1)
//			response.sendRedirect(request.getContextPath()+"/userPagingList");
			// 수정 완료되면 사용자 상세정보 페이지로 이동
			request.setAttribute("userVo", userVo);
			request.getRequestDispatcher("/user/user.jsp").forward(request, response);
		
		
	}

}
