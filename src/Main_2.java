import java.util.ArrayList;
import java.util.List;
interface IObserver {
    void update(float temperature);
}
interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}


class WeatherStation implements ISubject {
    private List<IObserver> observers;
    private float temperature;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float newTemperature) {
        System.out.println("Изменение температуры: " + newTemperature + "°C");
        this.temperature = newTemperature;
        notifyObservers();
    }
}

class WeatherDisplay implements IObserver {
    private String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " показывает новую температуру: " + temperature + "°C");
    }
}

class EmailNotifier implements IObserver {
    @Override
    public void update(float temperature) {
        System.out.println("Отправка email: Температура изменилась на " + temperature + "°C");
    }
}

public class Main_2 {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");
        EmailNotifier emailNotifier = new EmailNotifier();
        station.registerObserver(mobileApp);
        station.registerObserver(digitalBillboard);
        station.registerObserver(emailNotifier);
        station.setTemperature(25.0f);
        station.setTemperature(30.0f);
        station.removeObserver(digitalBillboard);
        station.setTemperature(28.0f);
    }
}


