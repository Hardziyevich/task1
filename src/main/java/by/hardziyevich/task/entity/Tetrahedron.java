package by.hardziyevich.task.entity;

import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.service.ShapeIdGenerator;
import by.hardziyevich.task.validator.ValidatorTetrahedron;

import java.util.List;

public class Tetrahedron{
    public static Shape newShape(List<Point> points) throws SomeException {
        return new Shape(ShapeIdGenerator.generateId(),"Tetrahedron",new ValidatorTetrahedron(points).checkTetrahedron());
    }
}
