package repository;

import config.DBUtil;
import dto.orderDTO.OrderStatisticsDTO;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
//
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrderRepoImpl implements OrderRepo {

    @Override
    public String saveOrder(OrderVO order) {
        String sql = "{CALL sp_saveOrder(?, ?, ?, ?)}";
        String newOrderId = null;
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, order.getOrderDate());
            cs.setString(2, order.getOrderStatus());
            cs.setInt(3, Integer.parseInt(order.getMemberId()));

            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            newOrderId = String.valueOf(cs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOrderId;
    }
    @Override
    public void saveOrderDetail(OrderDetailVO detail) {
        String sql = "{CALL sp_saveOrderDetail(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, detail.getOrderQuantity());
            cs.setInt(2, Integer.parseInt(detail.getProductId()));
            cs.setInt(3, Integer.parseInt(detail.getOrderId()));

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderVO findOrderById(String orderId) {
        String sql = "{CALL sp_findOrderById(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, orderId);
            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberId")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrder(OrderVO order) {
        String sql = "{CALL sp_updateOrder(?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setDate(1, Date.valueOf(order.getOrderDate()));
            cstmt.setString(2, order.getOrderStatus());
            cstmt.setString(3, order.getMemberId());
            cstmt.setString(4, order.getOrderId());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetailVO> findOrderDetailsByOrderId(String orderId) {
        List<OrderDetailVO> details = new ArrayList<>();
        String sql = "{CALL sp_findOrderDetailsByOrderId(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, orderId);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    details.add(new OrderDetailVO(
                            rs.getInt("orderDetailId"),
                            rs.getInt("orderQuantity"),
                            rs.getString("productId"),
                            rs.getString("orderId")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public List<OrderVO> findOrdersByStatus(String status) {
        List<OrderVO> orders = new ArrayList<>();
        String sql = "{CALL sp_findOrdersByStatus(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, status);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberId")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<OrderVO> findOrdersByFranchiseId(String franchiseId) {
        List<OrderVO> orders = new ArrayList<>();
        String sql = "{CALL sp_findOrdersByFranchiseId(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, franchiseId);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberId")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<OrderVO> findOrdersByDate(String date) {
        List<OrderVO> orders = new ArrayList<>();
        String sql = "{CALL sp_findOrdersByDate(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberId")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<OrderVO> findOrdersByDateRange(String startDate, String endDate) {
        List<OrderVO> orders = new ArrayList<>();
        String sql = "{CALL sp_findOrdersByDateRange(?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setDate(1, Date.valueOf(startDate));
            cstmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberId")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month) {
        int totalOrderRequests = 0;
        Map<String, Integer> productStats = new HashMap<>();
        String monthStr = (month < 10 ? "0" + month : String.valueOf(month));
        String pattern = year + "-" + monthStr + "-%";

        String sqlCount = "{CALL sp_getOrderCountByFranchiseAndMonth(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sqlCount)) {
            cstmt.setString(1, franchiseId);
            cstmt.setString(2, pattern);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            totalOrderRequests = cstmt.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlProduct = "{CALL sp_getProductStatsByFranchiseAndMonth(?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sqlProduct)) {
            cstmt.setString(1, franchiseId);
            cstmt.setString(2, pattern);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    int totalQuantity = rs.getInt("totalQuantity");
                    productStats.put(productId, totalQuantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new OrderStatisticsDTO(totalOrderRequests, productStats);
    }

    @Override
    public Map<String, Integer> getPendingOrderQuantities() {
        Map<String, Integer> pendingMap = new HashMap<>();
        String sql = "{CALL sp_getPendingOrderQuantities()}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                String productId = rs.getString("productId");
                int totalPending = rs.getInt("totalPending");
                pendingMap.put(productId, totalPending);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingMap;
    }

    @Override
    public OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        int lastMonth = cal.get(Calendar.MONTH) + 1;
        int lastYear = cal.get(Calendar.YEAR);
        return getOrderStatisticsByFranchiseAndMonth(franchiseId, lastYear, lastMonth);
    }
}
