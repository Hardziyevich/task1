package by.hardziyevich.task.entity;

import static java.lang.Double.*;

public class Point implements Comparable<Point>{
    private final double x;
    private final double y;
    private final double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && z == point.z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(x);
        result = prime * result + Double.hashCode(y);
        result = prime * result + Double.hashCode(z);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", z=").append(z);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int compareTo(Point o) {
        if(compare(this.x,o.x) > 0 && compare(this.y,o.y) > 0 && compare(this.z,o.z) > 0) {
            return 1;
        }
        else if(compare(this.x,o.x) < 0 && compare(this.y,o.y) < 0 && compare(this.z,o.z) < 0) {
            return -1;
        }
        return 0;
    }
}
