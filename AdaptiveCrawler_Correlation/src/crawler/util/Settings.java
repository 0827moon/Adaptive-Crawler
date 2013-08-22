package crawler.util;

import java.util.TreeMap;

public class Settings {
	public static String tableName;
	public static String tempTable = "NEWtable";
	public static String databaseName = "tridec01";
	public static final String HOSTNAME = "galaxyvm-19.eecs.qmul.ac.uk";
	public static final String PWD = "tweetdata";
	public static final String USER = "tweetdata";
	
	/*public static String databaseName = "tridec01";
	public static final String HOSTNAME = "quark.eecs.qmul.ac.uk";
	public static final String PWD = "98aw3879rg234";
	public static final String USER = "tridec01";*/
	
	/*public static String databaseName = "twitter";
	public static final String HOSTNAME = "127.0.0.1";
	public static final String PWD = "1234";
	public static final String USER = "root";*/
	public static final int keywordMax = 400;
	public static final int batchSize = 3000;
	public static int minNewFreq = 5;
	public static long timer = 10*60*1000;
	public static long sample = 60*1000+1;
	public static long pid = 1;
		
	//public static int minAveFreq = 5;
	//public static long discTimer = 60*60*1000;
	
	
	public static String[] baseKeywords = {"nba final","#nbafinal","#game7"};
	public static String[] blackList = {"#bbc", "#bbc2013", "#cnn", "#news", "#twitter", "#socialmedia", "#rt","#teamfollowback","#follow"};
	public static String[] queryKeywords;
	
	public static TreeMap<String,Integer> KeywordsFreq = new TreeMap<String,Integer>();	//all keywords accumulative freq
	public static TreeMap<String,Integer> TFHashtagFreq = new TreeMap<String,Integer>();	//time frame hashtags freq
	public static TreeMap<String,Integer> TFHashtagFreq_last = new TreeMap<String,Integer>();	//last time frame hashtags freq
	public static TreeMap<String,double[]> TFHashtagFreq_sample = new TreeMap<String,double[]>(); //sample results
	public static TreeMap<String,double[]> TFHashtagFreq_sample_last = new TreeMap<String,double[]>(); //last time frame sample results
	
	/*//Keys xintestyue
	public final static String ConsumerKey = "KzlDU5smHYYumN2ptFFAA";
	public final static String ConsumerSecret = "oMCYoK27t2VOL1MrXJXqZeZZ9dgkP1tNU8GDCPG2yjs";
	public final static String AccessToken = "726171218-vEtfy9FCOSFecdIMP0wfHB429nCJjQFuCrcF3hrd";
	public final static String AccessSecret = "hXVQzv3xCrLxILj91JjtXNrC0DjQ5wyOh9cqIO1x5nw";
	
	//Keys wufeng018
	public final static String ConsumerKey = "TaXNRxNmvw9pl1Z0tqzslQ";
	public final static String ConsumerSecret = "liFFevq064PvX0DYlpARvE0sPrZRGfTWdEBaY8pPxoU";
	public final static String AccessToken = "390850963-6WtXkNec2kprbzQxP3E4oj978oPh9wUo3KYoxyTn";
	public final static String AccessSecret = "dHBcDhHTFpcgwS0up3jfiPcqhbTc1p4CsYqzNBwpo";*/
		
	//Keys TestXinyue
	public final static String ConsumerKey = "YLzybEJPsAl2JrivNkbC8w";
	public final static String ConsumerSecret = "ED9SQF6acj2t1zTlBzDj4Y6BceMzLTa6gnS14KUh7X0";
	public final static String AccessToken = "554737626-Wawl1IymkNLzIMjq2tySwcCgIMzklcmQGn2myByV";
	public final static String AccessSecret = "lWogMLoq9qjzynsfDelkatY3kWuMxJ71xWl2cwUAc";
	
	/*//Keys XinyueTest
	public final static String ConsumerKey = "eOLsydrnteK2780xLv7Dw";
	public final static String ConsumerSecret = "kwJ5hbkhwgvpK5xXfyKkcjZ6uGg7ziPtI3Fdk";
	public final static String AccessToken = "491357478-HDIbEIDzO0NOQRdCfOjKurCijBAGoYQ63HR0BwCI";
	public final static String AccessSecret = "1KHlSdixqQINxIwhxEYTqXu6jEwYQlCgk6AZEDBFo";

	
	//Keys XinyueTest
	public final static String ConsumerKey = "j71iBUSyoFoLQiPgI7rl7A";
	public final static String ConsumerSecret = "mOQXZDXbhi1XOGLwgEvj2stLZWguDtwYgkRXeMk";
	public final static String AccessToken = "491357478-RA0XHpPMeseyagBWJ8Wp5O4n63QhilUyvGYNtGff";
	public final static String AccessSecret = "7Qfp1BZvPBdqqKeoj2mFnp5wSJ12vaFiNUdUa2XlXbM";
	
	//Keys XinyueTest
	/*public final static String ConsumerKey = "suWZhDTLQWS9W9Z02IxA";
	public final static String ConsumerSecret = "Ss9eZu3X42Ce9M6Dy8XgWac82Tb9e9DqHX7Hb5VFMZ8";
	public final static String AccessToken = "491357478-PKrhsWeLdu4UUB2Cy7yfK5Nj9VkQHWtegN4xyCI";
	public final static String AccessSecret = "FBzx3jnmxsYsTDlNmASy6RMvSYMN5xfMWbivYfNg6I";*/
	
	//Keys 0827moon
	/*public final static String ConsumerKey = "OwFARM8oTsdVWD7X0D1Q";
	public final static String ConsumerSecret = "BS43hqATtA9HhI0WePLU5HFJfWxeqfpn5HCr5mtKEo";
	public final static String AccessToken = "424600126-rwh7wMBlF8UPxGnBS4KnQOCKu7JI7d77tnGvZBI1";
	public final static String AccessSecret = "Dy9GiamK5YB6NnEo8tinCi5wmMQ7MR0MAsKBxHmYN8";*/
		
	//Laurissa
	/*public final static String ConsumerKey = "OcqAhLc9EtYQ9wJR45FrHg";
	public final static String ConsumerSecret = "GxWjJcxQ8wbaKU8HLAgr8qUlJ92gNeM1STvUFgyk";
	public final static String AccessToken = "15098575-RnS7Sq5iaKaTekxrJSJuuyegbtWrEGuxMlzxMxnRI";
	public final static String AccessSecret = "j1mKjJvVNr2ePN9kNxZ2kz8b57DfPmyL131CenMypg";*/
}
