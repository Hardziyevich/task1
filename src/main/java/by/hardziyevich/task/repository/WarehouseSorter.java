package by.hardziyevich.task.repository;

import by.hardziyevich.task.warehouse.Warehouse;

import java.util.List;

public interface WarehouseSorter {
    List<Warehouse> sortById();
    List<Warehouse> sortByName();
    List<Warehouse> sortByFirstPointX();
    List<Warehouse> sortByFirstPointY();
}
