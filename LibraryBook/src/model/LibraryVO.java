package model;

import java.util.Objects;

public class LibraryVO {

	private int no;
	private String isbn;
	private String serial; // isbn+일련번호
	private String callNum; // 청구기호
	private String bookLocation; // 책 위치
	private String borrowState;// 대출 상태
	private String memid;// 빌린사람
	private String returnDate;// 반납일
	private String reserveState;// 예약 상태

	public LibraryVO(int no, String isbn, String serial, String callNum, String bookLocation, String borrowState,
			String memid, String returnDate, String reserveState) {
		super();
		this.no = no;
		this.isbn = isbn;
		this.serial = serial;
		this.callNum = callNum;
		this.bookLocation = bookLocation;
		this.borrowState = borrowState;
		this.memid = memid;
		this.returnDate = returnDate;
		this.reserveState = reserveState;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getCallNum() {
		return callNum;
	}

	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}

	public String getBookLocation() {
		return bookLocation;
	}

	public void setBookLocation(String bookLocation) {
		this.bookLocation = bookLocation;
	}

	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getReserveState() {
		return reserveState;
	}

	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.no, this.serial);
	}

	@Override
	public boolean equals(Object obj) {
		LibraryVO lib = null;
		if (!(obj instanceof LibraryVO)) {
			return false;
		}
		lib = (LibraryVO) obj;
		return this.no == lib.no && this.serial.equals(lib.serial);
	}

	@Override
	public String toString() {
		return "LibraryVO [no=" + no + ", isbn=" + isbn + ", serial=" + serial + ", callNum=" + callNum
				+ ", bookLocation=" + bookLocation + ", borrowState=" + borrowState + ", memid=" + memid
				+ ", returnDate=" + returnDate + ", reserveState=" + reserveState + "]";
	}

}
