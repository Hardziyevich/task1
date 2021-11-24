package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import by.hardziyevich.task.warehouse.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositoryTest {
    private RepositoryImpl repository = RepositoryImpl.getRepository();
    private final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private final Shape rightShape = TetrahedronFactory.newShape(points);
    private final TetrahedronServiceImpl te = new TetrahedronServiceImpl();
    private final Warehouse warehouse = new Warehouse(rightShape.getId(), rightShape.getNameShape(), rightShape.getCoordinates(), te.areaTetrahedron(rightShape), te.volumeTetrahedron(rightShape));

    RepositoryTest() throws SomeException {
    }


    @Test
    void testSelectIdAndInsert() {
        assertTrue(repository.add(warehouse));
    }

    @Test
    void testRemove() {
        assertAll(() -> {
            repository.add(warehouse);
            assertEquals(warehouse, repository.remove(warehouse));
        });
    }

    @Test
    void testContain() {
        //assertTrue(repository.contains(warehouse));
    }

}
