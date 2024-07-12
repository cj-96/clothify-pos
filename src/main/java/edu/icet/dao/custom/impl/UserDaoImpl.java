package edu.icet.dao.custom.impl;

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
    public UserEntity get(String id) {
        return null;
    }

    @Override
    public boolean update(UserEntity dto) {
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

    @Override
    public boolean isExist(String email) {
        System.out.println("hi");
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            String sql = "FROM UserEntity WHERE email = :email";
            Query query = session.createQuery(sql);
            query.setParameter("email", email);

            boolean exists = !query.getResultList().isEmpty();

            session.getTransaction().commit();
            return exists;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updatePassword(String password, String email) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String sql = "UPDATE UserEntity SET password = :password WHERE email = :email";
        Query query = session.createQuery(sql);
        query.setParameter("password", password);
        query.setParameter("email", email);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        if(i>0){
            return true;
        }
        return false;
    }
}
