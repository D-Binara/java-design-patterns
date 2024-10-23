package behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

interface Observer {
    void update(float temperature, float humidity, float pressure);
}

class CurrentConditionsDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Current conditions: " + temperature + "C degrees, " + humidity + "% humidity, " + pressure + " hPa pressure");
    }
}

class StatisticsDisplay implements Observer {
    private List<Float> temperatureReadings = new ArrayList<>();

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatureReadings.add(temperature);
        float avgTemp = (float) temperatureReadings.stream().mapToDouble(a -> a).average().orElse(0.0);
        float maxTemp = temperatureReadings.stream().max(Float::compare).orElse(temperature);
        float minTemp = temperatureReadings.stream().min(Float::compare).orElse(temperature);
        System.out.println("Avg/Max/Min temperature: " + avgTemp + "C / " + maxTemp + "C / " + minTemp + "C");
    }
}


public class WeatherStationExample {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        // Register observers
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);

        // Set new measurements and notify observers
        weatherStation.setMeasurements(25, 65, 1013);
        weatherStation.setMeasurements(28, 70, 1012);
        weatherStation.setMeasurements(22, 60, 1011);
    }
}
