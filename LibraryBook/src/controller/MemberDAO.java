package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MemberVO;

public class MemberDAO {

	// 회원 등록
	public void setMemberRegister(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values (student_no_seq.nextval, ?, ?, ?, ?)";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memVO.getMemId());
			pstmt.setString(2, memVO.getMemPw());
			pstmt.setString(3, memVO.getMemName());
			pstmt.setString(4, memVO.getMemPhone());

			int cnt = pstmt.executeUpdate();

			if (cnt >= 1) {
				System.out.println(memVO.getMemName() + " 회원 등록 완료");
			} else {
				System.out.println("회원정보 등록 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close 오류");
			}
		}
	}

	// 회원 정보 수정
	public void setMemberUpdate(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update member set memPw=?, memPhone=? where memId=?";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memVO.getMemPw());
			pstmt.setString(2, memVO.getMemPhone());
			pstmt.setString(3, memVO.getMemId());

			int cnt = pstmt.executeUpdate();

			if (cnt >= 1) {
				System.out.println(memVO.getMemName() + "회원정보 수정 완료");
			} else {
				System.out.println("회원정보 수정 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close 오류");
			}
		}
	}

	// 회원 아이디 중복 체크
	public boolean getIdOverlap(String idCheck) {
		boolean CheckFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where memId = ?"; // 등록할 아이디를 기준으로 하는 쿼리를 실행

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idCheck);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 레코드값이 있다 = rs가 null이 아니다 = 아이디 중복
				CheckFlag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}
		return CheckFlag;
	}

	// 회원 로그인
	public MemberVO getMemberLogin(String memId, String memPw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memVO = null;
		String sql = "select * from member where memId = ? and memPw = ?";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				memVO = new MemberVO();
				memVO.setNo(rs.getInt("no"));
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemPw(rs.getString("memPw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemPhone(rs.getString("memPhone"));
				System.out.println("로그인성공");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}
		return memVO;
	}

	// 회원 이름
	public String getMemberName(String memId, String memPw) {
		String memName = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select memName from member where memId = ? and memPw= ?";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memName = rs.getString("memName");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}
		return memName;
	}

	// 회원 정보
	public void getMemberInfo(String memId, String memPw, boolean adminCheck) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memVO = null;
		String sql = null;

		try {
			con = DBcon.getConnection();

			if (adminCheck) {
//				sql = "select * from book bk join library lb on lb.isbn = bk.isbn right join member mb on lb.borrowmemid = mb.memid where memId = ?";
				sql = "select * from book bk join library lb on lb.isbn = bk.isbn right join member mb on mb.memid in(lb.borrowmemid,lb.reserveMemid) where memId = ? order by returnDate";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, memId);
			} else {
				sql = "select * from book bk join library lb on lb.isbn = bk.isbn right join member mb on mb.memid in(lb.borrowmemid,lb.reserveMemid) where memId = ? and memPw = ? order by returnDate";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, memId);
				pstmt.setString(2, memPw);
			}

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.printf("%-5s %-10s %-10s %-10s %-10s \n", "일련번호", "아이디", "비밀번호", "이름", "전화번호");
			} else {
				System.out.println("아이디 검색 불가");
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				memVO = new MemberVO();
				memVO.setNo(rs.getInt("no"));
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemPw(rs.getString("memPw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemPhone(rs.getString("memPhone"));

				System.out.printf("%-5d  %-10s  %-10s  %-10s  %-10s\n", memVO.getNo(), memVO.getMemId(),
						memVO.getMemPw(), memVO.getMemName(), memVO.getMemPhone());

				System.out.printf("\n대출&예약 도서\n");
				System.out.printf("%-18s %-30s %-17s %-10s %-10s \n", "도서일련번호", "도서명", "저자", "반납일", "예약");

				System.out.printf("%-20s %-25s %-15s %-10s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
						rs.getString("bookauthor"), rs.getDate("returndate") + "", rs.getString("reserveState"));
//				System.out.printf("%-20s %-25s %-15s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
//						rs.getString("bookauthor"),
//						rs.getDate("returndate") != null ? rs.getDate("returndate") + "" : "예약중");
			}
			while (rs.next()) {
				System.out.printf("%-20s %-25s %-15s %-10s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
						rs.getString("bookauthor"), rs.getDate("returndate") + "", rs.getString("reserveState"));
//				System.out.printf("%-20s %-25s %-15s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
//						rs.getString("bookauthor"),
//						rs.getDate("returndate") != null ? rs.getDate("returndate") + "" : "예약중");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}

	}
//
//	public void getMemberInfo(String memId, String memPw) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MemberVO memVO = null;
////		String sql = "select * from member where memId = ? and memPw = ?";
////		String sql = "select * from member mb join library lb on lb.borrowmemid = mb.memid join book bk on lb.isbn = bk.isbn where memId = ? and memPw = ?";
//		String sql = "select * from book bk join library lb on lb.isbn = bk.isbn right join member mb on lb.borrowmemid = mb.memid where memId = ? and memPw = ?";
//
//		try {
//			con = DBcon.getConnection();
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, memId);
//			pstmt.setString(2, memPw);
//
//			System.out.printf("%-5s %-10s %-10s %-10s %-10s \n", "일련번호", "아이디", "비밀번호", "이름", "전화번호");
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				memVO = new MemberVO();
//				memVO.setNo(rs.getInt("no"));
//				memVO.setMemId(rs.getString("memId"));
//				memVO.setMemPw(rs.getString("memPw"));
//				memVO.setMemName(rs.getString("memName"));
//				memVO.setMemPhone(rs.getString("memPhone"));
//
//				System.out.printf("%-5d  %-10s  %-10s  %-10s  %-10s\n", memVO.getNo(), memVO.getMemId(),
//						memVO.getMemPw(), memVO.getMemName(), memVO.getMemPhone());
//
//				System.out.printf("\n대출도서\n");
//				System.out.printf("%-18s %-30s %-17s %-10s \n", "도서일련번호", "도서명", "저자", "반납일");
//
//				System.out.printf("%-20s %-25s %-15s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
//						rs.getString("bookauthor"), rs.getDate("returndate") + "");
//			}
//			while (rs.next()) {
//				System.out.printf("%-20s %-25s %-15s %-10s \n", rs.getString("serial"), rs.getString("booktitle"),
//						rs.getString("bookauthor"), rs.getDate("returndate") + "");
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("SQLException 오류");
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (con != null)
//					con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("close오류");
//			}
//		}
//	}

	// 회원 전체 목록
	public void getMemberList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memVO = null;

		String sql = "select * from member order by no";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);

			System.out.printf("%-5s %-10s %-10s %-10s %-10s \n", "일련번호", "아이디", "비밀번호", "이름", "전화번호");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemberVO();
				memVO.setNo(rs.getInt("no"));
				memVO.setMemId(rs.getString("memId"));
				memVO.setMemPw(rs.getString("memPw"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemPhone(rs.getString("memPhone"));

				System.out.printf("%-5d  %-10s  %-10s  %-10s  %-10s\n", memVO.getNo(), memVO.getMemId(),
						memVO.getMemPw(), memVO.getMemName(), memVO.getMemPhone());

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}
	}

	// 회원 탈퇴
	public void deleteMember(String memId, String memPw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where memId = ? and memPw = ?";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);

			int cnt = pstmt.executeUpdate();

			if (cnt >= 1) {
				System.out.println(memId + "회원정보 삭제 완료");
			} else {
				System.out.println("회원정보 삭제 실패 : 대출중이거나 예약중인 도서 있음");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
			System.out.println("회원정보 삭제 실패 : 대출중이거나 예약중인 도서 있음");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close 오류");
			}
		}
	}
}
