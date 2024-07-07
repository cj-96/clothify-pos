package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.Customer;
import edu.icet.dto.User;

public interface UserBo extends SuperBo {
    Boolean saveUser(User user);

    Boolean isExist(String username,String password);

    Boolean isExist(String email);

    Boolean delete(String username, String password);

    boolean updatePassword(String text, String text1);
}
