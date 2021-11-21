package by.my.utility;

import by.my.entity.Plane;
import by.my.entity.Point;
import by.my.entity.Shape;
import by.my.entity.Tetrahedron;
import by.my.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TetrahedronUtilityTest {
    TetrahedronUtility tetrahedronUtility = new TetrahedronUtility();
    Shape shape = new Tetrahedron().build(List.of(new Point(0.5,0.29,0.82),new Point(1.0,0.0,0.0),new Point(0.5,0.87,0.0),new Point(0,0,0)));
    Shape shapePlane = new Plane().build(List.of(new Point(0.5,0.58,0.4),new Point(0.77,0.13,0.37),new Point(0.23,0.13,0.37)));

    @Test
    void testCutTetrahedron() throws SomeException {
        assertEquals(new Tetrahedron().build(List.of(new Point(0.5,0.58,0.4),new Point(0.77,0.13,0.37),new Point(0.23,0.13,0.37),new Point(0.5,0.29,0.82))),
        tetrahedronUtility.cutTetrahedron(shape,shapePlane));
    }

    @Test
    void testbelong(){
        assertTrue(tetrahedronUtility.checkBelongPlaneShape(shape,shapePlane));
    }
    @Test
    void testCheckPoint(){
        assertTrue(tetrahedronUtility.checkPoint(
                new Point(1.0, 0.0, 0.0),
                new Point(0.5, 0.29, 0.82),
                new Point(0.77, 0.13, 0.37)
        ));
    }

    @Test
    void testVolumeTetrahedron(){
        assertEquals(0.119, tetrahedronUtility.volumeTetrahedron(shape));
    }
    @Test
    void testareaTetrahedron(){
        assertEquals(1.744, tetrahedronUtility.areaTetrahedron(shape));
    }
    @Test
    void testAreaRegularTriangle(){
        assertEquals(0.436, tetrahedronUtility.areaRegularTriangle(shape));
    }

    @Test
    void testRounding(){
        assertAll(()-> {
                    assertEquals(0.268, tetrahedronUtility.rounding(0.26788));
                    assertEquals(1234.132, tetrahedronUtility.rounding(1234.1323575));
                }
        );
    }

    @Test
    void testPowDifNum(){
        assertEquals(0.25, tetrahedronUtility.powDifNum(1,0.5));
    }

    @Test
    void testLengthSide(){
        assertEquals(1.000, tetrahedronUtility.lengthSide(new Point(1.0,0.0,0.0),new Point(0.5,0.866,0.0)));
    }


}
