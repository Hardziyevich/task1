package by.hardziyevich.task.observer;

public interface Observable {

    void registerObserver(final Observer o);
    void removeObserver(final Observer o);
    void notifyObserver();
}
