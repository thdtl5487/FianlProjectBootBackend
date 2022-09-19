<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<title>메인화면</title>
<link rel="stylesheet" href="../css/common.css">
</head>
<body>
	<div class="container">
		<!-- 1. 게시글 등록 -->
		<div class="btn-wrapper">
			<form action="community/write.do">
				<input type="submit" value="게시글 등록" style="width:300px"  width="3000px" /><br>
				<br>
			</form>
			<!-- 2. 게시글 조회 -->
			<form action="community/read.do">
				<input type="submit" value="최근 게시글 보기" style="width:300px"  width="3000px"  /><br>
				<br>
			</form>
			<!-- 3. 게시글 수정 -->
			<form action="community/modify.do">
				<input type="submit" value="최근 게시글 수정" style="width:300px"  width="3000px"  /><br>
				<br>
			</form>
			<!-- 4. 게시글 삭제 -->
			<form action="community/delete.do">
				<input type="submit" value="최근 게시글 삭제" style="width:300px"  /><br>
				<br>
				</form>
	         <form action="community/getList.do">
	            <input type="submit" value="전체 글 목록 확인" style="width:300px"  /><br>
	            <br>
	         </form>
		</div>
	</div>
</body>
</html>