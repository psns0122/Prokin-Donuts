package controller;

import common.InputUtil;
import common.MenuUtil;
import dto.OrderDTO;
import dto.OrderItemDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderMain {
    private static OrderController controller;

    public static void main(String[] args) {
        initController();
        while (true) {
            showMainMenu();
        }
    }

    private static void initController() {
        controller = new OrderControllerImpl(new service.OrderServiceImpl());
    }

    private static void showMainMenu() {
        Map<Integer, Runnable> mainMenuActions = new HashMap<>();
        mainMenuActions.put(1, OrderMain::handleStoreManager);
        mainMenuActions.put(4, () -> {
            System.out.println("시스템을 종료합니다.");
            System.exit(0);
        });
        System.out.println("\n========================================");
        System.out.println("=== 역할 선택 ===");
        System.out.println("1. 점장 (발주 요청 제출/상태 확인, 통계)");
        System.out.println("4. 종료");
        System.out.println("========================================");
        MenuUtil.handleMenuSelection("메뉴 선택 (숫자 입력, 종료: exit): ", mainMenuActions);
    }

    private static void handleStoreManager() {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, OrderMain::submitOrder);
        System.out.println("\n--- 점장 메뉴 ---");
        System.out.println("1. 발주 요청 제출");
        System.out.println("5. 로그아웃");
        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    private static void submitOrder() {
        String franchiseId = getInputOrExit("가맹점 아이디 입력: ");
        List<OrderItemDTO> items = buildOrderItems();
        if (items.isEmpty()) {
            System.out.println("등록된 상품이 없습니다. 발주 요청 제출을 취소합니다.");
            return;
        }
        OrderDTO dto = new OrderDTO(franchiseId, items);
        String orderId = controller.submitOrder(dto);
        System.out.println("발주 요청 제출 완료. 발주 ID: " + orderId);
    }

    private static List<OrderItemDTO> buildOrderItems() {
        List<OrderItemDTO> items = new ArrayList<>();
        while (true) {
            String productInput = getInputOrExit("상품 아이디 입력 (종료하려면 'save' 입력): ");
            if (productInput.equalsIgnoreCase("save")) break;
            int quantity = Integer.parseInt(getInputOrExit("상품 발주 수량 입력: "));
            items.add(new OrderItemDTO(productInput, quantity));
        }
        return items;
    }

    private static String getInputOrExit(String prompt) {
        Optional<String> inputOpt = InputUtil.getInput(prompt);
        if (!inputOpt.isPresent()) {
            System.out.println("전 메뉴로 돌아갑니다.");
            System.exit(0);
        }
        return inputOpt.get();
    }
}
