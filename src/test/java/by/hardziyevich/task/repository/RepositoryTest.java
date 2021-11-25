package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class RepositoryTest {
    private final RepositoryImpl repository = RepositoryImpl.getRepository();
    private final List<Point> points = List.of(new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0));
    private final Shape rightShape = new Shape(1,"test",points);

    RepositoryTest() throws SomeException {
    }


    @Test
    void testAdd() throws SomeException {
        assertAll(() -> {
            assertTrue(repository.add(rightShape));
            assertThrows(SomeException.class,()->repository.add(null));
        });
    }

    @Test
    void testRemove() {
        assertAll(() -> {
            repository.add(rightShape);
            assertTrue(repository.remove(rightShape));
            assertThrows(SomeException.class,()->repository.remove(null));
        });
    }

    @Test
    void testContain() throws SomeException {
        repository.add(rightShape);
        assertTrue(repository.contains(rightShape));
    }

    @Test
    void testSelectId() throws SomeException {
        Shape shape1 = new Shape(4,"test",points);
        repository.add(shape1);
        assertThat(shape1,equalTo(repository.selectId(4)));
    }

    @Test
    void testSelect() throws SomeException {
        Shape shape1 = new Shape(3,"test",points);
        repository.add(shape1);
        assertThat(List.of(shape1),equalTo(repository.select((Predicate<Shape>) x-> x.getId() == 3)));
    }

    @Test
    void testSelectSpecification() throws SomeException {
        Shape shape1 = new Shape(5,"test",points);
        repository.add(shape1);
        assertThat(List.of(shape1),equalTo(repository.select((Specification) x-> x.getId() == 5)));
    }

}
