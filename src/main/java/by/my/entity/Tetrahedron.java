package by.my.entity;

import java.util.List;

public class Tetrahedron implements ShapeFactory {
    public Shape build(List<Point> points) {
        return new Shape("Tetrahedron",points);
    }
}
