package se.npet.microservices.messageconsumer.service;


import se.npet.microservices.messaging.model.TemperatureReadingMessage;

public interface TemperatureReadingsService {

  long storeTemperatureReading(TemperatureReadingMessage temperatureReadingMsg);
}
