import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class WinRate {
	
	public String[] T1Champions;
	public String[] T2Champions;
	public ArrayList<String> T1Items;
	public ArrayList<String> T2Items;
	public int T1Kill;
	public int T2Kill;
	public int T1Gold;
	public int T2Gold;
	public int T1AvgLevel;
	public int T2AveLevel;

	public WinRate() {
		this.T1Champions = new String[5];
		this.T2Champions = new String[5];
		this.T1Items = new ArrayList<String>();
		this.T2Items = new ArrayList<String>();
	}
	
	public String calculateWinRate(Connection conn){
		String r = "";
		int T1ChampionWinRate = 0;
		int c1 = this.T1Items.size();
		int c2 = this.T2Items.size();
		for(String x : T1Champions){
			T1ChampionWinRate += getChampionWinRate(conn, x);
		}
		T1ChampionWinRate = T1ChampionWinRate/5;
		if(c1>0){
			int w = 0;
		for(int i = 0;i<c1;i++){
			w += getItemWinRate(conn, this.T1Items.get(i));
		}
			w = w/(c1*5);
			T1ChampionWinRate+=w;
		}
		
		int T2ChampionWinRate = 0;
		for(String x : T2Champions){
			T2ChampionWinRate += getChampionWinRate(conn, x);
		}
		T2ChampionWinRate = T2ChampionWinRate/5;
		if(c2>0){
			int w = 0;
		for(int i = 0;i<c2;i++){
			w += getItemWinRate(conn, this.T2Items.get(i));
		}
			w = w/(c2*5);
			T2ChampionWinRate+=w;
		};
		
		r = "Team1 win rate: "+T1ChampionWinRate+", Team2 win rate: "+T2ChampionWinRate+". ";
		if(T1ChampionWinRate>T2ChampionWinRate){
			r += "Team1 Win!";
		}else if(T1ChampionWinRate==T2ChampionWinRate){
			r += "Fair Match!";
		}else {
			r += "Team2 Win!";
		}
		return r;
	}
	
	public int getChampionWinRate(Connection conn, String s) {
		try {
			CallableStatement cstmt;
			cstmt = LeagueProcedure.createCallabel2(
					conn, LeagueProcedure.CHAMPION_WIN_RATE,
					s);
			return cstmt.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getItemWinRate(Connection conn, String s) {
		try {
			CallableStatement cstmt;
			cstmt = LeagueProcedure.createCallabel2(
					conn, LeagueProcedure.ITEM_WIN_RATE,
					s);
			return cstmt.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
