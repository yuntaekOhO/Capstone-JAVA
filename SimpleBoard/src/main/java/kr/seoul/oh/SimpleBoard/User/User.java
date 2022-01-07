package kr.seoul.oh.SimpleBoard.User;

import lombok.Data;

@Data
public class User {
	String id;
	String email;
	String password;
	String name;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
}
