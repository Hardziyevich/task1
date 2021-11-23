package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Repository;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {

    private final List<Warehouse> warehouses = new ArrayList<>();
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
    public boolean contains(Warehouse warehouse) {
        return warehouses.contains(warehouse);
    }

    @Override
    public Warehouse insertId(int id, Warehouse warehouse) {
        return warehouses.set(id, warehouse);
    }

    @Override
    public boolean remove(Warehouse warehouse) {
        return warehouses.remove(warehouse);
    }

    @Override
    public Warehouse selectId(int index) {
        return warehouses.get(index);
    }

    @Override
    public Warehouse removeId(int index) {
        return warehouses.remove(index);
    }

    @Override
    public List<Warehouse> select(Predicate<Warehouse> predicate) {
        return warehouses.stream().filter(predicate).collect(Collectors.toList());
    }
}
