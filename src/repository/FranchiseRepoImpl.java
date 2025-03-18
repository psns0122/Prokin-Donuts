package repository;

import common.franchise.FranchiseErrorCode;
import config.DBUtil;
import dto.OrderDTO;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FranchiseRepoImpl implements FranchiseRepo{
    Connection conn = DBUtil.getConnection();
    PreparedStatement pstmt = null;
    CallableStatement cs = null;
    ResultSet rs = null;

//    public static void main(String[] args) {
//        FranchiseDTO franchise = new FranchiseDTO(5, "수원 영통구", 7);
//        String result = new FranchiseRepoImpl().insertFranchise(franchise)
//                .orElse(FranchiseErrorCode.DB_PROCEDURE_ERROR.getText());
//        System.out.println(FranchiseErrorCode.DB_ERROR.getText() + result);
//    }

    /**
     * [가맹점 등록 기능]
     * 본사관리자가 신규 가맹점을 등록
     *
     * @param franchise
     * @return
     */
    @Override
    public Optional<String> insertFranchise(FranchiseDTO franchise) {
        conn = DBUtil.getConnection();

        try {
            // p_franchiseLocation , p_memberNo
            String sql = "{call RegisterFranchise(?, ?, ?)}";

            cs = Objects.requireNonNull(conn).prepareCall(sql);

            // in 파라미터에 값 전달
            cs.setString(1, franchise.getFranchiseIdLocation());
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
     * @param franchise
     * @return
     */
    @Override
    public Optional<FranchiseDTO> updateFranchise(FranchiseDTO franchise) {
        return Optional.empty();
    }

    /**
     * [가맹점 삭제 기능]
     * 본사관리자는 가맹점 아이디로 가맹점을 삭제
     * 존재하지 않는 아이디일 경우 Optional 처리
     * @param franchiseId
     * @return
     */
    @Override
    public Optional<FranchiseDTO> deleteFranchise(int franchiseId) {
        return Optional.empty();
    }

    /**
     * [가맹점 전체 조회 기능]
     * 본사관리자는 전체 가맹점의 정보를 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<FranchiseDTO>> showAllFranchise() {
        return Optional.empty();
    }

    /**
     * [가맹점 상세 조회 기능]
     * 본사관리자는 가맹점아이디를 기준으로 조회할 수 있다
     * @param franchiseId
     * @return
     */
    @Override
    public Optional<List<FranchiseDTO>> showOneFranchise(int franchiseId) {
        return Optional.empty();
    }

    /**
     * [가맹점이 할당되지 않은 점주회원 조회 기능]
     * 본사관리자는 아직 본인의 가맹점이 주어지지 않은 점주회원을 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<FranchiseDTO>> showHaveNoFranchiseFM() {
        return Optional.empty();
    }

    /**
     * [제품 등록 기능]
     * 본사관리자가 신제품을 등록
     * @param productDTO
     * @return
     */
    @Override
    public Optional<ProductDTO> insertProduct(ProductDTO productDTO) {
        return Optional.empty();
    }

    /**
     * [카테고리 등록 기능]
     * 본사관리자가 새로운 카테고리를 등록
     * @param productCategoryDTO
     * @return
     */
    @Override
    public Optional<ProductCategoryDTO> insertProductCategory(ProductCategoryDTO productCategoryDTO) {
        return Optional.empty();
    }

    /**
     * [제품 수정 기능]
     * 본사관리자는 제품의 정보를 수정할 수 있다
     * 수정하려는 제품이 없을 경우 Optional 처리
     * @param productDTO
     * @return
     */
    @Override
    public Optional<ProductDTO> updateProduct(ProductDTO productDTO) {
        return Optional.empty();
    }

    /**
     * [제품 삭제 기능]
     * 본사관리자는 제품아이디로 제품을 삭제
     * 삭제하려는 제품이 없을 경우 Optional 처리
     * @param productDTO
     * @return
     */
    @Override
    public Optional<ProductDTO> deleteProduct(ProductDTO productDTO) {
        return Optional.empty();
    }

    /**
     * [전체 제품 조회 기능]
     * 본사관리자는 전체 제품의 정보를 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<ProductDTO>> showAllProduct() {
        return Optional.empty();
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

    /**
     * [전체 발주 기록 조회 기능]
     * 본사관리자는 전체 가맹점주의 발주기록을 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<OrderDTO>> showAllOrder() {
        return Optional.empty();
    }

    /**
     * [가맹점별 발주 기록 조회 기능]
     * 본사관리자는 가맹점별 발주 기록을 조회할 수 있다
     * @return
     */
    @Override
    public Optional<List<OrderDTO>> showAllOrderByFranchise() {
        return Optional.empty();
    }

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
