package model;

public class BoardDto {
	private int no;
	private String title;
	private String content;
	private String writer_id;
	private String regdate;
	private int hit;
	 
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getHit_count() {
		return hit;
	}
	public void setHit_count(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", title=" + title + ", content=" + content + ", writer_id=" + writer_id
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}
	
	
	
}
