package repository;

import common.franchise.FranchiseErrorCode;
import common.util.LoginUtil;
import config.DBUtil;
import dto.InventoryDTO;
import dto.warehouse.WarehouseDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WarehouseRepoImpl implements WarehouseRepo {
    Connection conn = DBUtil.getConnection();
    PreparedStatement pstmt = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    /**
     * [창고 등록 기능]
     * 가맹점 테이블에 신규 창고를 등록
     *
     * @param warehouse
     * @return
     */
    @Override
    public Optional<String> insertWarehouse(WarehouseDTO warehouse) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call RegisterWarehouse(?, ?, ?, ?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setString(1, warehouse.getAddress());
            cs.setString(2, warehouse.getWarehouseName());
            cs.setInt(3, warehouse.getCapacityLimit());
            cs.setInt(4, warehouse.getMemberNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(5);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,null);
        }
        return Optional.empty();
    }

    /**
     * [창고 수정 기능]
     * 본사관리자는 창고의 정보를 수정할 수 있다
     * 수정하려는 창고가 없을 경우 Optional 처리
     *
     * @param warehouse
     * @return
     */
    @Override
    public Optional<String> updateWarehouse(WarehouseDTO warehouse) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call UpdateWarehouseManager(?, ?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, warehouse.getWarehouseId());
            cs.setInt(2, warehouse.getMemberNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(3);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,null);
        }
        return Optional.empty();
    }

    /**
     * [창고 삭제 기능]
     * 본사관리자는 가맹점 아이디로 가맹점을 삭제
     * 존재하지 않는 아이디일 경우 Optional 처리
     *
     * @param warehouseId
     * @return
     */
    @Override
    public Optional<String> deleteWarehouse(int warehouseId) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call DeleteWarehouse(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, warehouseId);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(2);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,null);
        }
        return Optional.empty();
    }

    /**
     * [창고 전체 조회 기능]
     * 본사관리자는 전체 창고의 정보를 조회할 수 있다
     *
     * @return
     */
    @Override
    public Optional<List<WarehouseDTO>> showAllWarehouse() {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetAllWarehouses(?)}";

            cs = conn.prepareCall(sql);

            // OUT 파라미터 등록
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);

            // 프로시저 실행
            boolean hasResult = cs.execute();
            String resultMSG = cs.getString(1);  // OUT 파라미터 메시지 가져오기

            List<WarehouseDTO> loadWarehouse = new ArrayList<>();
            // 결과셋이 있는 경우 처리
            if (hasResult) {
                rs = cs.getResultSet();
                while (rs.next()) {
                    WarehouseDTO warehouse = new WarehouseDTO();
                    warehouse.setWarehouseId(rs.getInt("warehouseId"));
                    warehouse.setAddress(rs.getString("address"));
                    warehouse.setWarehouseName(rs.getString("warehouseName"));
                    warehouse.setCreateDate(rs.getDate("createDate"));
                    warehouse.setCapacityLimit(rs.getInt("capacityLimit"));
                    warehouse.setMemberNo(rs.getInt("memberNo"));
                    warehouse.setWMName(rs.getString("managerName"));
                    warehouse.setWHPhone(rs.getString("managerPhone"));
                    warehouse.setWHEmail(rs.getString("managerEmail"));
                    loadWarehouse.add(warehouse);
                }
            }

            // 가맹점이 없을 경우 메시지 반환 (디버깅 용도)
            if (loadWarehouse.isEmpty()) {
                System.out.println(FranchiseErrorCode.DB_ERROR.getText() + resultMSG);
            } else {
                return Optional.of(loadWarehouse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(rs,cs,null);
        }
        return Optional.of(Collections.emptyList());
    }

    /**
     * [소재지별 조회 기능]
     * 본사관리자는 소재지별 창고를 조회할 수 있다
     *
     * @param location
     * @return
     */
    @Override
    public Optional<List<WarehouseDTO>> showAllWarehouseByLocation(String location) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetWarehousesByLocation(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setString(1, location);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);

            // 프로시저 실행
            boolean hasResult = cs.execute();
            String resultMSG = cs.getString(2);  // OUT 파라미터 메시지 가져오기

            List<WarehouseDTO> loadWarehouse = new ArrayList<>();
            // 결과셋이 있는 경우 처리
            if (hasResult) {
                rs = cs.getResultSet();
                while (rs.next()) {
                    WarehouseDTO warehouse = new WarehouseDTO();
                    warehouse.setWarehouseId(rs.getInt("warehouseId"));
                    warehouse.setAddress(rs.getString("address"));
                    warehouse.setWarehouseName(rs.getString("warehouseName"));
                    warehouse.setCreateDate(rs.getDate("createDate"));
                    warehouse.setCapacityLimit(rs.getInt("capacityLimit"));
                    warehouse.setMemberNo(rs.getInt("memberNo"));
                    warehouse.setWMName(rs.getString("managerName"));
                    warehouse.setWHPhone(rs.getString("managerPhone"));
                    warehouse.setWHEmail(rs.getString("managerEmail"));
                    loadWarehouse.add(warehouse);
                }
            }

            // 상품이 없을 경우 메시지 반환 (디버깅 용도)
            if (loadWarehouse.isEmpty()) {
                System.out.println(FranchiseErrorCode.DB_ERROR.getText() + resultMSG);
            } else {
                return Optional.of(loadWarehouse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(rs,cs,null);
        }
        return Optional.of(Collections.emptyList());
    }

    /**
     * [내 창고 정보 조회 기능]
     * 창고관리자는 내 창고의 정보를 조회할 수 있다
     *
     * @return
     */
    @Override
    public Optional<List<WarehouseDTO>> showMyWarehouse() {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetWarehouseById(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달 (현재 로그인한 회원의 회원번호 전달)
            cs.setInt(1, LoginUtil.getLoginMember().getMemberNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);

            // 프로시저 실행
            boolean hasResult = cs.execute();
            String resultMSG = cs.getString(2);  // OUT 파라미터 메시지 가져오기

            List<WarehouseDTO> loadWarehouse = new ArrayList<>();
            // 결과셋이 있는 경우 처리
            if (hasResult) {
                rs = cs.getResultSet();
                while (rs.next()) {
                    WarehouseDTO warehouse = new WarehouseDTO();
                    warehouse.setWarehouseId(rs.getInt("warehouseId"));
                    warehouse.setAddress(rs.getString("address"));
                    warehouse.setWarehouseName(rs.getString("warehouseName"));
                    warehouse.setCreateDate(rs.getDate("createDate"));
                    warehouse.setCapacityLimit(rs.getInt("capacityLimit"));
                    warehouse.setMemberNo(rs.getInt("memberNo"));
                    warehouse.setWMName(rs.getString("managerName"));
                    warehouse.setWHPhone(rs.getString("managerPhone"));
                    warehouse.setWHEmail(rs.getString("managerEmail"));
                    loadWarehouse.add(warehouse);
                }
            }

            // 상품이 없을 경우 메시지 반환 (디버깅 용도)
            if (loadWarehouse.isEmpty()) {
                System.out.println(FranchiseErrorCode.DB_ERROR.getText() + resultMSG);
            } else {
                return Optional.of(loadWarehouse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(rs,cs,null);
        }
        return Optional.of(Collections.emptyList());
    }

    /**
     * [내 창고 재고 조회 기능]
     * 창고관리자는 내 창고의 재고를 조회할 수 있다
     *
     * @return
     */
    @Override
    public Optional<List<InventoryDTO>> showMyWarehouseInventory() {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetInventoryByWarehouse(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, LoginUtil.getLoginMember().getMemberNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);

            // 프로시저 실행
            boolean hasResult = cs.execute();
            String resultMSG = cs.getString(2);  // OUT 파라미터 메시지 가져오기

            List<InventoryDTO> loadInventory = new ArrayList<>();
            // 결과셋이 있는 경우 처리
            if (hasResult) {
                rs = cs.getResultSet();
                while (rs.next()) {
                    InventoryDTO inventory = new InventoryDTO();
                    inventory.setInventoryId(rs.getInt("inventoryId"));
                    inventory.setProductId(rs.getString("productId"));
                    inventory.setQuantity(rs.getInt("quantity"));
                    inventory.setWarehouseId(rs.getInt("warehouseId"));
                    inventory.setProductName(rs.getString("productName"));
                    loadInventory.add(inventory);
                }
            }

            // 상품이 없을 경우 메시지 반환 (디버깅 용도)
            if (loadInventory.isEmpty()) {
                System.out.println(FranchiseErrorCode.DB_ERROR.getText() + resultMSG);
            } else {
                return Optional.of(loadInventory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(rs,cs,null);
        }
        return Optional.of(Collections.emptyList());
    }
}
