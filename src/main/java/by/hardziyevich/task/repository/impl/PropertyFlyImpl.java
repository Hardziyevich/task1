package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.Property;
import by.hardziyevich.task.repository.PropertyFly;
import by.hardziyevich.task.repository.WarehouseFeature;

import java.util.EnumMap;
import java.util.Map;

public class PropertyFlyImpl implements PropertyFly {

    private Map<WarehouseFeature, Property> propertyMap;

    public PropertyFlyImpl() {
        propertyMap = new EnumMap<WarehouseFeature, Property>(WarehouseFeature.class);
    }

    @Override
    public Property areaRangeLessThanWarehouse(double volume, WarehouseFeature feature) {
        Property property = propertyMap.get(feature);
        if (property == null) {
            property = new Property.Builder().setValue(feature,volume).state(Property.CompareWarehouseState.LESS).build();
        }
        propertyMap.put(feature,property);
        return property;
    }

    @Override
    public Property areaRangeMoreThanWarehouse(double volume, WarehouseFeature feature) {
        Property property = propertyMap.get(feature);
        if (property == null) {
            property = new Property.Builder().setValue(feature,volume).state(Property.CompareWarehouseState.LESS).build();
        }
        propertyMap.put(feature,property);
        return property;
    }

    @Override
    public Property areaRangeSameWarehouse(double volume, WarehouseFeature feature) {
        Property property = propertyMap.get(feature);
        if (property == null) {
            property = new Property.Builder().setValue(feature,volume).state(Property.CompareWarehouseState.LESS).build();
        }
        propertyMap.put(feature,property);
        return property;
    }

    @Override
    public Property idFilter(int id,WarehouseFeature feature) {
        return new Property.Builder().id(id,feature).build();
    }
}
