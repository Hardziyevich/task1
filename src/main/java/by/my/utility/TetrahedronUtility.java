package by.my.utility;

import by.my.entity.*;
import by.my.exeption.SomeException;
import by.my.validator.ValidatorTetrahedron;

import java.text.DecimalFormat;
import java.util.*;

public class TetrahedronUtility {

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public ShapeFactory shapeFactory(String string) throws SomeException {
        if("Tetrahedron".equals(string)) {
            return new Tetrahedron();
        }
        else if("Plane".equals(string)){
            return new Plane();
        }
        else{
            throw new SomeException("Object does not exist");
        }
    }

    public Shape buildShape(ShapeFactory shapeFactory,Map<String,List<Point>> points) throws SomeException {
        List<Point> list = null;
        if(shapeFactory instanceof Tetrahedron){
            list = points.get("Tetrahedron");
            return shapeFactory.build(new ValidatorTetrahedron(list).checkTetrahedron());
        }
        else if(shapeFactory instanceof Plane){
            list = points.get("Plane");
            return shapeFactory.build(list);
        }
        else {
            throw new SomeException("Object does not exist");
        }
    }

    /**
     *
     * @param s1 tetrahedron
     * @param s2 plane
     * @return
     * @throws SomeException
     */
    public Shape cutTetrahedron(Shape s1,Shape s2) throws SomeException {
        if(!checkBelongPlaneShape(s1, s2)){
            throw new SomeException("The plane that cuts our tetrahedron does not belongs to him");
        }
        List<Point> plane = s2.getCoordinates();
        Point[][] sideTetrahedron = creatSideTetrahedron(s1);
        List<Point> newTetrahedron = new ArrayList<>();
        List<Point> unknownShape = new ArrayList<>();
        Map<Point,Integer> finder = new HashMap<>();
        for (Point point : plane) {
            for (int i = 0; i < sideTetrahedron.length; i++) {
                if (checkPoint(sideTetrahedron[i][0], sideTetrahedron[i][1], point)) {
                    newTetrahedron.add(point);
                    findAndPut(finder,sideTetrahedron[i][0]);
                    findAndPut(finder,sideTetrahedron[i][1]);
                }
            }
        }
        Integer integer = finder.values().stream().max(Integer::compareTo).get();
        newTetrahedron.add(finder.entrySet()
                        .stream()
                .filter(x-> x.getValue().equals(integer))
                .map(p->p.getKey())
                .reduce((p1,p2) -> p1).get());

        return buildShape(shapeFactory("Tetrahedron"),Map.of("Tetrahedron",newTetrahedron));
    }

    private void findAndPut(Map<Point,Integer> map,Point p){
        if(!map.containsKey(p)){
            map.put(p,1);
        }
        else if(map.containsKey(p)){
            map.compute(p,(k,v)-> v + 1);
        }
    }

    /**
     * Method check whether the plane that cuts our tetrahedron belongs to him
     * @param s1 tetrahedron
     * @param s2 plane
     * @return true - belong, false - vice versa
     */
    public boolean checkBelongPlaneShape(Shape s1,Shape s2){
        List<Point> plane = s2.getCoordinates();
        Point[][] sideTetrahedron = creatSideTetrahedron(s1);
        long count = plane.stream().filter(x -> {
            boolean state = false;
            for (int i = 0; i < sideTetrahedron.length; i++) {
                if (checkPoint(sideTetrahedron[i][0], sideTetrahedron[i][1], x)) {
                    state = true;
                }
            }
            return state;
        }).count();
        return count == 3;
    }

    private Point[][] creatSideTetrahedron(Shape tetrahedron){
        List<Point> tetrahedronPoint = tetrahedron.getCoordinates();
        Point[] ab = {tetrahedronPoint.get(0),tetrahedronPoint.get(1)};
        Point[] ac = {tetrahedronPoint.get(0),tetrahedronPoint.get(2)};
        Point[] ad = {tetrahedronPoint.get(0),tetrahedronPoint.get(3)};
        Point[] bd = {tetrahedronPoint.get(1),tetrahedronPoint.get(3)};
        Point[] cd = {tetrahedronPoint.get(2),tetrahedronPoint.get(3)};
        Point[] cb = {tetrahedronPoint.get(2), tetrahedronPoint.get(1)};
        return new Point[][]{ab,ac,ad,bd,cd,cb};
    }

    /**
     * The method checks if our point will fit the segment between points p1 and p2.
     * length(p1,p2) == length(p1,p3) + length(p2,p3)
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point between p1 and p2
     * @return true - point between p1 and p2, false - isn`t
     */
    public boolean checkPoint(Point p1,Point p2,Point p3){
        return Double.compare(rounding(lengthSide(p1, p2),2),rounding(lengthSide(p1,p3)+lengthSide(p2,p3),2)) == 0;
    }

    /**
     * Methode calculate volume of a tetrahedron
     * @param tetrahedron input tetrahedron
     * @return volume
     */
    public Double volumeTetrahedron(Shape tetrahedron){
        Point p1 = tetrahedron.getCoordinates().get(0);
        Point p2 = tetrahedron.getCoordinates().get(1);
        Double result = Math.sqrt(2) * Math.pow(lengthSide(p1, p2), 3) / 12;
        return rounding(result);
    }

    /**
     * Methode calculate area of a tetrahedron
     * @param tetrahedron input tetrahedron
     * @return area of tetrahedron
     */
    public Double areaTetrahedron(Shape tetrahedron){
        return areaRegularTriangle(tetrahedron)*4;
    }
    /**
     * Calculate area regular triangle
     *
     * @param shape input regular triangle
     * @return calculate area
     */
    public Double areaRegularTriangle(Shape shape) {
        Point p1 = shape.getCoordinates().get(0);
        Point p2 = shape.getCoordinates().get(1);
        Double result = Math.sqrt(3) * Math.pow(lengthSide(p1, p2), 2) / 4;
        return rounding(result);
    }

    /**
     * Calculate length between two point
     *
     * @param p1 first point
     * @param p2 second point
     * @return result will round to three decimal places
     */
    public Double lengthSide(Point p1, Point p2) {
        double result = Math.sqrt(powDifNum(p1.getX(), p2.getX()) +
                powDifNum(p1.getY(), p2.getY()) +
                powDifNum(p1.getZ(), p2.getZ()));
        return rounding(result);
    }

    /**
     * Calculate different between two digit and power result
     *
     * @param x first digit
     * @param y second digit
     * @return result will round to three decimal places
     */
    public Double powDifNum(double x, double y) {
        double result = Math.pow((x - y), 2);
        return rounding(result);
    }

    /**
     * Method round to three decimal places
     *
     * @param d input variable
     * @return rounding variable
     */
    public Double rounding(double d) {
        return rounding(d,3);
    }

    private Double rounding(double d, int places){
        StringBuilder strings = new StringBuilder("#.");
        for (int i = 0; i < places; i++) {
            strings.append("#");
        }
        DecimalFormat decimalFormat = new DecimalFormat(strings.toString());
        return Double.valueOf(decimalFormat.format(d).replace(",", "."));
    }
}
