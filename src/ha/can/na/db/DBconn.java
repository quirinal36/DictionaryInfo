package ha.can.na.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import ha.can.na.bean.DictionaryInfo;

public class DBconn {
	Logger logger = Logger.getLogger(DBconn.class.getSimpleName());

	private String userName 	= "dev";
	private String password 	= "789gagul";
	private String dbms 		= "mysql";
	private String dbName 		= "new_schema";
	private String serverName 	= "35.234.23.104";
	private int portNumber 		= 3306;

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		if (this.dbms.equals("mysql")) {
			conn = DriverManager.getConnection(
					"jdbc:" + this.dbms + "://" +
							this.serverName +
							":" + this.portNumber + "/" +
							this.dbName + "?" +
							"useSSL=false",
							connectionProps);
		}
		return conn;
	}
}
