package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 这是一个帮助类。 用于帮助获取数据库连接，释放连接。
 * 通过getInstance方法可以获得一个本类的实例，synchronized关键字保证了该方法的线程安全。
 * 
 * @author guokeWind
 * 
 */
public class DBHelp {
	private static DBHelp instance = null;
	private static Connection conn = null;
	private static Statement stat = null;
	private static final String DATABASE="video";
	private DBHelp() {
	}
	/**
	 * 连接数据库，获取数据库连接。 如果数据库或表不存在，则建立。
	 */
	static {
		try {
			Class.forName("com.java.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:"
					+ "3306/test", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败！");
		} catch (SQLException e) {
			System.out.println("连接数据库失败！");
		}
		try {
			stat = conn.createStatement();
			stat.execute("create database  if not exists "+DATABASE);
			stat.execute("use "+DATABASE);
			System.out.println("开始建表了");
			stat.execute("create table if not exists user(uid varchar(20) primary key not null,pwd varchar(20) not null,ugroup tinyint  not null,uname varchar(20) not null,money decimal(11,2) default 0,phonenumber varchar(20),id varchar(20) default null)");
			stat.execute("create table if not exists flight(fid varchar(20) primary key,depature varchar(20) not null,destination varchar(20) not null,fnumber varchar(20)  not null,fname  varchar(20) not null,starttime varchar(20) not null,endtime varchar(20) not null,startdate varchar(20) not null,seat  int not null,price decimal(7,2) not null,version varchar(20),discount int default 10)");
			stat.execute("create table if not exists orders(oid bigint unsigned primary key auto_increment,ouid varchar(20) ,ouname varchar(20) ,ofid  varchar(20) ,oidcard varchar(20),ouseat int,oprice decimal (7,2) not null,ophonenumber varchar(20) default null,time  timestamp  default current_timestamp)");
			stat.execute("create table if not exists company(cid int primary key auto_increment,cname varchar(20))");
			stat.execute("create table if not exists place(aid int primary key auto_increment,place varchar(20))");
			stat.execute("create table if not exists record(ruid varchar(20),points decimal(11,2) )");
			stat.execute("insert into user value('0','123456789',1,'超级管理员',0.0,'1555155','340305')");
		} catch (SQLException e) {
			System.out.println("建表失败！");
		}
	}
	/**
	 * 得到该类的实例。
	 * @return instance ， 返回的是DBHelp的一个实例，synchronized关键字保证了该方法的线程安全。。
	 */
	public synchronized static DBHelp getIntance() {
		if (instance == null) {
			instance = new DBHelp();
		}
		return instance;
	}
	/**
	 * 得到和预先设置的数据库的连接。
	 * @return Connection对象连接。
	 */
	public  Connection getConn(){
		Connection conn=null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:"
					+ "3306/"+DATABASE, "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 可以传入ResultSet, Statement, Connection对象，关闭传入的对象。
	 * @param rs  传入一个ResultSet对象，并关闭。
	 * @param stat 传入一个Statement对象，并关闭。
	 * @param conn 传入一个Connection对象，并关闭。
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		if(stat!=null){
				stat.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
		if(conn!=null){
				conn.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
