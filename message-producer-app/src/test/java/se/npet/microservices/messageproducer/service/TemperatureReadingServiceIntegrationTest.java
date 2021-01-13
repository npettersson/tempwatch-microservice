package se.npet.microservices.messageproducer.service;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.awaitility.Awaitility.await;

import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import se.npet.microservices.messageproducer.rest.model.TemperatureReadingDto;
import se.npet.microservices.messaging.Destinations;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;

@SpringBootTest(webEnvironment= WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
public class TemperatureReadingServiceIntegrationTest {

  @Autowired
  private JmsTemplate jmsTemplate;

  @Test
  public void testReceiveTemperatureReadingMessageAndPutItOnQueue() {

    Map<String, Object> tempDataMap = new HashMap<>();
    tempDataMap.put("sensorId", "sensor_1");
    tempDataMap.put("temperature", 15.0);
    tempDataMap.put("scale", "C");
    tempDataMap.put("measurementTimeStamp", "2020-11-24T10:11:48");

    given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(tempDataMap)
        .when()
        .post("http://localhost:8080/temperature")
        .then()
        .statusCode(200);

    await().atMost(1, TimeUnit.SECONDS).with()
        .pollInterval(100, TimeUnit.MILLISECONDS).un
        .until(this::receiveTemperatureReadingMessageFromQueue);
  }

  private boolean receiveTemperatureReadingMessageFromQueue() {
    TemperatureReadingMessage temperatureReadingMessage = (TemperatureReadingMessage) jmsTemplate.receiveAndConvert(Destinations.TEMPERATURE_READINGS_QUEUE);
    return (temperatureReadingMessage != null);
  }

}
