package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.List;
import java.util.function.Predicate;

public class Property {
    private static String nameShape;
    private double volume;
    private double area;
    private double min;
    private double max;
    private Point range;

    private Property(Builder builder) {
        String nameShape = builder.nameShape;
        double volume = builder.volume;
        double area = builder.area;
        double less = builder.min;
        double more = builder.max;
        Point range = builder.range;
    }

    public static Predicate<Warehouse> filterName(String name) {
        return new Builder().name(name).filterName;
    }

    public static Predicate<Warehouse> filterPointsFirstQuadrant(){
        return new Builder().filterPointsFirstQuadrant();
    }

    public static Predicate<Warehouse> filterAreaRange(double min, double max){
        return new Builder().rangeVolume(min, max).filterAreaRange();
    }

    public static Predicate<Warehouse> filterAreaRangeNegate(double min, double max){
        return new Builder().rangeVolume(min, max).filterAreaRange().negate();
    }

    public static Predicate<Warehouse> filterVolumeRange(double min, double max){
        return new Builder().rangeVolume(min, max).filterVolumeRange();
    }

    public static Predicate<Warehouse> filterVolumeRangeNegate(double min, double max){
        return new Builder().rangeVolume(min, max).filterVolumeRange().negate();
    }

    public static Predicate<Warehouse> filterDistanceFromOrigin(Point point){
        return new Builder().rangePoints(point).filterDistanceFromOrigin();
    }



    public static class Builder {
        private String nameShape;
        private double volume;
        private double area;
        private double min;
        private double max;
        private Point range;

        private final Predicate<Warehouse> filterName = w -> nameShape.equals(w.getNameShape());
        private final Predicate<Warehouse> pointsFirstQuadrant = w -> {
            List<Point> points = w.getPoints();
            return points.stream().allMatch(p1 -> p1.compareTo(new Point(0, 0, 0)) >= 0);
        };
        private final Predicate<Warehouse> areaRange = w -> min < w.getAreaTetrahedron() && w.getAreaTetrahedron() < max;
        private final Predicate<Warehouse> volumeRange = w -> min < w.getVolumeTetrahedron() && w.getVolumeTetrahedron() < max;
        private final Predicate<Warehouse> filterDistanceFromOrigin = w -> w.getPoints().stream().allMatch(p2-> range.compareTo(p2) > 0);

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

        public Predicate<Warehouse> filterName(){
            return filterName;
        }

        public Predicate<Warehouse> filterPointsFirstQuadrant(){
            return pointsFirstQuadrant;
        }

        public Predicate<Warehouse> filterAreaRange(){
            return areaRange;
        }

        public Predicate<Warehouse> filterVolumeRange(){
            return volumeRange;
        }

        public Predicate<Warehouse> filterDistanceFromOrigin() {
            return filterDistanceFromOrigin;
        }

        public Property build() {
            return new Property(this);
        }
    }
}
