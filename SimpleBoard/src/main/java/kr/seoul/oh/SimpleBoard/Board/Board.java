package kr.seoul.oh.SimpleBoard.Board;

import lombok.Data;

@Data
public class Board {
	private String seq;
	private String title;
	private String content;
	private String regdate;
	private String writer;
	private int cnt;
}
