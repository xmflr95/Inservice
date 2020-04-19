package board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Content extends HttpServlet {
private static final long serialVersionUID = 1L;
	
    public Content() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Content doGet() called....");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		News news = null;
		String sql = "SELECT * FROM IBKS WHERE NUMBER = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				news = new News();
				
				news.setNumber(rs.getInt("NUMBER"));
				news.setTitle(rs.getString("TITLE"));
				news.setRequestURL(rs.getString("REQUESTURL"));
				news.setDate(rs.getDate("DATE").toString());
				news.setContent(rs.getString("CONTENT"));
				news.setMedia(rs.getString("MEDIA"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		String path = "/board/content.jsp";
		request.setAttribute("news", news);
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
