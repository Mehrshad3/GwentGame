package model.weather;

public class Weather {
    private final WeatherElement weatherElement;

    public Weather(WeatherElement weatherElement) {
        this.weatherElement = weatherElement;
        weatherElement.initializeCounter();
    }

    public WeatherElement getWeather() {
        return weatherElement;
    }
}
