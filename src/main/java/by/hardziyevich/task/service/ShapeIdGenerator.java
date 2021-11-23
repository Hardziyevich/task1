package by.hardziyevich.task.service;

public class ShapeIdGenerator {

    private static int counter;

    private ShapeIdGenerator() {
    }

    public static int generateId() {
        return counter++;
    }
}
