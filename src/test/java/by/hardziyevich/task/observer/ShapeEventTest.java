package by.hardziyevich.task.observer;
import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeEventTest {
    @Test
    void testAllShapeEvent(){
        Shape shape = new Shape(1,"test", List.of(new Point(1,1,1)));
        ShapeEvent shapeEvent = new ShapeEvent(shape);
        assertEquals(shape,shapeEvent.getSource());
    }
}
