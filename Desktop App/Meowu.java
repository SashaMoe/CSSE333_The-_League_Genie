import java.sql.*;
import java.util.Scanner;


public class Meowu {
	
	/**
	 * The connection protocol for connections to the database server.
	 */
	private static final String CONNECTION_PROTOCOL = "jdbc:sqlserver";

	/**
	 * The URL of the server.
	 */
	private static final String SERVER = "titan.csse.rose-hulman.edu";

	/**
	 * The name of the copy of the Northwind database to manipulate.
	 */
	private static final String NORTHWIND = "The_League_Genie";

	/**
	 * The port to connect to the server on.
	 */
	private static final int PORT = 1433;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scanner input = new Scanner(System.in);
		String username = "chenz3";
		String pwd = "JVaQ)_(5^";
		try {
			testConnection(username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void testConnection(String username, String pwd) throws SQLException {
		String url = CONNECTION_PROTOCOL + "://" + SERVER + ":" + PORT + ";DatabaseName="
				+ NORTHWIND + ";SelectMethod=cursor;";
		Connection conn = DriverManager.getConnection(url, username, pwd);
		if (conn == null) {
			throw new SQLException("Null connection returned.");
		}
		System.out.println("Connection Successful!");
		String query = "SELECT * FROM Match";
//		PreparedStatement stmt = conn.prepareStatement(query);
//		stmt.execute();
//		ResultSet rs = stmt.getResultSet();
//		printTable(rs);
		executeStoredProcedure(conn);
		conn.close();
	}
	
	private static void executeStoredProcedure(Connection conn) throws SQLException {

		// Calls a stored procedure using parameters for the arguments and for
		// the result code.
		String query = "{call [get player items](?) }";
		CallableStatement cstmt = conn.prepareCall(query);
		// Replaces the question marks in the query string with parameters. Only
		// the second question mark is an input parameter; the others are output
		// parameters that we can read from after executing the procedure.
		// while avoid SQL injection attacks
		cstmt.setInt(1, 12345); // customerID, input parameter

		// Executes the stored procedure
		cstmt.execute();

		// Retrieves the return code and any output parameters.
		// It's best to do this after all result sets and update counts have
		// been retrieved.
		printTable(cstmt.getResultSet());

		// Cleanup
		cstmt.close();
		cstmt = null;
		conn.close();
		conn = null;
	}
	
	private static void printTable(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int c = rsmd.getColumnCount();
		int r = 0;
		for (int i = 1; i <= c; i++) {
			System.out.print(rsmd.getColumnLabel(i));
			if (i != c)
				System.out.print(" , ");
		}
		System.out.println("");
		while (rs.next()) {
			r++;
			System.out.print("Row: " + r + ": ");
			for (int i = 1; i <= c; i++) {
				System.out.print(rs.getString(i));
				
				Object columnObject = rs.getObject(i);
				
				if (i != c)
					System.out.print(" , ");
			}
			System.out.println("");
		}
	}
}
