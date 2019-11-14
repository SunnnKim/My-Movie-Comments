package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.MovieController;
import controller.UserController;
import dto.CommentDTO;
import dto.MovieDTO;
import dto.UserDTO;

public class CommentViewer {

	public int showCommentMenu(Scanner sc) {
		System.out.println("-------------------------------");
		System.out.println("1. Show movies & comments ");
		System.out.println("2. My Comments");
		System.out.println("3. back");
		System.out.print(">>> ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// 영화 선택하고 해당 코멘트 보는 메소드
	public MovieDTO selectMovieForComment(Scanner sc, MovieController mController) {

		// 영화 리스트 보여주기
		mController.printMovies();
		System.out.print(" Movie Number? : ");
		// 영화 번호 고르기
		int choice = sc.nextInt();
		sc.nextLine();


		// 선택한 영화 정보보여주기

//		showMovieComments(sc);
		MovieDTO selectedMovie = mController.selectOne(choice);
		if (selectedMovie == null) {
			System.out.println(" [ wrong number ]");
			return null;
		}
		return selectedMovie;
	}

// 선택한 영화정보 띄워주고 코멘트 관련 기능 출력하는 메소드  
	public void commentFunctionMenu(Scanner sc, MovieController mController, MovieDTO selectedMovie,
			CommentController cController, UserDTO logInUser, UserController uController) {

		// 선택한 영화의 정보를 띄워준다.

		movieOne: while (true) {
			mController.showMovieInfo(selectedMovie);
			System.out.println("1. write comment");
			System.out.println("2. show comments");
			System.out.println("3. back");
			System.out.print(">>> ");
			int choice = sc.nextInt();
			sc.nextLine();

			// 1. write 2.show comment 3.back
			if (choice == 1) {
				// write 기능
				writeComment(sc, cController, logInUser, selectedMovie);
			}
			// 1. write 2.show comment 3.back
			else if (choice == 2) {

				commentOne: while (true) {
					// 코멘트보기 기능
					// showCommentsByMovie(sc, cController, selectedMovie);
					System.out.println("  C O M M E N T S");
					// 선택된 영화에 해당하는 코멘트만 담아둔 ArrayList<CommentDTO>
					ArrayList<CommentDTO> movieCommentList = cController.selectCommentByMovie(selectedMovie,uController);
					System.out.println("-------------------------------");
					System.out.println("1. Show detail ");
					System.out.println("2. back");
					System.out.print(">>> ");
					choice = sc.nextInt();
					System.out.println("-------------------------------");
					if (choice == 1) {
						// 수정/삭제/보기 있는 코멘트 디테일보기
						System.out.print("comment number ? :");
						choice = sc.nextInt();
						sc.nextLine();
						commentFunctionMenu2(sc, cController, movieCommentList, logInUser, choice);
						break commentOne;
					}else {
						break commentOne;
						
					}
				}

				// 1. write 2.show comment 3.back
			} else if (choice == 3) { //
				break movieOne;
			} else {
				System.out.println("! Wrong Access !");

			}

		} // while 끝

	}

	private void commentFunctionMenu2(Scanner sc, CommentController cController, ArrayList<CommentDTO> movieCommentList,
			UserDTO logInUser, int choice) {

		CommentDTO selectedComment;
		outer: while (true) {
			System.out.println("-------------------------------");
			// 코멘트 선택하기
			selectedComment = cController.selectOne(choice, movieCommentList);
			// 코멘트가 없을 때
			if (selectedComment == null) {
				System.out.println("! Wrong Access !");
				return;
			}
			// 코멘트 개별 출력
			printCommentOne(selectedComment);
			if (logInUser.getId() == selectedComment.getId()) {
				System.out.println("1.update 2. delete 3. back");
				System.out.print(">>> ");
				choice = sc.nextInt();
				sc.nextLine();
				if (choice == 1) {
					// 업데이트
					updateComment(sc, selectedComment, cController);
					break outer;
				} else if (choice == 2) {
					// 코멘트 지우기
					deleteComment(sc, selectedComment, cController);
					break outer;
				}
			}
			// 내가 쓴 코멘트가 아닐 때
			else {
				System.out.println("1. back");
				System.out.println(">>> ");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					return;
				} else {
					System.out.println("! Wrong Access !");

				}
			}
		}

	}

	private void deleteComment(Scanner sc, CommentDTO selectedComment, CommentController cController) {
		System.out.println("[ Deleted It ! ]");
		cController.delete(selectedComment);

	}

	private void updateComment(Scanner sc, CommentDTO selectedComment, CommentController cController) {
		System.out.print("[ Title ] : ");
		selectedComment.setTitle(sc.nextLine());
		System.out.print("[ Contents ] : ");
		selectedComment.setContents(sc.nextLine());
		System.out.print("[ Stars? ] ★  x  ? : ");
		int starNum = sc.nextInt();
		while (starNum > 5 || starNum < 1) {
			System.out.println(" ! Number between 1~5 ! ");
			starNum = sc.nextInt();
		}
		selectedComment.setStars(starNum);
		cController.update(selectedComment);
		System.out.println("* updated ! *");
		System.out.println("-------------------------------");

	}

	public void writeComment(Scanner sc, CommentController cController, UserDTO logInUser, MovieDTO selectedMovie) {

		CommentDTO c = new CommentDTO();
		System.out.println("== NEW COMMENT ==");
		System.out.print("[ Title ] : ");
		c.setTitle(sc.nextLine());
		System.out.print("[ Comment ] : ");
		c.setContents(sc.nextLine());
		System.out.print("[ Stars? ] ★  x  ? : ");
		int starNum =sc.nextInt();
		while (starNum > 5 || starNum < 1) {
			System.out.println(" ! Number between 1~5 ! ");
			starNum = sc.nextInt();
		}
		c.setStars(starNum);
		sc.nextLine();
		cController.write(c, logInUser, selectedMovie);
		System.out.println("-------------------------------");
		System.out.println("  Add Comments!");

	}

	
	
	//My Comment 기능들 
	public void showMyComments(Scanner sc, CommentController cController, UserDTO logInUser,
			MovieController mController) {

		outer: while (true) {
			ArrayList<CommentDTO> myComments = cController.selectMyComments(cController, logInUser, mController);
			commentAll: while (true) {
				System.out.println(" MY COMMENTS");
				System.out.println("-------------------------------");
				// MyComments 모두 출력하기
				printMyComments(myComments, sc, mController);
				System.out.println("-------------------------------");
				System.out.println("1. show detail ");
				System.out.println("2. back");
				// System.out.println("2. write");
				System.out.print(">>> ");
				int choice = sc.nextInt();
				System.out.println("-------------------------------");
				if (choice == 1) { // 1.show detail
					System.out.print("Comment Number ? : ");
					choice = sc.nextInt();
					// 선택한 번호에 맞는 코멘트 가져오기
					CommentDTO selectedComment = cController.selectByMyComment(choice, myComments);
					// 해당 코멘트가 없으면
					if (selectedComment == null) {
						System.out.println("! Wrong Comment Number !");
						System.out.println("-------------------------------");
						continue;
					}
					commentOne: while (true) {

						printCommentOne(selectedComment);
						System.out.println("1. update");
						System.out.println("2. delete");
						System.out.println("3. back");
						System.out.print(">>> ");
						choice = sc.nextInt();
						sc.nextLine();
						if (choice == 1) {
							updateComment(sc, selectedComment, cController);
						} else if (choice == 2) {
							deleteComment(sc, selectedComment, cController);
							break commentAll;
						} else if (choice == 3) {
							break commentAll;
						} else {
							System.out.println("! Wrong Access !");
						}
					}

				}

				else if (choice == 2) {
					// 4. back
					 return;
				} else {
					System.out.println("! Wrong Access !");
				}
			}
		}

	}

	private void printMyComments(ArrayList<CommentDTO> myComments, Scanner sc, MovieController mController) {
		// TODO Auto-generated method stub
		MovieDTO m;
		if (myComments == null) {
			System.out.println("! 0 Comments !");
		}
		else {
			for (CommentDTO c : myComments) {
				m = mController.selectOne(c.getMovieId());
				System.out.println("[" + c.getPrintId() + "]" + m.getName() + " : \"" + c.getTitle()+"\"");
			}
		}

	}

//개별 코멘트 출력하는 메소드 
	public void printCommentOne(CommentDTO selectedComment) {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
		System.out.println(" \" " + selectedComment.getTitle() + " \"");
		System.out.println("-------------------------------");
		System.out.println("[ " + selectedComment.getContents() + " ]");
		System.out.println("[ Star rating : " + selectedComment.getStars() + " ]");
		System.out.println("-------------------------------");
		System.out.println("Wirtten  Time : " + sdf.format(selectedComment.getWrittenTime().getTime()));
		System.out.println("Recent Update : " + sdf.format(selectedComment.getUpdatedTime().getTime()));
		System.out.println("-------------------------------");

	}

}
