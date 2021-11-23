package by.hardziyevich.task.repository;

import by.hardziyevich.task.warehouse.Warehouse;

import java.util.List;
import java.util.function.Predicate;

public interface Repository {
    Warehouse insertId(int id, Warehouse warehouse);
    Warehouse removeId(int index);
    Warehouse selectId(int index);
    boolean remove(Warehouse warehouse);
    boolean contains(Warehouse warehouse);
    List<Warehouse> select(Predicate<Warehouse> predicate);
}
