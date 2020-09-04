package lk.ijse.dep.poss.business.custom.impl;

import lk.ijse.dep.poss.business.custom.ItemBO;
import lk.ijse.dep.poss.dao.DAOFactory;
import lk.ijse.dep.poss.dao.DAOType;
import lk.ijse.dep.poss.dao.custom.ItemDAO;
import lk.ijse.dep.poss.db.HibernateUtil;
import lk.ijse.dep.poss.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.dep.poss.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component

public class ItemBOImpl implements ItemBO {

    // Dependency Declaration
    @Autowired
    private ItemDAO itemDAO;

    public ItemBOImpl(){
        // Constructor Injection
//        this.itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
    }

    public String getNewItemCode() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx=null;

        String lastItemCode=null;
        try {
            tx=session.beginTransaction();

                lastItemCode = itemDAO.getLastItemCode();

            tx.commit();

        }catch (Throwable th){
            th.printStackTrace();
        }finally {
            session.clear();
        }


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

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);

        Transaction tx=null;
        List<ItemTM> items = new ArrayList<>();
        try {
            tx=session.beginTransaction();

            List<Item> allItems = itemDAO.findAll();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getItemCode(), item.getDescription(), item.getQtyOnHand(),
                    item.getUnitPrice().doubleValue()));
        }

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return items;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
//        return itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx=null;

        try {
            tx=session.beginTransaction();

            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.clear();
        }
    }

    public void deleteItem(String itemCode) throws Exception {
//        return itemDAO.delete(itemCode);
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx=null;

        try {
            tx=session.beginTransaction();

            itemDAO.delete(itemCode);

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.clear();
        }
    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {
//        return itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx=null;

        try {
            tx=session.beginTransaction();

            itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.clear();
        }
    }
}
