package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

import java.util.List;

public interface RepositorySorter {
    List<Shape> sortById();
    List<Shape> sortByName();
    List<Shape> sortByFirstPointX();
    List<Shape> sortByFirstPointY();
}
