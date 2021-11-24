package by.hardziyevich.task.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointsTest {

    @Test
    void testCompare(){
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(2,2,2);
        assertAll(()->{
            assertEquals(-1,p1.compareTo(p2));
            assertEquals(1,p2.compareTo(p1));
            assertEquals(0,p2.compareTo(p2));
        });
    }
}
