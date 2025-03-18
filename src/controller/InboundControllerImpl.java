package controller;

import common.util.MenuUtil;
import repository.InboundRepo;
import service.InboundService;

import java.util.HashMap;
import java.util.Map;

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
        menuActions.put(3, () -> inboundUpdate());
        menuActions.put(4, () -> inboundDelete());
        menuActions.put(5, () -> receipt());
        menuActions.put(6, () -> Status());

        MenuUtil.handleMenuSelection("메뉴 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    
}
