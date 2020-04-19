<%@page import="board.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	News news = (News) request.getAttribute("news");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Test Page</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	
	<style>
		.menu_btn {
			margin: 12px;
		}
		
		.back_btn {
			width: 140px;
			height: 50px;
			font-size: 20px;
		}
	</style>
</head>
<body>
	<div class="card">
		<div class="card-header">번호 : <%= news.getNumber() %></div>
		<div class="card-body">
			<h5 class="card-title">제목 : <%= news.getTitle() %></h5>
			<p class="card-text">언론 : <%= news.getMedia() %></p>
			<p class="card-text">등록일 : <%= news.getDate() %></p>
			<p class="card-text">내용 : </p>
			<p class="card-text"><%= news.getContent() %></p>
		</div>
	</div>
	
	<div class="menu_btn">
		<button class="btn btn-secondary back_btn" onclick="back_btn()">뒤로가기</button>
	</div>


<!-- BootStrap4 -->
<script>
	function back_btn() {
		// window.location.href = "/InService/board/list";
		history.back();
	}
</script>
<script src="js/jquery-3.4.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>