package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.repository.WarehouseSorter;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseSorterImpl implements WarehouseSorter {

    private final List<Warehouse> warehouses;
    Comparator<Warehouse> comparatorFirstPointX = (x,y)-> Double.compare(x.getPoints().get(0).getX(),y.getPoints().get(0).getX());
    Comparator<Warehouse> comparatorFirstPointY = (x,y)-> Double.compare(x.getPoints().get(0).getY(),y.getPoints().get(0).getY());

    public WarehouseSorterImpl(List<Warehouse> warehouses){
        this.warehouses = warehouses;
    }

    @Override
    public List<Warehouse> sortById(){
        return warehouses.stream().sorted(Comparator.comparingInt(Warehouse::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Warehouse> sortByName(){
        return warehouses.stream().sorted(Comparator.comparing(Warehouse::getNameShape)).collect(Collectors.toList());
    }

    @Override
    public List<Warehouse> sortByFirstPointX(){
        return warehouses.stream().sorted(comparatorFirstPointX).collect(Collectors.toList());
    }

    @Override
    public List<Warehouse> sortByFirstPointY(){
        return warehouses.stream().sorted(comparatorFirstPointY).collect(Collectors.toList());
    }
}
