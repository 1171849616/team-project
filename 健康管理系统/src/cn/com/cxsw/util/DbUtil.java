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
	private String url="jdbc:mysql://localhost:3306/health";
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	//1、加载驱动 static只加载一次 类的字节码文件第一次加载到
	//内存的时候执行
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动出现异常....");
			e.printStackTrace();
		}
	}
	//2、获取连接
	private Connection getConn(){
		try {
			conn = DriverManager.getConnection(url, userName, userPsw);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//封装一个更新的方法（DML）
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
	//4、封装查询的方法
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
			//将结果集中的数据转存到数据结构中。
			while(rs.next()){
				//创建新的Map用来存储当前行
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
	//5、释放资源
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
