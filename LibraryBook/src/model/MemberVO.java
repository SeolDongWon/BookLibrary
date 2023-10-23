package model;

import java.util.Objects;

public class MemberVO {

	private int no; // 일련번호
	private String memId; // 아이디
	private String memPw; // 비밀번호
	private String memName; // 이름
	private String memPhone; // 핸드폰번호

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.no, this.memId);
	}

	@Override
	public boolean equals(Object obj) {
		MemberVO mbm = null;
		if (!(obj instanceof MemberVO)) {
			return false;
		}
		mbm = (MemberVO) obj;
		return this.no == mbm.no && this.memId.equals(mbm.memId);
	}

	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", memId=" + memId + ", memPw=" + memPw + ", memName=" + memName + ", memPhone="
				+ memPhone + "]";
	}

}
