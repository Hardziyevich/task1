package by.hardziyevich.task.factory;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.service.ShapeIdGenerator;
import by.hardziyevich.task.validator.ValidatorTetrahedron;

import java.util.List;

public class TetrahedronFactory {
    public static Shape newShape(List<Point> points) throws SomeException {
        return new Shape(ShapeIdGenerator.generateId(), "Tetrahedron", new ValidatorTetrahedron(points).checkTetrahedron());
    }
}
