package cn.com.cxsw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
	private String userName="root";
	private String userPsw="root";
	private String url="jdbc:mysql://localhost:3306/new";
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	//1���������� staticֻ����һ�� ����ֽ����ļ���һ�μ��ص�
	//�ڴ��ʱ��ִ��
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("�������������쳣....");
			e.printStackTrace();
		}
	}
	//2����ȡ����
	private Connection getConn(){
		try {
			conn = DriverManager.getConnection(url, userName, userPsw);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//��װһ�����µķ�����DML��
	public int update(String sql,Object... arr){
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<arr.length;i++){
				ps.setObject(i+1, arr[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		return -1;
	}
	//4����װ��ѯ�ķ���
	public List<Map<String,Object>> query(String sql,Object... arr){
		List<Map<String,Object>> list=new ArrayList<>();
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<arr.length;i++){
				ps.setObject(i+1, arr[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			//��������е�����ת�浽���ݽṹ�С�
			while(rs.next()){
				//�����µ�Map�����洢��ǰ��
				Map<String,Object> map=new HashMap<>();
				for(int i=1;i<=rsmd.getColumnCount();i++){
					String key=rsmd.getColumnName(i);
					Object value=rs.getObject(key);
					map.put(key, value);
				}
				list.add(map);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release();
		}
		return null;
	}
	//5���ͷ���Դ
	private void release(){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
