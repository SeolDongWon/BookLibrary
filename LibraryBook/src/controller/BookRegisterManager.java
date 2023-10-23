package controller;

import java.util.Scanner;
import model.BookVO;

public class BookRegisterManager {
	public static Scanner scan = new Scanner(System.in);

	// 도서 목록
	public void bookList() {
		BookDAO bkDAO = new BookDAO();

		System.out.println("도서 전체 리스트");
		bkDAO.getBooklList();
		System.out.println();
	}

	// 도서 추가
	public void bookRegistr() {
		BookDAO bkDAO = new BookDAO();
		BookVO bkVO = new BookVO();

		System.out.println("도서 전체 리스트");
		bkDAO.getBooklList();

		String ISBN = null;
		String BookTitle = null;
		String BookAuthor = null;
		String BookRelease = null;
		String BookIntro = null;

		System.out.println();
		System.out.println("도서 정보 입력");
		System.out.print("ISBN : ");
		ISBN = scan.nextLine();
		System.out.print("도서명 : ");
		BookTitle = scan.nextLine();
		System.out.print("작가 : ");
		BookAuthor = scan.nextLine();
		System.out.print("출시일(YY/MM/DD) : ");
		BookRelease = scan.nextLine();
		System.out.print("책소개 : ");
		BookIntro = scan.nextLine();

		bkVO.setISBN(ISBN);
		bkVO.setBookTitle(BookTitle);
		bkVO.setBookAuthor(BookAuthor);
		bkVO.setBookRelease(BookRelease);
		bkVO.setBookIntro(BookIntro);

		bkDAO.setBookRegister(bkVO);

		System.out.println();
		System.out.println("과목 전체 리스트");
		bkDAO.getBooklList();
		System.out.println();
	}

	// 도서 수정
	public void bookUpdate() {
		BookDAO bkDAO = new BookDAO();
		BookVO bkVO = new BookVO();

		System.out.println("과목 전체 리스트(사용중인 과목 변경 불가)");
		bkDAO.getBooklList();
		
		int no = 0; // 수정할 과목 일련번호
		System.out.println();
		System.out.println("수정할 도서 일련번호 입력");
		System.out.print("일련번호 : ");
		no = scan.nextInt();
		scan.nextLine();

		String ISBN = null;
		String BookTitle = null;
		String BookAuthor = null;
		String BookRelease = null;
		String BookIntro = null;

		System.out.println();
		System.out.println("도서 정보 입력");
		System.out.print("ISBN : ");
		ISBN = scan.nextLine();
		System.out.print("도서명 : ");
		BookTitle = scan.nextLine();
		System.out.print("작가 : ");
		BookAuthor = scan.nextLine();
		System.out.print("출시일(YY/MM/DD) : ");
		BookRelease = scan.nextLine();
		System.out.print("책소개 : ");
		BookIntro = scan.nextLine();

		bkVO.setNo(no);
		bkVO.setISBN(ISBN);
		bkVO.setBookTitle(BookTitle);
		bkVO.setBookAuthor(BookAuthor);
		bkVO.setBookRelease(BookRelease);
		bkVO.setBookIntro(BookIntro);

		bkDAO.setbookUpdate(bkVO);

		System.out.println();
		System.out.println("과목 전체 리스트");
		bkDAO.getBooklList();
		System.out.println();
	}

	// 도서 삭제
	public void bookDelete() {
		BookDAO bkDAO = new BookDAO();
		int no = 0; // 삭제할 과목 번호

		System.out.println("도서 전체 리스트");
		bkDAO.getBooklList();

		System.out.println();
		System.out.println("삭제할 도서 일련번호 입력");
		System.out.print("일련번호 : ");
		no = scan.nextInt();
		scan.nextLine();

		bkDAO.setbookDelete(no);

		System.out.println();
		System.out.println("도서 전체 리스트");
		bkDAO.getBooklList();
		System.out.println();
	}
}