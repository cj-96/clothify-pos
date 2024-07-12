package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.SupplierBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.SuperDao;
import edu.icet.dao.custom.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.dto.User;
import edu.icet.entity.SupplierEntity;
import edu.icet.entity.UserEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {

    SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    @Override
    public Boolean saveSupplier(Supplier supplier) {
        return supplierDao.save(new ModelMapper().map(supplier, SupplierEntity.class));
    }

    @Override
    public Boolean UpdateSupplier(Supplier supplier) {

        return supplierDao.update(new ModelMapper().map(supplier, SupplierEntity.class));
    }

    @Override
    public String generateId() {
        Integer count = supplierDao.getSupplierCount();
        System.out.println(count);
        if (count == 0) {
            return "SP001";
        }else{
           count++;
            return (String.format("SP%03d", count));
        }

    }

    @Override
    public Supplier getSupplier(String id) {
        SupplierEntity supplierEntity = supplierDao.get(id);
        if(supplierEntity !=null) {
            return new ModelMapper().map(supplierEntity, Supplier.class);
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return supplierDao.delete(id);
    }
}
