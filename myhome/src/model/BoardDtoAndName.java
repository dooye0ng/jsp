package model;

public class BoardDtoAndName extends BoardDto {
	private String writer_name;

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	@Override
	public String toString() {
		return "BoardDtoAndName [writer_name=" + writer_name + ", getNo()=" + getNo() + ", getTitle()=" + getTitle()
				+ ", getContent()=" + getContent() + ", getWriter_id()=" + getWriter_id() + ", getRegdate()="
				+ getRegdate() + ", getHit()=" + getHit() + "]";
	}
}
