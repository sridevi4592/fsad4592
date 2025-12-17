package com.klu;
import java.sql.*;

public class JDBCCreate {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/fsads3";
		String usr="root";
		String pwd="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,usr,pwd);
			System.out.println("Connection Established");
			String query="create table if not exists Student("+ "id int primary key auto_increment,"+" name varchar(20)"+")";
			Statement st=con.createStatement();
			st.execute(query);
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
