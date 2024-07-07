package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.UserBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.UserEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class UserBoImpl implements UserBo {

    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public Boolean saveUser(User user) {
        System.out.println("UserBo okay");
        return userDao.save(new ModelMapper().map(user, UserEntity.class));
    }

    @Override
    public Boolean isExist(String username, String password) {
        return userDao.isExist(username,password);
    }

    @Override
    public Boolean isExist(String email) {
        return userDao.isExist(email);
    }

    @Override
    public Boolean delete(String username, String password) {
        return null;
    }

    @Override
    public boolean updatePassword(String password, String email) {
        return userDao.updatePassword(password,email);
    }
}
