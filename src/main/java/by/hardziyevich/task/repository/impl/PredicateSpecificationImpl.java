package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.function.Predicate;

public class PredicateSpecificationImpl implements Specification {

    private Predicate<Warehouse> warehousePredicate;

    public PredicateSpecificationImpl(Predicate<Warehouse> warehousePredicate){
        this.warehousePredicate = warehousePredicate;
    }

    @Override
    public boolean specify(Warehouse warehouse) {
        return warehousePredicate.test(warehouse);
    }
}
