package by.my.validator;

import by.my.exeption.SomeException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *  Class representing Monad design pattern.
 *  Not full correct, but in particular way that enough
 * @param <T>
 */
public class Validator<T> {

    private final T object;
    private final Collection<Throwable> exceptions = new LinkedList<>();

    public Validator(T object){
        this.object = object;
    }

    public static <T> Validator<T> of(final T object) {
        return new Validator<>(Objects.requireNonNull(object));
    }
    public Validator<T> validate(final Predicate<T> validation, final String message) {
        if (!validation.test(object)) {
            exceptions.add(new SomeException(message));
        }
        return this;
    }
    public T get() throws SomeException {
        if (exceptions.isEmpty()) {
            return object;
        }
        final var exception = new SomeException();
        exceptions.forEach(exception::addSuppressed);
        throw exception;
    }
}

