package common.Order;

import config.DBUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderIdGenerator {

    // "Order-YYYYMMDDNNN" 형식으로 주문번호 생성
    public static String generateOrderId() {
        String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        int count = getTodayOrderCount(today);
        String sequence = String.format("%03d", count + 1);
        return "Order-" + today + sequence;
    }

    // 오늘 날짜에 해당하는 주문 건수를 DB에서 조회 (orderDate는 DATE 타입으로 저장됨)
    private static int getTodayOrderCount(String today) {
        int count = 0;
        // today 문자열을 "YYYY-MM-DD" 형식으로 변환
        String formattedDate = today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6, 8);
        String sql = "SELECT COUNT(*) AS orderCount FROM `Order` WHERE orderDate = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(formattedDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("orderCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
