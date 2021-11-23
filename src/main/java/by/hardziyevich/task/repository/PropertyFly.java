package by.hardziyevich.task.repository;

public interface PropertyFly {
    Property areaRangeLessThanWarehouse(double volume, WarehouseFeature feature);
    Property areaRangeMoreThanWarehouse(double volume, WarehouseFeature feature);
    Property areaRangeSameWarehouse(double volume, WarehouseFeature feature);
    Property idFilter(int id, WarehouseFeature feature);

}

