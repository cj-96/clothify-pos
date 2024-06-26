package edu.icet.dao.custom.impl;

import edu.icet.dao.CrudDao;
import edu.icet.dao.custom.UserDao;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean delete(String username, String password) {
        return false;
    }

    @Override
    public boolean isExist(String userName, String password) {

        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            String sql = "FROM UserEntity WHERE userType = 'Admin' AND userName = :userName AND password = :password";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            query.setParameter("password", password);

            boolean exists = !query.getResultList().isEmpty();

            session.getTransaction().commit();
            return exists;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }
}
