package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.UserEntity;

public interface SupplierDao extends CrudDao<UserEntity> {

    Integer getSupplierCount();
}
