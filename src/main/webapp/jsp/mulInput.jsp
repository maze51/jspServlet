<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${cp}/MulCalculation" method="post">
		param1: <input type="text" name="param1"> <br>
		param2: <input type="text" name="param2"> <br>
		<button>실행</button>
	</form>
</body>
</html>