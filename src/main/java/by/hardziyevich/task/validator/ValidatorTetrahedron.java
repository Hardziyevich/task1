package by.hardziyevich.task.validator;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.exeption.SomeException;

import java.util.List;
import java.util.function.Predicate;

public class ValidatorTetrahedron {

    private final List<Point> object;
    private final Predicate<List<Point>> sizePoint = x -> x.size() == 4;


    public ValidatorTetrahedron(List<Point> object) throws SomeException {
        this.object = Validator.of(object).get();
    }

    /**
     * @return If data for tetrahedron correct, return data
     * @throws SomeException
     */
    public List<Point> checkTetrahedron() throws SomeException {
        return Validator.of(object)
                .validate(sizePoint, "It is impossible to build a tetrahedron from this number of points.")
                .get();
    }
}
