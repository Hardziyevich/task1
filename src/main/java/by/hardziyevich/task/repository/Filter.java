package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

import java.util.List;
import java.util.function.Predicate;

public class Filter {
    private static String nameShape;
    private double volume;
    private double area;
    private double min;
    private double max;
    private Point range;

    private Filter(Builder builder) {
        String nameShape = builder.nameShape;
        double volume = builder.volume;
        double area = builder.area;
        double less = builder.min;
        double more = builder.max;
        Point range = builder.range;
    }

    public static Predicate<Shape> filterName(String name) {
        return new Builder().name(name).filterName();
    }

    public static Predicate<Shape> filterPointsFirstQuadrant() {
        return new Builder().filterPointsFirstQuadrant();
    }

    public static Predicate<Shape> filterAreaRange(double min, double max) {
        return new Builder().rangeVolume(min, max).filterAreaRange();
    }

    public static Predicate<Shape> filterAreaRangeNegate(double min, double max) {
        return new Builder().rangeVolume(min, max).filterAreaRange().negate();
    }

    public static Predicate<Shape> filterVolumeRange(double min, double max) {
        return new Builder().rangeVolume(min, max).filterVolumeRange();
    }

    public static Predicate<Shape> filterVolumeRangeNegate(double min, double max) {
        return new Builder().rangeVolume(min, max).filterVolumeRange().negate();
    }

    public static Predicate<Shape> filterDistanceFromOrigin(Point point) {
        return new Builder().rangePoints(point).filterDistanceFromOrigin();
    }


    public static class Builder {

        private String nameShape;
        private double volume;
        private double area;
        private double min;
        private double max;
        private Point range;
        private final TetrahedronServiceImpl tetrahedronService = new TetrahedronServiceImpl();

        private final Predicate<Shape> filterName = w -> nameShape.equals(w.getNameShape());
        private final Predicate<Shape> areaRange = w -> min < tetrahedronService.areaTetrahedron(w) && tetrahedronService.areaTetrahedron(w) < max;
        private final Predicate<Shape> volumeRange = w -> min < tetrahedronService.volumeTetrahedron(w) && tetrahedronService.volumeTetrahedron(w) < max;
        private final Predicate<Shape> filterDistanceFromOrigin = w -> w.getCoordinates().stream().filter(p2 -> range.compareTo(p2) > 0).allMatch(p2 -> p2.compareTo(new Point(0,0,0)) > 0);
        private final Predicate<Shape> pointsFirstQuadrant = w -> {
            List<Point> points = w.getCoordinates();
            return points.stream().allMatch(p1 -> p1.compareTo(new Point(0, 0, 0)) >= 0);
        };

        public Builder name(String nameShape) {
            this.nameShape = nameShape;
            return this;
        }

        public Builder area(double area) {
            this.area = area;
            return this;
        }

        public Builder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder rangeVolume(double min, double max) {
            this.min = min;
            this.max = max;
            return this;
        }

        public Builder rangePoints(Point point) {
            this.range = point;
            return this;
        }

        public Predicate<Shape> filterName() {
            return filterName;
        }

        public Predicate<Shape> filterPointsFirstQuadrant() {
            return pointsFirstQuadrant;
        }

        public Predicate<Shape> filterAreaRange() {
            return areaRange;
        }

        public Predicate<Shape> filterVolumeRange() {
            return volumeRange;
        }

        public Predicate<Shape> filterDistanceFromOrigin() {
            return filterDistanceFromOrigin;
        }

        public Filter build() {
            return new Filter(this);
        }
    }
}
