package repository;

import config.DBConnectionManager;
import vo.OrderVO;
import vo.OrderDetailVO;
import dto.OrderDTO;
import vo.OrderStatisticsDTO;
import java.sql.*;
import java.util.*;

public class OrderRepoImpl implements OrderRepo {

    @Override
    public String saveOrder(OrderVO order) {
        String newOrderId = common.OrderIdGenerator.generateOrderId();
        String sql = "INSERT INTO `Order` (orderId, orderQuantity, productId, orderDate, orderStatus, memberNo, authorityId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newOrderId);
            pstmt.setInt(2, 0); // 단일 주문 dummy 값
            pstmt.setString(3, "MULTI");
            pstmt.setDate(4, Date.valueOf(order.getOrderDate()));
            pstmt.setString(5, order.getOrderStatus());
            pstmt.setString(6, order.getMemberNo());
            pstmt.setString(7, order.getAuthorityId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("No rows affected");
            return newOrderId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveOrderDetail(OrderDetailVO detail) {
        String sql = "INSERT INTO OrderDetail (orderQuantity, productId, orderId) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detail.getOrderQuantity());
            pstmt.setString(2, detail.getProductId());
            pstmt.setString(3, detail.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }