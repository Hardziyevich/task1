package by.my.validator;

import by.my.entity.Point;
import by.my.exeption.SomeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserTxt {

    private static final String REG_COORDINATE = "(\\d[.]\\d+\\s){2}(\\d[.]\\d+)";
    private final Path path;

    public ParserTxt(Path path) {
        this.path = path;
    }

    //Method is working and checks the correctness of the data
    public List<String> checker() throws SomeException {
        return readerFromTxt().stream()
                .filter(x -> x.matches(REG_COORDINATE))
                .collect(Collectors.toList());
    }

    //Method is working and reading line from txt file
    private List<String> readerFromTxt() throws SomeException {
        List<String> collect;
        try (Stream<String> lines = Files.lines(path)) {
            collect = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new SomeException(e.getMessage());
        }
        return collect;
    }

    //Creat list of Point. All strings must be the correctness.
    private List<Point> mapper() throws SomeException {
        return checker().stream()
                .map(x -> {
                    String[] s = x.split("\\s");
                    return new Point(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));})
                .collect(Collectors.toList());
    }

    public Map<String,List<Point>> builder() throws SomeException {
        Map<String,List<Point>> data = new HashMap<>();
        List<Point> list = mapper();
        data.put("Tetrahedron",list.subList(0,4));
        data.put("Plane",list.subList(4,list.size()));
        return data;
    }
}
