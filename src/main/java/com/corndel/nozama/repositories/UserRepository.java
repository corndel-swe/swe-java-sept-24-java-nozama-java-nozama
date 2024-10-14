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
    // make the query
    var query = "SELECT id, username, firstName, lastName, email, avatar\n";
    query += "FROM users \n";
    query += ("WHERE id = ?");

    // try with resources - get connection
    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query);) {

      // sub in the id argument
      stmt.setInt(1, id);

      // try with resources - execute query
      try (var rs = stmt.executeQuery();){

        if(rs.next()){
          var username = rs.getString("username");
          var firstName = rs.getString("firstName");
          var lastName = rs.getString("lastName");
          var email = rs.getString("email");
          var avatar = rs.getString("avatar");

          User user = new User(id, username, firstName, lastName, email, avatar);
          return user;
        }else{
          System.out.println("not a valid id.");
          return null;
        }
      }
    }
  }
}

