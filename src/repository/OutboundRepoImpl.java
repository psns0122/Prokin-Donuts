package repository;

import config.DBUtil;
import vo.outboundVO.OutboundDetailVO;
import vo.outboundVO.OutboundVO;

import java.sql.*;

public class OutboundRepoImpl implements OutboundRepo {
    @Override
    public String saveOutbound(OutboundVO outbound) {
        String sql = "INSERT INTO Outbound (outboundDate, outboundStatus) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, new java.sql.Date(outbound.getOutboundDate().getTime()));
            pstmt.setString(2, outbound.getOutboundStatus());
            int affected = pstmt.executeUpdate();
            if (affected == 0) throw new SQLException("No rows affected");
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return String.valueOf(rs.getInt(1));
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveOutboundDetail(OutboundDetailVO detail) {
        String sql = "INSERT INTO outboundDetail (quantity, productId, outboundId) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detail.getQuantity());
            pstmt.setInt(2, detail.getProductId()); // productId는 INT 타입
            pstmt.setInt(3, detail.getOutboundId());  // VO의 outboundId가 int 타입이므로 바로 사용
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
