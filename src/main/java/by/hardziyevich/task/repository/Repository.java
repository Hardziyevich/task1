package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

import java.util.List;
import java.util.function.Predicate;

public interface Repository {
    boolean add(Shape warehouseImpl) throws SomeException;
    boolean remove(Shape warehouseImpl) throws SomeException;
    boolean removeAll();
    boolean contains(Shape warehouseImpl);
    Shape selectId(int id);
    List<Shape> select(Specification specification);
    List<Shape> select(Predicate<Shape> predicate);
    List<Shape> selectAll();
}
