package kr.seoul.oh.SimpleBoard.Common;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.seoul.oh.SimpleBoard.Board.Board;
import kr.seoul.oh.SimpleBoard.User.User;

public class UriPatcher {
	private Object obj;
	private int page;
	private int count;
	private String path;

	public UriPatcher(Object obj, int count, int page) {
		this.obj = obj;
		this.count = count;
		this.page = page;
		if (obj instanceof Board) {
			this.path = "board/boardList";
		} else if (obj instanceof User) {
			this.path = "user/userList";
		}

	}

	public String getUri() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path(this.path)
				.queryParam("page", new Object[]{this.page}).queryParam("count", new Object[]{this.count}).build();
		return uriComponents.toUriString();
	}
}
