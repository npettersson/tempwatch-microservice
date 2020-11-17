CREATE TABLE temperature_sensors(
    sensor_id VARCHAR(25) UNIQUE NOT NULL,
    description VARCHAR(255),
    temperature_threshold DECIMAL(3,1) NOT NULL,
    allowed_deviation DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (sensor_id)
);

CREATE TABLE temperature_readings(
    id INT NOT NULL AUTO_INCREMENT,
    sensor_id VARCHAR(25) NOT NULL,
    temperature DECIMAL(3,1) NOT NULL,
    deviation VARCHAR(15) NOT NULL,
    measurement_ts TIMESTAMP NOT NULL,
    received_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (sensor_id) REFERENCES temperature_sensors(sensor_id)
);
