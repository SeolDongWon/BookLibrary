package controller;

import java.util.Scanner;
import model.BookVO;
import model.LibraryVO;

public class BookRegisterManager {
	public static Scanner scan = new Scanner(System.in);

	public void memberTotalList() {
		MemberDAO memDAO = new MemberDAO();
		String pw = null;
		System.out.printf("\n회원 정보 전체 목록\n관리자 비밀번호>>");
		pw = scan.nextLine();
		if (pw.equals("1234")) {
			memDAO.getMemberList();
		} else {
			System.out.println("관리자 비밀번호가 틀립니다.");
		}
	}

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

		System.out.println("도서 목록");
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
		System.out.println("도서 목록");
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

	// 도서관 보유 도서 목록
	public void libraryBookList() {
		LibraryDAO lbDAO = new LibraryDAO();
		lbDAO.libraryBookList();

	}

	// 도서 검색
	public void bookSearch() {
		LibraryDAO lbDAO = new LibraryDAO();
		String bookTitle = null;

		System.out.println("검색할 도서 제목 입력");
		bookTitle = scan.nextLine();

		lbDAO.searchBook(bookTitle);
	}

	// 소장 도서 추가
	public void collectionAdd() {
		BookDAO bkDAO = new BookDAO();
		LibraryDAO lbDAO = new LibraryDAO();
		LibraryVO lbVO = null;

		String isbn = null;
		String serial = null;
		String callNum = null;
		String bookLocation = null;
		String countNum = null;

		bkDAO.getBooklList();

		System.out.println();
		System.out.println("도서 정보 입력");
		System.out.print("ISBN : ");
		isbn = scan.nextLine().trim();
//		lbVO.setIsbn(scan.nextLine().trim());
		countNum = bkDAO.collectionCount(isbn);
		serial = isbn + countNum;

		// 이미 등록한 도서인지 확인
		if ((lbVO = lbDAO.getBookOverlap(isbn, countNum)) != null) {
			lbVO.setSerial(serial);
		} else {
			System.out.print("청구기호 : ");
			callNum = scan.nextLine();
			System.out.print("도서위치 : ");
			bookLocation = scan.nextLine();

			lbVO = new LibraryVO();
			lbVO.setIsbn(isbn);
			lbVO.setSerial(serial);
			lbVO.setCallNum(callNum);
			lbVO.setBookLocation(bookLocation);
		}
		bkDAO.collectionAdd(lbVO);
		System.out.println();

	}

	// 소장 도서 삭제
	public void collectionDelete() {
		BookDAO bkDAO = new BookDAO();
		String serial = null;
		System.out.printf("\n삭제할 소장 도서의 serial 입력\n>>");
		serial = scan.nextLine();
		bkDAO.collectionDelete(serial);

	}

}