import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.UUID;

public class OrderRepoImpl implements OrderRepo {

    @Override
    public String saveOrder(OrderVO order) {
        // 가독성 있는 주문번호 생성
        String newOrderId = OrderIdGenerator.generateOrderId();
        String sql = "INSERT INTO `Order` (orderId, orderQuantity, productId, orderDate, orderStatus, memberNo, authorityId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newOrderId);
            pstmt.setInt(2, 0);  // 단일 주문일 경우 dummy 값 사용 (여러 상품 주문은 OrderDetail에서 관리)
            pstmt.setString(3, "MULTI");
            // orderDate는 "yyyy-MM-dd" 형식의 문자열이어야 합니다.
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
        String sql = "INSERT INTO OrderDetail (orderDetailId, orderQuantity, productId, orderId) VALUES (NULL, ?, ?, ?)";
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

    @Override
    public OrderVO findOrderById(String orderId) {
        String sql = "SELECT orderId, orderDate, orderStatus, memberNo, authorityId FROM `Order` WHERE orderId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberNo"),
                            rs.getString("authorityId")
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
        String sql = "UPDATE `Order` SET orderDate = ?, orderStatus = ?, memberNo = ?, authorityId = ? WHERE orderId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(order.getOrderDate()));
            pstmt.setString(2, order.getOrderStatus());
            pstmt.setString(3, order.getMemberNo());
            pstmt.setString(4, order.getAuthorityId());
            pstmt.setString(5, order.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetailVO> findOrderDetailsByOrderId(String orderId) {
        List<OrderDetailVO> details = new ArrayList<>();
        String sql = "SELECT orderDetailId, orderQuantity, productId, orderId FROM OrderDetail WHERE orderId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
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
        String sql = "SELECT orderId, orderDate, orderStatus, memberNo, authorityId FROM `Order` WHERE orderStatus = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberNo"),
                            rs.getString("authorityId")
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
        String sql = "SELECT orderId, orderDate, orderStatus, memberNo, authorityId FROM `Order` WHERE memberNo = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, franchiseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberNo"),
                            rs.getString("authorityId")
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
        String sql = "SELECT orderId, orderDate, orderStatus, memberNo, authorityId FROM `Order` WHERE orderDate = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberNo"),
                            rs.getString("authorityId")
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
        String sql = "SELECT orderId, orderDate, orderStatus, memberNo, authorityId FROM `Order` WHERE orderDate BETWEEN ? AND ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new OrderVO(
                            rs.getString("orderId"),
                            rs.getDate("orderDate").toString(),
                            rs.getString("orderStatus"),
                            rs.getString("memberNo"),
                            rs.getString("authorityId")
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
        String monthStr = (month < 10 ? "0" + month : "" + month);
        String pattern = year + "-" + monthStr + "-%";
        String sqlCount = "SELECT COUNT(*) AS orderCount FROM `Order` WHERE memberNo = ? AND orderDate LIKE ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlCount)) {
            pstmt.setString(1, franchiseId);
            pstmt.setString(2, pattern);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) totalOrderRequests = rs.getInt("orderCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlProduct = "SELECT OD.productId, SUM(OD.orderQuantity) AS totalQuantity " +
                "FROM OrderDetail OD JOIN `Order` O ON OD.orderId = O.orderId " +
                "WHERE O.memberNo = ? AND O.orderDate LIKE ? GROUP BY OD.productId";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlProduct)) {
            pstmt.setString(1, franchiseId);
            pstmt.setString(2, pattern);
            try (ResultSet rs = pstmt.executeQuery()) {
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
        String sql = "SELECT OD.productId, SUM(OD.orderQuantity) AS totalPending " +
                "FROM OrderDetail OD JOIN `Order` O ON OD.orderId = O.orderId " +
                "WHERE O.orderStatus = '발주 승인 대기중' GROUP BY OD.productId";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
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
}