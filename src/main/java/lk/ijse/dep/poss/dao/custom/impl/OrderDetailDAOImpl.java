package lk.ijse.dep.poss.dao.custom.impl;

import lk.ijse.dep.poss.dao.custom.OrderDetailDAO;
import lk.ijse.dep.poss.entity.OrderDetail;
import lk.ijse.dep.poss.entity.OrderDetailPK;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class OrderDetailDAOImpl implements OrderDetailDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<OrderDetail> findAll() {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery("SELECT * FROM OrderDetail");
//            List<OrderDetail> orderDetails = new ArrayList<>();
//            while (rst.next()) {
//                orderDetails.add(new OrderDetail(rst.getString(1),
//                        rst.getString(2),
//                        rst.getInt(3),
//                        rst.getBigDecimal(4)));
//            }
//            return orderDetails;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
        return session.createQuery("FROM entity.OrderDetail").list();
    }

    @Override
    public OrderDetail find(OrderDetailPK key) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM OrderDetail WHERE orderId=? AND itemCode=?");
//            OrderDetailPK lk.ijse.dep.poss.entity = new OrderDetailPK();
//            pstm.setObject(1, lk.ijse.dep.poss.entity.getOrderId());
//            pstm.setObject(2, lk.ijse.dep.poss.entity.getItemCode());
//            ResultSet rst = pstm.executeQuery();
//            if (rst.next()) {
//                return new OrderDetail(rst.getString(1),
//                        rst.getString(2),
//                        rst.getInt(3),
//                        rst.getBigDecimal(4));
//            }
//            return null;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
        return session.createNativeQuery("SELECT od.orderId, od.itemCode, od.orderQty, od.unitPrice FROM OrderDetail od WHERE orderId=?1 AND itemCode=?2",OrderDetail.class)
                .setParameter(1,key.getOrderId())
                .setParameter(2,key.getItemCode()).uniqueResult();
    }

    @Override
    public void save(OrderDetail entity) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
//            OrderDetail orderDetail = lk.ijse.dep.poss.entity;
//            pstm.setObject(1, orderDetail.getOrderDetailPK().getOrderId());
//            pstm.setObject(2, orderDetail.getOrderDetailPK().getItemCode());
//            pstm.setObject(3, orderDetail.getOrderQty());
//            pstm.setObject(4, orderDetail.getUnitPrice());
//            return pstm.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        session.save(entity);
    }

    @Override
    public void update(OrderDetail entity) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET orderQty=?, unitPrice=? WHERE orderId=? AND itemCode=?");
//            OrderDetail orderDetail = lk.ijse.dep.poss.entity;
//            pstm.setObject(3, orderDetail.getOrderDetailPK().getOrderId());
//            pstm.setObject(4, orderDetail.getOrderDetailPK().getItemCode());
//            pstm.setObject(1, orderDetail.getOrderQty());
//            pstm.setObject(2, orderDetail.getUnitPrice());
//            return pstm.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        session.update(entity);
    }

    @Override
    public void delete(OrderDetailPK key) {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.
//                    prepareStatement("DELETE FROM OrderDetail WHERE orderId=? AND itemCode=?");
//            OrderDetailPK lk.ijse.dep.poss.entity = new OrderDetailPK();
//            pstm.setObject(1, lk.ijse.dep.poss.entity.getOrderId());
//            pstm.setObject(2, lk.ijse.dep.poss.entity.getItemCode());
//            return pstm.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        session.delete(session.get(key.getItemCode(),key.getOrderId()));
    }
}
