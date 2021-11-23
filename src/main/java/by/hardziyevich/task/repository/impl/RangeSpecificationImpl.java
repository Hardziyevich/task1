package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Property;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.repository.WarehouseFeature;
import by.hardziyevich.task.warehouse.Warehouse;

public class RangeSpecificationImpl implements Specification {
    private final int id;
    private final double area;
    private final double volume;
    private final Property.CompareWarehouseState state;
    private final WarehouseFeature feature;
    private Warehouse warehouse;

    public RangeSpecificationImpl(Property property) {
        this.area = property.getArea();
        this.volume = property.getVolume();
        this.state = property.getState();
        this.feature = property.getFeature();
        this.id = property.getId();
    }


    @Override
    public boolean specify(Integer id, Warehouse warehouse) {
        this.warehouse = warehouse;
        switch (state) {
            case LESS:
                return setData() == -1;
            case MORE:
                return setData() == 1;
            case SAME:
                return setData() == 0;
            default:
                if (feature == WarehouseFeature.ID) {
                    return setData() == id;
                } else {
                    return false;
                }
        }
    }

    private int setData() {
        int result = 0;
        switch (feature) {
            case AREA:
                result = Double.compare(warehouse.getAreaTetrahedron(), setData());
                break;
            case VOLUME:
                result = Double.compare(warehouse.getVolumeTetrahedron(), setData());
                break;
            case ID:
                result = id;
            default:
                break;
        }
        return result;
    }
}
