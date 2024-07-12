package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.Supplier;
import edu.icet.dto.User;

public interface SupplierBo extends SuperBo {

    Boolean saveSupplier(Supplier supplier);

    Boolean UpdateSupplier(Supplier supplier);

    String generateId();

    Supplier getSupplier(String id);

    Boolean delete(String id);
}
