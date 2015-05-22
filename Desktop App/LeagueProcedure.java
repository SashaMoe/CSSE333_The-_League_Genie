import java.sql.*;

public class LeagueProcedure {
	
	public static final String GET_PLAYER_ITEMS = "{call [get player items](?) }";
	public static final String GET_AVG_USER_LEVEL = "{call [get avg user level](?) }";
	public static final String GET_POST_GAME_STATS = "{call [get post-game stats](?) }";
	public static final String GET_MATCHES_OF_CHAMPION = "{call [get matches of champion](?) }";
	public static final String GENERATE_GAME = "{call [Generate Game](?"+QuestionMarks125()+") }";
	public static final String AUTHENTICATE_ADMIN =  "{? = call [Authenticate Admin](?) }";
	public static final String CHAMPION_WIN_RATE = "{? = call [Champion win rate](?) }";
	public static final String ITEM_WIN_RATE = "{? = call [Item win rate](?) }";
	
	public static String QuestionMarks125(){
		String s = "";
		for (int i = 0; i<125;i++){
			s += ", ?";
		}
		return s;
	}
	
	public static CallableStatement createCallabel(Connection conn, String query, int p) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setInt(1, p);
		cstmt.execute();
		return cstmt;
	}
	
	public static CallableStatement createCallabel(Connection conn, String query) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.execute();
		return cstmt;
	}
	
	public static CallableStatement createCallabel2(Connection conn, String query, String s) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.registerOutParameter(1, Types.INTEGER);
		cstmt.setString(2, s);
		cstmt.execute();
		return cstmt;
	}
	
	public static CallableStatement createCallabel(Connection conn, String query, String s) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(query);
		cstmt.setString(1, s);
		cstmt.execute();
		return cstmt;
	}
	
	public static CallableStatement createCallabel(Connection conn, String query, Values[] vs) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(query);
		for (int i=0;i<vs.length;i++) {
			if(vs[i].stringInputs!=null){
				cstmt.setString(i+1, vs[i].stringInputs);
			}else{
				cstmt.setInt(i+1, vs[i].intInputs);
			}
		}
		cstmt.execute();
		return cstmt;
	}

}
