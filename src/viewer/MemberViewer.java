package viewer;

import java.util.Scanner;

import controller.UserController;
import dto.UserDTO;

public class MemberViewer { // = userViewer 

	// 로그인과 관련된 메소드들

	// 로그인 하기
	public UserDTO logIn(Scanner sc, UserController uController) {
		// 아이디 + 패스워드 맞으면
		// list에 들어있는 UserDTO 반환
		// 없으면 Null 반환
		System.out.println("+++LOG IN+++");
		System.out.print("*  I    D  : ");
		String inputId = sc.nextLine();
		System.out.print("* PASSWORD : ");
		String inputPw = sc.nextLine();

		return uController.selectOne(inputId, inputPw);

	}

	public boolean loginFail(UserDTO logInUser, Scanner sc, UserController uController) {

		while(logInUser == null) {
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
				findIdPw(uController, sc);
				return false;
			}}
			return true;
			
		}
		
//		// 로그인 실패할 경우
		
		// 로그인 성공
	

	public void findIdPw(UserController uController, Scanner sc) {

		System.out.println("-------------------");
		System.out.println("   Find ID / PW");
		System.out.println("-------------------");
		System.out.println("1.Id  2.PW  3.Main");
		System.out.print(">>>>> ");
		int choice = sc.nextInt();
		sc.nextLine();
		if (choice == 1) {
			uController.findMyId(sc);
		} else if (choice == 2) {
			uController.findMyPw(sc);

		}

	}
	public int showLoginMenu(Scanner sc, int choice) {
		
		System.out.println("===========================");
		System.out.println(" 1. LogIn 2. Join 3. Exit");
		System.out.print(">>>>>>  ");
		try {
			choice = Integer.parseInt(sc.nextLine());			
		} catch (Exception e) {
			choice = -1;
		}
		return choice;
		
	}
	
	// 로그인 후 메인화면 출력 메세지 
	public int showMainMenu(Scanner sc,int choice) {
		System.out.println("-----------------------------------");
		System.out.println("1.Movie List  2.My profile 3.Log Out");
		System.out.print(">>>>>>");
		choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}
	// 회원정보 목록 출력 메세지 
	public int showProfileMenu(Scanner sc, int choice){
		System.out.println("-----------------------------------");
		System.out.println("1. Change Info ");
		System.out.println("2. Delete My Account");
		System.out.println("3. Back");
		System.out.print(">>>> ");
		choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}
	public void update(UserController uController, UserDTO logInUser, Scanner sc) {
		
		System.out.println("*** UPDATE MY PROFILE ***");
		System.out.println("  N a m e : ");
		logInUser.setName(sc.nextLine());
		System.out.println(" Password : ");
		logInUser.setPasssword(sc.nextLine());
		uController.updateProfile(logInUser);
		System.out.println("* Profile Updated *");
	}
	
	
	public void exit() {
		System.out.println("====================================");
		System.out.println(" Thank you for using MyMovieComment");
		System.out.println("====================================");
		return;
		
	}

	public boolean delete(UserDTO logInUser, UserController uController, Scanner sc) {
		System.out.println("* Delete My Account *");
		System.out.print(" delete account ? ( Y/N ) : ");
		String answer = sc.nextLine();
		if(answer.equalsIgnoreCase("y")) {
			System.out.print("password ? :");
			answer = sc.nextLine();
			if(answer.equals(logInUser.getPasssword())) {
				uController.delete(logInUser);
				return true;
			}
			else System.out.println(" [ Wrong Password ]\n * cancel request *");
			
		} else {
			System.out.println(" * cancel request *");
		}
		// 계정이 삭제되면 true,삭제에 실패하면 false 반환 
		return false;
		
		
	}
	
	
	
	
	
	
	
	
}
