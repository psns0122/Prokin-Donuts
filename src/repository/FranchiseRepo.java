package repository;


import dto.memberDTO.MemberDTO;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface FranchiseRepo {
    /**
     * [가맹점 등록 기능]
     * 본사관리자가 신규 가맹점을 등록
     */
    Optional<String> insertFranchise(FranchiseDTO franchise);

    /**
     * [가맹점 수정 기능]
     * 본사관리자는 가맹점의 정보를 수정할 수 있다
     * 수정하려는 가맹점이 없을 경우 Optional 처리
     */
    Optional<String> updateFranchise(FranchiseDTO franchise);

    /**
     * [가맹점 삭제 기능]
     * 본사관리자는 가맹점 아이디로 가맹점을 삭제
     * 존재하지 않는 아이디일 경우 Optional 처리
     */
    Optional<String> deleteFranchise(int franchiseId);

    /**
     * [가맹점 전체 조회 기능]
     * 본사관리자는 전체 가맹점의 정보를 조회할 수 있다
     */
    Optional<List<FranchiseDTO>> showAllFranchise();


    /**
     * [가맹점 상세 조회 기능]
     * 본사관리자는 가맹점아이디를 기준으로 조회할 수 있다
     */
    Optional<List<FranchiseDTO>> showOneFranchise(int franchiseId);

    /**
     * [가맹점이 할당되지 않은 점주회원 조회 기능]
     * 본사관리자는 아직 본인의 가맹점이 주어지지 않은 점주회원을 조회할 수 있다
     */
    Optional<List<MemberDTO>> showHaveNoFranchiseFM();

    /**
     * [제품 등록 기능]
     * 본사관리자가 신제품을 등록
     */
    Optional<String> insertProduct(ProductDTO productDTO);

    /**
     * [카테고리 등록 기능]
     * 본사관리자가 새로운 카테고리를 등록
     */
    Optional<String> insertProductCategory(ProductCategoryDTO productCategoryDTO);

    /**
     * [제품 수정 기능]
     * 본사관리자는 제품의 정보를 수정할 수 있다
     * 수정하려는 제품이 없을 경우 Optional 처리
     */
    Optional<String> updateProduct(ProductDTO productDTO);

    /**
     * [제품 삭제 기능]
     * 본사관리자는 제품아이디로 제품을 삭제
     * 삭제하려는 제품이 없을 경우 Optional 처리
     */
    Optional<String> deleteProduct(int productId);

    /**
     * [전체 제품 조회 기능]
     * 본사관리자는 전체 제품의 정보를 조회할 수 있다
     */
    Optional<List<ProductDTO>> showAllProduct();

    /**
     * [카테고리별 조회 기능]
     * 본사관리자는 카테고리별로 제품의 정보를 조회할 수 있다
     */
    Optional<List<ProductDTO>> showAllProductByCategory(int categoryId);

    /**
     * [제품 아이디로 조회 기능]
     * 본사관리자는 제품아이디를 기준으로 조회할 수 있다
     */
    Optional<List<ProductDTO>> showOneProduct(int productId);
//
//    /**
//     * [전체 발주 기록 조회 기능]
//     * 본사관리자는 전체 가맹점주의 발주기록을 조회할 수 있다
//     */
//    Optional<List<OrderDTO>> showAllOrder();
//
//    /**
//     * [가맹점별 발주 기록 조회 기능]
//     * 본사관리자는 가맹점별 발주 기록을 조회할 수 있다
//     */
//    Optional<List<OrderDTO>> showAllOrderByFranchise();


    /**
     * [발주 요청 승인 기능]
     * 본사관리자는 가맹점주의 발주요청을 승인할 수 있다
     */
   // boolean approveOrder(int orderId);

    /**
     * [발주 취소 승인]
     * 본사관리자는 가맹점주의 발주취소요청을 승인할 수 있다
     */
 //   boolean approveOrderCancel(int orderId);

}
