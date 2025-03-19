package repository.outboundRepo;

import config.DBConnectionManager;
import vo.outboundVO.OutboundDetailVO;
import vo.outboundVO.OutboundVO;

import java.sql.*;
import java.util.UUID;

public class OutboundRepoImpl implements OutboundRepo {
    @Override
    public String saveOutbound(OutboundVO outbound) {
        String newOutboundId = UUID.randomUUID().toString();
        String sql = "INSERT INTO Outbound (outboundId, outboundDate, productId, sectionId, warehouseId, authorityId, outboundStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // 본 예제에서는 헤더용이므로 dummy 값을 사용합니다.
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newOutboundId);
            pstmt.setDate(2, new java.sql.Date(outbound.getOutboundDate().getTime()));
            pstmt.setString(3, "MULTI");
            pstmt.setString(4, "DEFAULT");
            pstmt.setString(5, "DEFAULT");
            pstmt.setString(6, "DEFAULT");
            pstmt.setString(7, outbound.getOutboundStatus());
            int affected = pstmt.executeUpdate();
            if (affected == 0) throw new SQLException("No rows affected");
            return newOutboundId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveOutboundDetail(OutboundDetailVO detail) {
        // OutboundDetail 테이블 구조가 OrderDetail과 유사하다고 가정
        String sql = "INSERT INTO OutboundDetail (outboundDetailId, quantity, productId, orderId) VALUES (NULL, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detail.getQuantity());
            pstmt.setString(2, detail.getProductId());
            pstmt.setString(3, detail.getOutboundId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
