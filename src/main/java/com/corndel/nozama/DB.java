package com.corndel.nozama;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  /** TODO: Set the dbUrl */
  public static final Dotenv dotenv = Dotenv.load();
  public static final String dbUrl = dotenv.get("DB_URL");

  public static Connection getConnection() throws SQLException {
    /** TODO: finish this method */
    return DriverManager.getConnection(dbUrl);
  }
}
