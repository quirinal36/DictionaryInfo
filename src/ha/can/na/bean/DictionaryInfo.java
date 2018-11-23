package ha.can.na.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DictionaryInfo {
	int id;
	String title;
	String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static DictionaryInfo parseTo(ResultSet rs) throws SQLException {
		DictionaryInfo result = new DictionaryInfo();
		result.setId(rs.getInt("id"));
		result.setTitle(rs.getString("title").replaceAll("\\r", "").replaceAll("\\n", ""));
		result.setContent(rs.getString("content").replaceAll("\\r", "").replaceAll("\\n", " ").replaceAll("\"", "\\\'"));
		return result;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
