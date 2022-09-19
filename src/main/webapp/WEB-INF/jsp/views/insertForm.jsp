<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 등록</title>
<link rel="stylesheet" href="../css/common.css">
<c:set var="data" value="${cboard}" />
</head>
<body>
	<div class="container">
		<div class="box-wrapper">
		<form action="write.do" method="post">	   			
				<p>
					제목 : <br><input id="title_box" type="text" name="Btitle" value="${param.Btitle}">
				</p>
				<p>
					내용 : <br />
					<textarea id="content_box" name="Btext" rows="5" cols="30">${param.Btext}</textarea>
				</p>				
							
				<p>
					글쓴이 : <br />
					<input id="title_box" name="Bwriter" type="text" value="${param.Bwriter}">
				</p>				
							
				<input type="submit" value="게시글 등록">
				<button type="button" onclick="javascript:history.go(-1);">취소</button>								
			</form>
			
		</div>	
	</div>
</body>
</html>