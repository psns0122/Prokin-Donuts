package common;

import config.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderIdGenerator {
    public static String generateOrderId() {
        // 오늘 날짜를 "yyyyMMdd" 형식으로 생성
        String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        int count = getTodayOrderCount(today);
        // 순번은 3자리 (예:001)
        String sequence = String.format("%03d", count + 1);
        return "Order-" + today + sequence;
    }

    private static int getTodayOrderCount(String today) {
        int count = 0;
        // 날짜를 "yyyy-MM-dd" 형식으로 변환하여 orderDate와 비교 (DB의 orderDate는 DATE 타입)
        String formattedDate = today.substring(0,4) + "-" + today.substring(4,6) + "-" + today.substring(6,8);
        String sql = "SELECT COUNT(*) AS orderCount FROM `Order` WHERE orderDate = ?";
        try (Connection conn = DBConnectionManager.getConnection();
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
