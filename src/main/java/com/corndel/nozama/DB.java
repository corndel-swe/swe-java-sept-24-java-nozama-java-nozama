package com.corndel.nozama;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  /** TODO: Set the dbUrl */
  public static final String dbUrl = Dotenv.load().get("DB_URL");

  public static Connection getConnection() throws SQLException {
    /** TODO: finish this method */
      assert dbUrl != null;
      System.out.println(dbUrl);
      return DriverManager.getConnection(dbUrl);
  }
}
