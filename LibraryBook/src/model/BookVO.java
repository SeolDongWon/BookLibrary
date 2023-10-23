package model;

import java.util.Objects;

public class BookVO {
	private int no; // 일련번호
	private String ISBN; // 국제표준도서번호
	private String bookTitle;// 책 제목
	private String bookAuthor;// 작가
	private String bookRelease; // 책 발행일
	private String bookIntro;// 책소개

	public BookVO() {
		super();
	}

	public BookVO(int no, String iSBN, String bookTitle, String bookAuthor, String bookRelease, String bookIntro) {
		super();
		this.no = no;
		this.ISBN = iSBN;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookRelease = bookRelease;
		this.bookIntro = bookIntro;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookRelease() {
		return bookRelease;
	}

	public void setBookRelease(String bookRelease) {
		this.bookRelease = bookRelease;
	}

	public String getBookIntro() {
		return bookIntro;
	}

	public void setBookIntro(String bookIntro) {
		this.bookIntro = bookIntro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.no, this.ISBN);
	}

	@Override
	public boolean equals(Object obj) {
		BookVO book = null;
		if (!(obj instanceof BookVO)) {
			return false;
		}
		book = (BookVO) obj;
		return this.no == book.no && this.ISBN.equals(book.ISBN);
	}

	@Override
	public String toString() {
		return "BookVO [no=" + no + ", ISBN=" + ISBN + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ ", bookRelease=" + bookRelease + ", bookIntro=" + bookIntro + "]";
	}

}
