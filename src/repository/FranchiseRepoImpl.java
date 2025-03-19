package repository;


import common.franchise.FranchiseErrorCode;
import config.DBUtil;
import dto.orderDTO.OrderDTO;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;
import dto.memberDTO.MemberDTO;

import java.sql.*;
import java.util.*;

public class FranchiseRepoImpl implements FranchiseRepo{
    Connection conn = DBUtil.getConnection();
    PreparedStatement pstmt = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        FranchiseDTO franchise = new FranchiseDTO(5, "수원 영통구", 7);
        String result = new FranchiseRepoImpl().insertFranchise(franchise)
                .orElse(FranchiseErrorCode.DB_PROCEDURE_ERROR.getText());
        System.out.println(FranchiseErrorCode.DB_ERROR.getText() + result);
    }

    /**
     * [가맹점 등록 기능]
     * 본사관리자가 신규 가맹점을 등록
     *
     * @param franchise
     * @return
     */
    @Override
    public Optional<String> insertFranchise(FranchiseDTO franchise) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call RegisterFranchise(?, ?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setString(1, franchise.getFranchiseLocation());
            cs.setInt(2, franchise.getManagerNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(3, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(3);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [가맹점 수정 기능]
     * 본사관리자는 가맹점의 정보를 수정할 수 있다
     * 수정하려는 가맹점이 없을 경우 Optional 처리
     *
     * @param franchise
     * @return
     */
    @Override
    public Optional<String> updateFranchise(FranchiseDTO franchise) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call UpdateFranchiseManager(?, ?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, franchise.getFranchiseId());
            cs.setInt(2, franchise.getManagerNo());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(3, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(3);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
            return Optional.empty();
        }


    /**
     * [가맹점 삭제 기능]
     * 본사관리자는 가맹점 아이디로 가맹점을 삭제
     * 존재하지 않는 아이디일 경우 Optional 처리
     *
     * @param franchiseId
     * @return
     */
    @Override
    public Optional<String> deleteFranchise(int franchiseId) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call DeleteFranchise(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, franchiseId);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(2);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [가맹점 전체 조회 기능]
     * 본사관리자는 전체 가맹점의 정보를 조회할 수 있다
     *
     * @return
     */
    @Override
    public Optional<List<FranchiseDTO>> showAllFranchise() {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetAllFranchises(?)}";

            cs = conn.prepareCall(sql);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(1, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            rs = cs.executeQuery();
            String resultMSG = cs.getString(1);

            List<FranchiseDTO> loadFranchises = new ArrayList<>();
            while (rs.next()){
                FranchiseDTO franchise = new FranchiseDTO();
                franchise.setFranchiseId(rs.getInt("franchiseId"));
                franchise.setFranchiseLocation(rs.getString("managerNo"));
                franchise.setManagerNo(rs.getInt("franchiseLocation"));
                loadFranchises.add(franchise);
            }

            if (!loadFranchises.isEmpty()) {
                return Optional.of(loadFranchises);
            } else {
                return Optional.of(Collections.emptyList());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [가맹점 상세 조회 기능]
     * 본사관리자는 가맹점아이디를 기준으로 조회할 수 있다
     *
     * @param franchiseId
     * @return
     */
    @Override
    public Optional<List<FranchiseDTO>> showOneFranchise(int franchiseId) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetFranchiseById(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, franchiseId);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            rs = cs.executeQuery();
            String resultMSG = cs.getString(2);

            List<FranchiseDTO> loadFranchises = new ArrayList<>();
            while (rs.next()){
                FranchiseDTO franchise = new FranchiseDTO();
                franchise.setFranchiseId(rs.getInt("franchiseId"));
                franchise.setFranchiseLocation(rs.getString("managerNo"));
                franchise.setManagerNo(rs.getInt("franchiseLocation"));
                loadFranchises.add(franchise);
            }

            if (!loadFranchises.isEmpty()) {
                return Optional.of(loadFranchises);
            } else {
                return Optional.of(Collections.emptyList());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [가맹점이 할당되지 않은 점주회원 조회 기능]
     * 본사관리자는 아직 본인의 가맹점이 주어지지 않은 점주회원을 조회할 수 있다
     *
     * @return
     */
    @Override
    public Optional<List<MemberDTO>> showHaveNoFranchiseFM() {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call GetUnassignedFranchiseOwners(?)}";

            cs = conn.prepareCall(sql);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(1, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            rs = cs.executeQuery();
            String resultMSG = cs.getString(1);

            List<MemberDTO> loadMemberList = new ArrayList<>();
            while (rs.next()){
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMemberNo(rs.getInt("memberNo"));
                memberDTO.setAuthorityId(rs.getInt("authorityId"));
                memberDTO.setName(rs.getString("name"));
                memberDTO.setPhoneNumber(rs.getString("phoneNumber"));
                memberDTO.setEmail(rs.getString("email"));
                memberDTO.setAddress(rs.getString("address"));
                memberDTO.setId(rs.getString("id"));
                memberDTO.setPassword(rs.getString("password"));
                memberDTO.setLogstatus(rs.getString("logstatus"));
                loadMemberList.add(memberDTO);
            }

            if (!loadMemberList.isEmpty()) {
                return Optional.of(loadMemberList);
            } else {
                return Optional.of(Collections.emptyList());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [제품 등록 기능]
     * 본사관리자가 신제품을 등록
     *
     * @param productDTO
     * @return
     */
    @Override
    public Optional<String> insertProduct(ProductDTO productDTO) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call RegisterProduct(?, ?, ?, ?, ?)}";

            cs = Objects.requireNonNull(conn).prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setString(1, productDTO.getProductName());
            cs.setInt(2, productDTO.getProductPrice());
            cs.setInt(3, productDTO.getCategoryId());
            cs.setString(4, productDTO.getProductType());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(5, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(5);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [카테고리 등록 기능]
     * 본사관리자가 새로운 카테고리를 등록
     *
     * @param productCategoryDTO
     * @return
     */
    @Override
    public Optional<String> insertProductCategory(ProductCategoryDTO productCategoryDTO) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call RegisterCategory(?, ?, ?, ?)}";

            cs = Objects.requireNonNull(conn).prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, productCategoryDTO.getCategoryId());
            cs.setString(2, productCategoryDTO.getCategoryMid());
            cs.setString(3, productCategoryDTO.getCategorySub());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(4);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [제품 수정 기능]
     * 본사관리자는 제품의 정보를 수정할 수 있다
     * 수정하려는 제품이 없을 경우 Optional 처리
     *
     * @param productDTO
     * @return
     */
    @Override
    public Optional<String> updateProduct(ProductDTO productDTO) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call UpdateProduct(?, ?, ?, ?, ?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, productDTO.getProductId());
            cs.setString(2, productDTO.getProductName());
            cs.setInt(1, productDTO.getProductPrice());
            cs.setInt(2, productDTO.getCategoryId());
            cs.setString(1, productDTO.getProductType());

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(6, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(6);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [제품 삭제 기능]
     * 본사관리자는 제품아이디로 제품을 삭제
     * 삭제하려는 제품이 없을 경우 Optional 처리
     *
     * @param productId
     * @return
     */
    @Override
    public Optional<String> deleteProduct(int productId) {
        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call DeleteFranchise(?, ?)}";

            cs = conn.prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setInt(1, productId);

            // out 파라미터에 저장된 프로시저의 수행결과에 대한 외부 변수 등록
            cs.registerOutParameter(2, java.sql.Types.INTEGER);

            // 쿼리 수행, flag 값은 RS의 경우 true, 갱신, 카운트 또는 결과가 없는 경우 false 리턴
            cs.execute();
            String resultMSG = cs.getString(2);

            // resultMSG가 null이면 Optional.empty() 반환, 아니면 Optional.of(resultMSG) 반환
            return Optional.ofNullable(resultMSG).filter(s -> !s.isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    /**
     * [전체 제품 조회 기능]
     * 본사관리자는 전체 제품의 정보를 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<ProductDTO>> showAllProduct() {
        return null;
    }

    /**
     * [카테고리별 조회 기능]
     * 본사관리자는 카테고리별로 제품의 정보를 조회할 수 있다
     * @return
     */
    @Override
    public Optional<Map<Integer, ProductDTO>> showAllProductByCategory() {
        return Optional.empty();
    }

    /**
     * [제품 아이디로 조회 기능]
     * 본사관리자는 제품아이디를 기준으로 조회할 수 있다
     * @param productId
     * @return
     */
    @Override
    public Optional<List<ProductDTO>> showOneProduct(int productId) {
        return Optional.empty();
    }
//
//    /**
//     * [전체 발주 기록 조회 기능]
//     * 본사관리자는 전체 가맹점주의 발주기록을 조회할 수 있다
//     * @return
//     */
//    @Override
//    public Optional<List<OrderDTO>> showAllOrder() {
//        return Optional.empty();
//    }
//
//    /**
//     * [가맹점별 발주 기록 조회 기능]
//     * 본사관리자는 가맹점별 발주 기록을 조회할 수 있다
//     * @return
//     */
//    @Override
//    public Optional<List<OrderDTO>> showAllOrderByFranchise() {
//        return Optional.empty();
//    }

    /**
     * [발주 요청 승인 기능]
     * 본사관리자는 가맹점주의 발주요청을 승인할 수 있다
     * @param orderId
     * @return
     */
    @Override
    public boolean approveOrder(int orderId) {
        return false;
    }

    /**
     * [발주 취소 승인]
     * 본사관리자는 가맹점주의 발주취소요청을 승인할 수 있다
     * @param orderId
     * @return
     */
    @Override
    public boolean approveOrderCancel(int orderId) {
        return false;
    }
}