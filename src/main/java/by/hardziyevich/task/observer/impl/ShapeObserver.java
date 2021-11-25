package by.hardziyevich.task.observer.impl;


import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.observer.Observer;
import by.hardziyevich.task.observer.ShapeEvent;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.validator.ValidatorTetrahedron;
import by.hardziyevich.task.warehouse.Warehouse;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;

import java.util.List;


public class ShapeObserver implements Observer {
    @Override
    public void update(ShapeEvent data) throws SomeException {
        Shape shape = data.getSource();
        new ValidatorTetrahedron(shape.getCoordinates()).checkTetrahedron();
        Warehouse warehouse = WarehouseImpl.getInstance();
        int tetrahedronId = shape.getId();
        TetrahedronServiceImpl tetrahedronService = new TetrahedronServiceImpl();
        double areaTetrahedron = tetrahedronService.areaTetrahedron(shape);
        double volumeTetrahedron = tetrahedronService.volumeTetrahedron(shape);
        ShapeParameters shapeParameters = warehouse.receiveParameter(tetrahedronId);
        if (shapeParameters == null) {
            shapeParameters = new ShapeParameters(volumeTetrahedron, areaTetrahedron);
        }
        shapeParameters.setArea(areaTetrahedron);
        shapeParameters.setVolume(volumeTetrahedron);
        warehouse.updateParameters(tetrahedronId,shapeParameters);
    }
}
