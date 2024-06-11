import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void notifyObservers(Wasbeurt wasbeurt) {
        for (Observer observer : observers) {
            observer.update(wasbeurt);
        }
    }
}