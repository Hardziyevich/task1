package by.hardziyevich.task.service;

import by.hardziyevich.task.warehouse.Warehouse;

import java.util.function.Predicate;

public interface PredicateService {
    Predicate<Warehouse> getAreaLessThan();
    Predicate<Warehouse> getAreaMoreThan();
    Predicate<Warehouse> getAreaSameThan();
    Predicate<Warehouse> getAreaRangeRange();
    Predicate<Warehouse> getVolumeLessThan();
    Predicate<Warehouse> getVolumeMoreThan();
    Predicate<Warehouse> getVolumeSameThan();
    Predicate<Warehouse> getVolumeRangeRange();
}
