package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.MovieController;
import controller.UserController;
import dto.CommentDTO;
import dto.MovieDTO;
import dto.UserDTO;

public class ViewerMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserController uController = new UserController();
		MovieController mController = new MovieController();
		MemberViewer mViewer = new MemberViewer();
		CommentController cController = new CommentController(mController, uController);
		CommentViewer cViewer = new CommentViewer();

		int choice = 0;
		UserDTO logInUser;

		System.out.println("===========================");
		System.out.println("==== My MOVIE COMMENTS ====");
		program: while (true) {

			choice = mViewer.showLoginMenu(sc, choice);
			login: if (choice == 1) {
				// 로그인 + 로그인 후 기능
				logInUser = mViewer.logIn(sc, uController);
				if (!mViewer.loginFail(logInUser, sc, uController))
					break login;

				// 로그인 성공
				System.out.println("===============================");
				System.out.println(" \" " + logInUser.getName() + " \" just logged in!");

				while (true) {
					// 로그인 후 메인 화면
					// 1.Movie list 2.My profile 3.Log Out
					choice = mViewer.showMainMenu(sc, choice);

					// 1.Movie list : 영화목록 + 코멘트작성
					if (choice == 1) {
						System.out.println(" Movies & Comments");
						// 1. Movies&Comment 2. My Comments 3. back
						choice = cViewer.showCommentMenu(sc);
						if (choice == 1) { // Movies&Comment
							// 영화목록에서 영화 고르기 = selectedMovie
							MovieDTO selectedMovie = cViewer.selectMovieForComment(sc, mController);

							// 1. write comment 2. show comments 3. back
							cViewer.commentFunctionMenu(sc, mController, selectedMovie, cController, logInUser,uController);
							// 위의 코드는 3번을 누르면 종료된다.

						} else if (choice == 2) {
							// 2. My Comments

							// 내가 쓴 코멘트 보기 !!!
							cViewer.showMyComments(sc, cController, logInUser, mController);

						} else if (choice == 3) {
							// 뒤로가기
						} else {
							System.out.println("! Wrong Access !");
						}
						// 영화 선택하기

						// cViewer.showCommentMenu(sc, mController, cController, logInUser);
						// cViewer.writeComment(sc, cController, logInUser);

					}

					else if (choice == 2) {
						// 회원정보 보기

						// "1. Change Info 2. My Comments 3. Delete Account 4. back"
						System.out.print("password ? : ");
						String password = sc.nextLine();
						if(password.equals(logInUser.getPasssword())) {
						choice = mViewer.showProfileMenu(sc, choice, logInUser);
						switch (choice) {
						case 1:
							logInUser = mViewer.update(uController, logInUser, sc);
							break;
						case 2:
							// 계정 삭제
							boolean b = mViewer.delete(logInUser, uController, sc);
							if (b)
								break login;
							break;
						case 3:
							// back to main
							break;
						default:
							System.out.println("! Wrong Access !");
							break;

						}}
						else {
							System.out.println("! wrong password !");
						}
					} else if (choice == 3) {
						// 로그아웃
						System.out.println("=== logged out ===");
						break login;
					} else {
						// 123 이외의 숫자 - 잘못된 접근
						System.out.println("! Wrong Access !");
					}
				}

//========================================================================================================================
				// 1.login 2. join 3. exit
			} else if (choice == 2) {

				// 회원가입
				uController.joinUser(sc);
			} else if (choice == 3) {
				// 프로그램 종료
				mViewer.exit();
				break;

			} else { // 잘못된 접근
				System.out.println("! Wrong Access !");

			}

		}
		sc.close();
	}
}
