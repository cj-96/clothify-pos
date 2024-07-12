package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.CrudUtil;
import edu.icet.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean save(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
        session.remove(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public SupplierEntity get(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return supplierEntity;
    }

    @Override
    public boolean update(SupplierEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, dto.getId());
        try {
            BeanUtils.copyProperties(supplierEntity, dto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        session.merge(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public int getSupplierCount() {
        try{
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(*) FROM SupplierEntity");
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
