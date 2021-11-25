package by.hardziyevich.task.warehouse;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WarehouseImplTest {
    private static final Warehouse warehouseImplTest = WarehouseImpl.getInstance();
    private static final ShapeParameters shapeParameters = new ShapeParameters(1, 1);
    @BeforeAll
    void setting() throws SomeException {
        warehouseImplTest.addParameter(1, shapeParameters);
    }
    @Test
    void testWarehouse(){
        assertAll(() -> {
            assertEquals(shapeParameters, warehouseImplTest.receiveParameter(1));
            assertThrows(SomeException.class, () -> warehouseImplTest.updateParameters(1, null));
            assertThrows(SomeException.class, () -> warehouseImplTest.addParameter(1, null));
        });
    }

    @AfterAll
    void clearWarehouse(){
        warehouseImplTest.removeAll();
    }
}
