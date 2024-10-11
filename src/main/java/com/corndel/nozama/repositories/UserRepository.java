package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public static List<User> findAll() throws SQLException {
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users";

    try (var con = DB.getConnection();
        var stmt = con.createStatement();
        var rs = stmt.executeQuery(query); ) {

      var users = new ArrayList<User>();
      while (rs.next()) {
        var id = rs.getInt("id");
        var username = rs.getString("username");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");

        users.add(new User(id, username, firstName, lastName, email, avatar));
      }

      return users;
    }
  }

  public static User findById(int id) throws SQLException {
    // /** TODO: finish this method */
    // return null;

    // TODO: UNFINISH THIS METHOD
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users WHERE id = ?";

    try (var con = DB.getConnection();
        var stmt = con.prepareStatement(query)) {
      stmt.setInt(1, id);
      try (var rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        var username = rs.getString("username");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");

        return new User(id, username, firstName, lastName, email, avatar);
      }
    }
  }

  public static User create(String username) throws SQLException {
    var query = "INSERT INTO users (username, verified) VALUES (?, ?) RETURNING *";

    try (var con = DB.getConnection();
        var stmt = con.prepareStatement(query)) {
      stmt.setString(1, username);
      stmt.executeUpdate();
      try (var rs = stmt.getResultSet()) {
        if (!rs.next()) {
          return null;
        }
        var id = rs.getInt("id");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");

        return new User(id, username, firstName, lastName, email, avatar);
      }
    }
  }
}
