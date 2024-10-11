package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import org.junit.jupiter.api.Test;

public class D3E2Tests {
  D3E2 d3e2 = new D3E2();
  D3E2.Account account = d3e2.new Account("legolas", "legolas@thefellowship.com", "elf4life");

  @Test
  public void accountThrowsBadRequestIfUsernameIsNull() {
    assertThatThrownBy(() -> account.updateUsername(null, "elf4life"))
        .isInstanceOf(BadRequestResponse.class);
  }

  @Test
  public void accountThrowsUnauthorizedIfPasswordIsNull() {
    assertThatThrownBy(() -> account.updateUsername("aragorn", null))
        .isInstanceOf(UnauthorizedResponse.class);
  }

  @Test
  public void accountThrowsForbiddenIfPasswordIsIncorrect() {
    assertThatThrownBy(() -> account.updateUsername("aragorn", "wrongPassword"))
        .isInstanceOf(ForbiddenResponse.class);
  }

  @Test
  public void accountUpdatesUsername() throws Exception {
    var newUsername = "aragorn";
    account.updateUsername(newUsername, "elf4life");
    assertThat(account.username).isEqualTo(newUsername);
  }
}
