package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.Item;


public interface ItemBo extends SuperBo {
    Boolean saveItem(Item item);

    Boolean UpdateItem(Item item);

    String generateId();

    Item getItem(String id);

    Boolean delete(String id);
}
