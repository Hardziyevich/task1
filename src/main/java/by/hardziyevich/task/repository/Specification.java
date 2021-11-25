package by.hardziyevich.task.repository;


import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

@FunctionalInterface
public interface Specification {
    boolean specify(Shape shape);
}
