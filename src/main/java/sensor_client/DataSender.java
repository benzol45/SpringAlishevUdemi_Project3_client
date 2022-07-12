package sensor_client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataSender {
    public static void sendRandomValues() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HttpStatus> response;

        String randomSensorName = "sensor" + (new Random().nextInt())%100;

        response = restTemplate.postForEntity("http://localhost:8080/sensors/registration", Map.of("name",randomSensorName),HttpStatus.class);
        if (response.getStatusCode()==HttpStatus.OK) {
            System.out.println("Sensor creation OK");
        } else {
            System.out.println("Sensor creation ERROR with code " + response.getStatusCode().value());
            return;
        }

        Map<String,Object> measurement = new HashMap<>();

        measurement.put("sensor",Map.of("name",randomSensorName));
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            measurement.put("value",random.nextFloat(-100,100));
            measurement.put("raining",random.nextBoolean());
            HttpEntity<Map<String,Object>> requestEntity = new HttpEntity<>(measurement);

            response = restTemplate.postForEntity("http://localhost:8080/measurements/add", requestEntity, HttpStatus.class);
            if (response.getStatusCode()==HttpStatus.OK) {
                System.out.println("Measurement " + i + " add OK");
            } else {
                System.out.println("Measurement " + i + " add ERROR with code " + response.getStatusCode().value());
            }
        }
    }
}
