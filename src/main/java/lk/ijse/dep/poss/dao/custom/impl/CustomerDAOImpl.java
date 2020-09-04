package lk.ijse.dep.poss.dao.custom.impl;

import lk.ijse.dep.poss.dao.custom.CustomerDAO;
import lk.ijse.dep.poss.entity.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class CustomerDAOImpl implements CustomerDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session=session;

    }

    @Override
    public String getLastCustomerId() {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
//            if (rst.next()){
//                return rst.getString(1);
//            }else{
//                return null;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
        return (String) session.createNativeQuery("SELECT c.customerId FROM Customer c ORDER BY c.customerId DESC LIMIT 1").uniqueResult();

    }

    @Override
    public List<Customer> findAll() {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            Statement stm = connection.createStatement();
//            ResultSet resultSet = stm.executeQuery("SELECT * FROM Customer");
//            ArrayList<Customer>customers = new ArrayList<>();
//            while (resultSet.next()){
//                customers.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
//            }
//            return customers;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return session.createQuery("FROM lk.ijse.dep.poss.entity.Customer").list();
    }

    @Override
    public Customer find(String key) {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
//            pstm.setObject(1,key);
//            ResultSet resultSet = pstm.executeQuery();
//            if(resultSet.next()){
//                return new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
//            }
//            return null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return (Customer) session.createQuery("SELECT c.customerId , c.customerName, c.customerAddress FROM lk.ijse.dep.poss.entity.Customer c").getSingleResult();
        return session.get(Customer.class,key);
    }

    @Override
    public void save(Customer entity) {
//        Connection connection = DBConnection.getInstance().getConnection();
//        Customer customer =  lk.ijse.dep.poss.entity;
//        try {
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
//            pstm.setObject(1,customer.getCustomerId());
//            pstm.setObject(2,customer.getCustomerName());
//            pstm.setObject(3,customer.getCustomerAddress());
//            return pstm.executeUpdate()>0;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return false;
//        }
        session.save(entity);
    }

    @Override
    public void update(Customer entity) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Customer customer =  lk.ijse.dep.poss.entity;
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
//            pstm.setObject(1, customer.getCustomerId());
//            pstm.setObject(2, customer.getCustomerName());
//            pstm.setObject(3, customer.getCustomerAddress());
//            return pstm.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        session.update(entity);
    }

    @Override
    public void delete(String key) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
//            pstm.setObject(1, key);
//            return pstm.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        session.delete(session.get(Customer.class,key));
    }


}
