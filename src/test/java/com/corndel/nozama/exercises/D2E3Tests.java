package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import java.util.List;

import org.junit.jupiter.api.Test;

public class D2E3Tests {
  Javalin app = D2E3.createApp();
  List<Alarm> alarms = AlarmRepository.findAll();

  @Test
  public void GET_alarms_returns_all_alarms() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var alarmsJson = new JavalinJackson().toJsonString(alarms, List.class);
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
          var id = 0;
          var alarmJson = new JavalinJackson().toJsonString(AlarmRepository.findById(id), Alarm.class);
          var response = client.get("/alarms/" + Integer.toString(id));
          assertThat(response.code()).isEqualTo(200);
          assertThat(response.body().string()).isEqualTo(alarmJson);
        });
  }

  @Test
  public void POST_alarms_adds_alarm() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var before = AlarmRepository.findAll();
          var alarm = new Alarm("13:37", "Have lunch");
          var alarmJson = new JavalinJackson().toJsonString(alarm, Alarm.class);
          var response = client.post("/alarms", alarm);
          var after = AlarmRepository.findAll();

          assertThat(response.code()).isEqualTo(201);
          assertThat(response.body().string()).isEqualTo(alarmJson);
          assertThat(after.size()).isEqualTo(before.size() + 1);

        });
  }
}
