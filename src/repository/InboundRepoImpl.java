package repository;

import config.DBUtil;
import dto.inbound.InboundDTO;
import dto.inbound.ProductDTO;
import vo.ProductVO;
import vo.inbound.InboundVO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InboundRepoImpl implements InboundRepo {
    Connection conn = DBUtil.getConnection();
    PreparedStatement pstmt = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    /**
     * [입고 검수 기능]
     * 입고테이블에서 '입고승인' 상태인 행을 가져온다.
     * @return '입고승인 행'
     */
    @Override
    public Optional<List<InboundVO>> findByApprovedStatus(int warehouseId) {
        List<InboundVO> list = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call ApprovedStatus(?)}");
            cs.setInt(1, warehouseId);
            rs = cs.executeQuery();

            while(rs.next()) {
                InboundVO inboundVO = InboundVO.builder()
                        .inboundId(rs.getInt("inboundId"))
                        .inboundDate(rs.getDate("inboundDate"))
                        .status(rs.getString("inboundStatus"))
                        .warehouseId(rs.getInt("warehouseId"))
                        .build();
                list.add(inboundVO);
            }
            rs.close();
            cs.close();
            return Optional.of(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * [입고 검수 기능]
     * 입고 ID의 상태 (승인 -> 완료)를 변경
     * 상태가 (승인 -> 완료) 되면 해당 입고 품목 재고에 반영 (트리거)
     * @param inboundId
     */
    @Override
    public void updateCompletedStatus(int inboundId) {
        try {
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call updateCompletedStatus(?)}");
            cs.setInt(1, inboundId);
            cs.execute();
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 제품 테이블의 모든 정보를 가져온다.
     * 프로시저 사용 X
     * @return 창고관리자가 입고를 주문할 때 보는 상품 메뉴
     */
    @Override
    public Optional<List<ProductDTO>> getProductInfo() {
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                ProductDTO productDTO = ProductDTO.builder()
                        .productId(rs.getInt("productId"))
                        .productName(rs.getString("productName"))
                        .productPrice(rs.getInt("categoryId"))
                        .productType(rs.getString("productType"))
                        .build();
                list.add(productDTO);
            }
            rs.close();
            pstmt.close();
            return Optional.of(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerInboundInfo(List<ProductVO> inboundList) {

    }

    @Override
    public Optional<String> getInboundStatus(int inboundId) {
        return Optional.empty();
    }

    @Override
    public void updateInboundInfo(int inboundId, List<ProductVO> inboundList) {

    }

    @Override
    public void deleteInboundInfo(int inboundId) {

    }

    @Override
    public Optional<List<InboundDTO>> getInboundRequest() {
        return Optional.empty();
    }

    @Override
    public void updateInboundStatus(int inboundId) {

    }

    @Override
    public Optional<List<InboundDTO>> getAllInboundInfo(int warehouseId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<InboundDTO>> getAllInbound() {
        return Optional.empty();
    }

    @Override
    public Optional<List<InboundDTO>> getInboundByDate(Date start_date, Date end_date) {
        return Optional.empty();
    }


}
