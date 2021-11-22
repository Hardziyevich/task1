package by.hardziyevich.task.validator;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.exeption.SomeException;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTetrahedronTest {
    private final List<Point> points1 = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private final List<Point> points2 = List.of(new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    @Test
    void testCheckTetrahedron() {
        assertAll(() -> {
            assertEquals(points1, new ValidatorTetrahedron(points1).checkTetrahedron());
            assertThrows(SomeException.class,()-> new ValidatorTetrahedron(points2).checkTetrahedron());
        });
    }

}
