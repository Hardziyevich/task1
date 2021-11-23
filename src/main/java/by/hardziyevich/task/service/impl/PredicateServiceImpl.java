package by.hardziyevich.task.service.impl;

import by.hardziyevich.task.service.PredicateService;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.function.Predicate;

public class PredicateServiceImpl implements PredicateService {
    private final double value;
    private final double[] rangeState;
    private final Predicate<Warehouse> areaLessThan = x -> setData(x.getAreaTetrahedron(), State.LESS);
    private final Predicate<Warehouse> areaMoreThan = x -> setData(x.getAreaTetrahedron(), State.MORE);
    private final Predicate<Warehouse> areaSameThan = x -> setData(x.getAreaTetrahedron(), State.SAME);
    private final Predicate<Warehouse> areaRangeRange = x -> setData(x.getAreaTetrahedron());
    private final Predicate<Warehouse> volumeLessThan = x -> setData(x.getVolumeTetrahedron(), State.LESS);
    private final Predicate<Warehouse> volumeMoreThan = x -> setData(x.getVolumeTetrahedron(), State.MORE);
    private final Predicate<Warehouse> volumeSameThan = x -> setData(x.getVolumeTetrahedron(), State.SAME);
    private final Predicate<Warehouse> volumeRangeRange = x -> setData(x.getVolumeTetrahedron());

    public enum State {
        LESS,
        MORE,
        SAME
    }

    public PredicateServiceImpl(double value, double[] rangeState){
        this.value = value;
        this.rangeState = rangeState;
    }

    @Override
    public Predicate<Warehouse> getAreaLessThan() {
        return areaLessThan;
    }

    @Override
    public Predicate<Warehouse> getAreaMoreThan() {
        return areaMoreThan;
    }

    @Override
    public Predicate<Warehouse> getAreaSameThan() {
        return areaSameThan;
    }

    @Override
    public Predicate<Warehouse> getAreaRangeRange() {
        return areaRangeRange;
    }

    @Override
    public Predicate<Warehouse> getVolumeLessThan() {
        return volumeLessThan;
    }

    @Override
    public Predicate<Warehouse> getVolumeMoreThan() {
        return volumeMoreThan;
    }

    @Override
    public Predicate<Warehouse> getVolumeSameThan() {
        return volumeSameThan;
    }

    @Override
    public Predicate<Warehouse> getVolumeRangeRange() {
        return volumeRangeRange;
    }

    private boolean setData(double warehouse, State state) {
        switch (state) {
            case LESS:
                return Double.compare(warehouse, value) < 0;
            case MORE:
                return Double.compare(warehouse, value) > 0;
            case SAME:
                return Double.compare(warehouse, value) == 0;
            default: return false;
        }
    }

    private boolean setData(double warehouse){
        boolean stateLess = Double.compare(warehouse,rangeState[1]) < 0;
        boolean stateMore = Double.compare(warehouse,rangeState[0]) > 0;
        return stateLess && stateMore;
    }
}
