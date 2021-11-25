package by.hardziyevich.task.parser;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.validator.ValidatorTxt;

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
    private static final String REG_SPLIT = "\\s";
    private final Path path;

    public ParserTxt(Path path) throws SomeException {
        this.path = new ValidatorTxt(path).checkTxt();
    }

    //Method is working and checks the correctness of the data
    private List<String> checker() throws SomeException {
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
    public List<Point> getPoints() throws SomeException {
        return checker().stream()
                .map(x -> {
                    String[] s = x.split(REG_SPLIT);
                    return new Point(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
                })
                .collect(Collectors.toList());
    }
}
