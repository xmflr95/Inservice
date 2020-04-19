package board;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NewsData {
	public ArrayList<News> readNewsData() throws Exception {
//		키워드 순서대로 저장할 리스트
		ArrayList<News> newsList = new ArrayList<News>();
		
//		키워드 가져오기
		JSONParser parser = new JSONParser();
//		keyword.json 경로
		Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/news.json"));
		
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray newsArray = (JSONArray) jsonObject.get("news");
		
		int size = newsArray.size();
		
		for (int i = 0; i < size; i++) {
			News news = new News();
			JSONObject newsObject = (JSONObject) newsArray.get(i);
			
			String title = newsObject.get("title").toString();
			String requestURL = newsObject.get("requestURL").toString();
			String date = newsObject.get("date").toString();
			String media = newsObject.get("media").toString();
			String content = newsObject.get("content").toString();
			
			news.setTitle(title);
			news.setRequestURL(requestURL);
			news.setDate(date);
			news.setMedia(media);
			news.setContent(content);
			
			newsList.add(news);
		}
		
//		for (News item : newsList) {
//			System.out.println(item.getTitle());
//		}
//		
		return newsList;
	}
}
