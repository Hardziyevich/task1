package by.hardziyevich.task.repository;

import by.hardziyevich.task.warehouse.Warehouse;

import java.util.List;
import java.util.function.Predicate;

public interface Repository {
    boolean add(Warehouse warehouse);
    boolean remove(Warehouse warehouse);
    boolean contains(Warehouse warehouse);
    Warehouse selectId(int id);
    List<Warehouse> select(Specification specification);
    List<Warehouse> select(Predicate<Warehouse> predicate);
    List<Warehouse> allWarehouse();
}
