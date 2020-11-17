package se.npet.microservices.messageproducer.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import se.npet.microservices.messageproducer.rest.model.TemperatureReadingDto;
import se.npet.microservices.messageproducer.service.TemperatureReadingService;
import se.npet.microservices.messageproducer.service.model.TemperatureReadingMessage;
import se.npet.microservices.messageproducer.service.model.TemperatureReadingMessageCreator;

@RestController()
public class TemperatureReadingController {

  private final TemperatureReadingService temperatureReadingService;

  public TemperatureReadingController(TemperatureReadingService temperatureReadingService) {
    this.temperatureReadingService = temperatureReadingService;
  }

  @PostMapping(path = "temperature")
  public void receiveTemperatureReading(TemperatureReadingDto temperatureReadingDto) {
    temperatureReadingService.sendTemperatureReadingMessage(fromDto(temperatureReadingDto));
  }

  private static TemperatureReadingMessage fromDto(TemperatureReadingDto temperatureReadingDto) {
    return TemperatureReadingMessageCreator.newMessage(temperatureReadingDto.getSensorId(), temperatureReadingDto.getTemperature(),
        temperatureReadingDto.getMeasurementTimeStamp());
  }

}
