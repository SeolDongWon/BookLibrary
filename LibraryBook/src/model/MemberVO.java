package model;

import java.util.Objects;

public class MemberVO {

	private int no; // 일련번호
	private String memid; // 아이디
	private String memepw; // 비밀번호
	private String memname; // 이름
	private String memphone; // 핸드폰번호

	public MemberVO(int no, String memid, String memepw, String memname, String memphone) {
		super();
		this.no = no;
		this.memid = memid;
		this.memepw = memepw;
		this.memname = memname;
		this.memphone = memphone;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getMemepw() {
		return memepw;
	}

	public void setMemepw(String memepw) {
		this.memepw = memepw;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public String getMemphone() {
		return memphone;
	}

	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.no, this.memid);
	}

	@Override
	public boolean equals(Object obj) {
		MemberVO mbm = null;
		if (!(obj instanceof MemberVO)) {
			return false;
		}
		mbm = (MemberVO) obj;
		return this.no == mbm.no && this.memid.equals(mbm.memid);
	}

	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", memid=" + memid + ", memepw=" + memepw + ", memname=" + memname + ", memphone="
				+ memphone + "]";
	}

}
