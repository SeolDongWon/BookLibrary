package controller;

import java.util.Scanner;
import model.MemberVO;

public class MemberRegisterManager {
	public static Scanner input = new Scanner(System.in);

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
		memName = input.nextLine();

		do { // 아이디 중복 체크
			System.out.print("아이디(8자 이상 12자 이내)>>");
			memId = input.nextLine();
			id_check = memDAO.getIdOverlap(memId);
			if (id_check) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		} while (id_check);

		System.out.print("비밀번호(12자 이내)>>");
		memPw = input.nextLine();

		System.out.print("전화번호>>");
		memPhone = input.nextLine();

		memVO.setMemId(memId);
		memVO.setMemPw(memPw);
		memVO.setMemName(memName);
		memVO.setMemPhone(memPhone);
		memDAO.setMemberRegister(memVO);

		System.out.println();
		System.out.println("등록 회원 정보");
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw());
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
			memId = input.nextLine();
			System.out.print("비밀번호>>");
			memPw = input.nextLine();

			if (memDAO.getMemberLogin(memId, memPw) != null) {
				success = true;
			} else {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}

		} while (!success);

		memName = memDAO.getMemberName(memId, memPw);

		System.out.printf("\n수정할 회원\n아이디>>" + memId);

		System.out.printf("\n비밀번호(12자 이내)>>");
		chMemPw = input.nextLine();
		System.out.print("전화번호>>");
		chMemPhone = input.nextLine();

		memVO.setMemId(memId);
		memVO.setMemPw(chMemPw);
		memVO.setMemName(memName);
		memVO.setMemPhone(chMemPhone);
		memDAO.setMemberUpdate(memVO);

		System.out.println();
		System.out.println("회원 정보 수정 결과");
		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw());
		System.out.println();
	}

	// 회원 전체 목록
	public void memberTotalList() {
		MemberDAO memDAO = new MemberDAO();
		String pw = null;
		System.out.printf("\n회원 정보 전체 목록\n관리자 비밀번호>>");
		pw = input.nextLine();
		if (pw.equals("1234")) {
			memDAO.getMemberList();
		} else {
			System.out.println("관리자 비밀번호가 틀립니다.");
		}
	}

	// 회원 삭제
	public void memberDelete() {
		MemberDAO memDAO = new MemberDAO();
		String pw = null;
		System.out.printf("\n학생 정보 전체 목록\n관리자 비밀번호>>");
		pw = input.nextLine();
		if (pw.equals("1234")) {
			String memId = null; // 아이디
			System.out.print("삭제할 아이디>>");
			memId = input.nextLine();
			memDAO.deleteMember(memId);
		} else {
			System.out.println("관리자 비밀번호가 틀립니다.");
		}
	}

	// 회원 정보 보기
	public void memberInfo() {
		MemberDAO memDAO = new MemberDAO();
		MemberVO memVO = null;

		String memId = null; // 아이디
		String memPw = null; // 입력 비밀번호
		boolean success = false;

		System.out.println("로그인");
		do {
			System.out.print("아이디>>");
			memId = input.nextLine();
			System.out.print("비밀번호>>");
			memPw = input.nextLine();

			if ((memVO = memDAO.getMemberLogin(memId, memPw)) != null) {
				success = true;
			} else {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}

		} while (!success);

		memDAO.getMemberInfo(memVO.getMemId(), memVO.getMemPw());
	}
}