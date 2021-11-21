package by.my.validator;
import by.my.entity.Point;
import by.my.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTxtTest {

    ParserTxt parserTxt = new ParserTxt(Path.of("D:\\TaskForEpam\\task1\\src\\test\\resources\\1.txt"));

    @Test
    void testBuilder() throws SomeException {
        Map<String,List<Point>> data = new HashMap<>();
        data.put("Tetrahedron",List.of(new Point(0.0,0.0,0.0),new Point(1.0,0.0,0.0),new Point(0.5,0.87,0.0),new Point(0.5, 0.29, 0.82)));
        data.put("Plane",List.of(new Point(0.23,0.13,0.37),new Point(0.77,0.13,0.37),new Point(0.5,0.58,0.4)));
        assertEquals(data,parserTxt.builder());
    }
}
