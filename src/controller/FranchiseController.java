package controller;

public interface FranchiseController {
    /** 가맹점 관리 메뉴 출력 */
    void showFranchiseMenu(int authorityId);

    /** 가맹점 등록 */
    void insertFranchise();

    /** 가맹점 수정 */
    void updateFranchise();

    /** 가맹점 삭제 */
    void deleteFranchise();

    /** 가맹점 전체 조회 */
    void viewFranchiseAll();

    /** 가맹점 상세 조회 */
    void viewFranchiseOne();

    /** 가맹점이 할당되지 않은 점주회원 조회 */
    void viewManagerHaveNoFranchise();

    /** 신제품 등록 */
    void insertProduct();

    /** 카테고리 등록 */
    void insertProductCategory();

    /** 제품 수정 */
    void updateProduct();

    /** 제품 삭제 */
    void deleteProduct();

    /** 전체 제품 조회 */
    void viewProductAll();

    /** 카테고리별 조회 */
    void viewProductByCategory();

    /** 제품 아이디로 조회 */
    void viewProductById();

    /** 전체 발주 기록 조회 */
    void viewOrderAll();

    /** 가맹점별 발주 기록 조회 */
    void viewOrderById();

    /** 발주 요청 승인 */
    void setOrderApproval();

    /** 발주 취소 승인 */
    void setOrderCancelApproval();
}
