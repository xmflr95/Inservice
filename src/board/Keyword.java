package board;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Keyword {
	
	public ArrayList<KeywordVO> getKeyword() throws Exception {
//		키워드 순서대로 저장할 리스트
		ArrayList<KeywordVO> keyList = new ArrayList<KeywordVO>();
		
//		키워드 가져오기
		JSONParser parser = new JSONParser();
//		keyword.json 경로
		Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/keyword.json"));
		
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray keywordArray = (JSONArray) jsonObject.get("keyword");
		
		int size = keywordArray.size();
		
		for (int i = 0; i < size; i++) {
			JSONObject nameObject = (JSONObject) keywordArray.get(i);

			if (nameObject == null) {
				System.out.println("keyword가 없습니다!");
				return keyList;
			} else {
				KeywordVO keyword = new KeywordVO();			
				
				String companyName = nameObject.get("name").toString();
				
				keyword.setName(companyName);
				
				keyList.add(keyword);				
			}
			
		}
		
		return keyList;
	}
	
//		JSON 
	public void setKeyword(ArrayList<KeywordVO> keywordList) throws Exception {
		int size = keywordList.size();
		
		JSONObject jsonObject = new JSONObject();
		
		// 전체 키워드 리스트
		JSONArray keywordArray = new JSONArray();
		
		// 키 하나의 정보
		JSONObject keyInfo = new JSONObject();
		
		for (int i = 0; i < size; i++) {
			KeywordVO company = keywordList.get(i);
			
			keyInfo.put("name", company.getName());
			
			// JsonArray에 기사 하나 정보 저장
			keywordArray.add(keyInfo);
			// 다음 정보를 위한 초기화
			keyInfo = new JSONObject();
		}
		
//		"news" 키워드 obejct에 배열 저장
		jsonObject.put("keyword", keywordArray);
		
		String jsonInfo = jsonObject.toJSONString();
//		System.out.println(jsonInfo);
//		
		
//		JSON 생성
		FileWriter file = new FileWriter("C:/Users/xmflr/Desktop/test/keyword.json", false);
		file.write(jsonInfo);
		file.flush();
		file.close();
		
		System.out.println("☆★☆★☆★☆★☆★ << 내보내기 작업 완료 >> ☆★☆★☆★☆★☆★");
	}
	
//	JSON keyword 삭제
	public void deleteKeyword(ArrayList<KeywordVO> keywordList) throws Exception {
		int size = keywordList.size();
		
		JSONObject jsonObject = new JSONObject();
		// 전체 키워드 리스트
		JSONArray keywordArray = new JSONArray();
		// 키 하나의 정보
		JSONObject keyInfo = new JSONObject();
//		JSON 생성
//		FileWriter file = new FileWriter("C:/Users/xmflr/Desktop/test/keyword.json", false);
//		file.write(jsonInfo);
//		file.flush();
//		file.close();
	}
}
