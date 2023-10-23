package aMain;

import controller.BookRegisterManager;
import controller.LibraryRegisterManager;
import controller.MemberRegisterManager;
import view.MENU_BOOK;
import view.MENU_LIBRARY;
import view.MENU_MAIN;
import view.MENU_MEMBER;
import view.MenuViewer;

public class BookLibraryMain {

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		int selectMenu = 0;
		boolean stopFlag = false;

		while (!stopFlag) {
			try {
				// 메인메뉴 출력
				MenuViewer.mainMenuView();
				// 키보드 입력
				selectMenu = MenuViewer.scan.nextInt();
				// 입력버퍼 클리어
				MenuViewer.scan.nextLine();

				switch (selectMenu) {
				case MENU_MAIN.MEMBER: // MENU_MAIN의 상수 정수 1
					memberMenu(); // 회원 관리 메뉴
					break;
				case MENU_MAIN.BOOKINFO: // MENU_MAIN의 상수 정수 2
					bookInfoMenu();// 도서 관리 메뉴
					break;
				case MENU_MAIN.LOAN: // MENU_MAIN의 상수 정수 3
					bookLoan(); // 도서관 이용 메뉴 메뉴
					break;
				case MENU_MAIN.EXIT: // MENU_MAIN의 상수 정수 4
					System.out.println("프로그램을 종료합니다.");
					stopFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을다시 시작하세요.");
				stopFlag = true;
			}
		}
	}

	private static void memberMenu() { // 회원 관리 메뉴
		MemberRegisterManager mrm = new MemberRegisterManager();// 회원 객체를 만들 자리
		int selectMenu = 0;
		// 회원 CRUD 컨트롤러
		MenuViewer.memberMenuView(); // 회원 관리 메뉴 출력
		selectMenu = MenuViewer.scan.nextInt(); // 회원 메뉴 입력
		MenuViewer.scan.nextLine();
		switch (selectMenu) {
		case MENU_MEMBER.MYINFO: // MENU_MEMBER의 상수 정수 1 // 회원 정보
			mrm.memberInfo();
			System.out.println("");
			break;
		case MENU_MEMBER.INSERT: // MENU_MEMBER의 상수 정수 2 // 회원 등록
			mrm.memberRegistr();
			System.out.println("");
			break;
		case MENU_MEMBER.UPDATE: // MENU_MEMBER의 상수 정수 3 // 회원 수정
			System.out.println("");
			mrm.memberUpdate();
			break;
		case MENU_MEMBER.DELETE: // MENU_MEMBER의 상수 정수 4 // 회원 삭제
			mrm.memberDelete();
			System.out.println("");
			break;
		case MENU_MEMBER.LIST: // MENU_MEMBER의 상수 정수 5 // 모든 회원 리스트
			mrm.memberTotalList();
			System.out.println("");
			break;
		case MENU_MEMBER.MAIN: // MENU_MEMBER의 상수 정수 6
			return; // 나가기
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}

	private static void bookInfoMenu() { // 도서 관리 메뉴
		BookRegisterManager brm = new BookRegisterManager();
		int selectMenu = 0;
		// 객체 // 도서 관리 CRUD 컨트롤러
		MenuViewer.bookMenuView();
		selectMenu = MenuViewer.scan.nextInt(); // 도서 관리 메뉴 출력
		MenuViewer.scan.nextLine(); // 도서 메뉴 입력
		switch (selectMenu) {
		case MENU_BOOK.LIST: // MENU_BOOKINFO의 상수 정수 1 // 도서 목록
			brm.bookList();
			System.out.println("");
			break;
		case MENU_BOOK.INSERT: // MENU_BOOKINFO의 상수 정수 2 // 도서 등록
			System.out.println("");
			brm.bookRegistr();
			break;
		case MENU_BOOK.UPDATE: // MENU_BOOKINFO의 상수 정수 3 // 도서 수정
			System.out.println("");
			brm.bookUpdate();
			break;
		case MENU_BOOK.DELETE: // MENU_BOOKINFO의 상수 정수 4 // 도서 삭제
			System.out.println("");
			brm.bookDelete();
			break;
		case MENU_BOOK.MAIN: // MENU_BOOKINFO의 상수 정수 5
			return; // 나가기
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}

	private static void bookLoan() { // 도서관 이용메뉴 메뉴
		LibraryRegisterManager lrm = new LibraryRegisterManager();// 도서관 이용메뉴 정보 객체 자리
		int choice = 0;
		// 도서관 이용메뉴 CRUD 컨트롤러
		MenuViewer.libraryMenuView(); // 도서관 이용메뉴 메뉴 출력
		choice = MenuViewer.scan.nextInt(); // 도서관 이용메뉴 메뉴 입력
		MenuViewer.scan.nextLine();
		switch (choice) {
		case MENU_LIBRARY.LIST: // MENU_BOOKLOAN의 상수 정수 1 // 도서 검색
			lrm.bookSearch();
			System.out.println("");
			break;
		case MENU_LIBRARY.LOAN: // MENU_BOOKLOAN의 상수 정수 2 // 도서 대출
			lrm.bookBorrow();
			System.out.println("");
			break;
		case MENU_LIBRARY.RESERVATION: // MENU_BOOKLOAN의 상수 정수 3 // 대출 예약
			lrm.bookReserve();
			System.out.println("");
			break;
		case MENU_LIBRARY.RETURN: // MENU_BOOKLOAN의 상수 정수 4 // 도서 반납
			lrm.bookReturn();
			System.out.println("");
			break;
		case MENU_LIBRARY.POSTPONE: // MENU_BOOKLOAN의 상수 정수 5 // 반납 연기
			lrm.bookPostpone();
			System.out.println("");
			break;
		case MENU_LIBRARY.MAIN: // MENU_BOOKLOAN의 상수 정수 6
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}

}
