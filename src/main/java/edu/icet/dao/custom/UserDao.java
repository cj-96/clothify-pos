package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity> {
    boolean delete(String username, String password);

    boolean isExist(String username, String password);

    boolean isExist(String email);

    boolean updatePassword(String password, String email);
}
