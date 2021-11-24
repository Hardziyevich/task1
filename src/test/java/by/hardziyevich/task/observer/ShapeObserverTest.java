package by.hardziyevich.task.observer;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.observer.impl.ShapeObserver;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.warehouse.Warehouse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ShapeObserverTest {
    private final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private Shape notRightShape = new Shape(1, "test", List.of(new Point(1, 1, 1)));
    private Shape rightShape = TetrahedronFactory.newShape(points);
    private RepositoryImpl repositoryImpl = RepositoryImpl.getRepository();
    private TetrahedronServiceImpl te = new TetrahedronServiceImpl();
    Warehouse warehouse = new Warehouse(rightShape.getId(),rightShape.getNameShape(),rightShape.getCoordinates(),te.areaTetrahedron(rightShape), te.volumeTetrahedron(rightShape));

    ShapeObserverTest() throws SomeException {
    }

    @Test
    void testUpdate() {
        repositoryImpl.add(warehouse);
        ShapeEvent shapeEvent = new ShapeEvent(rightShape);
        ShapeObserver shapeObserver = new ShapeObserver();
        shapeObserver.update(shapeEvent);
        assertEquals(warehouse, repositoryImpl.selectId(rightShape.getId()));
    }
}
