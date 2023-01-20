import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

	public Statement DbCon() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stat = null;
		
		String dbFileUrl = "C:\\Users\\박준서\\Desktop\\study\\student.db";
		
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			// SQLite 데이터베이스 파일에 연결
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFileUrl);
			
			stat = con.createStatement();
			return stat;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return stat;
	}

}
