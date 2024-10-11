package com.corndel.nozama.exercises;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class D1E1Tests {

  static final String dbUrl = "jdbc:sqlite:nozama.db";

  @Test
  public void DbExists() {
    var file = new File("nozama.db");
    assert file.exists();
  }

  @Test
  public void UserTableExists() {
    try (var connection = DriverManager.getConnection(dbUrl)) {
      var metadata = connection.getMetaData();
      var resultSet = metadata.getTables(null, null, "users", new String[] { "TABLE" });
      assert resultSet.next();
    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  public void UserTableHasSeedData() {
    try (var connection = DriverManager.getConnection(dbUrl)) {
      var statement = connection.createStatement();
      var resultSet = statement.executeQuery("SELECT * FROM users");
      assert resultSet.next();
    } catch (Exception e) {
      fail(e);
    }

  }

}
