<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FileUploadForm</title>
</head>
<body>
	<form action="${cp }/fileUpload"
	method="post" enctype="multipart/form-data">
	<%-- enctype부분 설정이 위와 같이 되어 있어야 파일 업로드가 가능하다 --%>
		userId : <input type="text" name="userId" value="brown"/><br>
		file : <input type="file" name="profile"/><br>
		<input type="submit" value="전송"/>
	</form>
</body>
</html>