package by.hardziyevich.task.service.impl;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.service.ShapeService;

import java.text.DecimalFormat;

public class ShapeServiceImpl implements ShapeService {

    /**
     * The method checks if our point will fit the segment between points p1 and p2.
     * length(p1,p2) == length(p1,p3) + length(p2,p3)
     *
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point between p1 and p2
     * @return true - point between p1 and p2, false - isn`t
     */
    @Override
    public boolean checkPointBelong(Point p1, Point p2, Point p3) {
        return Double.compare(rounding(lengthSide(p1, p2), 1), rounding(lengthSide(p1, p3) + lengthSide(p2, p3), 1)) == 0;
    }

    /**
     * Calculate length between two point
     *
     * @param p1 first point
     * @param p2 second point
     * @return result will round to three decimal places
     */
    @Override
    public Double lengthSide(Point p1, Point p2) {
        double result = Math.sqrt(powDifNum(p1.getX(), p2.getX()) +
                powDifNum(p1.getY(), p2.getY()) +
                powDifNum(p1.getZ(), p2.getZ()));
        return rounding(result, 2);
    }

    /**
     * Calculate different between two digit and power result
     *
     * @param x first digit
     * @param y second digit
     * @return result will round to three decimal places
     */
    @Override
    public Double powDifNum(double x, double y) {
        double result = Math.pow((x - y), 2);
        return rounding(result, 2);
    }

    /**
     * Method round to different number decimal places
     *
     * @param d        input variable
     * @param accuracy input variable accuracy
     * @return rounding variable
     */
    @Override
    public Double rounding(double d, int accuracy) {
        StringBuilder strings = new StringBuilder("#.");
        for (int i = 0; i < accuracy; i++) {
            strings.append("#");
        }
        DecimalFormat decimalFormat = new DecimalFormat(strings.toString());
        return Double.valueOf(decimalFormat.format(d).replace(",", "."));
    }
}
