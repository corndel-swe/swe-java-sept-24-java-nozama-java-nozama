package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class D2E3Tests {
  D2E3 d2e3 = new D2E3();
  Javalin app = d2e3.javalinApp();
  ArrayList<Alarm> alarms = d2e3.getAlarms();

  @Test
  public void GET_alarms_returns_all_alarms() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var alarmsJson = new JavalinJackson().toJsonString(alarms, ArrayList.class);
          var response = client.get("/alarms");
          assertThat(response.code()).isEqualTo(200);
          assertThat(response.body().string()).isEqualTo(alarmsJson);
        });
  }

  @Test
  public void GET_alarms_index_returns_specifed_alarm() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var index = 0;
          var alarmJson = new JavalinJackson().toJsonString(alarms.get(index), Alarm.class);
          var response = client.get("/alarms/" + Integer.toString(index));
          assertThat(response.code()).isEqualTo(200);
          assertThat(response.body().string()).isEqualTo(alarmJson);
        });
  }

  @Test
  public void POST_alarms_adds_alarm() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var alarm = new Alarm("13:37", "Have lunch");
          var alarmJson = new JavalinJackson().toJsonString(alarm, Alarm.class);
          var response = client.post("/alarms", alarm);

          assertThat(response.code()).isEqualTo(201);
          assertThat(response.body().string()).isEqualTo(alarmJson);
          assertThat(alarms.getLast()).usingRecursiveComparison().isEqualTo(alarm);
        });
  }
}
