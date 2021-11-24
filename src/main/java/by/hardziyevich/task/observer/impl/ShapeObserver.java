package by.hardziyevich.task.observer.impl;


import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.observer.Observer;
import by.hardziyevich.task.observer.ShapeEvent;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.validator.ValidatorTetrahedron;
import by.hardziyevich.task.warehouse.Warehouse;


public class ShapeObserver implements Observer {
    @Override
    public void update(ShapeEvent data) {
        Shape shape = data.getSource();
        RepositoryImpl repositoryImpl = RepositoryImpl.getRepository();
        boolean isTetrahedron;
        try {
            new ValidatorTetrahedron(shape.getCoordinates()).checkTetrahedron();
            isTetrahedron = true;
        } catch (SomeException e) {
            isTetrahedron = false;
        }
        if (isTetrahedron) {
            int tetrahedronId = shape.getId();
            TetrahedronServiceImpl tetrahedronService = new TetrahedronServiceImpl();
            Warehouse warehouse = repositoryImpl.selectId(tetrahedronId);
            double areaTetrahedron = tetrahedronService.areaTetrahedron(shape);
            double volumeTetrahedron = tetrahedronService.volumeTetrahedron(shape);
            if (warehouse == null) {
                warehouse = new Warehouse(tetrahedronId,shape.getNameShape(),shape.getCoordinates(),areaTetrahedron, volumeTetrahedron);
            }
            warehouse.setAreaTetrahedron(tetrahedronService.areaTetrahedron(shape));
            warehouse.setVolumeTetrahedron(tetrahedronService.volumeTetrahedron(shape));
            repositoryImpl.add(warehouse);
        }
    }
}
