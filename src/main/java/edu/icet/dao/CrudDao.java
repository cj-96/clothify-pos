package edu.icet.dao;

import edu.icet.entity.SupplierEntity;

public interface CrudDao <T> extends SuperDao {
    boolean save(T dto);
    boolean delete(String id);
    T get(String id);
    boolean update(T dto);
}
