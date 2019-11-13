package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CommentController;
import controller.MovieController;
import dto.CommentDTO;
import dto.MovieDTO;
import dto.UserDTO;

public class CommentViewer {

	
	public void showCommentMenu(Scanner sc, MovieController mController, CommentController cController, UserDTO logInUser) {
		System.out.println("----------------");
		System.out.println("1.  Comment ");
		System.out.println("2.   Rank");
		System.out.println("3.   back");
		System.out.println("----------------");
		System.out.print(">>>> ");
		int choice = sc.nextInt();
		sc.nextLine();
		// 코멘트 관리 
		if(choice == 1) {
			// 영화번호 목록 출력 
			ArrayList<MovieDTO> list = mController.selectAll();
			
			System.out.println("---------------------------");
			System.out.printf(" no.\t Movie\n");
			for(MovieDTO m : list) {
				System.out.printf("%3d\t%s\t\n",m.getId(),m.getName());
			}
			System.out.println("---------------------------");;
			System.out.println(" Select Movie ID");
			System.out.print(">>> ");
			
			int movie = sc.nextInt();
			if(movie >= list.size()) { // 영화 목록에 없는 번호이면 메소드 종료
				System.out.println(" [ wrong number ]");
				return;
			}
			
			System.out.println("1. write 2. select 3. back");
			System.out.print(">>> ");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				writeComment(sc, cController, logInUser);
			}else if (choice == 2) {
			
//	수요일 : selectOne 메소드 만들기 부터 ~
			}
		}
		
	}

	public void writeComment(Scanner sc, CommentController cController, UserDTO logInUser) {
		
		
		
		
			
			CommentDTO c = new CommentDTO();
			System.out.println("== NEW COMMENT ==");
			System.out.print("[ Title ] : ");
			c.setTitle(sc.nextLine());
			System.out.print("[ Comment ] : ");
			c.setContents(sc.nextLine());
			System.out.print("[ Star Rate ] : ★ x ");
			c.setStars(sc.nextInt());
			sc.nextLine();
			cController.write(c,logInUser);
			System.out.println("  Add Comments!");
			System.out.println("=================");
			
			
		
		
		
	}
	// 코멘트 보는 메소드
	public void showOne(Scanner sc, CommentController cController, MovieController mController, UserDTO logInUser) {
		
		
		
		
		
	}
	
	
}
