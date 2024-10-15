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
         var rs = stmt.executeQuery(query);) {

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
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users WHERE id = ?";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {

      stmt.setInt(1, id);
      try (var rs = stmt.executeQuery()) {
        if (rs.next()) {
          var username = rs.getString("username");
          var firstName = rs.getString("firstName");
          var lastName = rs.getString("lastName");
          var email = rs.getString("email");
          var avatar = rs.getString("avatar");

          return new User(id, username, firstName, lastName, email, avatar);
        } else {
          return null;
        }
      }
    }
  }

  public static User logUserIn(int id, String password) throws SQLException, Exception {
    // Check if user is null

    var query = "SELECT id, username, firstName, lastName, email, avatar, password FROM users WHERE id = ?";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {

      stmt.setInt(1, id);
      try (var rs = stmt.executeQuery()) {
        if (rs.next()) {
          var usernameDB = rs.getString("username");
          var firstName = rs.getString("firstName");
          var lastName = rs.getString("lastName");
          var email = rs.getString("email");
          var avatar = rs.getString("avatar");
          var passwordDB = rs.getString("password");
          System.out.println(passwordDB);

          if (passwordDB.equals(password)) {
            System.out.println("password are a match, Logging in");
            return new User(id, usernameDB, firstName, lastName, email, avatar);
          }
          else {
            throw new Exception("password are not a match, cant log in");
        }
        }
      }
    }
      System.out.println("No user found to log in");
      return null;
  }
}
