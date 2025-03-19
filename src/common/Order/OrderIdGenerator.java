package common.Order;

import config.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderIdGenerator {

    // "Order-YYYYMMDDNNN" 형식으로 주문번호 생성
    public static String generateOrderId() {
        // 오늘 날짜(YYYYMMDD) 문자열
        String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        // 오늘 날짜에 해당하는 주문 건수 조회 (이미 발행된 주문 건수)
        int count = getTodayOrderCount(today);
        // 다음 순번: count+1, 3자리 형식(예: 001, 002, ...)
        String sequence = String.format("%03d", count + 1);
        return "Order-" + today + sequence;
    }

    // 오늘 날짜(YYYYMMDD)에 해당하는 주문 건수를 DB에서 조회
    private static int getTodayOrderCount(String today) {
        int count = 0;
        // orderDate는 DATE 타입으로 저장된다고 가정합니다.
        // today를 "YYYY-MM-DD" 형태로 변환
        String formattedDate = today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6, 8);
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
