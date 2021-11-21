package by.my.entity;

import java.util.List;
import java.util.Objects;

public class Shape {

    private String id;
    private List<Point> coordinates;

    public Shape(String id, List<Point> coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }


    public String getId() {
        return id;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape that = (Shape) o;
        return Objects.equals(id, that.id) && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinates);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "id='" + id + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
