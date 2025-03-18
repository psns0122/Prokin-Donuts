package controller;

import common.util.InputUtil;
import common.util.MenuUtil;
import dto.inbound.ProductDTO;
import repository.InboundRepoImpl;
import service.InboundService;
import service.InboundServiceImpl;
import vo.inbound.InboundDetailVO;
import vo.inbound.InboundVO;
import java.time.LocalDate;
import java.util.*;

public class InboundControllerImpl implements InboundController {
    public static void main(String[] args) {
        InboundControllerImpl inboundController = new InboundControllerImpl(new InboundServiceImpl(new InboundRepoImpl()));
        inboundController.warehouseManager(1);
    }
    private final InboundService inboundService;

    public InboundControllerImpl(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    /**
     * 창고 관리자 호출
     * 창고 ID를 매개변수로 받는다고 가정
     *
     * @param warehouseId
     */
    void warehouseManager(int warehouseId) {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, () -> inspect(warehouseId));
        menuActions.put(2, () -> request(warehouseId));
        menuActions.put(3, () -> inboundUpdate(warehouseId));
        menuActions.put(4, () -> inboundDelete(warehouseId));
        menuActions.put(5, () -> receipt(warehouseId));
        /** menuActions.put(6, () -> Status()); */



        MenuUtil.handleMenuSelection("메뉴 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    /**
     * 창고 관리자의 입고 검수 기능
     * inspect, printInboundList
     * -> printInboundList 메서드 기능 구분
     * -> Refactoring 필요
     */
    private void inspect(int warehouseId) {
        // 입고 승인 상태인 입고요청을 출력한다.
        printInboundList(warehouseId);
        //승인 할 입고 ID를 입력하면 해당 입고 ID는 (승인 -> 완료) 상태로 변경되며
        // Trigger 를 통해 재고에 반영된다.
    }

    /**
     * 입고 승인 상태인 입고요청을 출력
     *
     * @param warehouseId
     */
    private void printInboundList(int warehouseId) {
        List<InboundVO> list = inboundService.getApprovalInboundList(warehouseId);
        list.forEach(System.out::println);
        int inboundId = InputUtil.getIntegerInput("입고를 완료할 ID를 입력하세요");
        inboundService.completedInbound(inboundId); // 입고 ID 상태 (승인 -> 완료)
        System.out.println("입고 완료!");
    }

    /**
     * 입고 요청 등록
     * 1. 입고 요청시 필요한 상품 메뉴 출력
     * 2. 입고를 신청할 상품의 상품 ID, 수량을 선택  + 날짜 선택
     * 3. 모두 선택하면 List 로 담아서 저장
     * 4. 저장 시 입고 테이블, 입고 상세 테이블에 각각 저장
     * Refactoring -> request 메서드 기능 단위별로
     */
    private void request(int warehouseId) {
        List<InboundDetailVO> list = new ArrayList<>();

        // 상품 메뉴 출력
        printProductMenu();

        // Refactoring 필요 !
        while (true) {
            // 입고할 상품과 수량 선택
            int end = InputUtil.getIntegerInput("종료: 0입력");
            if (end == 0) break;
            int productId = InputUtil.getIntegerInput("상품 ID를 입력하세요.");
            int quantity = InputUtil.getIntegerInput("수량을 입력하세요.");
            InboundDetailVO inboundDetailVO = InboundDetailVO.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .build();
            list.add(inboundDetailVO);
        }

        //날짜 선택 InputUtil 작성  2025-03-18 형식?
        LocalDate date = InputUtil.getDate("입고예약 날짜를 입력해주세요.");

        //입고 테이블에 입고 요청 등록
        InboundVO inboundVO = InboundVO.builder()
                .inboundDate(date)
                .status("입고요청")
                .warehouseId(warehouseId)
                .build();
        // 입고 테이블에 입고 요청 등록
        inboundService.registerInbound(inboundVO);

        // 입고 요청 상세 테이블에 입고요청 상세 등록
        inboundService.registerDetailInfo(list);

        // 입고, 입고요청 상세 등록 완료되면 성공 !
        System.out.println("성공");
    }

    /**
     * [입고 요청 등록]
     * 상품 메뉴 출력
     */
    private void printProductMenu() {
        List<ProductDTO> list = inboundService.getProductMenu();
        list.forEach(System.out::println);
    }

    /**
     * 입고 요청 수정
     * --> 추후 개발 예정
     */
    private void inboundUpdate(int warehouseId) {
    }


    /**
     * 입고 요청 취소
     * 1. 입고 요청 취소가 가능한 리스트를 출력한다.
     * 2. 해당 리스트 중 입고 취소할 입고요청의 ID를 입력 받는다
     * 3. 해당 입고 ID가 취소 가능한지 확인한다.
     * 4. 취소 가능하면 삭제 / 취소 불가능하면 불가능하다 안내
     * Refactoring -> 기능 별로 메서드 추출 예정
     */
    private void inboundDelete(int warehouseId) {
        // 입고 요청 리스트 출력(요청, 승인 상태인 경우에만 가능)
        List<InboundVO> list = inboundService.getInboundList(warehouseId);
        list.forEach(System.out::println);

        int inboundId = InputUtil.getIntegerInput("입고를 취소할 입고ID를 입력하세요.");
        //취소 가능하면 -> 삭제
        /**
         * checkInboundDate 기능 구현 필요!
         */
        if(inboundService.checkInboundDate(inboundId)) {
            inboundService.deleteInboundInfo(inboundId);
        } else {
            System.out.println("취소 불가능합니다.");
        }
    }

    /**
     * 입고 고지서 출력
     */

    private void receipt(int warehouseId) {
        // 입고 요청, 승인 상태인 입고고지서를 가져와 출력한다.
        List<InboundVO> list = inboundService.getInboundList(warehouseId);
        list.forEach(System.out::println);
    }







    // 총관리자 호출
    void Headquarters() {

    }

}
