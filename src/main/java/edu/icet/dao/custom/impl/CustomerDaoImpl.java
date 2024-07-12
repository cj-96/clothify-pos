package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.CustomerDao;
import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;


public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;

//        try {
//            String sql = ("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");
//            return CrudUtil.execute(sql,
//                    entity.getId(),
//                    entity.getTitle(),
//                    entity.getName(),
//                    entity.getDob(),
//                    entity.getSalary(),
//                    entity.getAddress(),
//                    entity.getCity(),
//                    entity.getProvince(),
//                    entity.getPostalCode());
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public CustomerEntity get(String id) {
        return null;
    }

    @Override
    public boolean update(CustomerEntity dto) {
        return false;
    }
}
