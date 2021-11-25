package by.hardziyevich.task.service;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TetrahedronFactoryServiceImplTest {
    private final TetrahedronServiceImpl tetrahedronUtility = new TetrahedronServiceImpl();
    private final Shape rightTetrahedron = TetrahedronFactory.newShape(List.of(new Point(0.5, 0.29, 0.82), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0, 0, 0)));
    private final Shape notRightTetrahedron = TetrahedronFactory.newShape(List.of(new Point(1.5, 3.29, 0.82), new Point(1.0, 0.0, 1.0), new Point(0.5, 2.87, 0.0), new Point(0, 0, 0)));
    private final List<Point> points = List.of(new Point(0.5, 0.58, 0.4), new Point(0.77, 0.13, 0.37), new Point(0.23, 0.13, 0.37));
    private final Shape expectedTetrahedron = TetrahedronFactory.newShape(List.of(new Point(0.5, 0.58, 0.4), new Point(0.77, 0.13, 0.37), new Point(0.23, 0.13, 0.37), new Point(0.5, 0.29, 0.82)));


    public TetrahedronFactoryServiceImplTest() throws SomeException {
    }

    @Test
    void testVolumeNewShape() throws SomeException {
        assertTrue(Arrays.stream(tetrahedronUtility.volumeNewShape(rightTetrahedron, tetrahedronUtility.cutTetrahedronByPoint(rightTetrahedron, points)))
                .allMatch(x -> Double.compare(x, 0.02) == 0 || Double.compare(x, 0.1) == 0));
    }


    @Test
    void testCutTetrahedronByPoint() throws SomeException {
        System.out.println(tetrahedronUtility.cutTetrahedronByPoint(rightTetrahedron, points));
        assertEquals(expectedTetrahedron.getCoordinates(), tetrahedronUtility.cutTetrahedronByPoint(rightTetrahedron, points).getCoordinates());
    }

    @Test
    void testIsRightTetrahedron() {
        assertAll(() -> {
            assertTrue(tetrahedronUtility.isRightTetrahedron(rightTetrahedron));
            assertFalse(tetrahedronUtility.isRightTetrahedron(notRightTetrahedron));
        });
    }

    @Test
    void testBelong() throws SomeException {
        assertTrue(tetrahedronUtility.isOwnerTetrahedron(rightTetrahedron, points));
    }

    @Test
    void testVolumeTetrahedron() {
        assertEquals(0.12, tetrahedronUtility.volumeTetrahedron(rightTetrahedron));
    }

    @Test
    void testAreaTetrahedron() {
        assertEquals(1.72, tetrahedronUtility.areaTetrahedron(rightTetrahedron));
    }

}
