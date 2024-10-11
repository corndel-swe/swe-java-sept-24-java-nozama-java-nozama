package com.corndel.nozama.models;

public class User {
  private Integer id;
  public String username;
  public String firstName;
  public String lastName;
  public String email;
  public String avatar;

  public User(Integer id, String username, String firstName, String lastName, String email, String avatar) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.avatar = avatar;
  }

  public Integer getId() {
    return id;
  }
}
