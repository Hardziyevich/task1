package by.hardziyevich.task.observer;

import by.hardziyevich.task.exeption.SomeException;

public interface Observer {

    void update(final ShapeEvent data) throws SomeException;
}
