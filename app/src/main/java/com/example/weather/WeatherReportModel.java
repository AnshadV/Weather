package com.example.weather;

public class WeatherReportModel {

    public int id;
    public float temp;
    public float temp_min;
    public float temp_max;
    public String date;
    public String weather_main;
    public String weather_desc;
    public int pressure;
    public int humidity;
    public int visibility;

    public WeatherReportModel(int id, float temp, float temp_min, float temp_max, String date, String weather_main, String weather_desc, int pressure, int humidity, int visibility) {
        this.id = id;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.date = date;
        this.weather_main = weather_main;
        this.weather_desc = weather_desc;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return
                "Date='" + date + '\n' +
                "Temperature=" + temp + '\n' +
                "Low=" + temp_min +
                ", High=" + temp_max + '\n' +
                "" + weather_main + '\'' +
                ", " + weather_desc + '\n' +
                "pressure=" + pressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility ;
    }

    public WeatherReportModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather_main() {
        return weather_main;
    }

    public void setWeather_main(String weather_main) {
        this.weather_main = weather_main;
    }

    public String getWeather_desc() {
        return weather_desc;
    }

    public void setWeather_desc(String weather_desc) {
        this.weather_desc = weather_desc;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
