package board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public List() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	System.out.println("이것이 init!!");
    	NewsData readNews = new NewsData();
    	ArrayList<News> newsDataList = null;
    	
    	try {
    		newsDataList = readNews.readNewsData();
		} catch (Exception e) {
			System.out.println("JSON 읽기 도중 오류 발생!");
		}
    	
    	String insertSql = "INSERT INTO IBKS("
				+ "NUMBER, TITLE, REQUESTURL, DATE, CONTENT, MEDIA) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
    	
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int size = newsDataList.size();
		int count = 1;
		String state = "";
		try {
			conn = DBConn.getConnection();
			
			for (int i = 0; i < size; i++) {
				pstmt = conn.prepareStatement(insertSql);
				
				News news = newsDataList.get(i);
				
				String newsDate = news.getDate();
				java.sql.Date date = java.sql.Date.valueOf(newsDate);
				
				pstmt.setInt(1, count);
				pstmt.setString(2, news.getTitle());
				pstmt.setString(3, news.getRequestURL());
				pstmt.setDate(4, date);
				pstmt.setString(5, news.getContent());
				pstmt.setString(6, news.getMedia());
				
				int result = 0;
				result = pstmt.executeUpdate();
				
				if (result == 1)
					state = "success";
				else 
					state = "fail";
				
				count++;
			}
			
			pstmt.close();
			DBConn.close();
		} catch (SQLException e) {
			System.out.println("SQL INSERT 오류 발생! ");
			e.printStackTrace();
		}
		
		
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("List doGet() called....");
		
		ArrayList<KeywordVO> keywordList = null;
		keywordList = KeywordList.getKeywordList();
		
		String selectKeyword = request.getParameter("keyword");
		System.out.println("selectKeyword : " + selectKeyword);
		
		// page
		int curPage = 1; // 현재 페이지
		int pageSize = 15; // 뿌릴 게시글 개수
		int numberOfBlock = 10; //표시할 페이지 수
		
		int start = (curPage - 1) * pageSize + 1;
		int end = curPage * pageSize;
		
		String currentPageNum = request.getParameter("currentPage");
		
		if (currentPageNum == null) {
			currentPageNum = "1";
		}
		
		int offset = (Integer.parseInt(currentPageNum) - 1) * pageSize;
		System.out.println(offset);
		// 0 -> 0, 1-> 0,         2 -> 30, 3 -> 45
		// 0           2 * ps
		// ( cpn - 1 ) * ps?
		// 0*15 = 0; 1 * 15 = 1; 2 * 15 = 30; ...
//		System.out.println("currentPageNum : " + currentPageNum);

//		int offset = (Integer.parseInt(currentPageNum)) * pageSize;
		
		String sql = "SELECT NUMBER, TITLE, DATE, MEDIA "
				+ "FROM IBKS LIMIT " + pageSize;
		
		String sql2 = "SELECT list1.* FROM ("
				+ "SELECT NUMBER, TITLE, DATE, MEDIA FROM IBKS "
				+ "ORDER BY NUMBER ASC) list1 "
				+ "LIMIT " + offset + ", " + pageSize;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<News> newsList = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql2);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				News news = null;
				newsList = new ArrayList<>();
				
				do {
					news = new News();
					news.setNumber(rs.getInt("NUMBER"));
					news.setTitle(rs.getString("TITLE"));
					news.setDate(rs.getDate("DATE").toString());
					news.setMedia(rs.getString("MEDIA"));
					
					newsList.add(news);
				} while (rs.next());
			}
			
			rs.close();
			pstmt.close();
		
		} catch(Exception e) {
			System.out.println(e);
		}
		
//		Pagination
		PageBlock pageBlock = new PageBlock();
		pageBlock.setCurPage(curPage);
		pageBlock.setNumberPerPage(pageSize);
		pageBlock.setNumberOfBlock(numberOfBlock);
		pageBlock.getNumberOfPages();
		
		int pageBlockStart = (curPage - 1) / numberOfBlock * numberOfBlock + 1;
		//  
		int pageBlockEnd = (curPage - 1) / numberOfBlock * numberOfBlock + numberOfBlock;
		
		if (pageBlockEnd > pageBlock.getNumberOfBlocks()) {
			pageBlockEnd = pageBlock.getNumberOfBlocks();
		}
		
		pageBlock.setStart(pageBlockStart);
		pageBlock.setEnd(pageBlockEnd);
		
		pageBlock.prev = pageBlock.getStart() == 1 ? false : true;
		pageBlock.next = pageBlock.getEnd() == pageBlock.getNumberOfBlocks() ? false : true;
		
		System.out.printf("curpage:%d\n",curPage);
		System.out.printf("start: %d\n",pageBlock.getStart());
		System.out.printf("end: %d\n",pageBlock.getEnd());
		System.out.printf("numberOfBlocks: %d\n",pageBlock.getNumberOfBlocks());
		
		
		// JSP로 포워드
		String path = "/board/list.jsp";
		RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		
		
		request.setAttribute("keywordList", keywordList);
		request.setAttribute("keyword", selectKeyword);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("list", newsList);
		dipatcher.forward(request, response);
	}

}
