package by.my.validator;

import by.my.entity.Point;
import by.my.entity.Tetrahedron;
import by.my.exeption.SomeException;
import by.my.utility.TetrahedronUtility;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

public class ValidatorTetrahedron{

    private List<Point> object;
    private final Predicate<List<Point>> sizePoint = x -> x.size() == 4;

    public ValidatorTetrahedron(List<Point> object) {
        this.object = object;
    }

    /**
     *
     * @return If data for tetrahedron correct, return data
     * @throws SomeException
     */
    public List<Point> checkTetrahedron() throws SomeException {
        return Validator.of(object).validate(sizePoint,"It is impossible to build a tetrahedron from this number of points.")
                .get();
    }
}
