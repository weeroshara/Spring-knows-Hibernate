package lk.ijse.dep.poss.business.custom.impl;

import lk.ijse.dep.poss.business.custom.ItemBO;
import lk.ijse.dep.poss.dao.DAOFactory;
import lk.ijse.dep.poss.dao.DAOType;
import lk.ijse.dep.poss.dao.custom.ItemDAO;
import lk.ijse.dep.poss.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.dep.poss.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ItemBOImpl implements ItemBO {

    // Dependency Declaration
    @Autowired
    private ItemDAO itemDAO;

    public ItemBOImpl(){
        // Constructor Injection
//        this.itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
    }

    public String getNewItemCode() throws Exception {


        String lastItemCode=itemDAO.getLastItemCode();


        if (lastItemCode == null) {
            return "I001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }


    public List<ItemTM> getAllItems() throws Exception {

        List<ItemTM> items = new ArrayList<>();

            List<Item> allItems = itemDAO.findAll();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getItemCode(), item.getDescription(), item.getQtyOnHand(),
                    item.getUnitPrice().doubleValue()));
        }
        return items;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {

            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

    }

    public void deleteItem(String itemCode) throws Exception {

            itemDAO.delete(itemCode);

    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {

            itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));


    }
}
