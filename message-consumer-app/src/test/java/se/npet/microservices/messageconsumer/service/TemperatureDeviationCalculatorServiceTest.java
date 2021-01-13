package se.npet.microservices.messageconsumer.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import se.npet.microservices.messageconsumer.service.model.Deviation;

class TemperatureDeviationCalculatorServiceTest {

  private TemperatureDeviationCalculatorImpl deviationCalculator = new TemperatureDeviationCalculatorImpl();

  @Test
  void calculateDeviation_HIGH() {
    assertEquals(Deviation.HIGH, deviationCalculator.calculateDeviation(BigDecimal.valueOf(20.0), 10.0, 5.0));
  }

  @Test
  void calculateDeviation_LOW() {
    assertEquals(Deviation.LOW, deviationCalculator.calculateDeviation(BigDecimal.valueOf(5.0), 10.0, 5.0));
  }

  @Test
  void calculateDeviation_NORMAL() {
    assertEquals(Deviation.NORMAL, deviationCalculator.calculateDeviation(BigDecimal.valueOf(12.0), 10.0, 5.0));
  }
}
