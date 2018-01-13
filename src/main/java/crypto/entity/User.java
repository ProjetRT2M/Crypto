package crypto.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import web.db.Entity;
import web.util.Util;

public class User extends Entity {
	private String login;
	private String password;
	private ArrayList<String> permissions;
	private Date regDate;
	private String ipAddress;
	private String email;
	private String apiKey;

	public String getLogin() {
		return login;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public String getRegDate() {
		return regDate.toString();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getEmail() {
		return email != null ? email : "";
	}

	public String getApiKey() {
		return apiKey != null ? apiKey : "";
	}

	@Override
	protected void init(ResultSet result) throws SQLException {
		id = result.getInt("id");
		login = result.getString("login");
		password = result.getString("password");
		permissions = Util.strList(result.getString("permissions").split(","));
		regDate = result.getDate("regDate");
		ipAddress = result.getString("ipAddress");
		email = result.getString("email");
		apiKey = result.getString("apiKey");
	}

	@Override
	protected HashMap<String, Object> getDataToSave() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("login", login);
		data.put("password", password);
		data.put("permissions", String.join(",", permissions));
		data.put("email", email);
		data.put("apiKey", apiKey);

		return data;
	}
}
