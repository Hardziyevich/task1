package by.hardziyevich.task.service;

import by.hardziyevich.task.entity.Point;
import org.junit.jupiter.api.Test;

import static by.hardziyevich.task.service.ShapeService.*;
import static by.hardziyevich.task.service.ShapeService.lengthSide;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeServiceTest {
    @Test
    void testRounding(){
        assertAll(()-> {
                    assertEquals(0.27, rounding(0.26788,2));
                    assertEquals(1234.13, rounding(1234.1323575,2));
                }
        );
    }

    @Test
    void testPowDifNum(){
        assertEquals(0.25, powDifNum(1,0.5));
    }

    @Test
    void testLengthSide(){
        assertEquals(1.000, lengthSide(new Point(1.0,0.0,0.0),new Point(0.5,0.866,0.0)));
    }

    @Test
    void testCheckPoint(){
        assertTrue(checkPointBelong(
                new Point(1.0, 0.0, 0.0),
                new Point(0.5, 0.29, 0.82),
                new Point(0.77, 0.13, 0.37)
        ));
    }
}
