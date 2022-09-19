<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>메인화면</title>
<link rel="stylesheet" href="../css/common.css">
<c:set var="data" value="${cboard}" />
</head>
<body>
	<div class="container">
		<div class="box-wrapper">			
			<p>제목 : <br>
			<input id="title_box" type="text" name="btitle" value="${cboard.btitle}">			
			</p>
			
			<p>글 내용 : 
			<div id="content_box"> ${cboard.btext}</div>

			</p>
			<form action="/">
				<input type="submit" value="처음으로">
			</form>
		</div>
	</div>
</body>
</html>