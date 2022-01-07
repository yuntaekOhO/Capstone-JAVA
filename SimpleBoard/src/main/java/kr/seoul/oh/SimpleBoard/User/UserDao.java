package kr.seoul.oh.SimpleBoard.User;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private final String LIST_USERS = "select id, email, name from user order by id desc limit ?,?";
	private final String GET_USER_BY_ID = "select id, email, name from user where id=?";
	private final String ADD_USER = "insert user(email, password, name) values(:email, sha2(:password,256), :name)";
	private final String UPDATE_USER = "update user set email=:email, name=:name where id=:id";
	private final String DELETE_USER = "delete from user where id=?";
	private final String GET_USER_BY_EMAIL_PASSWORD = "select id, email, name from user where (email, password) = (?, sha2(?,256))";
	private final String UPDATE_PASSWORD = "update user set password=sha2(?,256) where (id, password) = (?, sha2(?,256))";
	private final String CHECK_EMAIL = "select email from user";
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<User>(User.class);
	private Logger logger = LogManager.getLogger();

	public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.logger.info(this.getClass().getSimpleName() + "인스턴스를 생성했습니다.");
	}

	public List<User> listUsers(int offset, int count) {
		return this.jdbcTemplate.query("select id, email, name from user order by id desc limit ?,?",
				this.USER_ROW_MAPPER, new Object[]{offset, count});
	}

	public User getUserById(String id) {
		return (User) this.jdbcTemplate.queryForObject("select id, email, name from user where id=?",
				this.USER_ROW_MAPPER, new Object[]{id});
	}

	public int addUser(User user) {
		return this.namedParameterJdbcTemplate.update(
				"insert user(email, password, name) values(:email, sha2(:password,256), :name)",
				new BeanPropertySqlParameterSource(user));
	}

	public int updateUser(User user) {
		return this.namedParameterJdbcTemplate.update("update user set email=:email, name=:name where id=:id",
				new BeanPropertySqlParameterSource(user));
	}

	public int deleteUser(String id) {
		return this.jdbcTemplate.update("delete from user where id=?", new Object[]{id});
	}

	public User getUserByEmailAndPassword(String email, String password) {
		return (User) this.jdbcTemplate.queryForObject(
				"select id, email, name from user where (email, password) = (?, sha2(?,256))", this.USER_ROW_MAPPER,
				new Object[]{email, password});
	}

	public int updatePassword(String id, String oldPassword, String newPassword) {
		return this.jdbcTemplate.update("update user set password=sha2(?,256) where (id, password) = (?, sha2(?,256))",
				new Object[]{newPassword, id, oldPassword});
	}

	public List<User> emailDuplicateCheck() {
		return this.jdbcTemplate.query("select email from user", this.USER_ROW_MAPPER);
	}
}
