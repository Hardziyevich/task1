package by.hardziyevich.task.repository;
import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.repository.impl.PointSpecificationImpl;
import by.hardziyevich.task.warehouse.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointSpecificationImplTest {
    private final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));

    @Test
    void testSpecify(){
        Warehouse warehouse = new Warehouse(0,0,points);
        PointSpecificationImpl test = new PointSpecificationImpl(points);
        assertTrue(test.specify(1,warehouse));
    }
}
