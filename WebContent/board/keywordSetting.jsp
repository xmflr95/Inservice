<%@page import="board.KeywordList"%>
<%@page import="board.Keyword"%>
<%@page import="board.KeywordVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<KeywordVO> curKeyword = (ArrayList<KeywordVO>) request.getAttribute("curKeyword");
	//ArrayList<KeywordVO> addKeyword = (ArrayList<KeywordVO>) request.getAttribute("addKeyword");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>키워드 설정</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	
	<style>
		body {
			padding: 24px;
		}
		
		.main_title {
			display: inline-block;
			cursor: pointer;
		}
		
		.keyword_list {
			margin: 24px 0 0 0;
		}
		
		#add_form {
			margin: 24px 0 0 0;
		}
		
		.menu_btn {
			margin: 24px 0 0 0;
		}
		
		.add_keyword_btn .back_btn {
			width: 144px;
			height: 54px;
			font-size: 20px;
		}
	</style>
</head>
<body>
	<h1 class="main_title font-weight-bold" onclick="main_title_refresh(); return false;">키워드 설정</h1>
	
	<div class="keyword_list">
		<h5>▷▶ 현재 기업 리스트</h5>
		<ul class="list-group">
		<%
		if (curKeyword.size() != 0) {
			for (int i = 0; i < curKeyword.size(); i++) {
			String keyword = curKeyword.get(i).getName();
		%>
			<li class="list-group-item" value="<%= i %>">
			<%= keyword %>
			<form action="keywordDelete" method="POST" style="display:inline;">
				<button type="submit" class="close delete_btn" aria-label="Close" onclick="" name="delKeyNum" value="<%= i %>">
					<span aria-hidden="true">&times;</span>
				</button>
			</form>
			</li>
		<%
			}
		} else {
		%>
			<li class="list-group-item">키워드가 없습니다. 키워드를 추가하십시오.</li>	
		<%
		}
		%>
		</ul>
	</div>
	
	<form method="POST" class="add_keyword_form" id="add_form">
		<div class="form-group">
		    <label for="addKeyword">※ 키워드 이름</label>
		    <input type="text" class="form-control add_keyword" id="addKeyword" name="addKeyword">
	  	</div>
	  	<button type="submit" class="btn btn-success add_btn">추가하기</button>		
	</form>
	
	
	<div class="menu_btn">
		<button class="btn btn-primary export_keyword_btn" onclick="export_keyword_btn_onclick();">키워드 내보내기</button>
		<button class="btn btn-secondary back_btn" onclick="back_btn();">목록으로</button>
	</div>


<!-- BootStrap4 -->
<script>
	function main_title_refresh() {
		window.location.href = "/InService/board/keywordSetting";
	}

	function delete_btn_onclick() {
//		window.location.href = "/InService/board/keywordDelete?delKeyNum=" + clickNum;		
	}

	
	function export_keyword_btn_onclick() {
		const notice = confirm("키워드를 JSON 파일로 내보내시겠습니까?");
		if (notice) {
			window.location.href = "/InService/board/keywordSave";
			alert("내보내기 완료!");
		} else {
			return;
		}	
	}

	function back_btn() {
		window.location.href = "/InService/board/list";		
	}
</script>
<script src="js/jquery-3.4.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>