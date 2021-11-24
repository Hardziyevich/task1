package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Repository;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.*;
import java.util.function.Predicate;

public class RepositoryImpl implements Repository {

    private List<Warehouse> warehouses = new ArrayList<>();
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
    public Warehouse selectId(int id) {
        return warehouses.stream().filter(w -> w.getId() == id).findFirst().get();
    }

    @Override
    public boolean add(Warehouse warehouse) {
        return warehouses.add(warehouse);
    }

    @Override
    public boolean remove(Warehouse warehouse) {
        return warehouses.remove(warehouses);
    }

    @Override
    public List<Warehouse> select(Specification specification) {
        return null;
    }

    @Override
    public List<Warehouse> select(Predicate<Warehouse> predicate) {
        return null;
    }

    @Override
    public List<Warehouse> allWarehouse() {
        return List.copyOf(warehouses);
    }
}
