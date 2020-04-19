package board;

import java.util.ArrayList;

public class KeywordList {
	private static ArrayList<KeywordVO> keywordList = null;
	
	private KeywordList() {
		
	}
	
	public static ArrayList<KeywordVO> getKeywordList() {
		if (keywordList == null) {
			keywordList = new ArrayList<KeywordVO>();
		}
		return keywordList;
	}
}
