package view;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner scan = new Scanner(System.in);

	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println();
		System.out.println("미래도서관 대출반납 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 회원 목록/입력/수정/삭제");
		System.out.println("2. 도서 목록/입력/수정/삭제");
		System.out.println("3. 도서 대출/반납/취소");
		System.out.println("4. 프로그램 종료");
		System.out.print("번호 선택>>");
	}

	// 회원 관리 메뉴
	public static void memberMenuView() {
		System.out.println();
		System.out.println("회원 메뉴 번호를 입력하세요.");
		System.out.println("1. 회원 정보");
		System.out.println("2. 회원 추가");
		System.out.println("3. 회원 수정");
		System.out.println("4. 회원 삭제");
		System.out.println("5. 메인 메뉴");
		System.out.print("번호 선택>>");
	}

	// 도서 관리 메뉴
	public static void bookMenuView() {
		System.out.println();
		System.out.println("도서 관리 메뉴 번호를 입력하세요.");
		System.out.println("1. 도서 목록");
		System.out.println("2. 도서 추가");
		System.out.println("3. 도서 수정");
		System.out.println("4. 도서 삭제");
		System.out.println("5. 메인 메뉴");
		System.out.print("번호 선택>>");
	}

	// 도서관 이용메뉴
	public static void libraryMenuView() {
		System.out.println();
		System.out.println("도서관 이용메뉴 번호를 입력하세요.");
		System.out.println("1. 도서 검색");
		System.out.println("2. 도서 대출");
		System.out.println("3. 대출 예약");
		System.out.println("4. 도서 반납");
		System.out.println("5. 반납 연기");
		System.out.println("6. 메인 메뉴");
		System.out.print("번호 선택>>");
	}
}
