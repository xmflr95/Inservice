package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class KeywordDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public KeywordDelete() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delKeyNum = request.getParameter("delKeyNum");
		
		ArrayList<KeywordVO> keywordList = null;
		keywordList = KeywordList.getKeywordList();
		
		int delNumber = Integer.parseInt(delKeyNum);
		System.out.println(delNumber);
		
		keywordList.remove(delNumber);
		
		response.sendRedirect("/InService/board/keywordSetting");
	}

}
