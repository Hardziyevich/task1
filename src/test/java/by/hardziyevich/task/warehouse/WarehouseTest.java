package by.hardziyevich.task.warehouse;

import by.hardziyevich.task.entity.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {
    @Test
    void testWarehouse() {
        Warehouse warehouseTest = new Warehouse(1, 2, List.of(new Point(1,1,1)));
        assertAll(() -> {
            assertEquals(1, warehouseTest.getAreaTetrahedron());
            assertEquals(2, warehouseTest.getVolumeTetrahedron());
            warehouseTest.setAreaTetrahedron(3);
            warehouseTest.setVolumeTetrahedron(3);
            assertEquals(3, warehouseTest.getAreaTetrahedron());
            assertEquals(3, warehouseTest.getVolumeTetrahedron());

        });
    }
}
