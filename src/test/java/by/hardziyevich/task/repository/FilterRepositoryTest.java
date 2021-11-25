package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.repository.impl.RepositoryImpl;
import by.hardziyevich.task.service.TetrahedronService;
import by.hardziyevich.task.service.impl.TetrahedronServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static by.hardziyevich.task.repository.PropertyFilter.PropertyParameter.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FilterRepositoryTest {
    private final RepositoryImpl repository = RepositoryImpl.getRepository();
    private final List<Point> points = List.of(new Point(0.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(0.5, 0.87, 0.0), new Point(0.5, 0.29, 0.82));
    private final List<Point> points1 =List.of(new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0), new Point(1.0, 1.0, 1.0));
    private final Shape rightShape = new Shape(1, "test", points);
    private final Shape testShape = new Shape(1,"test1",points1);
    private final TetrahedronService ts = new TetrahedronServiceImpl();

    @BeforeAll
    void settingRepository() throws SomeException {
        repository.add(rightShape);
    }

    @Test
    void testFilterName() throws SomeException {
        FilterRepository filter = new FilterRepository();
        assertThat(List.of(rightShape), equalTo(repository.select(filter.filterName("test"))));
    }

    @Test
    void testFilterDistanceFromOrigin() throws SomeException {
        FilterRepository filter = new FilterRepository();
        repository.add(testShape);
        assertThat(List.of(testShape), equalTo(repository.select(filter.filterPointQuadrant(new Point(0.5,0.5,0.5)))));
    }

    @Test
    void testFilterAreaRange() {
        FilterRepository filter = new FilterRepository();
        double area = ts.areaTetrahedron(rightShape);
        System.out.println(area);
        assertThat(List.of(rightShape), equalTo(repository.select(filter.filterParameters(AREA,1, 3))));
    }

    @Test
    void testFilterAreaVolume() {
        FilterRepository filter = new FilterRepository();
        double volume = ts.volumeTetrahedron(rightShape);
        System.out.println(volume);
        assertThat(List.of(rightShape), equalTo(repository.select(filter.filterParameters(VOLUME,0, 0.13))));
    }

    @AfterAll
    void clearRepository() throws SomeException {
        repository.removeAll();
    }

}
