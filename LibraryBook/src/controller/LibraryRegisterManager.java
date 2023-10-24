package controller;

import java.util.Scanner;

import model.MemberVO;

public class LibraryRegisterManager {
	public static Scanner scan = new Scanner(System.in);

//	// 도서 검색
//	public void bookSearch() {
//		LibraryDAO lbDAO = new LibraryDAO();
//		String bookTitle = null;
//
//		System.out.println("검색할 도서 제목 입력");
//		bookTitle = scan.nextLine();
//
//		lbDAO.searchBook(bookTitle);
//	}

	// 도서 대출
	public void bookBorrow(MemberVO memVO, MemberRegisterManager mrm, BookRegisterManager brm) {
//		MemberRegisterManager mrm = new MemberRegisterManager();
		LibraryDAO lbDAO = new LibraryDAO();
//		MemberDAO memDAO = new MemberDAO();
//		MemberVO memVO = null;

		String serial = null;
		boolean borrowCheck = false;

		// 도서 검색
		brm.bookSearch();

		// 도서 상태 확인
		System.out.printf("\n대출할 도서의 일련번호 입력\n");
		serial = scan.nextLine();
		borrowCheck = lbDAO.getBorrowState(serial, memVO.getMemId());

		if (!borrowCheck) {
			return;
		}
		lbDAO.borrowBook(memVO, serial);
	}

	// 도서 반납
	public void bookReturn(MemberVO memVO, MemberRegisterManager mrm) {
//		MemberRegisterManager mrm = new MemberRegisterManager();
		LibraryDAO lbDAO = new LibraryDAO();
		MemberDAO memDAO = new MemberDAO();
//		MemberVO memVO = null;
		String serial = null;

//		if ((memVO = mrm.loginMethod()) == null) {
//			return;
//		}

		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw(), false);

		System.out.println("반납할 책의 일련번호를 입력");
		serial = scan.nextLine();

		lbDAO.returnBook(serial,memVO);

	}

	// 대출 예약
	public void bookReserve(MemberVO memVO, MemberRegisterManager mrm, BookRegisterManager brm) {
//		MemberRegisterManager mrm = new MemberRegisterManager();
		LibraryDAO lbDAO = new LibraryDAO();
//		MemberDAO memDAO = new MemberDAO();
//		MemberVO memVO = null;
		String serial = null;
		boolean borrowCheck = false;

//		if ((memVO = mrm.loginMethod()) == null) {
//			return;
//		}
		// 도서 검색
		brm.bookSearch();

		// 도서 상태 확인
		System.out.println("예약할 책의 일련번호를 입력");
		serial = scan.nextLine();
		borrowCheck = lbDAO.getReserveState(serial, memVO.getMemId());

		if (!borrowCheck) {
			return;
		}
		// 도서 예약
		lbDAO.reserveBook(memVO, serial);

	}

	// 반납 연기
	public void bookPostpone(MemberVO memVO, MemberRegisterManager mrm) {
//		MemberRegisterManager mrm = new MemberRegisterManager();
		LibraryDAO lbDAO = new LibraryDAO();
		MemberDAO memDAO = new MemberDAO();
//		MemberVO memVO = null;
		String serial = null;
//		boolean borrowCheck = false;

//		if ((memVO = mrm.loginMethod()) == null) {
//			return;
//		}
		// 대출 목록
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw(), false);

		System.out.printf("\n반납을 연기할 책의 일련번호를 입력\n");
		serial = scan.nextLine();

//		// 도서 상태 확인
//		borrowCheck = lbDAO.getReserveState(serial, memVO.getMemId());
//
//		if (!borrowCheck) {
//			return;
//		}

		lbDAO.postponeBook(memVO, serial);
	}

//	// 도서관 보유 도서 목록
//	public void libraryBookList() {
//		LibraryDAO lbDAO = new LibraryDAO();
//		lbDAO.libraryBookList();
//
//	}
}