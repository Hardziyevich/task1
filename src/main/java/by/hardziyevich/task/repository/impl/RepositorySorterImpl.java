package by.hardziyevich.task.repository.impl;


import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.repository.RepositorySorter;
import by.hardziyevich.task.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorySorterImpl implements RepositorySorter {
    private final List<Shape> shapes;
    Comparator<Shape> comparatorFirstPointX = (x, y)-> Double.compare(x.getCoordinates().get(0).getX(),y.getCoordinates().get(0).getX());
    Comparator<Shape> comparatorFirstPointY = (x, y)-> Double.compare(x.getCoordinates().get(0).getY(),y.getCoordinates().get(0).getY());

    public RepositorySorterImpl(List<Shape> shapes) throws SomeException {
        this.shapes = Validator.of(shapes).get();
    }

    @Override
    public List<Shape> sortById(){
        return shapes.stream().sorted(Comparator.comparingInt(Shape::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Shape> sortByName(){
        return shapes.stream().sorted(Comparator.comparing(Shape::getNameShape)).collect(Collectors.toList());
    }

    @Override
    public List<Shape> sortByFirstPointX(){
        return shapes.stream().sorted(comparatorFirstPointX).collect(Collectors.toList());
    }

    @Override
    public List<Shape> sortByFirstPointY(){
        return shapes.stream().sorted(comparatorFirstPointY).collect(Collectors.toList());
    }
}
