package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import dto.CommentDTO;
import dto.MovieDTO;
import dto.UserDTO;

public class CommentController {

	ArrayList<CommentDTO> list;
	// 생성자

	public CommentController(MovieController mController, UserController uController) {
		// 기본 코멘트 3개
		list = new ArrayList<CommentDTO>();

		CommentDTO c1 = new CommentDTO();
		c1.setId(0);
		c1.setWriterId(0);
		c1.setMovieId(6);
		c1.setPrintId(0);
		c1.setTitle("올해 최고의 영화 !!");
		c1.setContents("정말 재밌는 영화입니다. 꼭 관람하세요 !");
		c1.setStars(5); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c1.setWriterName(uController.selectAll().get(c1.getWriterId()).getName());
		c1.setWrittenTime(Calendar.getInstance());
		c1.setUpdatedTime(Calendar.getInstance());

		CommentDTO c2 = new CommentDTO();
		c2.setId(1);
		c2.setWriterId(1);
		c2.setMovieId(0);
		c1.setPrintId(7);
		c2.setTitle("별로에요");
		c2.setContents("보다가 잠들 정도로 재미없는 영화. ");
		c2.setStars(2); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c2.setWriterName(uController.selectAll().get(c2.getWriterId()).getName());
		c2.setWrittenTime(Calendar.getInstance());
		c2.setUpdatedTime(Calendar.getInstance());

		CommentDTO c3 = new CommentDTO();
		c3.setId(2);
		c3.setWriterId(0);
		c3.setMovieId(2);
		c1.setPrintId(2);
		c3.setTitle("재밌었어요 ");
		c3.setContents("그럭저럭 재밌었습니다. \n 한번 쯤 봐도 괜찮을 영화");
		c3.setStars(3); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c3.setWriterName(uController.selectAll().get(c3.getWriterId()).getName());
		c3.setWrittenTime(Calendar.getInstance());
		c3.setUpdatedTime(Calendar.getInstance());

		CommentDTO c4 = new CommentDTO();
		c4.setId(3);
		c4.setWriterId(0);
		c4.setMovieId(0);
		c1.setPrintId(2);
		c4.setTitle("잘 만들었네요");
		c4.setContents("CG가 쩌네요. 또 보라고 하면 볼 수 \n 있을거같아요. 굿!!");
		c4.setStars(4); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c4.setWriterName(uController.selectAll().get(c4.getWriterId()).getName());
		c4.setWrittenTime(Calendar.getInstance());
		c4.setUpdatedTime(Calendar.getInstance());

		CommentDTO c5 = new CommentDTO();
		c5.setId(4);
		c5.setWriterId(2);
		c5.setMovieId(3);
		c1.setPrintId(2);
		c5.setTitle("좋았어요.");
		c5.setContents("가족이랑 봤는데 재밌었습니다. \n 감동적이네요 ~ ");
		c5.setStars(4); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c5.setWriterName(uController.selectAll().get(c5.getWriterId()).getName());
		c5.setWrittenTime(Calendar.getInstance());
		c5.setUpdatedTime(Calendar.getInstance());
	
		
		
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);

	}

	public ArrayList<CommentDTO> selectAll() {
		return list;
	}

	// 새로운 코멘트 추가하기
	public void write(CommentDTO c, UserDTO logInUser, MovieDTO m) {

		int id = list.get(list.size() - 1).getId() + 1;
		c.setId(id);
		id = logInUser.getId();
		c.setWriterId(logInUser.getId());
		//c.setPrintId(0);
		c.setWriterName(logInUser.getName());
		c.setWrittenTime(Calendar.getInstance());
		c.setUpdatedTime(Calendar.getInstance());
		c.setMovieId(m.getId());
		
		list.add(c);

	}

	// 코멘트 하나보기
	public CommentDTO selectOne(int id, ArrayList<CommentDTO> commentMovieList) {

		for (CommentDTO c : commentMovieList) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	public CommentDTO selectOneByPrintId(int id, ArrayList<CommentDTO> commentMovieList) {
		
		for (CommentDTO c : commentMovieList) {
			if (c.getPrintId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public CommentDTO selectByMyComment(int id, ArrayList<CommentDTO>  myComment) {
		
		for(CommentDTO c : myComment) {
			if(c.getPrintId() == id) {
				return c;
			}
		}return null;
		
	}

	// 영화에 따른 코멘트 보기
	public ArrayList<CommentDTO> selectCommentByMovie(MovieDTO selectedMovie,UserController uController) {

		ArrayList<CommentDTO> commentByMovie = new ArrayList<CommentDTO>() ;
		CommentDTO temp = new CommentDTO();
		ArrayList<UserDTO> writers = uController.selectAll();
		
		int i = 0;
		String userName ="";
		for (int j =0 ; j< list.size(); j++) {
			// 코멘트의 번호 다시정하기
			if (selectedMovie.getId() == list.get(j).getMovieId()) {
				temp = list.get(j);
				for(UserDTO u : writers) {
					if(u.getId() == temp.getWriterId()) {
						userName = u.getName();
					}
				}
				temp.setPrintId(i);
				temp.setId(j);
				System.out.println("[" + temp.getPrintId() + "] \""
						+ temp.getTitle() + "\" by " + userName);
				//commentByMovie.add(c);
				
				commentByMovie.add(temp);
				i++;
			}
		}

		// return commentListByMovie;
		return commentByMovie;

	}

	public void update(CommentDTO cDTO) {

		cDTO.setUpdatedTime(Calendar.getInstance());
		for (int i = 0; i < list.size(); i++) {
			if (cDTO.getId() == list.get(i).getId()) {
				list.set(list.indexOf(cDTO), cDTO);
			}
		}

	}

	public void delete(CommentDTO cDTO) {
		
		list.remove(cDTO);

	}
	
	//내가쓴 코멘트 모아보기 

	public ArrayList<CommentDTO> selectMyComments(CommentController cController, UserDTO logInUser
			, MovieController mController) {

		ArrayList<CommentDTO> myComments = new ArrayList<CommentDTO>();
		MovieDTO m;
		int i=0;
		for (CommentDTO c : cController.selectAll()) {
			if (logInUser.getId() ==  c.getWriterId()) {
				m = mController.selectOne(c.getMovieId());
				myComments.add(c);
				myComments.get(i).setPrintId(i);
				i++;
			}
		}
		
		
		return myComments;
	}
	
	public void deleteAccountComments(UserDTO logInUser) {
		for(int i = 0; i<list.size();i++) {
			if(logInUser.getId()==list.get(i).getWriterId()) {
				list.remove(i);
			}
		}
	}
	
	
	
	
}
