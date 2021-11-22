package by.hardziyevich.task.service.impl;

import by.hardziyevich.task.entity.*;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.service.TetrahedronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static by.hardziyevich.task.service.ShapeService.*;
import static java.lang.Double.compare;

public class TetrahedronServiceImpl implements TetrahedronService {

    private static final Logger log = LoggerFactory.getLogger(TetrahedronServiceImpl.class);

    /**
     * Calculating the volume of new shapes
     * @param tetrahedronOld tetrahedron old
     * @param tetrahedronNew tetrahedron new
     * @return
     */
    @Override
    public Double[] volumeNewShape(Shape tetrahedronOld, Shape tetrahedronNew) {
        Double volumeTetrahedronNew = rounding(volumeTetrahedron(tetrahedronNew),2);
        Double volumeTetrahedronOld = rounding(volumeTetrahedron(tetrahedronOld),2);
        Double volumeUnknownShape = rounding(volumeTetrahedronOld - volumeTetrahedronNew,2);
        return new Double[]{volumeTetrahedronNew,volumeUnknownShape};
    }

    /**
     * We cut the tetrahedron with the cutting plane. The cutting plane must be parallel to the side of the tetrahedron.
     *
     * @param s1     tetrahedron
     * @param points plane
     * @return
     * @throws SomeException
     */
    @Override
    public Shape cutTetrahedronByPoint(Shape s1, List<Point> points) throws SomeException {
        if (!isOwnerTetrahedron(s1, points)) {
            log.warn("The plane that cuts our tetrahedron does not belongs to him");
            throw new SomeException("The plane that cuts our tetrahedron does not belongs to him");
        }
        Point[][] sideTetrahedron = creatSideTetrahedron(s1);
        List<Point> MayBeTetrahedron = new ArrayList<>();
        Map<Point, Integer> finder = new HashMap<>();
        for (Point point : points) {
            for (int i = 0; i < sideTetrahedron.length; i++) {
                if (checkPointBelong(sideTetrahedron[i][0], sideTetrahedron[i][1], point)) {
                    MayBeTetrahedron.add(point);
                    findAndPut(finder, sideTetrahedron[i][0]);
                    findAndPut(finder, sideTetrahedron[i][1]);
                }
            }
        }
        Integer integer = finder.values().stream().max(Integer::compareTo).get();
        MayBeTetrahedron.add(finder.entrySet()
                .stream()
                .filter(x -> x.getValue().equals(integer))
                .map(p -> p.getKey())
                .reduce((p1, p2) -> p1).get());
        Shape tetrahedronNew = Tetrahedron.newShape(MayBeTetrahedron);
        if (isRightTetrahedron(tetrahedronNew)) {
            log.warn("New shape isn`t tetrahedron");
            throw new SomeException("New shape isn`t tetrahedron");
        }
        return tetrahedronNew;
    }

    /**
     * Method check whether the plane that cuts our tetrahedron belongs to him
     *
     * @param tetrahedron tetrahedron
     * @param points      plane
     * @return true - belong, false - vice versa
     */
    @Override
    public boolean isOwnerTetrahedron(Shape tetrahedron, List<Point> points) {
        Point[][] sideTetrahedron = creatSideTetrahedron(tetrahedron);
        long count = points.stream().filter(x -> {
            boolean state = false;
            for (int i = 0; i < sideTetrahedron.length; i++) {
                if (checkPointBelong(sideTetrahedron[i][0], sideTetrahedron[i][1], x)) {
                    state = true;
                }
            }
            return state;
        }).count();
        return count == 3;
    }

    @Override
    public boolean isRightTetrahedron(Shape tetrahedron) {
        Point[][] side = creatSideTetrahedron(tetrahedron);
        double[] doubles = Arrays.stream(side).mapToDouble(p -> lengthSide(p[0], p[1])).toArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < doubles.length - 1; i += 2) {
            list.add(compare(doubles[i], doubles[i - 1]));
        }
        return list.stream().allMatch(x -> x == 0);
    }

    /**
     * Methode calculate volume of a tetrahedron
     *
     * @param tetrahedron input tetrahedron
     * @return volume
     */
    @Override
    public Double volumeTetrahedron(Shape tetrahedron) {
        Point p1 = tetrahedron.getCoordinates().get(0);
        Point p2 = tetrahedron.getCoordinates().get(1);
        Double result = Math.sqrt(2) * Math.pow(lengthSide(p1, p2), 3) / 12;
        return rounding(result, 2);
    }

    /**
     * Methode calculate area of a tetrahedron
     *
     * @param tetrahedron input tetrahedron
     * @return area of tetrahedron
     */
    @Override
    public Double areaTetrahedron(Shape tetrahedron) {
        return areaRegularTriangle(tetrahedron) * 4;
    }

    /**
     * Calculate area regular triangle
     *
     * @param shape input regular triangle
     * @return calculate area
     */
    private Double areaRegularTriangle(Shape shape) {
        Point p1 = shape.getCoordinates().get(0);
        Point p2 = shape.getCoordinates().get(1);
        Double result = Math.sqrt(3) * Math.pow(lengthSide(p1, p2), 2) / 4;
        return rounding(result, 2);
    }

    private Point[][] creatSideTetrahedron(Shape tetrahedron) {
        List<Point> tetrahedronPoint = tetrahedron.getCoordinates();
        Point[] ab = {tetrahedronPoint.get(0), tetrahedronPoint.get(1)};
        Point[] ac = {tetrahedronPoint.get(0), tetrahedronPoint.get(2)};
        Point[] ad = {tetrahedronPoint.get(0), tetrahedronPoint.get(3)};
        Point[] bd = {tetrahedronPoint.get(1), tetrahedronPoint.get(3)};
        Point[] cd = {tetrahedronPoint.get(2), tetrahedronPoint.get(3)};
        Point[] cb = {tetrahedronPoint.get(2), tetrahedronPoint.get(1)};
        return new Point[][]{ab, ac, ad, bd, cd, cb};
    }

    private void findAndPut(Map<Point, Integer> map, Point p) {
        if (!map.containsKey(p)) {
            map.put(p, 1);
        } else if (map.containsKey(p)) {
            map.compute(p, (k, v) -> v + 1);
        }
    }
}
