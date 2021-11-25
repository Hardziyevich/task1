package by.hardziyevich.task.entity;

import java.util.Objects;

public class ShapeParameters {
    private double volume;
    private double area;

    public ShapeParameters(double volume, double area) {
        this.volume = volume;
        this.area = area;
    }

    public double getVolume() {
        return volume;
    }

    public double getArea() {
        return area;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShapeParameters) obj;
        return Double.doubleToLongBits(this.volume) == Double.doubleToLongBits(that.volume) &&
                Double.doubleToLongBits(this.area) == Double.doubleToLongBits(that.area);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(volume);
        result = prime * result + Double.hashCode(area);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ShapeParameters{");
        sb.append("volume=").append(volume);
        sb.append(", area=").append(area);
        sb.append("}");
        return sb.toString();
    }
}
