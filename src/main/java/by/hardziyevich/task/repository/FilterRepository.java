package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;

import java.util.function.Predicate;

import static by.hardziyevich.task.repository.PropertyFilter.*;

public class FilterRepository {

    public Predicate<Shape> filterName(String name) throws SomeException {
        return new Builder().name(name).build().getFilterName();
    }

    public Predicate<Shape> filterPointQuadrant(Point range) throws SomeException {
        return new Builder().rangePoints(range).build().getFilterAllQuadrant();
    }

    public Predicate<Shape> filterParameters(PropertyParameter parameter, double min, double max) {
        Predicate<Shape> result = x -> false;
        switch (parameter) {
            case AREA:
                result = new Builder().rangeVolume(min, max).build().getAreaRange();
                break;
            case VOLUME:
                result = new Builder().rangeVolume(min, max).build().getVolumeRange();
                break;
        }
        return result;
    }
}
