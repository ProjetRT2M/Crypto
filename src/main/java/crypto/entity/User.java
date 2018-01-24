package crypto.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.codec.digest.DigestUtils;
import web.db.Entity;
import web.db.SelectQuery;
import web.util.EntityException;

public class User extends Entity {
	private static final List<String> AUTHORIZED_PERMISSIONS = Arrays.asList("member", "modo", "admin");

	private String login;
	private String password;
	private List<String> permissions;
	private String regDate;
	private String ipAddress;
	private String email;
	private String apiKey;
	private String apiSecret;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public String getRegDate() {
		return regDate;
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

	public String getApiSecret() {
		return apiSecret != null ? apiSecret : "";
	}

	public void setLogin(String login) throws EntityException {
		if (login.length() < 3 || login.length() > 20) {
			throw new EntityException(t.t("login.size"));
		}

		int nbLogin = new SelectQuery<>(User.class)
			.addCondition("login", "=", login)
			.count();

		if (nbLogin > 0) {
			throw new EntityException(t.t("login.unavailable", login));
		}

		this.login = login;
	}

	public void setPassword(String password) throws EntityException {
		if (password.length() < 6 || password.length() > 30) {
			throw new EntityException(t.t("password.size"));
		}

		this.password = DigestUtils.sha256Hex(password);
	}

	public void setPermissions(List<String> permissions) throws EntityException {
		for (String permission : permissions) {
			if (!AUTHORIZED_PERMISSIONS.contains(permission)) {
				throw new EntityException(t.t("permission.invalid", permission));
			}
		}

		this.permissions = permissions;
	}

	public void setEmail(String email) throws EntityException {
		if (email == null || email.isEmpty()) {
			this.email = null;
		} else {
			try {
				InternetAddress emailAddress = new InternetAddress(email);
				emailAddress.validate();
				this.email = email;
			} catch (AddressException e) {
				throw new EntityException("\"" + email + "\" : " + t.t("email.invalid"));
			}
		}
	}

	public void setApiKey(String apiKey) {
		if (apiKey == null || apiKey.isEmpty()) {
			this.apiKey = null;
		} else {
			this.apiKey = apiKey;
		}
	}

	public void setApiSecret(String apiSecret) {
		if (apiSecret == null || apiSecret.isEmpty()) {
			this.apiSecret = null;
		} else {
			this.apiSecret = apiSecret;
		}
	}

	@Override
	public void save() {
		if (id == 0) {
			regDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
			ipAddress = app.getRequest().getIpAddress();
		}

		dataToSave.put("login", login);
		dataToSave.put("password", password);
		dataToSave.put("permissions", String.join(",", permissions));
		dataToSave.put("regDate", regDate);
		dataToSave.put("ipAddress", ipAddress);
		dataToSave.put("email", email);
		dataToSave.put("apiKey", apiKey);
		dataToSave.put("apiSecret", apiSecret);
		super.save();
	}

	@Override
	protected void init(ResultSet result) throws SQLException {
		id = result.getInt("id");
		login = result.getString("login");
		password = result.getString("password");
		permissions = Arrays.asList(result.getString("permissions").split(","));
		regDate = result.getString("regDate");
		ipAddress = result.getString("ipAddress");
		email = result.getString("email");
		apiKey = result.getString("apiKey");
		apiSecret = result.getString("apiSecret");
	}
}
