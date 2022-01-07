package kr.seoul.oh.SimpleBoard.Board;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	private final String LIST_BOARDS = "select seq, title, left(regdate, 16) regdate, writer, cnt from board order by seq desc limit ?,?";
	private final String GET_BOARD = "select seq, title, content, left(regdate, 16) regdate, writer, cnt from board where seq=?";
	private final String ADD_BOARD = "insert board(title, content, writer) values(:title,:content,:writer)";
	private final String UPDATE_BOARD = "update board set title=:title, content=:content where seq=:seq";
	private final String DELETE_BOARD = "delete from board where seq=?";
	private final String INCREASE_COUNT = "update board set cnt=cnt+1 where seq=?";
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Board> BOARD_ROW_MAPPER = new BeanPropertyRowMapper(Board.class);

	public BoardDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Board> listBoards(int offset, int count) {
		return this.jdbcTemplate.query(
				"select seq, title, left(regdate, 16) regdate, writer, cnt from board order by seq desc limit ?,?",
				this.BOARD_ROW_MAPPER, new Object[]{offset, count});
	}

	public Board getBoard(String seq) {
		return (Board) this.jdbcTemplate.queryForObject(
				"select seq, title, content, left(regdate, 16) regdate, writer, cnt from board where seq=?",
				this.BOARD_ROW_MAPPER, new Object[]{seq});
	}

	public int addBoard(Board board) {
		return this.namedParameterJdbcTemplate.update(
				"insert board(title, content, writer) values(:title,:content,:writer)",
				new BeanPropertySqlParameterSource(board));
	}

	public int updateBoard(Board board) {
		return this.namedParameterJdbcTemplate.update("update board set title=:title, content=:content where seq=:seq",
				new BeanPropertySqlParameterSource(board));
	}

	public int deleteBoard(String seq) {
		return this.jdbcTemplate.update("delete from board where seq=?", new Object[]{seq});
	}

	public void increaseCount(String seq) {
		this.jdbcTemplate.update("update board set cnt=cnt+1 where seq=?", new Object[]{seq});
	}
}
