package by.hardziyevich.task.warehouse;

import by.hardziyevich.task.entity.Point;
import by.hardziyevich.task.entity.ShapeParameters;
import by.hardziyevich.task.exeption.SomeException;
import by.hardziyevich.task.warehouse.impl.WarehouseImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseImplTest {
    @Test
    void testWarehouse() throws SomeException {
        Warehouse warehouseImplTest = WarehouseImpl.getInstance();
        ShapeParameters shapeParameters = new ShapeParameters(1, 1);
        warehouseImplTest.addParameter(1, shapeParameters);
        assertAll(() -> {
            assertEquals(shapeParameters, warehouseImplTest.receiveParameter(1));
            assertThrows(SomeException.class, () -> warehouseImplTest.updateParameters(1, null));
            assertThrows(SomeException.class, () -> warehouseImplTest.addParameter(1, null));
        });
    }
}
