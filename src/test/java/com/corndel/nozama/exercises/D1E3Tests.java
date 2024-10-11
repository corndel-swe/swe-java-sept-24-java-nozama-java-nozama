package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import com.corndel.nozama.repositories.UserRepository;

public class D1E3Tests {

  @Test
  public void ReturnsAUser() throws SQLException {
    assertThat(UserRepository.findById(21)).isNotNull();
  }

  @Test
  public void FindsUserWithCorrectId() throws SQLException {
    assertThat(UserRepository.findById(21).username).isEqualTo("Earl.Pollich76");
  }
}
