package by.my.entity;

import by.my.entity.Point;
import by.my.entity.Shape;

import java.util.List;

public interface ShapeFactory {
    Shape build(List<Point> points);
}
