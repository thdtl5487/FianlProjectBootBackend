<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="../css/common.css">
<c:set var="data" value="${cboard.btext}" />
</head>

<body>
	<div class="container">
		<div class="box-wrapper">
			<form action="modify.do" method="post">
				<p>
					제목 : <br>
					<input id="title_box" type="text"  value="${cboard.btitle}" name = "btitle">
<%-- 					<input id="title_box" type="hidden" name="btitle" value="${Param.btitle}"> --%>
				</p>
				<p>
					내용 : <br />
					<textarea id="content_box" rows="5" cols="30"  name = "btext">${cboard.btext}</textarea>
<%-- 					<input id="title_box" type="hidden" name="btext" value="${Param.btext}"> --%>
				</p>
				<p>
					글쓴이 : <br />
					<input id="title_box"  type="text" value ="${cboard.bwriter}"  name = "bwriter">
<%-- 					<input id="title_box" name="bwriter" type="hidden" value="${Param.bwriter}"> --%>
					
				</p>		
				<input type="submit" value="게시글 수정">
				<button type="button" onclick="javascript:history.go(-1);">취소</button>				
			</form>		
		</div>
	</div>
</body>
</html>		