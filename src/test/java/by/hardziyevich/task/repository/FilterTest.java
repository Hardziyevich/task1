package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.TetrahedronService;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FilterTest {
    private static final RepositoryImpl repository = RepositoryImpl.getRepository();
    private static final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private static final List<Point> points1 =List.of(new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0));
    private static final Shape rightShape = new Shape(1, "test", points);
    private static final Shape testShape = new Shape(1,"test1",points1);
    private static final TetrahedronService te = new TetrahedronServiceImpl();

    static {
        try {
            repository.add(rightShape);
        } catch (SomeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFilterName() {
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterName("test"))));
    }

    @Test
    void testFilterPointsFirstQuadrant() {
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterPointsFirstQuadrant())));
    }

    @Test
    void testFilterAreaRange() {
        double area = te.areaTetrahedron(rightShape);
        System.out.println(area);
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterAreaRange(1, 3))));
    }

    @Test
    void testFilterAreaRangeNegate() {
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterAreaRangeNegate(3, 1))));
    }

    @Test
    void testFilterVolumeRange() {
        double volume = te.volumeTetrahedron(rightShape);
        System.out.println(volume);
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterVolumeRange(0, 1))));
    }

    @Test
    void testFilterVolumeRangeNeg() {
        assertThat(List.of(rightShape), equalTo(repository.select(Filter.filterVolumeRangeNegate(1, 0))));
    }

    @Test
    void testFilterDistanceFromOrigin() throws SomeException {
        repository.add(testShape);
        assertThat(List.of(testShape), equalTo(repository.select(Filter.filterDistanceFromOrigin(new Point(0.5,0.5,0.5)))));
    }

    @AfterAll
    void clearRepository() throws SomeException {
        repository.removeAll();
    }

}
