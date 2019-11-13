package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.MovieController;
import dto.CommentDTO;
import dto.MovieDTO;
import dto.UserDTO;

public class CommentViewer {

	public int showCommentMenu(Scanner sc) {
		System.out.println("-------------------");
		System.out.println("1. Movie & Comment ");
		System.out.println("2.  My Comments");
		System.out.println("3.    back");
		System.out.println("-------------------");
		System.out.print(">>> ");
		int choice;
		choice = Integer.parseInt(sc.nextLine());
		return choice;
	}

	public MovieDTO selectMovieForComment(Scanner sc, MovieController mController) {

		ArrayList<MovieDTO> list = mController.selectAll();

		// 영화 리스트 보여주기
		mController.printMovies();
		System.out.print(" Movie Number? : ");
		// 영화 번호 고르기
		int choice = Integer.parseInt(sc.nextLine());

		// 선택한 영화 정보보여주기

//		showMovieComments(sc);
		MovieDTO selectedMovie = mController.selectOne(choice);
		if (selectedMovie == null) {
			System.out.println(" [ wrong number ]");
			return null;
		}
		return selectedMovie;
	}

	public void commentFunctionMenu(Scanner sc, MovieController mController, MovieDTO selectedMovie,
			CommentController cController, UserDTO logInUser) {

		// 선택한 영화의 정보를 띄워준다.
		mController.showMovieInfo(selectedMovie);

		while (true) {
			System.out.println("1. write comment");
			System.out.println("2. show comments");
			System.out.println("3. back");
			System.out.print(">>> ");
			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 1) {
				// write 기능
				writeComment(sc, cController, logInUser, selectedMovie);
			} else if (choice == 2) {
				
				while(true) {
				// 코멘트보기 기능
				// showCommentsByMovie(sc, cController, selectedMovie);
				System.out.println(" === Comments ===");
				// 선택된 영화에 해당하는 코멘트만 담아둔 ArrayList<CommentDTO>
				ArrayList<CommentDTO> movieCommentList = cController.selectCommentByMovie(selectedMovie);
				System.out.println("-----------------");

				System.out.println("1. Show detail ");
				System.out.println("2. back");
				System.out.print(">>> ");
				choice = sc.nextInt();
				System.out.println("-----------------");
				if (choice == 1) {
					// 수정/삭제/보기 있는 코멘트 디테일보기
					System.out.print("comment number ? :");
					choice = sc.nextInt();
					sc.nextLine();
					commentFunctionMenu2(sc, cController, movieCommentList,logInUser,choice);
					
				} else {
					// back
					break;
				}
				}
				// back
			} else if (choice == 3) {
				return;
			} else {
				System.out.println("! Wrong Access !");
			}

		} // while

	}

	private void commentFunctionMenu2(Scanner sc, CommentController cController,
			ArrayList<CommentDTO> movieCommentList,UserDTO logInUser, int choice) {

		
		CommentDTO selectedComment;
		while(true) {
		System.out.println("-------------------------------");
		// 코멘트 선택하기
		selectedComment = cController.selectOne(choice, movieCommentList);
		// 코멘트가 없을 때
		if (selectedComment == null) {
			System.out.println("! Wrong Access !");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
		System.out.println("-------------------------------");
		System.out.println(" \" " + selectedComment.getTitle() + " \"");
		System.out.println("[ " + selectedComment.getContents() + " ]");
		System.out.println(" * Star rating : " + selectedComment.getStars());
		System.out.println("by " + selectedComment.getWriterName());
		System.out.println("-------------------------------");
		System.out.println("Wirtten  Time : " + sdf.format(selectedComment.getWrittenTime().getTime()));
		System.out.println("Recent Update : " + sdf.format(selectedComment.getUpdatedTime().getTime()));
		System.out.println("-------------------------------");
		if(logInUser.getName().equals(selectedComment.getWriterName())) {
			System.out.println("1.update 2. delete 3. back");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				//업데이트 
				updateComment(sc, selectedComment,cController);
				
			}
			else if(choice == 2) {
				// 코멘트 지우기
				deleteComment(sc,selectedComment,cController);
				break;
			}
		}
		//내가 쓴 코멘트가 아닐 때 
		else {
			System.out.println("1. back");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				
			}else {
				System.out.println("! Wrong Access !");
				return;
			}
		}}

	}

	private void deleteComment(Scanner sc, CommentDTO selectedComment, CommentController cController) {
		System.out.println("* Deleted It ! *");
		cController.delete(selectedComment);
		
		
	}

	private void updateComment(Scanner sc, CommentDTO selectedComment, CommentController cController) {
		System.out.print("[ Title ] : ");
		selectedComment.setTitle(sc.nextLine());
		System.out.print("[ Contents ] : ");
		selectedComment.setContents(sc.nextLine());
		System.out.print("[ Stars? ] ★  x  ? : ");
		selectedComment.setStars(sc.nextInt());
		cController.update(selectedComment);
		System.out.println("* updated ! *");
		
		
	}

	public void writeComment(Scanner sc, CommentController cController, UserDTO logInUser, MovieDTO selectedMovie) {

		CommentDTO c = new CommentDTO();
		System.out.println("== NEW COMMENT ==");
		System.out.print("[ Title ] : ");
		c.setTitle(sc.nextLine());
		System.out.print("[ Comment ] : ");
		c.setContents(sc.nextLine());
		System.out.print("[ Stars? ] ★  x  ? : ");
		c.setStars(sc.nextInt());
		sc.nextLine();
		cController.write(c, logInUser, selectedMovie);
		System.out.println("-------------------");
		System.out.println("  Add Comments!");

	}

}
