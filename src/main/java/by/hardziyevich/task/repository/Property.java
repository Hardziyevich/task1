package by.hardziyevich.task.repository;

public class Property {
    private final int id;
    private final double area;
    private final double volume;
    private final CompareWarehouseState state;
    private final WarehouseFeature feature;

    public enum CompareWarehouseState {
        LESS,
        MORE,
        SAME,
    }

    private Property(Builder builder) {
        this.id = builder.id;
        this.area = builder.area;
        this.volume = builder.volume;
        this.state = builder.state;
        this.feature = builder.feature;
    }

    public int getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public double getVolume() {
        return volume;
    }

    public CompareWarehouseState getState() {
        return state;
    }

    public WarehouseFeature getFeature() {
        return feature;
    }

    public static class Builder {
        private int id;
        private double area;
        private double volume;
        private CompareWarehouseState state;
        private WarehouseFeature feature;

        public Builder() {
            super();
        }

        public Builder id(int id, WarehouseFeature feature){
            this.feature = feature;
            this.id = id;
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

        public Builder setValue(WarehouseFeature feature,double data){
            switch (feature){
                case VOLUME:{
                    this.feature = feature;
                    this.volume = data;
                    return this;
                }
                case AREA:{
                    this.feature = feature;
                    this.area = data;
                    return this;
                }
                default: return this;
            }
        }

        public Builder state(CompareWarehouseState state) {
            this.state = state;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}
