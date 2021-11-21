package by.my.entity;

import java.util.List;

public class Plane implements ShapeFactory {
    @Override
    public Shape build(List<Point> points) {
        return new Shape("Plane",points);
    }
}
