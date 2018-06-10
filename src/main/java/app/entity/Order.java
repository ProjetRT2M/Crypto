package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import web.db.Entity;
import web.util.EntityException;

public class Order extends Entity {
  private int userId;
  private String currency;
  private String currencyLong;
  private float percentage;
  private String status;

  public String getCurrency() {
    return currency;
  }

  public String getCurrencyLong() {
    return currencyLong;
  }

  public float getPercentage() {
    return percentage;
  }

  public String getStatus() {
    return status;
  }

  public void setUser(int userId) {
    this.userId = userId;
  }

  public void setCurrency(String currency) throws EntityException {
    if (currency.length() < 1) {
      throw new EntityException("Veuillez renseigner une monnaie");
    }

    this.currency = currency;
  }

  public void setCurrencyLong(String currencyLong) throws EntityException {
    if (currencyLong.length() < 1) {
      throw new EntityException("Veuillez renseigner une monnaie");
    }

    this.currencyLong = currencyLong;
  }

  public void setPercentage(float percentage) throws EntityException {
    if (percentage <= 0) {
      throw new EntityException("Veuillez renseigner un pourcentage positif");
    }

    this.percentage = percentage;
  }

  public void setPercentage(String percentage) throws EntityException {
    try {
      setPercentage(Float.parseFloat(percentage));
    } catch (NumberFormatException e) {
      throw new EntityException("Veuillez renseigner un pourcentage positif");
    }
  }

  @Override
  public void save() {
    if (id == 0) {
      status = "waiting";
    }

    dataToSave.put("userId", userId);
    dataToSave.put("currency", currency);
    dataToSave.put("currencyLong", currencyLong);
    dataToSave.put("percentage", percentage);
    dataToSave.put("status", status);

    super.save();
  }

  @Override
  protected void init(ResultSet result) throws SQLException {
    userId = result.getInt("userId");
    currency = result.getString("currency");
    currencyLong = result.getString("currencyLong");
    percentage = result.getFloat("percentage");
    status = result.getString("status");
  }
}
