package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dto.UserDTO;

public class UserController {

	ArrayList<UserDTO> list;

	public UserController() { // 생성자

		list = new ArrayList<UserDTO>();
		// 기본 회원 데이터들

		UserDTO admin = new UserDTO();
		admin.setId(0); // 관리 id값은 0
		admin.setUserId("admin");
		admin.setPasssword("admin");
		admin.setName("admin");
		admin.setJumin("000000");

		UserDTO user1 = new UserDTO();
		user1.setId(1); // 관리 id값은 0
		user1.setUserId("user1111");
		user1.setPasssword("user1111");
		user1.setName("User1");
		user1.setJumin("930610");

		UserDTO user2 = new UserDTO();
		user2.setId(2); // 관리 id값은 0
		user2.setUserId("user2222");
		user2.setPasssword("user2222");
		user2.setName("User2");
		user2.setJumin("940203");

		list.add(admin);
		list.add(user1);
		list.add(user2);

	}

	// 회원과 관련된 메소드 :

	// 모든 회원목록 불러오기
	public ArrayList<UserDTO> selectAll() {
		return list;
	}
	
	public UserDTO selectOne(String userId, String userPw) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserId().equals(userId) && list.get(i).getPasssword().equals(userPw)) {
				return list.get(i);
			}
		}
		return null;
	}

	//회원가입하는 메소드
	public void join(UserDTO u) {
		list.add(u);
	}
	//회원정보 업데이트
	public void updateProfile(UserDTO userDTO) {
		list.set(list.indexOf(userDTO), userDTO);
	}
	//회원 정보 삭제 	
	public void delete(UserDTO u) {
		list.remove(u);
	}

	
	
	
}
