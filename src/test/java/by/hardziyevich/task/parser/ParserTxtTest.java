package by.hardziyevich.task.parser;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTxtTest {

    String resourceName = "1.txt";
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(resourceName).getFile());
    String absolutePath = file.getAbsolutePath();

    @Test
    void testBuilder() throws SomeException {
        ParserTxt parserTxt = new ParserTxt(Path.of(absolutePath));
        List<Point> data = new ArrayList<>();
        data.add(new Point(0.0, 0.0, 0.0));
        data.add(new Point(1.0, 0.0, 0.0));
        data.add(new Point(0.5, 0.87, 0.0));
        data.add(new Point(0.5, 0.29, 0.82));
        data.add(new Point(0.23, 0.13, 0.37));
        data.add(new Point(0.77, 0.13, 0.37));
        data.add(new Point(0.5, 0.58, 0.4));
        assertEquals(data, parserTxt.getPoints());
    }

    @Test
    void testException() throws SomeException {
        assertAll(()->{
            assertThrows(SomeException.class, () -> new ParserTxt(null));
            assertThrows(SomeException.class, () -> new ParserTxt(Path.of("asdasdasd")));
        });
    }
}
