package by.hardziyevich.task.observer;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.observer.impl.ShapeObserver;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.warehouse.Warehouse;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ShapeObserverTest {
    private final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private Shape notRightShape = new Shape(1, "test", List.of(new Point(1, 1, 1)));
    private final Shape rightShape = TetrahedronFactory.newShape(points);
    private TetrahedronServiceImpl te = new TetrahedronServiceImpl();
    private final Warehouse warehouse = WarehouseImpl.getInstance();
    private final ShapeParameters shapeParameters = new ShapeParameters(te.volumeTetrahedron(rightShape), te.areaTetrahedron(rightShape));

    ShapeObserverTest() throws SomeException {
    }

    @Test
    void testUpdate() throws SomeException {
        warehouse.addParameter(rightShape.getId(), shapeParameters);
        ShapeEvent shapeEvent = new ShapeEvent(rightShape);
        ShapeObserver shapeObserver = new ShapeObserver();
        shapeObserver.update(shapeEvent);
        assertAll(() -> {
            assertEquals(shapeParameters, warehouse.receiveParameter(rightShape.getId()));
            assertThrows(SomeException.class, () -> warehouse.updateParameters(1,null));
            assertThrows(SomeException.class, () -> warehouse.addParameter(1,null));
        });
    }
}
