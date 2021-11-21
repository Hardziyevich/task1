package by.my;

import by.my.entity.Point;
import by.my.entity.Shape;
import by.my.entity.ShapeFactory;
import by.my.exeption.SomeException;
import by.my.utility.TetrahedronUtility;
import by.my.validator.ParserTxt;
import by.my.validator.ValidatorTxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger(Main.class);
        Path path = null;
        try {
            path = new ValidatorTxt(Path.of("D:\\TaskForEpam\\task1\\src\\main\\resources\\1.tx")).builder();
            ParserTxt parserTxt = new ParserTxt(path);
            Map<String, List<Point>> points = parserTxt.builder();
            TetrahedronUtility tetrahedronUtility = new TetrahedronUtility();
            List<ShapeFactory> shapeFactoryList = new ArrayList<>();
            for (String s : points.keySet()) {
                shapeFactoryList.add(tetrahedronUtility.shapeFactory(s));
            }
            List<Shape> shapes = shapeFactoryList.stream().map(x-> {
                Shape shape = null;
                try {
                    shape = tetrahedronUtility.buildShape(x,points);
                } catch (SomeException e) {
                    log.warn("Something wrong:",e);
                }
                return shape;
            }).collect(Collectors.toList());


        } catch (SomeException e) {
            log.warn("Something wrong:",e);
        }
    }
}
