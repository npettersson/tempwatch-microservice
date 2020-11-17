package se.npet.microservices.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = AbstractDbAndMessagingIntegrationTest.Initializer.class)
public abstract class AbstractDbAndMessagingIntegrationTest {

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String ACTIVEMQ_IMAGE = "rmohr/activemq:latest";

    static MySQLContainer<?> mySqlContainer = new MySQLContainer();

    static GenericContainer<?> activeMQContainer = new GenericContainer<>(ACTIVEMQ_IMAGE)
        .withExposedPorts(61616);

    private static void startContainers() {
      Startables.deepStart(Stream.of(mySqlContainer, activeMQContainer)).join();
    }

    private static Map<String, Object> createConnectionConfiguration() {
      Map<String, Object> configs = new HashMap<>();
      configs.put("spring.datasource.url", mySqlContainer.getJdbcUrl());
      configs.put("spring.datasource.username", mySqlContainer.getUsername());
      configs.put("spring.datasource.password", mySqlContainer.getPassword());

      configs.put("spring.activemq.broker-url", "tcp://localhost:" + activeMQContainer.getMappedPort(61616));

      return configs;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      startContainers();
      ConfigurableEnvironment environment = applicationContext.getEnvironment();
      MapPropertySource testContainersConfig = new MapPropertySource(
          "testcontainers", createConnectionConfiguration()
      );
      environment.getPropertySources().addFirst(testContainersConfig);
    }
  }
}
