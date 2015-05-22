import java.sql.*;

public class LeagueConnection {
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
	
	public Connection conn;
	private String username;
	private String pwd;

	public LeagueConnection() {
		this.username = "333Spring2015League";
		this.pwd = "333Spring2015League";
		String url = CONNECTION_PROTOCOL + "://" + SERVER + ":" + PORT + ";DatabaseName="
				+ NORTHWIND + ";SelectMethod=cursor;";
		try {
			this.conn = createConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection createConnection(String url) throws SQLException {
		Connection conn = DriverManager.getConnection(url, username, pwd);
		if (conn == null) {
			throw new SQLException("Null connection returned.");
		}
		System.out.println("Connection Successful!");
		return conn;
	}
	
	public void closeConnection() throws SQLException {
		this.conn.close();
		this.conn = null;
	}
}
