package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PageBlock {
	private int curPage = 1; // 현재 페이지
	private int numberOfBlock = 10; // 출력할 페이지 블록 수
	private int numberOfBlocks = 0; // 총 페이지 블록 수 
	private int numberPerPage = 15; // 출력할 게시글 수
	private int start = 1; // 시작 페이지 블록 값
	private int end = start + numberOfBlock; //끝 페이지 블록 값
	
	public boolean prev, next; //이전, 다음버튼
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getNumberOfBlock() {
		return numberOfBlock;
	}
	public void setNumberOfBlock(int numberOfBlock) {
		this.numberOfBlock = numberOfBlock;
	}
	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}
	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}
	public int getNumberPerPage() {
		return numberPerPage;
	}
	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}

	public void getNumberOfPages() {
		
		String sql = "SELECT CEIL(COUNT(*) / ?) AS numberOfBlocks FROM IBKS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, numberPerPage);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				numberOfBlocks = rs.getInt("numberOfBlocks");
				System.out.printf("numberOfBlocks:%d\n", numberOfBlocks);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
				
	}
}
