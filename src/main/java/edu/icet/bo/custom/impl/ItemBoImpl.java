package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.ItemBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.ItemDao;
import edu.icet.dto.Item;
import edu.icet.dto.Supplier;
import edu.icet.entity.ItemEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public Boolean saveItem(Item item) {
        return itemDao.save(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public Boolean UpdateItem(Item item) {
        return itemDao.update(new ModelMapper().map(item, ItemEntity.class));
    }

    @Override
    public String generateId() {
        Integer count = itemDao.getSupplierCount();
        System.out.println(count);
        if (count == 0) {
            return "I001";
        }else{
            count++;
            return (String.format("I%03d", count));
        }
    }

    @Override
    public Item getItem(String id) {
        ItemEntity itemEntity = itemDao.get(id);
        if(itemEntity !=null) {
            return new ModelMapper().map(itemEntity, Item.class);
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return itemDao.delete(id);
    }
}
