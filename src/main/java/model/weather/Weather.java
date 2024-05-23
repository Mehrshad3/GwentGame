package model.weather;

public class Weather {
    private WeatherElement weatherElement;

    public Weather(WeatherElement weatherElement) {
        this.weatherElement = weatherElement;
        weatherElement.initializeCounter();
    }

    public WeatherElement getWeather() {
        return weatherElement;
    }

    public void passTime() {
        weatherElement.passTime();
        if (weatherElement.counter == 0) {
            weatherElement = WeatherElement.NEUTRAL;
        }
    }
}
