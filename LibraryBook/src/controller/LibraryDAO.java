package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import model.LibraryVO;
import model.MemberVO;

public class LibraryDAO {

	// 도서 검색
	public void searchBook(String bookTitle) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBcon.getConnection();
			String sql = "select * from library lb inner join book bk on lb.isbn = bk.isbn where booktitle like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + bookTitle + "%");

			System.out.printf("%-19s %-28s %-11s %-14s %-6s %-8s %-13s %s\n", "일련번호", "도서명", "작가", "청구기호", "도서위치",
					"대출상태", "반납일", "예약상태");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.printf("%-20s %-25s %-10s %-15s %-7s %-8s %-15s %s\n", rs.getString("serial"),
						rs.getString("booktitle"), rs.getString("bookauthor"), rs.getString("callnum"),
						rs.getString("booklocation"), rs.getString("borrowstate"), rs.getDate("returndate"),
						rs.getString("reservestate"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	// 도서 상태
	public boolean getBorrowState(String serial, String memId) {
		boolean CheckFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from Library where serial = ? AND borrowMemid is null AND (reserveMemid = ? OR reserveMemid is null)";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serial);
			pstmt.setString(2, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				CheckFlag = true;
			}

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("대출 가능");
			} else {
				System.out.println("대출 불가능 : 대출 혹은 대출예약 중인 도서입니다");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return CheckFlag;
	}

	// 도서 대출
	public void borrowBook(MemberVO memVO, String serial) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;

		String sql = "update Library set borrowState = ? , borrowMemid = ?, returnDate = sysdate+1, reserveState ='', reserveMemid = ''where serial = ?";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "대출중");
			pstmt.setString(2, memVO.getMemId());
			pstmt.setString(3, serial);

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("대출 성공");
			} else {
				System.out.println("대출 실패");
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	// 도서 반납
	public void returnBook(String serial) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;

		String sql = "update Library set borrowState = '' , borrowMemid = '', returnDate = '' where serial = ?";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serial);

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("반납 성공");
			} else {
				System.out.println("반납 실패");
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	// 도서 예약
	public void reserveBook(MemberVO memVO, String serial) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;

		String sql = "update Library set reserveState = ? , reserveMemid = ? where serial = ?";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "예약중");
			pstmt.setString(2, memVO.getMemId());
			pstmt.setString(3, serial);

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("대출예약 성공");
			} else {
				System.out.println("대출예약 실패");
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

	// 도서 예약 상태 확인
	public boolean getReserveState(String serial, String memId) {
		boolean CheckFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// borrowMemid가 null 이면 비교연산 불가로 오류발생
		String sql = "select * from Library where serial = ? AND reserveMemid is null AND NVL(borrowMemid,0) != ?";

		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serial);
			pstmt.setString(2, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				CheckFlag = true;
			}

			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("대출예약 가능");
			} else {
				System.out.println("대출예약 불가능 : 대출 혹은 대출예약 중인 도서입니다");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return CheckFlag;
	}

	// 반납 연기
	public void postponeBook(MemberVO memVO, String serial) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;

		String sql = "update library set returnDate = (select returnDate from library where serial = ?)+1 where serial = ? AND borrowMemid = ? AND reserveState is null";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serial);
			pstmt.setString(2, serial);
			pstmt.setString(3, memVO.getMemId());

//			rs = pstmt.executeQuery();
			int cnt = pstmt.executeUpdate();
			if (cnt >= 1) {
//			if (rs.next()) {
				System.out.println("반납 연기 성공");
			} else {
				System.out.println("대출 예약이 있거나 입력오류");
			}

//			int cnt = pstmt.executeUpdate();
//			if (cnt >= 1) {
//				System.out.println("대출예약 성공");
//			} else {
//				System.out.println("대출예약 실패");
//			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
//				if (rs != null) {
//					rs.close();
//				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}
}