package se.npet.microservices.messageproducer.rest;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.npet.microservices.messageproducer.rest.model.TemperatureConverter;
import se.npet.microservices.messageproducer.rest.model.TemperatureReadingDto;
import se.npet.microservices.messageproducer.service.TemperatureReadingService;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;
import se.npet.microservices.messaging.model.TemperatureReadingMessageCreator;

@RestController()
public class TemperatureReadingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureReadingController.class);

  private final TemperatureReadingService temperatureReadingService;

  public TemperatureReadingController(TemperatureReadingService temperatureReadingService) {
    this.temperatureReadingService = temperatureReadingService;
  }

  @PostMapping(path = "temperature")
  public void receiveTemperatureReading(@RequestBody TemperatureReadingDto temperatureReadingDto) {
    LOGGER.info("Receiving temp reading <{}>", temperatureReadingDto);
    temperatureReadingService.sendTemperatureReadingMessage(fromDto(temperatureReadingDto));
  }

  private static TemperatureReadingMessage fromDto(TemperatureReadingDto temperatureReadingDto) {

    BigDecimal temperatureInCelsius = TemperatureConverter.convert(temperatureReadingDto.getTemperature(), temperatureReadingDto.getScale());

    return TemperatureReadingMessageCreator.newMessage(temperatureReadingDto.getSensorId(), temperatureInCelsius,
        temperatureReadingDto.getMeasurementTimeStamp());
  }

}
