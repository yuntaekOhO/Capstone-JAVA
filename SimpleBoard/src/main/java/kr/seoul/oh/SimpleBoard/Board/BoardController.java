package kr.seoul.oh.SimpleBoard.Board;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.seoul.oh.SimpleBoard.Common.UriPatcher;

@Controller
public class BoardController {
	@Autowired
	private BoardDao boardDao;
	private Board emptyBoard;
	private Logger logger = LogManager.getLogger();
	String uri;
	private int page;
	private int count;

	@GetMapping({"/board/boardList"})
	public void boardList(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int count, Model model) {
		this.page = page;
		this.count = count;
		int offset = (page - 1) * count;
		List<Board> boardList = this.boardDao.listBoards(offset, count);
		this.uri = (new UriPatcher(this.emptyBoard, count, page)).getUri();
		model.addAttribute("uri", this.uri);
		model.addAttribute("boardList", boardList);
		this.logger.info("게시판 목록 접속");
	}

	@GetMapping({"/board/boardInfo"})
	public void boardInfo(String seq, Model model) {
		Board board = this.boardDao.getBoard(seq);
		this.boardDao.increaseCount(seq);
		model.addAttribute("uri", this.uri);
		model.addAttribute("board", board);
		this.logger.info("{} 번 글 조회", board.getSeq());
	}

	@GetMapping({"/board/boardForm"})
	public void boardForm() {
	}

	@GetMapping({"/board/boardEdit"})
	public void boardEdit(String seq, Model model) {
		Board board = this.boardDao.getBoard(seq);
		model.addAttribute("board", board);
		this.logger.info("{} 번 글 수정", board.getSeq());
	}

	@PostMapping({"/board/addBoard"})
	public String addBoard(Board board, Model model) throws IOException {
		this.boardDao.addBoard(board);
		model.addAttribute("board", board);
		this.logger.info("{} 번 글이 등록되었습니다.", board.getSeq());
		return "redirect:boardList";
	}

	@PostMapping({"/board/updateBoard"})
	public String updateBoard(Board board, Model model) throws IOException {
		this.boardDao.updateBoard(board);
		model.addAttribute("board", board);
		this.logger.info("{} 번 글이 수정되었습니다.", board.getSeq());
		return "redirect:boardInfo?seq=" + board.getSeq();
	}

	@GetMapping({"/board/deleteBoard"})
	public String deleteBoard(String seq) {
		int updateRows = this.boardDao.deleteBoard(seq);
		if (updateRows == 0) {
			this.logger.warn("삭제 실패");
			return String.format("redirect:boardList?page=%s&count=%s", this.page, this.count);
		} else {
			this.logger.info("{} 번 글이 삭제되었습니다.", seq);
			return String.format("redirect:boardList?page=%s&count=%s", this.page, this.count);
		}
	}
}
