package ha.can.na.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ha.can.na.bean.DictionaryInfo;
import ha.can.na.db.DBconn;

public class DictionaryController {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	public List<DictionaryInfo> getDictionaryList(){
		DBconn dbconn = new DBconn();

		List<DictionaryInfo> list = new ArrayList<>();
		try(Connection conn = dbconn.getConnection()){
			final String sql = new StringBuilder().append("SELECT * FROM DictionaryInfo").toString();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DictionaryInfo info = DictionaryInfo.parseTo(rs);
				
				list.add(info);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public JSONArray listToJSONArray (List<DictionaryInfo> input) {
		JSONArray result = new JSONArray();
		Iterator<DictionaryInfo> iter = input.iterator();
		while(iter.hasNext()) {
			DictionaryInfo item = iter.next();
			try {
				JSONObject jsonObj = new JSONObject(item.toString());
				result.put(new JSONObject(jsonObj.toString()));
			}catch(JSONException e) {
				logger.info(item.toString());
				e.printStackTrace();
			}
		}
		return result;
	}
	public int updateDictionary(DictionaryInfo input) {
		int result = 0;
		DBconn dbconn = new DBconn();
		try(Connection conn = dbconn.getConnection()){
			String sql = new StringBuilder().append("UPDATE DictionaryInfo").append(" ")
					.append("SET title=?, content=?").append(" ")
					.append("WHERE id = ?").append(" ").toString();
			PreparedStatement stmt = conn.prepareStatement(sql);
			result = stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
