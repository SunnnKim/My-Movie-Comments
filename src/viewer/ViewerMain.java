package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.MovieController;
import controller.UserController;
import dto.CommentDTO;
import dto.UserDTO;

public class ViewerMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserController uController = new UserController();
		MovieController mController = new MovieController();
		MemberViewer mViewer = new MemberViewer();
		CommentController cController = new CommentController(mController);
		CommentViewer cViewer = new CommentViewer();
		
		int choice=0;
		UserDTO logInUser;

		System.out.println("===========================");
		System.out.println("=====My MOVIE COMMENTS=====");
		program: while (true) {

			choice = mViewer.showLoginMenu(sc, choice);
			login:
			if (choice == 1) {
				// 로그인 + 로그인 후 기능
				logInUser = mViewer.logIn(sc, uController);
				if (!mViewer.loginFail(logInUser, sc, uController)) break login;
				
				//로그인 성공 
				System.out.println("=====================================");
				System.out.println("* \"" + logInUser.getName() + "\" just logged in! *");
				
				
				while(true) {
				//로그인 후 메인 화면
				// 1.Movie list 2.My profile 3.Log Out
				choice = mViewer.showMainMenu(sc, choice);
				
				// 영화목록 + 코멘트 
				if(choice == 1) {
					//
					
					//내일 : 	아래 코드 구조좀 다시 짜보기 ~
					mController.printMovies();
					//1.Comment 2. Rank  3. back
					if(choice == 1) {
						cViewer.showCommentMenu(sc, mController, cController, logInUser);
					//	cViewer.writeComment(sc, cController, logInUser);
						
					}else if (choice == 2) {
						cViewer.showOne(sc, cController, mController, logInUser);
						
					}else if (choice == 3) {
						
					}else if (choice == 4) {
						
					}else {
						// 메인 메뉴로 
						System.out.println("! Wrong Access !");
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				}else if(choice == 2) {
					// 회원정보 보기 
					
					//"1. Change Info 2. My Comments 3. Delete Account 4. back"
					choice = mViewer.showProfileMenu(sc, choice);
					switch (choice) {
					case 1:
						mViewer.update(uController, logInUser, sc);
						break;
					case 2 :
					//	mViewer.showMyComments()
						break;
					case 3 :
						//계정 삭제 
						boolean b = mViewer.delete(logInUser,uController,sc);
						if(b) 	break login;
						break ;
					case 4 : 
						//back to main
						break;
					default :
						System.out.println("! Wrong Access !");
						break;
						
					}
				}else if(choice == 3) {
					//로그아웃  
					System.out.println("=== logged out ===");
					break login;
				}else {
					//123 이외의 숫자 - 잘못된 접근
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

//		
//		

//			
//			
//			
//				
//				 // 로그인 성공
					
//					
//					
//					movieSection :
//					if (choice == 1) {
//
						
//						if(choice == 1) {
//							//mController.selectOne(String movieName);
//							//만들 메소드임
//							
//						}else {
//							break movieSection;
//						}
//						
//						
//						
//						// Movies - comments
//
//					} else if (choice == 2) {
//						// 회원정보 수정
//						
//						if(choice == 1) {
//							// 정보 수정
//							
//						}else if(choice ==2) {
//							//내가작성한 코멘트 보기
//							
//						}else if( choice == 3) {
//							break movieSection;
//						}else {
//							System.out.println("Wrong Access !!!");
//							System.out.println(": Logged Out");
//							break;
//						}
//						
//						
//
//					}else if(choice == 3) {
//						//로그아웃

//					}
//					else {
//						System.out.println("Wrong Access !!!");
//						System.out.println(": Logged Out");
//						break;
//					}
//					}					
//				}}
//		
//			 
//			 
//	// -------------------------------------------------------------------------------------		 
//			 
//			 //System.out.println(" 1. LogIn 2. Join 3. Exit");
//			 //2번 회원가입하기 
//			 else if (choice == 2) {
//				 // join in

//
//			} //종료하기  
//			 else if (choice == 3) {
//				//vController.exit();
//				break;
//			} // 다른 숫자 누르면 메인으로   
//			 else {

//
//			}
//
//	}

		sc.close();
	}
}
