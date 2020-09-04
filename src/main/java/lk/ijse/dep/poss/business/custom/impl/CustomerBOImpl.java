package lk.ijse.dep.poss.business.custom.impl;

import lk.ijse.dep.poss.AppInitializer;
import lk.ijse.dep.poss.business.custom.CustomerBO;
import lk.ijse.dep.poss.dao.DAOFactory;
import lk.ijse.dep.poss.dao.DAOType;
import lk.ijse.dep.poss.dao.custom.CustomerDAO;
import lk.ijse.dep.poss.db.HibernateUtil;
import lk.ijse.dep.poss.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.dep.poss.util.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class CustomerBOImpl implements CustomerBO {

    // Field Injection
//    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    @Autowired
    private CustomerDAO customerDAO;

    public List<CustomerTM> getAllCustomers() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);

        Transaction tx=null;
        List<CustomerTM> customers = new ArrayList<>();
        try {
            tx = session.beginTransaction();

            List<Customer> allCustomers = customerDAO.findAll();

            for (Customer customer : allCustomers) {
                customers.add(new CustomerTM(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress()));
            }

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return customers;


//        List<Customer> allCustomers = customerDAO.findAll();
//        List<CustomerTM> customers = new ArrayList<>();
//        for (Customer customer : allCustomers) {
//            customers.add(new CustomerTM(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress()));
//        }
//        return customers;
    }

    public void saveCustomer(String id, String name, String address) throws Exception {
//        return customerDAO.save(new Customer(id, name, address));
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);

        Transaction tx=null;
        try {
            tx = session.beginTransaction();

            customerDAO.save(new Customer(id, name, address));

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }

    public void deleteCustomer(String customerId) throws Exception {
//        return customerDAO.delete(customerId);

        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);

        Transaction tx=null;
        try {
            tx = session.beginTransaction();

            customerDAO.delete(customerId);

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }

    public void updateCustomer(String name, String address, String customerId) throws Exception {
//        return customerDAO.update(new Customer(customerId, name, address));

        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);

        Transaction tx=null;
        try {
            tx = session.beginTransaction();

            customerDAO.update(new Customer(customerId, name, address));

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }

    }

    public String getNewCustomerId() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);

        Transaction tx=null;
        String lastCustomerId =null;
        try {
            tx=session.beginTransaction();

            lastCustomerId = customerDAO.getLastCustomerId();

            tx.commit();
        }catch (Throwable th){
            th.printStackTrace();
            tx.rollback();

        }finally {
            session.close();
        }




        if (lastCustomerId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }

    }

}
