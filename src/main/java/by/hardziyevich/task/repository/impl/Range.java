package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.function.Predicate;

public class Range implements Specification {
    private Predicate<Warehouse> predicate;

    public Range(Predicate<Warehouse> predicate){
        this.predicate = predicate;
    }
    @Override
    public boolean specify(Integer id, Warehouse warehouse) {
        return predicate.test(warehouse);
    }
}
