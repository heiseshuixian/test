package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����һ�������ࡣ ���ڰ�����ȡ���ݿ����ӣ��ͷ����ӡ�
 * ͨ��getInstance�������Ի��һ�������ʵ����synchronized�ؼ��ֱ�֤�˸÷������̰߳�ȫ��
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
	 * �������ݿ⣬��ȡ���ݿ����ӡ� ������ݿ������ڣ�������
	 */
	static {
		try {
			Class.forName("com.java.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:"
					+ "3306/test", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ�ܣ�");
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ�ܣ�");
		}
		try {
			stat = conn.createStatement();
			stat.execute("create database  if not exists "+DATABASE);
			stat.execute("use "+DATABASE);
			System.out.println("��ʼ������");
			stat.execute("create table if not exists user(uid varchar(20) primary key not null,pwd varchar(20) not null,ugroup tinyint  not null,uname varchar(20) not null,money decimal(11,2) default 0,phonenumber varchar(20),id varchar(20) default null)");
			stat.execute("create table if not exists flight(fid varchar(20) primary key,depature varchar(20) not null,destination varchar(20) not null,fnumber varchar(20)  not null,fname  varchar(20) not null,starttime varchar(20) not null,endtime varchar(20) not null,startdate varchar(20) not null,seat  int not null,price decimal(7,2) not null,version varchar(20),discount int default 10)");
			stat.execute("create table if not exists orders(oid bigint unsigned primary key auto_increment,ouid varchar(20) ,ouname varchar(20) ,ofid  varchar(20) ,oidcard varchar(20),ouseat int,oprice decimal (7,2) not null,ophonenumber varchar(20) default null,time  timestamp  default current_timestamp)");
			stat.execute("create table if not exists company(cid int primary key auto_increment,cname varchar(20))");
			stat.execute("create table if not exists place(aid int primary key auto_increment,place varchar(20))");
			stat.execute("create table if not exists record(ruid varchar(20),points decimal(11,2) )");
			stat.execute("insert into user value('0','123456789',1,'��������Ա',0.0,'1555155','340305')");
		} catch (SQLException e) {
			System.out.println("����ʧ�ܣ�");
		}
	}
	/**
	 * �õ������ʵ����
	 * @return instance �� ���ص���DBHelp��һ��ʵ����synchronized�ؼ��ֱ�֤�˸÷������̰߳�ȫ����
	 */
	public synchronized static DBHelp getIntance() {
		if (instance == null) {
			instance = new DBHelp();
		}
		return instance;
	}
	/**
	 * �õ���Ԥ�����õ����ݿ�����ӡ�
	 * @return Connection�������ӡ�
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
	 * ���Դ���ResultSet, Statement, Connection���󣬹رմ���Ķ���
	 * @param rs  ����һ��ResultSet���󣬲��رա�
	 * @param stat ����һ��Statement���󣬲��رա�
	 * @param conn ����һ��Connection���󣬲��رա�
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
