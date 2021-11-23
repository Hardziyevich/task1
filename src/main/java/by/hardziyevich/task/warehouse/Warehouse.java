package by.hardziyevich.task.warehouse;

public class Warehouse {

    private static Warehouse instance;
    private double areaTetrahedron;
    private double volumeTetrahedron;

    private Warehouse(){
    }

    public static Warehouse getInstance() {
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
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
}
