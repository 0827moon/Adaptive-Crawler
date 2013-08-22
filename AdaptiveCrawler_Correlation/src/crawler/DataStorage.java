package crawler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import crawler.util.DBUtil;
import crawler.util.Settings;

import twitter4j.HashtagEntity;
import twitter4j.Status;

public class DataStorage extends Thread{
	
	private static ArrayList<Status> statusList;
	
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstm;
	
	private static String useDatabase = "use "+Settings.databaseName;
	private static String sqlFormat = "insert into " +Settings.tableName+ "(pid, createdAt, " +
			"geoLocationLat, geoLocationLong, placeInfo, id, tweet, source, " +
			"lang, screenName, replyTo, rtCount, hashtags) "+
		"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String tableContent = "CREATE TABLE IF NOT EXISTS "+Settings.tableName+
			"(pid bigint(50) NOT NULL," +
			"createdAt text DEFAULT NULL, " +
			"geoLocationLat double NOT NULL," +
			"geoLocationLong double NOT NULL," +	
			"placeInfo text, "+
			"id bigint(50) NOT NULL, " +
			"tweet longtext CHARACTER SET utf8, " +
			"source text CHARACTER SET utf8, " +
			"lang text," +
			"screenName VARCHAR(150), "+
			"replyTo text, "+
			"rtCount bigint(50), "+
			"hashtags text, "+ 
			"PRIMARY KEY (pid)" +
			")DEFAULT CHARACTER SET utf8 COLLATE=utf8_unicode_ci;"; //upper and lower case are the same: COLLATE=utf8_unicode_ci;";
	

	public void initialTable() {
		conn=DBUtil.getConn();
		stmt=DBUtil.createStmt(conn);
		DBUtil.update(useDatabase, stmt);
		DBUtil.update(tableContent, stmt);
		DBUtil.close(conn, stmt, null, null);
	}

	public void run(){
		while(true) {
			TwitterCrawler.expire = TwitterCrawler.Timer.expire();
			if(TwitterCrawler.dataStream.getSize()>=Settings.batchSize || TwitterCrawler.expire){
				if(TwitterCrawler.expire){
					System.out.println("TIMER GOING OFF...");
					TwitterCrawler.Timer.restart();
				}
				System.out.print("STORING (BY THREAD) DATA TO DATABASE...");
				long begin = System.currentTimeMillis();
				
				try{
					conn=DBUtil.getConn();
					conn.setAutoCommit(false);
					stmt=DBUtil.createStmt(conn);
					DBUtil.update(useDatabase, stmt);
					pstm=DBUtil.createPreStmt(conn, sqlFormat);
					
					statusList = TwitterCrawler.dataStream.getList();
					System.out.print(statusList.size()+" items...");
					for (int i = 0; i < statusList.size();i++) {
						sqlStore(statusList.get(i));
					}		
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					pstm.executeBatch();
					conn.commit();
					DBUtil.close(conn, stmt, pstm, null);	
				} catch (SQLException e) {
					if(e.getErrorCode()!=1366)
						e.printStackTrace();
				}
					
				long end = System.currentTimeMillis();
				System.out.println("STORAGE FINISHED IN "+ (end-begin)/1000.00 +" SECS...");
			}
			if(TwitterCrawler.expire){
				TwitterCrawler.finishStore = true;
			}
			try {
				DataStorage.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void sqlStore(Status status) throws SQLException{
		long sql_pid = Settings.pid;
		Settings.pid++;
		
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss, z");
		String sqlCreateAt = tempDate.format(new java.util.Date(status.getCreatedAt().getTime()));

		double sqlGeoLocationLat = 0;
		double sqlGeoLocationLong = 0;
		
		if (status.getGeoLocation()!=null){
			sqlGeoLocationLat = status.getGeoLocation().getLatitude();
			sqlGeoLocationLong = status.getGeoLocation().getLongitude();
		}
		
		String sqlPlace = (status.getPlace()!= null ? status.getPlace().getFullName(): "");
		long sqlId = status.getId();
		String sqlTweet = status.getText().replace("'", "''");
		String sqlSource = status.getSource().replace("'", "''");
		sqlSource = sqlSource.replace("\\","\\\\");
		String sqlLang = status.getUser().getLang();	
		String sqlScreenName = status.getUser().getScreenName();			
		String sqlReplyTo = status.getInReplyToScreenName();		
		long sqlRtCount = status.getRetweetCount();
		
		HashtagEntity[] hashs = status.getHashtagEntities();
		String sqlHashtags = "";
		for (HashtagEntity hash: hashs) sqlHashtags+=hash.getText()+" "; 
		
		pstm.setLong(1, sql_pid);
		pstm.setString(2, sqlCreateAt);
		pstm.setDouble(3, sqlGeoLocationLat);
		pstm.setDouble(4, sqlGeoLocationLong);
		pstm.setString(5, sqlPlace);
		pstm.setLong(6, sqlId);
		pstm.setString(7, sqlTweet);
		pstm.setString(8, sqlSource);
		pstm.setString(9, sqlLang);
		pstm.setString(10, sqlScreenName);
		pstm.setString(11, sqlReplyTo);
		pstm.setLong(12, sqlRtCount);
		pstm.setString(13, sqlHashtags);

		pstm.addBatch();
	}
}
