package by.hardziyevich.task.service;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;

import java.util.List;

public interface TetrahedronService {

    Double areaTetrahedron(Shape tetrahedron);
    Double volumeTetrahedron(Shape tetrahedron);
    boolean isRightTetrahedron(Shape tetrahedron);
    boolean isOwnerTetrahedron(Shape tetrahedron, List<Point> points);
    Shape cutTetrahedronByPoint(Shape s1,List<Point> points) throws SomeException;
    Double[] volumeNewShape(Shape tetrahedronOld,Shape tetrahedronNew);
}
