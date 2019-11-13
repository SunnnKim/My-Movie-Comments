package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import dto.CommentDTO;
import dto.UserDTO;

public class CommentController {

	ArrayList<CommentDTO> list;
	// 생성자

	public CommentController(MovieController mController) {
		// 기본 코멘트 3개
		list = new ArrayList<CommentDTO>();

		CommentDTO c1 = new CommentDTO();
		c1.setId(0);
		c1.setWriterId(0);
		c1.setMovie(mController, 0);
		c1.setTitle("Best Movie ever!");
		c1.setTitle("first comment for test.");
		c1.setStars(5); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c1.setWriterName(mController.selectAll().get(c1.getId()).getName());
		c1.setWrittenTime(Calendar.getInstance());
		c1.setUpdatedTime(Calendar.getInstance());

		CommentDTO c2 = new CommentDTO();
		c2.setId(1);
		c2.setWriterId(1);
		c2.setMovie(mController, 1);
		c2.setTitle("TEST TITLE 2");
		c2.setTitle("second comment for test.");
		c2.setStars(3); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c2.setWriterName(mController.selectAll().get(c2.getId()).getName());
		c2.setWrittenTime(Calendar.getInstance());
		c2.setUpdatedTime(Calendar.getInstance());

		CommentDTO c3 = new CommentDTO();
		c3.setId(2);
		c3.setWriterId(0);
		c3.setMovie(mController, 2);
		c3.setTitle("TEST TITLE 3");
		c3.setTitle("third comment for test.");
		c3.setStars(1); // 숫자 입력하면 자동으로 별찍어주는 메소드로 구현하기
		c3.setWriterName(mController.selectAll().get(c3.getId()).getName());
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
	public void write(CommentDTO c, UserDTO logInUser) {
		int id = list.get(list.size()-1).getId()+1;
		
		c.setId(id);
		c.setWriterId(logInUser.getId());
		c.setWriterName(logInUser.getName());
		c.setWrittenTime(Calendar.getInstance());
		c.setUpdatedTime(Calendar.getInstance());
		list.add(c);

	}

}
