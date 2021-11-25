package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.repository.Repository;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.validator.Validator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {

    private final List<Shape> shapeStorage = new ArrayList<>();
    private static RepositoryImpl repositoryImpl;

    private RepositoryImpl() {
    }

    public static RepositoryImpl getRepository() {
        if (repositoryImpl == null) {
            repositoryImpl = new RepositoryImpl();
        }
        return repositoryImpl;
    }

    @Override
    public boolean contains(Shape shape) {
        return shapeStorage.contains(shape);
    }

    @Override
    public Shape selectId(int id) {
        return shapeStorage.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public boolean add(Shape shape) throws SomeException {
        return shapeStorage.add(Validator.of(shape).get());
    }

    @Override
    public boolean remove(Shape shape) throws SomeException {
        return shapeStorage.remove(Validator.of(shape).get());
    }

    @Override
    public boolean removeAll() {
        return shapeStorage.removeAll(shapeStorage);
    }

    @Override
    public List<Shape> select(Specification specification) {
        return shapeStorage.stream().filter(specification::specify).collect(Collectors.toList());
    }

    @Override
    public List<Shape> select(Predicate<Shape> predicate) {
        return shapeStorage.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Shape> selectAll() {
        return List.copyOf(shapeStorage);
    }
}
