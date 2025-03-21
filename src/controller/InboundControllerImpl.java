package controller;

import common.inbound.InboundText;
import common.util.InputUtil;
import common.util.LoginUtil;
import common.util.MenuUtil;
import dto.inbound.ProductDTO;
import service.InboundService;
import vo.inbound.InboundDetailVO;
import vo.inbound.InboundStatusVO;
import vo.inbound.InboundVO;

import java.time.LocalDate;
import java.util.*;

public class InboundControllerImpl implements InboundController {
    /*public static void main(String[] args) {
        InboundControllerImpl inboundController = new InboundControllerImpl(new InboundServiceImpl(new InboundRepoImpl()));
        inboundController.warehouseManager(1);
        //inboundController.Headquarters();
    }*/

    private final InboundService inboundService;

    public InboundControllerImpl(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    /**
     * 창고관리자 작업시 필요한 창고ID를
     * loginUtil에 있는 멤버 ID로 가져온다.
     */
    private int getWarehouseId(int memberId) throws IllegalArgumentException {
        return inboundService.getWarehouseId(memberId);
    }

    /**
     * 창고 관리자 호출
     */
    public void warehouseManager() {
        int warehouseId = getWarehouseId(LoginUtil.getLoginMember().getMemberNo());
        while (true) {
            try {
                printWmMenu(); //창고 관리자 메뉴 출력
                Map<Integer, Runnable> menuActions = new HashMap<>();
                menuActions.put(1, () -> inspectInbound(warehouseId));
                menuActions.put(2, () -> requestInbound(warehouseId));
                menuActions.put(3, () -> updateInbound(warehouseId));
                menuActions.put(4, () -> deleteInbound(warehouseId));
                menuActions.put(5, () -> printReceipt(warehouseId));
                menuActions.put(6, () -> InboundStatus(warehouseId));
                MenuUtil.handleMenuSelection(InboundText.MENU_CHOICE.getText(), menuActions);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 창고 관리자의 메뉴
     */
    private static void printWmMenu() {
        System.out.println(InboundText.WM_MENU_HEADER.getText());
        System.out.println(InboundText.MENU.getText());
        System.out.println(InboundText.MENU_BOTTOM.getText());
    }

    /**
     * 창고 관리자의 입고 검수 기능
     * inspect, printInboundList
     */
    private void inspectInbound(int warehouseId) throws IllegalArgumentException {
        printInboundList(warehouseId);
        selectInboundId();
    }

    /**
     * 입고 승인 상태인 입고요청을 출력
     *
     * @param warehouseId
     */
    private void printInboundList(int warehouseId) throws IllegalArgumentException {
        List<InboundVO> list = inboundService.getApprovalInboundList(warehouseId);
        list.forEach(System.out::println);
    }

    /**
     * 승인 할 입고 ID를 입력하면 해당 입고 ID는 (승인 -> 완료) 상태로 변경되며
     * Trigger 를 통해 재고에 반영된다.
     */
    private void selectInboundId() throws IllegalArgumentException {
        int inboundId = InputUtil.getIntegerInput(InboundText.COMPLETE_INBOUND.getText());
        boolean check = inboundService.completedInbound(inboundId); // 입고 ID 상태 (승인 -> 완료)
        if (check) System.out.println(InboundText.COMPLETE_TEXT.getText());
        else {
            System.out.println(InboundText.ERROR_COMPLETE_TEXT.getText());
        }
    }

    /**
     * 입고 요청 등록
     * getInboundList
     * ProductId의 보관타입에 따라 냉장->1, 냉동->2, 실온->3
     * 1. 입고 요청시 필요한 상품 메뉴 출력
     * 2. 입고를 신청할 상품의 상품 ID, 수량을 선택  + 날짜 선택
     * 3. 상품 보관타입에 따라 섹션 ID 저장
     * 4. 모두 선택하면 List 로 담아서 저장
     * 5. 저장 시 입고 테이블, 입고 상세 테이블에 각각 저장
     */
    private void requestInbound(int warehouseId) throws IllegalArgumentException {
        List<InboundDetailVO> list = new ArrayList<>();
        // 상품 메뉴 출력
        printProductMenu();
        // 다음 입고 번호를 가져오는 기능
        int inboundId = inboundService.getNextInboundId() + 1;
        getInboundList(inboundId, list);

        //날짜 선택 InputUtil 작성  2025-03-18 형식
        LocalDate date = InputUtil.getDate(InboundText.INBOUND_DATE.getText());

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
     * 입고받은 상품 목록 반환 하는 기능
     *
     * @param inboundId
     * @param list
     */
    private void getInboundList(int inboundId, List<InboundDetailVO> list) {
        while (true) {
            // 입고할 상품과 수량 선택
            int productId = InputUtil.getIntegerInput(InboundText.PRODUCT_ID.getText());
            int quantity = InputUtil.getIntegerInput(InboundText.QUANTITY.getText());
            InboundDetailVO inboundDetailVO = InboundDetailVO.builder()
                    .productId(productId)
                    .inboundId(inboundId)
                    .quantity(quantity)
                    .sectionId(inboundService.getStoredType(productId))
                    .build();
            list.add(inboundDetailVO);
            int end = InputUtil.getIntegerInput(InboundText.SELECT_STOP.getText());
            if (end == 0) break;
        }
    }

    /**
     * [입고 요청 등록]
     * 상품 메뉴 출력
     */
    private void printProductMenu() throws IllegalArgumentException {
        List<ProductDTO> list = inboundService.getProductMenu();
        list.forEach(System.out::println);
        System.out.println();
    }

    /**
     * 입고 요청 수정
     * --> 추후 개발 예정
     */
    private void updateInbound(int warehouseId) {
    }


    /**
     * 입고 요청 취소
     * 1. 입고 요청 취소가 가능한 리스트를 출력한다.
     * 2. 해당 리스트 중 입고 취소할 입고요청의 ID를 입력 받는다
     * 3. 해당 입고 ID가 취소 가능한지 확인한다.
     * 4. 취소 가능하면 삭제 / 취소 불가능하면 불가능하다 안내
     * Refactoring -> 기능 별로 메서드 추출 예정
     */
    private void deleteInbound(int warehouseId) throws IllegalArgumentException {
        // 입고 요청 리스트 출력(요청, 승인 상태인 경우에만 가능)
        printDeleteList(warehouseId);

        int inboundId = InputUtil.getIntegerInput(InboundText.DELETE_ID.getText());
        //취소 가능하면 -> 삭제

        if (inboundService.checkInboundDate(inboundId)) {
            inboundService.deleteInboundInfo(inboundId);
            System.out.println(InboundText.DELETE.getText());
            System.out.println();
        } else {
            System.out.println(InboundText.NOT_DELETE.getText());
            System.out.println();
        }
    }

    private void printDeleteList(int warehouseId) throws IllegalArgumentException {
        List<InboundVO> list = inboundService.getInboundList(warehouseId);
        list.forEach(System.out::println);
        System.out.println();
    }

    /**
     * 입고 고지서 출력
     */
    private void printReceipt(int warehouseId) throws IllegalArgumentException {
        // 입고 요청, 승인 상태인 입고고지서를 가져와 출력한다.
        List<InboundVO> list = inboundService.getInboundList(warehouseId);
        list.forEach(System.out::println);
        System.out.println();
    }

    /**
     * 창고 관리자 입고 현황 조회
     * (입고 상세 정보 출력)
     */
    private void InboundStatus(int warehouseId) throws IllegalArgumentException {
        List<InboundStatusVO> list = inboundService.getInboundDetail(warehouseId);
        list.forEach(System.out::println);
        System.out.println();
    }


    // 총관리자 호출
    public void Headquarters() {
        while (true) {
            try {
                printHqMenu();
                Map<Integer, Runnable> menuActions = new HashMap<>();
                menuActions.put(1, () -> approved());
                menuActions.put(2, () -> printInbound());
                menuActions.put(3, () -> printTotalInbound());
                MenuUtil.handleMenuSelection(InboundText.MENU_CHOICE.getText(), menuActions);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * (전체창고) 입고현황 출력
     */
    private void printTotalInbound() throws IllegalArgumentException {
        List<InboundStatusVO> list = inboundService.getAllInbound();
        list.forEach(System.out::println);
    }

    private static void printHqMenu() {
        System.out.println(InboundText.HQ_MENU_HEADER.getText());
        System.out.println(InboundText.MENU1.getText());
        System.out.println(InboundText.MENU_BOTTOM.getText());
    }

    /**
     * 총관리자의 입고 요청 승인 기능
     */
    private void approved() throws IllegalArgumentException {
        // 입고 '요청'상태인 입고요청 리스트 출력
        List<InboundVO> list = inboundService.getInboundRequest();
        list.forEach(System.out::println);
        System.out.println(InboundText.MENU_BOTTOM.getText());

        // 입고를 승인할 입고 ID를 입력 받으면 해당 입고 ID의 상태를 (요청 -> 승인) 상태로 변경한다.
        int inboundId = InputUtil.getIntegerInput("입고를 승인할 입고 ID를 입력하세요.");
        inboundService.updateInboundStatus(inboundId);
        System.out.println("입고 승인이 완료되었습니다.");
    }

    /**
     * 총관리자의 입고 고지서 출력
     */
    private void printInbound() {
        int warehouseId = InputUtil.getIntegerInput("고지서를 출력할 창고 ID를 입력하세요.");
        printReceipt(warehouseId);
    }

}
