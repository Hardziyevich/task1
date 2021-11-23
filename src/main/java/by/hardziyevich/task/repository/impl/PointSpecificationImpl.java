package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.List;
import java.util.Objects;

public class PointSpecificationImpl implements Specification {
    private List<Point> points;

    public PointSpecificationImpl(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean specify(Integer id, Warehouse warehouse) {
        return points.stream()
                .filter(x -> warehouse.getPoints().stream().anyMatch(p -> Objects.equals(x, p)))
                .count() == 4L;
    }
}
