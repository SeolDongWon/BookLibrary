package view;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner scan = new Scanner(System.in);

	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println();
		System.out.println("미래도서관 대출반납 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 프로그램 종료");
		System.out.println("1. 회원 가입/수정/탈퇴");
		System.out.println("2. 관리자 메뉴");
		System.out.println("3. 도서관 이용");
		System.out.print("번호 선택>>");
	}

	// 회원 관리 메뉴
	public static void memberMenuView() {
		System.out.println();
		System.out.println("회원 메뉴 번호를 입력하세요.");
		System.out.println("0. 메인 메뉴");
		System.out.println("1. 회원 정보");
		System.out.println("2. 회원 가입");
		System.out.println("3. 회원 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.print("번호 선택>>");
	}

	// 도서 관리 메뉴
	public static void bookMenuView() {
		System.out.println();
		System.out.println("관리 메뉴 번호를 입력하세요.");
		System.out.println("0. 메인 메뉴");
		System.out.println("1. 도서정보 목록");
		System.out.println("2. 도서정보 추가");
		System.out.println("3. 도서정보 수정");
		System.out.println("4. 도서정보 삭제");
		System.out.println("5. 소장 도서 목록");
		System.out.println("6. 소장 도서 추가");
		System.out.println("7. 소장 도서 삭제");
		System.out.println("8. 회원 검색");
		System.out.print("번호 선택>>");
	}

	// 도서관 이용메뉴
	public static void libraryMenuView() {
		System.out.println();
		System.out.println("도서관 이용메뉴 번호를 입력하세요.");
		System.out.println("0. 메인 메뉴");
		System.out.println("1. 도서 목록&검색");
		System.out.println("2. 도서 대출");
		System.out.println("3. 대출 예약");
		System.out.println("4. 도서 반납");
		System.out.println("5. 반납 연기");
		System.out.println("6. 내 정보");
		System.out.print("번호 선택>>");
	}
}
