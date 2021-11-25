package by.hardziyevich.task.warehouse;

import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;

public interface Warehouse {
    ShapeParameters addParameter(int id, ShapeParameters shapeParameters) throws SomeException;
    ShapeParameters receiveParameter(int id) throws SomeException;
    ShapeParameters updateParameters(int id, ShapeParameters shapeParameters) throws SomeException;
}
