package by.hardziyevich.task.warehouse.impl;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.validator.Validator;
import by.hardziyevich.task.warehouse.Warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WarehouseImpl implements Warehouse {

    private static WarehouseImpl instance;
    private Map<Integer, ShapeParameters> parametersWarehouse = new HashMap<>();

    private WarehouseImpl() {

    }

    public static WarehouseImpl getInstance(){
        if(instance == null){
            instance = new WarehouseImpl();
        }
        return instance;
    }

    @Override
    public ShapeParameters addParameter(int id, ShapeParameters shapeParameters) throws SomeException {
        return parametersWarehouse.put(id, Validator.of(shapeParameters).get());
    }

    @Override
    public ShapeParameters receiveParameter(int id) throws SomeException {
        return Validator.of(parametersWarehouse.get(id)).get();
    }

    @Override
    public ShapeParameters updateParameters(int id, ShapeParameters shapeParameters) throws SomeException {
        return parametersWarehouse.putIfAbsent(id,Validator.of(shapeParameters).get());
    }

    @Override
    public void removeAll() {
        parametersWarehouse.clear();
    }
}
