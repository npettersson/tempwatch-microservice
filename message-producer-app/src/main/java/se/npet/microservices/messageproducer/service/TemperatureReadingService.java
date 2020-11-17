package se.npet.microservices.messageproducer.service;

import se.npet.microservices.messaging.model.TemperatureReadingMessage;

public interface TemperatureReadingService {

  void sendTemperatureReadingMessage(TemperatureReadingMessage temperatureReadingMessage);
}
