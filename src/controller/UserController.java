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

	public void joinUser(Scanner sc) {

		UserDTO m = new UserDTO();
		int id = list.get(list.size() - 1).getId() + 1;
		m.setId(id); // id - auto increment
		System.out.println("++++ JOIN ++++");
		System.out.print("*   I  D   :");
		// 아이디 중복확인 및 정규표현식
		String userId = sc.nextLine();
		outer: while (true) {
			int i = 0, j = 0;
			inner: for (UserDTO u : list) {
				if (userId.equals(u.getName())) {
					i++;
					j++;
					break inner;
				}
			}
			if ( ! userId.matches("^[a-z]{1}[a-zA-Z0-9]{3,9}$"))
				i++;
			switch (i) {
			case 0:
				break outer;
			case 1:
				if (j == 1) {
					System.out.println("! I D Exist !");
					System.out.print("*   I  D   :");
					userId = sc.nextLine();
				} else {
					System.out.println(" ! 4 ~ 10 letter (contains a~z & 0~9)!");
					System.out.print("*   I  D   :");
					userId = sc.nextLine();
				}
				break;
			case 2:
				System.out.println("! I D Exist !");
				System.out.print("*   I  D   :");
				userId = sc.nextLine();
				break;
			}
		}
		m.setUserId(userId);
		System.out.println("! PW must contain letter & number (length : 4~15) !");
		System.out.print("* PASSWORD :");
		String password = sc.nextLine();

		// 비밀번호 정규 표현식
		while (true) {
			
			if (!password.matches("^(?=.*[a-z])(?=.*[0-9])(?=.*\\d)[a-zA-Z\\d]{4,}$")) {
				System.out.println("! PW must contain letter & number (length : 4~15) !");
				System.out.print("* PASSWORD :");
				password = sc.nextLine();
			} else
				break;
		}
		m.setPasssword(password);
		
		System.out.println("!Set jumin(YYMMDD) for Id/Pw  ex - 930610");
		System.out.print("*  JUMIN   :");
		String jumin = sc.nextLine();
		while(true) {
			if(!jumin.matches("^[0-9]{6}$")) {
				System.out.println("!Set jumin(YYMMDD) for Id/Pw  ex - 930610");
				System.out.print("*  JUMIN   :");
				jumin = sc.nextLine();
			}else break;
		}
		m.setJumin(jumin);
		System.out.print("*  N a m e :");
		m.setName(sc.nextLine());

		list.add(m);

	}

	// 아이디로 찾기
	public void findMyId(Scanner sc) {

		System.out.println("==== Find Id ====");
		System.out.print(" Name? : ");
		String name = sc.nextLine();
		System.out.print(" Jumin ? : ");
		String jumin = sc.nextLine();
		for (UserDTO uDTO : list) {
			if (uDTO.getName().equals(name) && uDTO.getJumin().equals(jumin)) {
				System.out.println("========================");
				System.out.println(" I D  : " + uDTO.getUserId());
				System.out.println(" P W  : " + uDTO.getPasssword());
				return;
			}

		}
		System.out.println("Id NOT exist !!!");

	}

	// 아이디 및 패스워드로 찾기
	public void findMyPw(Scanner sc) {

		System.out.println("==== Find Pw ====");
		System.out.print(" I D ? : ");
		String userID = sc.nextLine();
		System.out.print(" Jumin ? : ");
		String jumin = sc.nextLine();
		for (UserDTO uDTO : list) {
			if (uDTO.getJumin().equals(jumin) && uDTO.getUserId().equals(userID)) {
				System.out.println("===========================");
				System.out.println("  I D  : " + uDTO.getUserId());
				System.out.println("  P W  : " + uDTO.getPasssword());
				return;
			}

		}
		System.out.println("====================");
		System.out.println(" Wrong Infomation !!");
		System.out.println("====================");

	}

	public void updateProfile(UserDTO userDTO) {

		list.set(list.indexOf(userDTO), userDTO);

	}

	public void delete(UserDTO u) {
		list.remove(u);
	}

}
