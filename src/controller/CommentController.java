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
		c1.setMovieId(0);
		c1.setTitle("Best Movie ever!");
		c1.setContents("first comment for test.");
		c1.setStars(5); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c1.setWriterName(uController.selectAll().get(c1.getId()).getName());
		c1.setWrittenTime(Calendar.getInstance());
		c1.setUpdatedTime(Calendar.getInstance());

		CommentDTO c2 = new CommentDTO();
		c2.setId(1);
		c2.setWriterId(1);
		c2.setMovieId(0);
		c2.setTitle("TEST TITLE 2");
		c2.setContents("second comment for test.");
		c2.setStars(3); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c2.setWriterName(uController.selectAll().get(c2.getId()).getName());
		c2.setWrittenTime(Calendar.getInstance());
		c2.setUpdatedTime(Calendar.getInstance());

		CommentDTO c3 = new CommentDTO();
		c3.setId(2);
		c3.setWriterId(0);
		c3.setMovieId(2);
		c3.setTitle("TEST TITLE 3");
		c3.setContents("third comment for test.");
		c3.setStars(1); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c3.setWriterName(uController.selectAll().get(c3.getId()).getName());
		c3.setWrittenTime(Calendar.getInstance());
		c3.setUpdatedTime(Calendar.getInstance());

		list.add(c1);
		list.add(c2);
		list.add(c3);

	}

	public ArrayList<CommentDTO> selectAll() {
		return list;
	}

	// 새로운 코멘트 추가하기
	public void write(CommentDTO c, UserDTO logInUser,MovieDTO m) {
		
		int id = list.get(list.size()-1).getId()+1;
		
		c.setId(id);
		c.setWriterId(logInUser.getId());
		c.setWriterName(logInUser.getName());
		c.setWrittenTime(Calendar.getInstance());
		c.setUpdatedTime(Calendar.getInstance());
		c.setMovieId(m.getId());
		list.add(c);

	}
	//코멘트 하나보기
	public CommentDTO selectOne(int id, ArrayList<CommentDTO> commentMovieList) {
		
		for (CommentDTO c : commentMovieList) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	//영화에 따른 코멘트 보기 
	public ArrayList<CommentDTO> selectCommentByMovie(MovieDTO selectedMovie){
		
		ArrayList<CommentDTO> commentMovieList = new ArrayList<CommentDTO>();
		int i = 0;
		for(CommentDTO c : list) {
			//코멘트의 번호 다시정하기
			if(selectedMovie.getId() == c.getMovieId()) {
				c.setId(i);
				commentMovieList.add(c);
				System.out.println("["+commentMovieList.get(i).getId()+"] \""+ 
						commentMovieList.get(i).getTitle()+"\" by "+ commentMovieList.get(i).getWriterName());
				i++;
			}}
	
			
			
			//return commentListByMovie;
		return commentMovieList;
		
		
		
	}
	public void update(CommentDTO cDTO) {
		
		cDTO.setUpdatedTime(Calendar.getInstance());
		for(int i =0; i < list.size(); i++ ) {
			if(cDTO.getId() == list.get(i).getId()) {
				list.set(list.indexOf(cDTO),cDTO);
			}
		}
		
		
	}
	public void delete(CommentDTO cDTO) {
		list.remove(cDTO);
		
	}


}
