package com.luke.designpatterns.observerPattern;

import java.util.ArrayList;
import java.util.List;

// 主题接口
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// 具体主题 - 气象站
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }
}

// 观察者接口
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// 具体观察者 - 手机
class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Phone Display: Temperature - " + temperature + "°C, Humidity - " + humidity + "%");
    }
}

// 具体观察者 - 电视
class TVDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("TV Display: Pressure - " + pressure + "hPa");
    }
}

// 客户端代码
public class Client {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        // 添加观察者
        Observer phoneDisplay = new PhoneDisplay();
        Observer tvDisplay = new TVDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);

        // 模拟气象站测量并通知观察者
        weatherStation.setMeasurements(25.5f, 60.0f, 1012.5f);
    }
}