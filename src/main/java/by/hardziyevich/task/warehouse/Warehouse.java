package by.hardziyevich.task.warehouse;

import by.hardziyevich.task.entity.Point;

import java.util.List;
import java.util.Objects;

public class Warehouse {

    private List<Point> points;
    private double areaTetrahedron;
    private double volumeTetrahedron;

    public Warehouse(double areaTetrahedron, double volumeTetrahedron, List<Point> points) {
        this.areaTetrahedron = areaTetrahedron;
        this.volumeTetrahedron = volumeTetrahedron;
        this.points = points;
    }

    public List<Point> getPoints() {
        return List.copyOf(points);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getAreaTetrahedron() {
        return areaTetrahedron;
    }

    public void setAreaTetrahedron(double areaTetrahedron) {
        this.areaTetrahedron = areaTetrahedron;
    }

    public double getVolumeTetrahedron() {
        return volumeTetrahedron;
    }

    public void setVolumeTetrahedron(double volumeTetrahedron) {
        this.volumeTetrahedron = volumeTetrahedron;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Double.compare(warehouse.areaTetrahedron, areaTetrahedron) == 0 && Double.compare(warehouse.volumeTetrahedron, volumeTetrahedron) == 0 && Objects.equals(points, warehouse.points);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(areaTetrahedron);
        result = prime * result + Double.hashCode(volumeTetrahedron);
        if (points == null) {
            result = prime * result;
        } else {
            for (Point coordinate : points) {
                result = prime * result + (coordinate == null ? 0 : coordinate.hashCode());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("areaTetrahedron=").append(areaTetrahedron);
        sb.append(", volumeTetrahedron=").append(volumeTetrahedron);
        sb.append("}");
        points.forEach(p -> sb.append(p).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}
