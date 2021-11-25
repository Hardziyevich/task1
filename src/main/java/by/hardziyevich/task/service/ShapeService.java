package by.hardziyevich.task.service;

import by.hardziyevich.task.entity.Point;

public interface ShapeService {
    boolean checkPointBelong(Point p1, Point p2, Point p3);
    Double lengthSide(Point p1, Point p2);
    Double powDifNum(double x, double y);
    Double rounding(double d, int accuracy);
}
