package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.User;

public interface SupplierBo extends SuperBo {

    Boolean saveSupplier(User user);
}
