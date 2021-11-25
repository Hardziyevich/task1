package by.hardziyevich.task.observer;

import by.hardziyevich.task.exeption.SomeException;

public interface Observable {

    void registerObserver(final Observer o);
    void removeObserver(final Observer o);
    void notifyObserver() throws SomeException;
}
