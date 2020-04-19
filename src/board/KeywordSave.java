package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KeywordSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public KeywordSave() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<KeywordVO> keywordList = null;		
		keywordList = KeywordList.getKeywordList();
		
		Keyword keyword = new Keyword();
		
		try {
			keyword.setKeyword(keywordList);
		} catch (Exception e) {
			System.out.println("키워드 내보내기 오류!");
		}
		
		response.sendRedirect("/InService/board/keywordSetting");
	}

}
