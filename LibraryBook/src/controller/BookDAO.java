package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.BookVO;
import model.LibraryVO;

public class BookDAO {
	// 도서 목록
	public void getBooklList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bkVO = null;

		String sql = "select * from book order by no";
		try {
			con = DBcon.getConnection();
			pstmt = con.prepareStatement(sql);

			System.out.printf("%-10s %-20s %-30s %-20s \n", "일련번호", "ISBN", "도서명", "작가");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bkVO = new BookVO();
				bkVO.setNo(rs.getInt("no"));
				bkVO.setISBN(rs.getString("ISBN"));
				bkVO.setBookTitle(rs.getString("bookTitle"));
				bkVO.setBookAuthor(rs.getString("bookAuthor"));
				bkVO.setBookRelease(rs.getString("bookRelease") + "");
				bkVO.setBookIntro(rs.getString("bookIntro"));

				System.out.printf("%-10d %-20s %-30s %-20s \n", bkVO.getNo(), bkVO.getISBN(), bkVO.getBookTitle(),
						bkVO.getBookAuthor());

			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("close 오류");
			}
		}
	}

	// 도서 등록
	public void setBookRegister(BookVO bkVO) {
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
//		String sql = "insert into book values (book_no_seq.nextval, ?, ?,?,?,?)";
		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_in_book(?,?,?,?,?)}");
			cstmt.setString(1, bkVO.getISBN());
			cstmt.setString(2, bkVO.getBookTitle());
			cstmt.setString(3, bkVO.getBookAuthor());
			cstmt.setString(4, bkVO.getBookRelease());
			cstmt.setString(5, bkVO.getBookIntro());
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, bkVO.getISBN());
//			pstmt.setString(2, bkVO.getBookTitle());
//			pstmt.setString(3, bkVO.getBookAuthor());
//			pstmt.setString(4, bkVO.getBookRelease());
//			pstmt.setString(5, bkVO.getBookIntro());

//			int cnt = pstmt.executeUpdate();
			int cnt = cstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println(bkVO.getBookTitle() + " 도서 등록 완료");
			} else {
				System.out.println("도서 등록 실패");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("close 오류");
			}
		}
	}

	// 도서 수정
	public boolean setbookUpdate(BookVO bkVO) {
		boolean updateFlag = false;
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

//		String sql = "update book set ISBN=?, bookTitle=? ,bookAuthor=?, bookRelease=? ,bookIntro=? where no=?";

		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_up_book(?,?,?,?,?,?)}");
			cstmt.setString(1, bkVO.getISBN());
			cstmt.setString(2, bkVO.getBookTitle());
			cstmt.setString(3, bkVO.getBookAuthor());
			cstmt.setString(4, bkVO.getBookRelease());
			cstmt.setString(5, bkVO.getBookIntro());
			cstmt.setInt(6, bkVO.getNo());

//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, bkVO.getISBN());
//			pstmt.setString(2, bkVO.getBookTitle());
//			pstmt.setString(3, bkVO.getBookAuthor());
//			pstmt.setString(4, bkVO.getBookRelease());
//			pstmt.setString(5, bkVO.getBookIntro());
//			pstmt.setInt(6, bkVO.getNo());

//			int cnt = pstmt.executeUpdate();
			int cnt = cstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println(bkVO.getBookTitle() + " 도서 수정 완료");
			} else {
				System.out.println("도서 수정 실패");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("close 오류");
			}
		}
		return updateFlag;
	}

	// 도서 삭제
	public void setbookDelete(int no) {
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

//		String sql = "delete from book where no = ?";
		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_del_book(?)}");
			cstmt.setInt(1, no);

//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setInt(1, no);
//			int cnt = pstmt.executeUpdate();
			int cnt = cstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("도서 삭제 완료");
			} else {
				System.out.println("도서 삭제 실패");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("close 오류");
			}
		}
	}

	// 소장 도서 추가
	public void collectionAdd(LibraryVO lbVO) {
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

//		String sql = "insert into Library values(Library_no_seq.nextval,?,?,?,?,'','','','','')";
		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_in_library(?,?,?,?)}");

			cstmt.setString(1, lbVO.getIsbn());
			cstmt.setString(2, lbVO.getSerial());
			cstmt.setString(3, lbVO.getCallNum());
			cstmt.setString(4, lbVO.getBookLocation());

//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setString(1, lbVO.getIsbn());
//			pstmt.setString(2, lbVO.getSerial());
//			pstmt.setString(3, lbVO.getCallNum());
//			pstmt.setString(4, lbVO.getBookLocation());
//			int cnt = pstmt.executeUpdate();

			int cnt = cstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("도서관에 책 추가 완료");
			} else {
				System.out.println("도서관에 책 추가 실패");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("close 오류");
			}
		}
	}

	// 보유 도서 카운트
	public String collectionCount(String isbn) {
		String bookCount = null;
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
//		ResultSet rs = null;
//		String sql = "select LPAD(count(*)+1, 2,'0') as bookCount from library where isbn = ?";

		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_cnt_library(?,?)}");
			cstmt.setString(1, isbn);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();

//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, isbn);
//			rs = pstmt.executeQuery();
			System.out.println(cstmt.getString(2));
			bookCount = cstmt.getString(2);
//			if (rs.next()) {
//				System.out.println(rs.getString("bookCount"));
//				bookCount = rs.getString("bookCount");
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("close오류");
			}
		}
		return bookCount;
	}

	// 도서 삭제
	public void collectionDelete(String serail) {
		Connection con = null;
//		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

//		String sql = "delete from library where serial = ?";
		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call pro_del_library(?)}");
			cstmt.setString(1, serail);
//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setString(1, serail);
//			int cnt = pstmt.executeUpdate();
			int cnt = cstmt.executeUpdate();
			if (cnt >= 1) {
				System.out.println("소장 도서 삭제 완료");
			} else {
				System.out.println("소장 도서 삭제 실패");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SQLException 오류");
		} finally {
			try {
//				if (pstmt != null)
//					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("close 오류");
			}
		}
	}

}