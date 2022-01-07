package kr.seoul.oh.SimpleBoard.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.seoul.oh.SimpleBoard.Common.UriPatcher;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	User emptyUser;
	private int page;
	private int count;
	String userUri;
	private Logger logger = LogManager.getLogger();

	@GetMapping({"/**"})
	public void mapDefault(HttpServletRequest request) {
		this.logger.debug("Default mapping : {}", request.getPathInfo());
	}

	@GetMapping({"/user/loginForm"})
	public void userLogin() {
		this.logger.info("로그인 화면 접속");
	}

	/*
	 * 회원 목록 화면
	 */
	@GetMapping({"/user/userList"})
	public void userList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "count", required = false, defaultValue = "10") int count, Model model) {
		this.page = page;
		this.count = count;
		int offset = (page - 1) * count;
		List<User> userList = this.userDao.listUsers(offset, count);
		this.userUri = (new UriPatcher(this.emptyUser, count, page)).getUri();
		model.addAttribute("uri", this.userUri);
		model.addAttribute("userList", userList);
		this.logger.info("회원 목록 접속");
	}

	/*
	 * 회원 상세 정보 화면
	 */
	@GetMapping({"/user/userInfo"})
	public void userInfo(String id, Model model) {
		User user = this.userDao.getUserById(id);
		model.addAttribute("uri", this.userUri);
		model.addAttribute("user", user);
		this.logger.info("ID : {} 인 회원 상세 조회", user.getId());
	}

	@GetMapping({"/user/createAccount"})
	public void userForm() {
	}

	/*
	 * 회원 정보 수정 화면
	 */
	@GetMapping({"/user/userEdit"})
	public void userEdit(String id, Model model) {
		User user = this.userDao.getUserById(id);
		model.addAttribute("user", user);
		this.logger.info("ID : {} 유저 수정 화면", user.getId());
	}

	/*
	 * 회원 가입 액션
	 */
	@PostMapping({"/user/addUser"})
	public String addUser(User user, RedirectAttributes model) {
		model.addFlashAttribute("user", user);

		try {
			this.userDao.addUser(user);
			this.logger.info("{}님이 회원가입 했습니다.", user.getName());
			return "redirect:createComplete";
		} catch (DuplicateKeyException var4) {
			return "redirect:createAccount?mode=ERROR";
		}
	}

	/*
	 * 회원 정보 수정 액션
	 */
	@PostMapping({"/user/updateUser"})
	public String updateUser(User user, Model model) {
		this.userDao.updateUser(user);
		model.addAttribute("user", user);
		this.logger.info("수정내용 : {}", user.toString());
		return "redirect:userInfo?id=" + user.getId();
	}

	/*
	 * 회원 삭제 액션
	 */
	@GetMapping({"/user/deleteUser"})
	public String deleteUser(String id, Model model) {
		int updatedRows = this.userDao.deleteUser(id);
		if (updatedRows != 1) {
			this.logger.info("삭제 실패");
			return String.format("redirect:userList?page=%s&count=%s", this.page, this.count);
		} else {
			this.logger.info("삭제 성공");
			return String.format("redirect:userList?page=%s&count=%s", this.page, this.count);
		}
	}

	/*
	 * 로그인 액션
	 */
	@PostMapping({"/user/loginAction"})
	public String loginAction(String email, String password, HttpSession session, RedirectAttributes model) {
		try {
			User user = this.userDao.getUserByEmailAndPassword(email, password);
			session.setAttribute("user", user);
			this.logger.info("{}님이 로그인 했습니다.", user.getName());
			return "redirect:userInfo?id=" + user.getId();
		} catch (EmptyResultDataAccessException var6) {
			model.addFlashAttribute("email", email);
			return "redirect:loginForm?mode=ERROR";
		}
	}

	/*
	 * 로그아웃 액션
	 */
	@GetMapping({"/user/logout"})
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "common/index";
	}
}
