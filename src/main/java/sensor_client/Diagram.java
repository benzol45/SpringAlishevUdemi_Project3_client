package sensor_client;

import org.knowm.xchart.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class Diagram {

    public static void displayDiagram() {
        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> list = restTemplate.getForObject("http://localhost:8080/measurements", List.class);

        double[] xValue = new double[list.size()];
        double[] yValue = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            xValue[i] = i + 1;
            yValue[i] = (double) list.get(i).get("value");
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Temperature").xAxisTitle("Measurement").yAxisTitle("Value").build();
        chart.getStyler().setLegendVisible(false);

        chart.addSeries("sensor data", xValue, yValue);

        new SwingWrapper(chart).displayChart();
    }
}
