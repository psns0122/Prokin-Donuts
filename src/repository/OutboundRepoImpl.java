package repository;

import config.DBUtil;
import vo.outboundVO.OutboundDetailVO;
import vo.outboundVO.OutboundVO;

import java.sql.*;

public class OutboundRepoImpl implements OutboundRepo {
    @Override
    public String saveOutbound(OutboundVO outbound) {
        String sql = "{CALL sp_saveOutbound(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setDate(1, new java.sql.Date(outbound.getOutboundDate().getTime()));
            cstmt.setString(2, outbound.getOutboundStatus());
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            return String.valueOf(cstmt.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveOutboundDetail(OutboundDetailVO detail) {
        String sql = "{CALL sp_saveOutboundDetail(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, detail.getQuantity());
            cstmt.setInt(2, detail.getProductId());
            cstmt.setInt(3, detail.getOutboundId());
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
