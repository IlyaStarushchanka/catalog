package com.ilya.catalog.service.admin;

import java.util.List;

public interface CrudService<T> {

    T create(T state);

    List<T> getList();

    T getById(long id);

    void delete(long id);

}
