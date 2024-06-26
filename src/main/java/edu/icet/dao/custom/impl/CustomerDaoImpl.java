package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.CustomerDao;
import edu.icet.entity.CustomerEntity;
import edu.icet.util.CrudUtil;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();


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
}
