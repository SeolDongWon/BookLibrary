import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import controller.DBcon;

public class ProcedureTest1 {
	public static Scanner scan = new Scanner(System.in);
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] agrs) {
		Connection con = null;
		CallableStatement cstmt = null;
		String deptno;
		String rate;

		try {
			con = DBcon.getConnection();
			cstmt = con.prepareCall("{call proc01(?,?)}"); // 실행할 프로시저 명시, ?는 매개변수의 개수만큼 입력
			System.out.print("인상 부서번호 입력>>");
			deptno = scan.nextLine();
			System.out.print("인상률 입력>>");
			rate = scan.nextLine();

			cstmt.setInt(1, Integer.parseInt(deptno));
			cstmt.setFloat(2, Float.parseFloat(rate));
			cstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
				System.out.println("완료");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
