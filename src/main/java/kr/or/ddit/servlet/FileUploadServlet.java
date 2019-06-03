package kr.or.ddit.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/fileUpload")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// userId, profile 파라미터를 확인
		String userId = request.getParameter("userId");
		String profile = request.getParameter("profile");
		logger.debug("userId : {}", userId);
		logger.debug("profile : {}", profile);
		
		
		Part part = request.getPart("profile"); // 괄호 안은 input type에 적은 name 속성명(파라미터명)
		logger.debug("part.getSize() : {}", part.getSize()); // 파일을 선택하지 않았을 경우 0으로 뜬다
		
		// 파일이 존재할 때만 파일을 정해진 위치에 기록한다
		if(part.getSize() > 0){
			logger.debug("part.getContentType() : {}", part.getContentType());
			logger.debug("part.getName() : {}", part.getName());
			
	//		Collection<String> headerNames = part.getHeaderNames();
	//		for(String header : headerNames)
	//			logger.debug("{} : {}",header, part.getHeader(header));
			
			String contentDisposition = part.getHeader("content-disposition");
			String fileName = PartUtil.getFileName(contentDisposition);
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
			// 파일 디스크에 쓰기
			part.write(uploadPath + File.separator + UUID.randomUUID().toString() + ext);
			part.delete(); // 디스크 임시 공간에 저장된 파일이 있다면 삭제한다.
			}
		}
	}

}
