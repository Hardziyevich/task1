package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.factory.TetrahedronFactory;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.repository.impl.RepositorySorterImpl;
import by.hardziyevich.task.service.TetrahedronService;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositorySorterTest {
    private static final RepositoryImpl repository = RepositoryImpl.getRepository();
    private static final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private static final List<Point> points1 =List.of(new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0));
    private static final Shape rightShape = new Shape(1, "test", points);
    private static final Shape testShape = new Shape(2,"test1",points1);
    static {
        try {
            repository.add(testShape);
            repository.add(rightShape);
        } catch (SomeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSortedById(){
        RepositorySorterImpl sorter = new RepositorySorterImpl(repository.selectAll());
        assertThat(List.of(rightShape,testShape),equalTo(sorter.sortById()));
    }

    @Test
    void testSortedByName(){
        RepositorySorterImpl sorter = new RepositorySorterImpl(repository.selectAll());
        assertThat(List.of(rightShape,testShape),equalTo(sorter.sortByName()));
    }

    @Test
    void testSortedByFirstPoint(){
        RepositorySorterImpl sorter = new RepositorySorterImpl(repository.selectAll());
        assertThat(List.of(rightShape,testShape),equalTo(sorter.sortByFirstPointX()));
    }

    @Test
    void testSortedByFirstPointY(){
        RepositorySorterImpl sorter = new RepositorySorterImpl(repository.selectAll());
        assertThat(List.of(rightShape,testShape),equalTo(sorter.sortByFirstPointY()));
    }

    @AfterAll
    void clearRepository() throws SomeException {
        repository.removeAll();
    }
}
