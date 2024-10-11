package com.corndel.nozama;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DB {
  public static final String dbUrl = "jdbc:sqlite:nozama.db";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(dbUrl);
  }
}
