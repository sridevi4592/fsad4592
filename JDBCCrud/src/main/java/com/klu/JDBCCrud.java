package com.klu;
import java.sql.*;


public class JDBCCrud {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/fsads3";
		String usr="root";
		String pwd="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, usr, pwd);
			System.out.println("Database Connection Established");
			Statement st=con.createStatement();
			String createDept="create table if not exists Dept("+
			                "dept_id int primary key auto_increment,"+
					        "dept_name varchar(20)"+")";
			st.execute(createDept);
			System.out.println(" Department table created");
			st.execute("CREATE TABLE IF NOT EXISTS Emp (\r\n"
					+ "    emp_id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "    emp_name VARCHAR(50),\r\n"
					+ "    sal DOUBLE,\r\n"
					+ "    dept_id INT,\r\n"
					+ "    FOREIGN KEY (dept_id) REFERENCES Dept(dept_id)\r\n"
					+ ");");
			System.out.println(" Employee table created");
			st.executeUpdate("insert into dept values(101,'CSE')");
			System.out.println("record have been inserted successfully into dept table");
			st.executeUpdate("insert into emp values(4592,'Sridevi',25000,101)");
			System.out.println("record have been inserted successfully into employee table");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
