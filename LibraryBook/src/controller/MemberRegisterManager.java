package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import model.MemberVO;

public class MemberRegisterManager {
	public static Scanner scan = new Scanner(System.in);

	// 관리자 로그인
	public boolean adminLogin() {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;

		String inputId = null;
		String inputPw = null;
		boolean loginFlag = false;

		System.out.printf("\n관리자id 입력>>");
		inputId = scan.nextLine();
		System.out.printf("\n관리자pw입력>>");
		inputPw = scan.nextLine();

		try {
			fileInputStream = new FileInputStream("src/proper/db.properties");
			properties.load(fileInputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String adminId = properties.getProperty("username");
		String adminPw = properties.getProperty("password");

		if (adminId.equals(inputId) && adminPw.equals(inputPw)) {
			System.out.println("관리자 로그인 성공");
			loginFlag = true;
		} else {
			System.out.println("관리자 로그인 실패");
		}

		return loginFlag;

	}

	// 회원 전체 목록
	public void memberTotalList(MemberRegisterManager mrm) {
		MemberDAO memDAO = new MemberDAO();
		memDAO.getMemberList();

		boolean adminCheck = true;
		String userId = null;
		System.out.printf("\n검색할 아이디 입력>>");
		userId = scan.nextLine();

		memDAO.getMemberInfo(userId, null, adminCheck);
	}

	// 회원 로그인
	public MemberVO loginMethod() {
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

	// 회원 정보 등록
	public void memberRegistr() {
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = new MemberVO();

		String memId = null; // 아이디
		String memPw = null; // 비밀번호
		String memName = null; // 이름
		String memPhone = null; // 핸드폰번호
		boolean id_check = false; // 아이디 체크

		System.out.printf("회원 정보 입력\n성명>>");
		memName = scan.nextLine();

		do { // 아이디 중복 체크
			System.out.print("아이디(8자 이상 12자 이내)>>");
			memId = scan.nextLine();
			id_check = memDAO.getIdOverlap(memId);
			if (id_check) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		} while (id_check);

		System.out.print("비밀번호(12자 이내)>>");
		memPw = scan.nextLine();

		System.out.print("전화번호>>");
		memPhone = scan.nextLine();

		memVO.setMemId(memId);
		memVO.setMemPw(memPw);
		memVO.setMemName(memName);
		memVO.setMemPhone(memPhone);
		memDAO.setMemberRegister(memVO);

		System.out.println();
		System.out.println("등록 회원 정보");
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw(), false);
		System.out.println();
	}

	// 회원 정보 수정
	public void memberUpdate() {
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = new MemberVO();

		String memId = null; // 아이디
		String memPw = null; // 입력 비밀번호
		String memName = null;
		String chMemPw = null; // 수정 비밀번호
		String chMemPhone = null; // 수정 전화번호
		boolean success = false;

		System.out.println("회원 정보 수정");
		do {
			System.out.print("아이디>>");
			memId = scan.nextLine();
			System.out.print("비밀번호>>");
			memPw = scan.nextLine();

			if (memDAO.getMemberLogin(memId, memPw) != null) {
				success = true;
			} else {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}

		} while (!success);

		memName = memDAO.getMemberName(memId, memPw);

		System.out.printf("\n수정할 회원\n아이디>>" + memId);

		System.out.printf("\n비밀번호(12자 이내)>>");
		chMemPw = scan.nextLine();
		System.out.print("전화번호>>");
		chMemPhone = scan.nextLine();

		memVO.setMemId(memId);
		memVO.setMemPw(chMemPw);
		memVO.setMemName(memName);
		memVO.setMemPhone(chMemPhone);
		memDAO.setMemberUpdate(memVO);

		System.out.println();
		System.out.println("회원 정보 수정 결과");
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw(), false);
		System.out.println();
	}

	// 회원 탈퇴
	public void memberDelete() {
		MemberDAO memDAO = new MemberDAO();
		String memId = null;
		String memPw = null;

		System.out.print("탈퇴할 아이디>>");
		memId = scan.nextLine();
		System.out.print("패스워드>>");
		memPw = scan.nextLine();

		memDAO.deleteMember(memId, memPw);
	}

	// 회원 정보 보기
	public void memberInfo(MemberVO memVO) {
		MemberDAO memDAO = new MemberDAO();
//		MemberVO memVO = null;

//		String memId = null; // 아이디
//		String memPw = null; // 입력 비밀번호
//		boolean success = false;
//
//		System.out.println("로그인");
//		do {
//			System.out.print("아이디>>");
//			memId = scan.nextLine();
//			System.out.print("비밀번호>>");
//			memPw = scan.nextLine();
//
//			if ((memVO = memDAO.getMemberLogin(memId, memPw)) != null) {
//				success = true;
//			} else {
//				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
//			}
//
//		} while (!success);

		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw(), false);
	}
}