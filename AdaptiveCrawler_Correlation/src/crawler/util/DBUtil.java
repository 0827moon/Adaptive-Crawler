package crawler.util;
import java.sql.*;


public class DBUtil {
	//private static String ip="127.0.0.1";
	private static String ip = Settings.HOSTNAME;  
	private static String user = Settings.USER;
	private static String pwd = Settings.PWD;
	private static String driver = "com.mysql.jdbc.Driver";        
	private static String encode = "utf-8";
        
        
    public static Connection getConn() {
    	Connection conn = null;
       try {
    	   Class.forName(driver);
    	   conn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/?useUnicode=true&characterEncoding="+encode, user , pwd);
    	   //conn = DriverManager.getConnection("jdbc:sqlite:"+databaseName);
       } catch (Exception e) {
        	e.printStackTrace();
    	}
       return conn;
    }
        
    public static Statement createStmt(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
    }
        
	public static PreparedStatement createPreStmt(Connection conn, String sql) {
		PreparedStatement pstm=null;
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstm;
	}
		
	public static ResultSet query(String sql,Statement stmt) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
        
	public static int update(String sql,Statement stmt) {
		int ret = 0;
		try {
			//System.out.println("SQL Statement: "+sql);
			ret = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ret;
	}
        
	public static void close(Connection conn,Statement stmt,PreparedStatement pstm, ResultSet rs)	{
		close(rs);
		close(pstm);
		close(stmt);
		close(conn);
	}
	private static void close(Connection conn) {
		if(conn != null) {
			try {
			        conn.close();
			} catch (SQLException e) {
			        e.printStackTrace();
			}
			conn = null;
		}
	}
        
	private static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
	}
	
	private static void close(PreparedStatement pstm) {
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstm = null;
		}
	}
        
	private static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
}