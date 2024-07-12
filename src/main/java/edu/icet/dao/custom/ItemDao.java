package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.ItemEntity;
import edu.icet.entity.SupplierEntity;

public interface ItemDao extends CrudDao<ItemEntity> {
    int getSupplierCount();
}
