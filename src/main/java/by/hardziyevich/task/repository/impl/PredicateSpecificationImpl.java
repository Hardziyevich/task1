package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

import java.util.function.Predicate;

public class PredicateSpecificationImpl implements Specification {

    private Predicate<Shape> shapePredicate;

    public PredicateSpecificationImpl(Predicate<Shape> shapePredicate){
        this.shapePredicate = shapePredicate;
    }

    @Override
    public boolean specify(Shape shape) {
        return shapePredicate.test(shape);
    }
}
