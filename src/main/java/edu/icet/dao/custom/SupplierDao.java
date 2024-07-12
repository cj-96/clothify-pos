package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.entity.UserEntity;

public interface SupplierDao extends CrudDao<SupplierEntity> {


    int getSupplierCount();
}
