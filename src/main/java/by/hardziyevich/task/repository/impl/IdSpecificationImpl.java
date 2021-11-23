package by.hardziyevich.task.repository.impl;

import by.hardziyevich.task.entity.Shape;
import by.hardziyevich.task.repository.Specification;
import by.hardziyevich.task.warehouse.Warehouse;

public class IdSpecificationImpl implements Specification {
    private int id;

    public IdSpecificationImpl(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Integer id,Warehouse warehouse) {
        return this.id == id;
    }
}
