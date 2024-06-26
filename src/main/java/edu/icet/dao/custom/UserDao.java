package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity> {
    public boolean delete(String username, String password);

    public boolean isExist(String username, String password);
}
