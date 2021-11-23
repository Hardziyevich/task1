package by.hardziyevich.task.repository;


import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.warehouse.Warehouse;

public interface Specification {
    boolean specify(Integer id,Warehouse warehouse);
}
