package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class KeywordSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public KeywordSetting() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Keyword keyword = new Keyword();
		// 키워드 읽기
		
		ArrayList<KeywordVO> keywordList = null;
		
		keywordList = KeywordList.getKeywordList();
		
		// JSP로 포워드
		String path = "/board/keywordSetting.jsp";
		RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		
		// 현재 키워드 리스트
		request.setAttribute("curKeyword", keywordList);
		dipatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
		String keyword = request.getParameter("addKeyword");
		
		ArrayList<KeywordVO> keywordList = null;
		keywordList = KeywordList.getKeywordList();
		
		KeywordVO newKeyword = new KeywordVO();
		newKeyword.setName(keyword);
		
		keywordList.add(newKeyword);
		
		// JSP로 포워드
		String path = "/board/keywordSetting.jsp";
		RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		
		// 현재 키워드 리스트
		request.setAttribute("curKeyword", keywordList);
		dipatcher.forward(request, response);
	}
}
