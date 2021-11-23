package by.hardziyevich.task.repository;


import by.hardziyevich.task.warehouse.Warehouse;

public interface Specification {
    boolean specify(Warehouse warehouse);
}
