package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.validator.Validator;

import java.util.List;
import java.util.function.Predicate;

public final class PropertyFilter {
    private final String nameShape;
    private final double min;
    private final double max;
    private final Point range;
    private final Predicate<Shape> filterName;
    private final Predicate<Shape> areaRange;
    private final Predicate<Shape> volumeRange;
    private final Predicate<Shape> filterAllQuadrant;

    private PropertyFilter(Builder builder) {
        nameShape = builder.nameShape;
        min = builder.min;
        max = builder.max;
        range = builder.range;
        filterName = builder.filterName;
        areaRange = builder.areaRange;
        volumeRange = builder.volumeRange;
        filterAllQuadrant = builder.filterAllQuadrant;
    }

    public enum PropertyParameter {
        VOLUME,
        AREA
    }

    public Predicate<Shape> getFilterName() {
        return filterName;
    }

    public Predicate<Shape> getFilterAllQuadrant() {
        return filterAllQuadrant;
    }

    public Predicate<Shape> getAreaRange() {
        return areaRange;
    }

    public Predicate<Shape> getVolumeRange() {
        return volumeRange;
    }

    public static class Builder {

        private String nameShape;
        private double min;
        private double max;
        private Point range;

        private final TetrahedronServiceImpl tetrahedronService = new TetrahedronServiceImpl();
        private final Predicate<Shape> filterName = w -> nameShape.equals(w.getNameShape());
        private final Predicate<Shape> areaRange = w -> min < tetrahedronService.areaTetrahedron(w) && tetrahedronService.areaTetrahedron(w) < max;
        private final Predicate<Shape> volumeRange = w -> min < tetrahedronService.volumeTetrahedron(w) && tetrahedronService.volumeTetrahedron(w) < max;
        private final Predicate<Shape> filterAllQuadrant = w -> w.getCoordinates().stream().filter(p2 -> range.compareTo(p2) > 0).allMatch(p2 -> p2.compareTo(new Point(0, 0, 0)) > 0);

        public Builder name(String nameShape) throws SomeException {
            this.nameShape = Validator.of(nameShape).get();
            return this;
        }

        public Builder rangeVolume(double min, double max) {
            this.min = min;
            this.max = max;
            return this;
        }

        public Builder rangePoints(Point point) throws SomeException {
            this.range = Validator.of(point).get();
            return this;
        }

        public PropertyFilter build() {
            return new PropertyFilter(this);
        }
    }
}
