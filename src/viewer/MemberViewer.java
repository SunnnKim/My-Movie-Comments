package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.UserController;
import dto.UserDTO;

public class MemberViewer { // = userViewer
	// 로그인 + 회원정보와 관련된 메소드들

	// 로그인 하기
	public UserDTO logIn(Scanner sc, UserController uController) {
		// 아이디 + 패스워드 맞으면
		// list에 들어있는 UserDTO 반환
		// 없으면 Null 반환
		System.out.println("+++ LOG IN +++");
		System.out.print("*  I    D  : ");
		String inputId = sc.nextLine();
		System.out.print("* PASSWORD : ");
		String inputPw = sc.nextLine();
		return uController.selectOne(inputId, inputPw);
	}

	public void joinUser(Scanner sc, UserController uController) {
		// 모든 유저 담아오는 어레이리스트
		ArrayList<UserDTO> uList = uController.selectAll();
		UserDTO newUser = new UserDTO();
		int id = uList.get(uList.size() - 1).getId() + 1;
		newUser.setId(id); // id - auto increment
		System.out.println("++++ JOIN ++++");
		System.out.print("*   I  D   :");
		// 아이디 중복확인 및 정규표현식
		String userId = sc.nextLine();
		outer: while (true) {
			int i = 0, j = 0;
			inner: for (UserDTO u : uList) {
				if (userId.equals(u.getUserId())) {
					i++;
					j++;
					break inner;
				}
			}
			if (!userId.matches("^[a-z]{1}[a-zA-Z0-9]{3,9}$"))
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
		newUser.setUserId(userId);
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
		newUser.setPasssword(password);
		System.out.println("!Set jumin(YYMMDD) for Id/Pw  ex - 930610");
		System.out.print("*  JUMIN   :");
		String jumin = sc.nextLine();
		while (true) {
			if (!jumin.matches("^[0-9]{6}$")) {
				System.out.println("!Set jumin(YYMMDD) for Id/Pw  ex - 930610");
				System.out.print("*  JUMIN   :");
				jumin = sc.nextLine();
			} else
				break;
		}
		newUser.setJumin(jumin);
		System.out.print("*  N a m e :");
		newUser.setName(sc.nextLine());
		uController.join(newUser);
	}

	public boolean loginFail(UserDTO logInUser, Scanner sc, UserController uController) {
		while (logInUser == null) {
			System.out.println("Wrong ID or Password !!!");
			System.out.println("-------------------------");
			System.out.println("1. Main");
			System.out.println("2. lost Id/Pw");
			System.out.print(">>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			if (choice == 1) {
				return false;
			} else if (choice == 2) {
				// 아이디 비번찾기 화면으로
				findIdPw(uController, sc, uController.selectAll());
				return false;
			}
		}return true;
	}

	// 로그인 실패할 경우
	public void findIdPw(UserController uController, Scanner sc, ArrayList<UserDTO> uList) {
		System.out.println("-------------------");
		System.out.println("   Find ID / PW");
		System.out.println("-------------------");
		System.out.println("1.Id  2.PW  3.Main");
		System.out.print(">>>>> ");
		int choice = sc.nextInt();
		sc.nextLine();
		if (choice == 1) {
			findMyId(sc, uList);
		} else if (choice == 2) {
			findMyPw(sc, uList);
		}
	}

	// 아이디 및 패스워드로 찾기
	public void findMyPw(Scanner sc, ArrayList<UserDTO> uList) {
		System.out.println("==== Find Pw ====");
		System.out.print(" I D ? : ");
		String userID = sc.nextLine();
		System.out.print(" Jumin ? : ");
		String jumin = sc.nextLine();
		for (UserDTO uDTO : uList) {
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

	public int showLoginMenu(Scanner sc, int choice) {
		System.out.println("===========================");
		System.out.println(" 1. LogIn 2. Join 3. Exit");
		System.out.print(">>>>>>  ");
		try {
			choice = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			choice = -1;
		}
		return choice;
	}

	// 로그인 후 메인화면 출력 메세지
	public int showMainMenu(Scanner sc, int choice) {
		System.out.println("-------------------------------");
		System.out.println("1. Movie & Comment");
		System.out.println("2. My Profile");
		System.out.println("3. Log Out");
		System.out.print(">>> ");
		try {
			choice = sc.nextInt();
		} catch (Exception e) {
			choice = -1;
		}
		return choice;
	}

	// 회원정보 목록 출력 메세지
	public int showProfileMenu(Scanner sc, int choice, UserDTO logInUser) {
		System.out.println(" \"" + logInUser.getName() + "\"'s Profile");
		System.out.println("  I D   : " + logInUser.getUserId());
		System.out.println("  P W   : " + logInUser.getPasssword());
		System.out.println("  Name  : " + logInUser.getName());
		System.out.println("  Jumin : " + logInUser.getJumin());
		System.out.println("-------------------------------");
		System.out.println("1. Change Info ");
		System.out.println("2. Delete My Account");
		System.out.println("3. Back");
		System.out.print(">>>> ");
		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			choice = -1;
		}
		return choice;
	}

	public UserDTO update(UserController uController, UserDTO logInUser, Scanner sc) {
		System.out.println("*** UPDATE MY PROFILE ***");
		System.out.print(" Name : ");
		logInUser.setName(sc.nextLine());
		System.out.print(" Password : ");
		String password = sc.nextLine();
		while (true) {
			if (!password.matches("^[a-z0-9]*{4,15}$")) {
				System.out.println("! PW must contain letter & number (length : 4~15) !");
				System.out.print("* PASSWORD :");
				password = sc.nextLine();
			} else
				break;
		}
		logInUser.setPasssword(password);
		System.out.println("");
		uController.updateProfile(logInUser);
		System.out.println("* Profile Updated *");
		return logInUser;
	}

	public void exit() {
		System.out.println("======================================");
		System.out.println(" Thank you for using My Movie Comment");
		System.out.println("======================================");
		return;
	}

	public boolean delete(UserDTO logInUser, UserController uController, Scanner sc, CommentController cController) {
		System.out.println("* Delete My Account *");
		System.out.print(" delete account ? ( Y/N ) : ");
		String answer = sc.nextLine();
		if (answer.equalsIgnoreCase("y")) {
			System.out.print("password ? :");
			answer = sc.nextLine();
			if (answer.equals(logInUser.getPasssword())) {
				uController.delete(logInUser); // 회원계정삭제
				cController.deleteAccountComments(logInUser);
				return true;
			} else
				System.out.println(" [ Wrong Password ]\n * cancel request *");
		} else {
			System.out.println(" * cancel request *");
		}
		// 계정이 삭제되면 true,삭제에 실패하면 false 반환
		return false;
	}

	// 아이디로 찾기
	public void findMyId(Scanner sc, ArrayList<UserDTO> uList) {
		System.out.println("==== Find Id ====");
		System.out.print(" Name? : ");
		String name = sc.nextLine();
		System.out.print(" Jumin ? : ");
		String jumin = sc.nextLine();
		for (UserDTO uDTO : uList) {
			if (uDTO.getName().equals(name) && uDTO.getJumin().equals(jumin)) {
				System.out.println("========================");
				System.out.println(" I D  : " + uDTO.getUserId());
				System.out.println(" P W  : " + uDTO.getPasssword());
				return;
			}
		}
		System.out.println("Id NOT exist !!!");
	}

	public boolean showLogInUser(Scanner sc, UserDTO logInUser, MemberViewer mViewer, UserController uController,
			int choice, CommentController cController) {
		boolean logOut = false;
		System.out.print("password ? : ");
		sc.nextLine();
		String password = sc.nextLine();
		if (password.equals(logInUser.getPasssword())) {
			choice = mViewer.showProfileMenu(sc, choice, logInUser);
			switch (choice) {
			case 1:
				logInUser = mViewer.update(uController, logInUser, sc);
				break;
			case 2:
				// 계정 삭제
				logOut = mViewer.delete(logInUser, uController, sc, cController);
				break;
			case 3:
				// back to main
				break;
			default:
				System.out.println("! Wrong Access !");
				break;
			}
		} else {
			System.out.println("! wrong password !");
		}
		return logOut;
	}
}
