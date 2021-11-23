package by.hardziyevich.task.warehouse;

import java.util.Objects;

public class Warehouse {

    private double areaTetrahedron;
    private double volumeTetrahedron;

    public Warehouse(double areaTetrahedron, double volumeTetrahedron) {
        this.areaTetrahedron = areaTetrahedron;
        this.volumeTetrahedron = volumeTetrahedron;
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
        return Double.compare(warehouse.areaTetrahedron, areaTetrahedron) == 0 && Double.compare(warehouse.volumeTetrahedron, volumeTetrahedron) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(areaTetrahedron);
        result = prime * result + Double.hashCode(volumeTetrahedron);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("areaTetrahedron=").append(areaTetrahedron);
        sb.append(", volumeTetrahedron=").append(volumeTetrahedron);
        sb.append("}");
        return sb.toString();
    }
}
