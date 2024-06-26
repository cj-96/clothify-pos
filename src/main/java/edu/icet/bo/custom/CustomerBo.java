package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.Customer;

public interface CustomerBo extends SuperBo {

    Boolean saveCustomer(Customer customer);

    Boolean deleteById(String id);
}
