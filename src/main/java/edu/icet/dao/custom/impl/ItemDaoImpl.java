package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.ItemDao;
import edu.icet.entity.ItemEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.CrudUtil;
import edu.icet.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity entity) {
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
        ItemEntity itemEntity = session.get(ItemEntity.class, id);
        session.remove(itemEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ItemEntity get(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ItemEntity itemEntity = session.get(ItemEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return itemEntity;
    }

    @Override
    public boolean update(ItemEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ItemEntity itemEntity = session.get(ItemEntity.class, dto.getId());
        try {
            BeanUtils.copyProperties(itemEntity, dto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        session.merge(itemEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public int getSupplierCount() {
        try{
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(*) FROM ItemEntity");
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
