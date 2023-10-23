package controller;

import java.util.Scanner;

import model.MemberVO;

public class LibraryRegisterManager {
	public static Scanner scan = new Scanner(System.in);

	// 로그인 메서드
	private MemberVO loginMethod() {
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;

		String memId = null;
		String memPw = null;
		String mainMenu = null;
		boolean loginCheck = false;

		System.out.println();
		System.out.println("도서관 이용을 위한 정보 입력");
		do {
			System.out.print("아이디 : ");
			memId = scan.nextLine();
			System.out.print("비밀번호 : ");
			memPw = scan.nextLine();
			memVO = memDAO.getMemberLogin(memId, memPw);

			if (memVO != null) {
				loginCheck = true;
			}
			if (!loginCheck) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시입력");
				System.out.print("메인 메뉴로 이동(y/n) : ");
				mainMenu = scan.next();
				scan.nextLine();
				if (mainMenu.equals("y") || mainMenu.equals("Y")) {
					return null;
				}
				System.out.println();
			}
		} while (!loginCheck);
		return memVO;
	}

	// 도서 검색
	public void bookSearch() {
		LibraryDAO lbDAO = new LibraryDAO();
		String bookTitle = null;

		System.out.println("검색할 도서 제목 입력");
		bookTitle = scan.nextLine();

		lbDAO.searchBook(bookTitle);

	}

	// 도서 대출
	public void bookBorrow() {
		LibraryDAO lbDAO = new LibraryDAO();
//		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;

		String serial = null;
		boolean borrowCheck = false;

		// 회원 정보 입력
		if ((memVO = loginMethod()) == null) {
			return;
		}

		// 도서 검색
		bookSearch();

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
	public void bookReturn() {
		LibraryDAO lbDAO = new LibraryDAO();
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;
		String serial = null;

		if ((memVO = loginMethod()) == null) {
			return;
		}

		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw());

		System.out.println("반납할 책의 일련번호를 입력");
		serial = scan.nextLine();

		lbDAO.returnBook(serial);

	}

	// 대출 예약
	public void bookReserve() {
		LibraryDAO lbDAO = new LibraryDAO();
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;
		String serial = null;
		boolean borrowCheck = false;

		if ((memVO = loginMethod()) == null) {
			return;
		}
		// 도서 검색
		bookSearch();

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
	public void bookPostpone() {
		LibraryDAO lbDAO = new LibraryDAO();
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;
		String serial = null;
//		boolean borrowCheck = false;

		if ((memVO = loginMethod()) == null) {
			return;
		}
		// 대출 목록
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw());

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

}