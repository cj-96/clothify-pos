package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.CustomerBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.CustomerDao;
import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public Boolean saveCustomer(Customer dto) {
        return customerDao.save(new ModelMapper().map(dto, CustomerEntity.class));
    }

    @Override
    public Boolean deleteById(String id) {
        return false;
    }
}
