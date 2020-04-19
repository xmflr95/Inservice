package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DBInit extends HttpServlet {
	private static Connection connection = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		String className = "com.mysql.jdbc.Driver";
		try {
			Class.forName(className);
			System.out.println("JDBC Driver loading complete!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initConnectionPool() {
		String url = this.getInitParameter("url");
		String user = this.getInitParameter("user");
		String password = this.getInitParameter("password");
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("create Connection success");		
		} catch (SQLException e) {
			System.out.println("create Connection error");
			e.printStackTrace();
		}
	}

}
