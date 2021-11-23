package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Repository;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {

    private Map<Integer,Warehouse> warehouses = new HashMap<>();
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
        return warehouses.containsValue(warehouse);
    }

    @Override
    public Warehouse insertId(int id, Warehouse warehouse) {
        return warehouses.put(id,warehouse);
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
    public List<Warehouse> select(Specification specification) {
        return warehouses.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
