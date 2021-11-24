package by.hardziyevich.task.repository;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.repository.impl.PredicateSpecificationImpl;
import by.hardziyevich.task.warehouse.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PredicateSpecificationImplTest {
    Warehouse warehouse = new Warehouse(1,"test", List.of(new Point(1,1,1),new Point(1,1,1)),2,2);
    Warehouse warehouse1 = new Warehouse(1,"test", List.of(new Point(-1,-1,-1),new Point(-1,-1,-1)),5,-2);

    @Test
    void testName(){
        PredicateSpecificationImpl predicateSpecification = new PredicateSpecificationImpl(Property.filterName("test"));
        assertTrue(predicateSpecification.specify(warehouse));
    }

    @Test
    void testFirstQuadrant() {
        PredicateSpecificationImpl predicateSpecification = new PredicateSpecificationImpl(Property.filterPointsFirstQuadrant());
        assertTrue(predicateSpecification.specify(warehouse));
    }

    @Test
    void testAreaRange(){
        PredicateSpecificationImpl predicateSpecification1 = new PredicateSpecificationImpl(Property.filterAreaRange(0,4));
        PredicateSpecificationImpl predicateSpecification2 = new PredicateSpecificationImpl(Property.filterAreaRangeNegate(0,4));
        assertAll(()->{
            assertTrue(predicateSpecification1.specify(warehouse));
            assertTrue(predicateSpecification2.specify(warehouse1));

        });
    }

    @Test
    void testVolumeRange(){
        PredicateSpecificationImpl predicateSpecification1 = new PredicateSpecificationImpl(Property.filterVolumeRange(0,4));
        PredicateSpecificationImpl predicateSpecification2 = new PredicateSpecificationImpl(Property.filterVolumeRangeNegate(0,4));
        assertAll(()->{
            assertTrue(predicateSpecification1.specify(warehouse));
            assertTrue(predicateSpecification2.specify(warehouse1));

        });
    }
    @Test
    void testRangePoint(){
        PredicateSpecificationImpl predicateSpecification1 = new PredicateSpecificationImpl(Property.filterDistanceFromOrigin(new Point(2,2,2)));
        predicateSpecification1.specify(warehouse);
        assertTrue(predicateSpecification1.specify(warehouse));
    }
}
