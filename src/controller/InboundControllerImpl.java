package controller;

import common.util.InputUtil;
import common.util.MenuUtil;
import repository.InboundRepo;
import service.InboundService;
import vo.inbound.InboundVO;

import java.util.*;

public class InboundControllerImpl implements InboundController {
    private final InboundService inboundService;

    public InboundControllerImpl(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    /**
     * 창고 관리자 호출
     * 창고 ID를 매개변수로 받는다고 가정
     * @param warehouseId
     */
    void warehouseManager(int warehouseId) {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, () -> inspect(warehouseId));
        menuActions.put(2, () -> request());
        /*menuActions.put(3, () -> inboundUpdate());
        menuActions.put(4, () -> inboundDelete());
        menuActions.put(5, () -> receipt());
        menuActions.put(6, () -> Status());*/

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
     * @param warehouseId
     */
    private void printInboundList(int warehouseId) {
        Scanner input = new Scanner(System.in);
        List<InboundVO> list = inboundService.getApprovalInboundList(warehouseId);
        list.forEach(System.out::println);
        int inboundId = InputUtil.getIntegerInput("입고를 완료할 ID를 입력하세요");
        inboundService.completedInbound(inboundId); // 입고 ID 상태 (승인 -> 완료)
        System.out.println("입고 완료!");
    }





    // 총관리자 호출
    void Headquarters() {

    }

}
