package by.ilya.catalog.service;

import java.util.List;

public interface CrudService<T> {

    T create(T state);

    List<T> getList();

    T getById(long id);

    void delete(long id);

}
