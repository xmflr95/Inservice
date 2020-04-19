<%@page import="board.KeywordVO"%>
<%@page import="board.PageBlock"%>
<%@page import="board.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<%
	PageBlock pageBlock = (PageBlock) request.getAttribute("pageBlock");
	ArrayList<News> list = (ArrayList<News>) request.getAttribute("list");
	ArrayList<KeywordVO> keywordList = (ArrayList<KeywordVO>) request.getAttribute("keywordList");
	String keyword = (String) request.getAttribute("keyword");

	int size = 0;
	if (keywordList != null) {
		 size = keywordList.size();	
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>언런 정보 리스트</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	
	<style>
		body {
			padding: 24px;
		}
			
		.header {
			margin: 20px 0 20px 0;
		}
		
		h1 {
			display: inline-block;
		}
		
		#main_title {
			cursor: pointer;
		}
		
		.keyword_block {
			float: right;
			margin: 0 24px; 0 0;
		}
		
		.keyword_select {
			display: inline-block;
		}
		
		.keyword_label {
			margin: 0 12px 0 64px;
		}
		
		table {
			padding: 100px;
		}
		
		tfoot {
			padding: 24px;
		}
	</style>
</head>
<body>
	<div class="header">
		<h1 class="font-weight-bold" onclick="title_refresh();" id="main_title">JSP 언론 정보 리스트</h1>
		
		<div class="keyword_block">
			<form class="keyword_select" id="keyword_select" action="" method="GET">
				<label for="keyword_list" class="keyword_label">
					기업 선택 : 
					<select id="keyword_list" name="keyword" class="keyword_list" onchange="keyword_list_onChange();">
							<option disabled="disabled" <%= keyword == null ? "selected" : "" %>>기업 선택</option>
						<%
						if (keywordList != null) {
							for (int i = 0; i < size; i++) { 
								String company = keywordList.get(i).getName();
								//System.out.println("company[" + i + "] : " + company);
								//System.out.println("keyword : " + keyword);
							%>	
								<option value="<%= company %>" <%= company.equals(keyword) ? "selected" : "" %>><%= company %></option>
						<% 
							}
						} else {
						%>
						<%
						}
						%>
					</select>
				</label>
			</form>
			
			<button class="btn btn-primary" onclick="keyword_btn_onClick()">키워드 설정</button>
		</div>
	</div>
	
<table class="table">
	<thead>
		<tr align="center">
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">언론사</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
		<% if (list != null) { 
			for (News news : list) { 
		%>
			<tr align="center">
				<th scope="row"><%= news.getNumber() %></th>
				<td><a href="content?seq=<%= news.getNumber() %>"><%= news.getTitle() %></a></td>
				<td><%= news.getMedia() %></td>
				<td><%= news.getDate() %></td>
			</tr>
		<% 	}
		} 
		%>
	</tbody>
	<tfoot>
		<tr align="center">
			<td>
				<div class="pagination">
					<% 
						if(pageBlock.prev){
					%>
							<a href="list?currentPage=<%= pageBlock.getStart() - 1 %>">&laquo;</a>
					<% 
						}
					
						for(int i = pageBlock.getStart(); i <= pageBlock.getEnd(); i++) {
							if(i == pageBlock.getCurPage()) {
					%>
							<a class="active" href="list?currentPage=<%= i %>">[<%= i %>]</a>
					<%
							} else {
					%>
							<a href="list?currentPage=<%= i %>">[<%= i %>]</a>
					<%						
							}
						}
					%>
					<% 	if(pageBlock.next) { %>
							<a href="list?currentPage=<%= pageBlock.getEnd() + 1 %>">&raquo;</a>
					<% 	} %>
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<script>
	function title_refresh() {
		window.location.href = "/InService/board/list";
	}

	function keyword_list_onChange() {
		const selectForm = document.getElementById("keyword_select");
		//const keywordList = document.getElementById("keyword_list").options[document.getElementById("keyword_list").selectedIndex].value;
		// 자동 submit?
		selectForm.submit();
		// alert(keywordList);
	}

	function keyword_btn_onClick() {
		window.location.href = "/InService/board/keywordSetting";
	}
</script>
<!-- BootStrap4 -->
<script src="js/jquery-3.4.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>