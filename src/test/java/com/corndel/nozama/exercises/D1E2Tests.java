package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.corndel.nozama.DB;

public class D1E2Tests {

  @Test
  public void DbUrlIsSet() {
    assertThat(DB.dbUrl)
        .isNotNull()
        .isNotBlank();
  }

  @Test
  public void CanGetConnection() {
    try (var connection = DB.getConnection()) {
      assertThat(connection).isNotNull();
    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  public void CanGetUserTable() {
    try (var connection = DB.getConnection()) {
      var metadata = connection.getMetaData();
      var resultSet = metadata.getTables(null, null, "users", new String[] { "TABLE" });
      assert resultSet.next();
    } catch (Exception e) {
      fail(e);
    }
  }

}
