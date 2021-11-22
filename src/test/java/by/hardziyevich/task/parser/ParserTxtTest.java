package by.hardziyevich.task.parser;
import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTxtTest {

    ParserTxt parserTxt = new ParserTxt(Path.of("D:\\TaskForEpam\\task1\\src\\test\\resources\\1.txt"));

    ParserTxtTest() throws SomeException {
    }

    @Test
    void testBuilder() throws SomeException {
        List<Point> data = new ArrayList<>();
        data.add(new Point(0.0,0.0,0.0));
        data.add(new Point(1.0,0.0,0.0));
        data.add(new Point(0.5,0.87,0.0));
        data.add(new Point(0.5, 0.29, 0.82));
        data.add(new Point(0.23,0.13,0.37));
        data.add(new Point(0.77,0.13,0.37));
        data.add(new Point(0.5,0.58,0.4));
        assertEquals(data,parserTxt.getPoints());
    }
}
