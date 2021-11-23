package by.hardziyevich.task.entity;

import by.hardziyevich.task.observer.Observable;
import by.hardziyevich.task.observer.Observer;
import by.hardziyevich.task.observer.ShapeEvent;
import by.hardziyevich.task.observer.impl.ShapeObserver;

import java.util.List;
import java.util.Objects;

public class Shape implements Observable {

    private final int id;
    private final String nameShape;
    private List<Point> coordinates;
    private Observer observer;

    public Shape(int id, String nameShape, List<Point> coordinates) {
        this.id = id;
        this.nameShape = nameShape;
        this.coordinates = coordinates;
    }


    public int getId() {
        return id;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
        notifyObserver();
    }

    public List<Point> getCoordinates() {
        return List.copyOf(coordinates);
    }


    @Override
    public void registerObserver(Observer o) {
        observer = o;
    }

    @Override
    public void removeObserver(Observer o) {
        observer = null;
    }

    @Override
    public void notifyObserver() {
        ShapeEvent event = new ShapeEvent(this);
        if (observer == null) {
            observer = new ShapeObserver();
        }
        observer.update(event);
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
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(id);
        result = prime * result + (nameShape == null ? 0 : nameShape.hashCode());
        if (coordinates == null) {
            result = prime * result;
        } else {
            for (Point coordinate : coordinates) {
                result = prime * result + (coordinate == null ? 0 : coordinate.hashCode());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Shape{");
        sb.append("id= ").append(id);
        sb.append(", nameShape= ").append(nameShape);
        sb.append(", coordinates= ");
        coordinates.forEach(p -> sb.append(p).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}
