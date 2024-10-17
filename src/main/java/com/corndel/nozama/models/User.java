package com.corndel.nozama.models;

public class User {
  private Integer id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String avatar;
  private String password;
  public User(Integer id, String username, String firstName, String lastName, String email, String avatar , String password) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.avatar = avatar;
    this.password =password;
  }

  public User(int id) {
  }
  public User() {
  }

  public User(int id, String username, String firstName, String lastName, String email, String avatar) {
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
