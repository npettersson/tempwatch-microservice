package se.npet.microservices.messageconsumer.service;

import org.springframework.stereotype.Component;
import se.npet.microservices.messageconsumer.service.model.Deviation;

@Component
public class TemperatureDeviationCalculatorImpl implements TemperatureDeviationCalculator {

  @Override
  public Deviation calculateDeviation(double temperatureValue, double threshold, double allowedDeviation) {
    if (temperatureValue >= (threshold + allowedDeviation)) {
      return Deviation.HIGH;
    }

    if (temperatureValue <= (threshold - allowedDeviation)) {
      return Deviation.LOW;
    }

    return Deviation.NORMAL;
  }

}
