package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.SupplierDao;
import edu.icet.entity.UserEntity;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(UserEntity dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Integer getSupplierCount() {
        return null;
    }
}
