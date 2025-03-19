package controller;

import common.Order.OrderText;
import common.Order.OrderErrorCode;
import common.util.InputUtil;
import common.util.MenuUtil;
import dto.orderDTO.OrderDTO;
import dto.orderDTO.OrderItemDTO;
import dto.orderDTO.OrderStatisticsDTO;
import dto.orderDTO.PendingInventoryComparisonDTO;
import service.OrderService;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import vo.orderVO.OrderStatus;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderControllerImpl implements OrderController {
    private OrderService orderService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String LINE = "========================================";

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    // 권한별 메뉴 실행 메서드
    public void runStoreManagerMenu() {
        handleStoreManager();
    }
    public void runHeadquartersMenu() {
        handleHeadquarters();
    }
    public void runWarehouseMenu() {
        handleWarehouseManager();
    }

    @Override
    public String submitOrder(OrderDTO dto) {
        return orderService.submitOrder(dto);
    }

    @Override
    public void approveOrder(String orderId) {
        orderService.approveOrder(orderId);
    }

    @Override
    public void shipOrder(String orderId) {
        orderService.shipOrder(orderId);
    }

    @Override
    public void holdOrder(String orderId, String reason) {
        orderService.holdOrder(orderId, reason);
    }

    @Override
    public OrderVO getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<OrderDetailVO> getOrderDetails(String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @Override
    public List<OrderVO> getOrdersByStatus(String status) {
        return orderService.getOrdersByStatus(status);
    }

    @Override
    public List<OrderVO> getOrdersByFranchiseId(String franchiseId) {
        return orderService.getOrdersByFranchiseId(franchiseId);
    }

    @Override
    public List<OrderVO> getOrdersByDate(String date) {
        return orderService.getOrdersByDate(date);
    }

    @Override
    public List<OrderVO> getOrdersByDateRange(String startDate, String endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @Override
    public OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month) {
        return orderService.getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }

    @Override
    public Map<String, PendingInventoryComparisonDTO> getPendingInventoryComparisons() {
        return orderService.getPendingInventoryComparisons();
    }

    @Override
    public OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId) {
        return orderService.getLastMonthOrderStatistics(franchiseId);
    }

    // 전체 메뉴 실행
    public void runOrderManagementMenu() {
        while (true) {
            showMainMenu();
        }
    }

    private void showMainMenu() {
        Map<Integer, Runnable> mainMenuActions = new HashMap<>();
        mainMenuActions.put(1, this::handleStoreManager);
        mainMenuActions.put(2, this::handleHeadquarters);
        mainMenuActions.put(3, this::handleWarehouseManager);
        mainMenuActions.put(4, this::exitSystem);

        System.out.println("\n" + LINE);
        System.out.println(OrderText.MENU_HEADER.getText());
        System.out.println(OrderText.MAIN_MENU.getText());
        System.out.println(OrderText.MENU_BOTTOM.getText());

        MenuUtil.handleMenuSelection("메뉴 선택 (숫자 입력, 종료: exit): ", mainMenuActions);
    }

    // 점장 메뉴 처리
    private void handleStoreManager() {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::submitOrderCLI);
        menuActions.put(2, this::storeManagerCheckOrders);
        menuActions.put(3, () -> showStoreManagerDetailedStatistics(getLastMonthYear(), getLastMonth()));
        menuActions.put(4, () -> showStoreManagerDetailedStatistics(getCurrentYear(), getCurrentMonth()));
        menuActions.put(5, this::logout);

        System.out.println("\n" + LINE);
        System.out.println(OrderText.STORE_MENU.getText());
        System.out.println(OrderText.MENU_BOTTOM.getText());

        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    // 본사 메뉴 처리
    private void handleHeadquarters() {
        showHeadquarterDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::headquarterCheckAndApproveOrders);
        menuActions.put(2, this::logout);

        System.out.println("\n" + LINE);
        System.out.println(OrderText.HEADQUARTERS_MENU.getText());
        System.out.println(OrderText.MENU_BOTTOM.getText());

        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    // 창고 관리자 메뉴 처리
    private void handleWarehouseManager() {
        showWarehouseDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::warehouseOrderDetailInquiry);
        menuActions.put(2, this::warehouseDashboardRefresh);
        menuActions.put(3, this::pendingOrderInquiry);
        menuActions.put(4, this::logout);

        System.out.println("\n" + LINE);
        System.out.println(OrderText.WAREHOUSE_MENU.getText());
        System.out.println(OrderText.MENU_BOTTOM.getText());

        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    // 점장 메뉴 내부 기능들
    private void submitOrderCLI() {
        String franchiseId = getInputOrExit(OrderText.INPUT_FRANCHISE_ID.getText());
        List<OrderItemDTO> items = buildOrderItems();
        if (items.isEmpty()) {
            System.out.println(OrderErrorCode.ORDER_NOT_FOUND.getText());
            return;
        }
        OrderDTO dto = new OrderDTO(franchiseId, items);
        String orderId = submitOrder(dto);
        System.out.println(OrderText.ORDER_SUBMIT_SUCCESS.getText() + orderId);
    }

    private List<OrderItemDTO> buildOrderItems() {
        List<OrderItemDTO> items = new ArrayList<>();
        while (true) {
            String productInput = getInputOrExit(OrderText.INPUT_PRODUCT_ID.getText());
            if (productInput.equalsIgnoreCase("save")) break;
            OrderItemDTO existing = findOrderItemByProductId(items, productInput);
            if (existing != null) {
                String modify = getInputOrExit("이미 상품 " + productInput + "이 입력되어 있습니다. 수량 " +
                        existing.getOrderQuantity() + "개 등록됨. 수정하시겠습니까? (y/Y 입력): ");
                if (modify.equalsIgnoreCase("y")) {
                    int newQuantity = Integer.parseInt(getInputOrExit("새로운 발주 수량 입력: "));
                    items.remove(existing);
                    items.add(new OrderItemDTO(productInput, newQuantity));
                }
            } else {
                int quantity = Integer.parseInt(getInputOrExit(OrderText.INPUT_PRODUCT_QUANTITY.getText()));
                items.add(new OrderItemDTO(productInput, quantity));
            }
        }
        return items;
    }

    private OrderItemDTO findOrderItemByProductId(List<OrderItemDTO> items, String productId) {
        return items.stream().filter(item -> item.getProductId().equals(productId)).findFirst().orElse(null);
    }

    private void storeManagerCheckOrders() {
        String franchiseId = getInputOrExit(OrderText.INPUT_FRANCHISE_ID.getText());
        List<OrderVO> orders = getOrdersByFranchiseId(franchiseId);
        if (orders.isEmpty()) {
            System.out.println(OrderText.NO_ORDER.getText());
            return;
        }
        displayOrderList(orders);
        String input = getInput("상세 조회할 발주 ID를 입력하거나 'back' 입력하여 메뉴로 돌아가세요: ");
        if (input.equalsIgnoreCase("back")) return;
        displayOrderDetails(input);
    }

    private void showStoreManagerDetailedStatistics(int year, int month) {
        String franchiseId = getInputOrExit(OrderText.INPUT_FRANCHISE_ID.getText());
        OrderStatisticsDTO stats = getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
        System.out.println("\n" + LINE);
        System.out.println(year + "년 " + month + "월 발주 통계");
        System.out.println(LINE);
        System.out.println("총 발주 요청 건수: " + stats.getTotalOrderRequests());
        System.out.println("상품별 발주 수량:");
        if (stats.getProductOrderQuantities().isEmpty()) {
            System.out.println("  없음");
        } else {
            stats.getProductOrderQuantities().forEach((productId, quantity) -> {
                System.out.printf("  제품 ID: %-10s 수량: %-5d%n", productId, quantity);
            });
        }
        System.out.println(LINE);
    }

    private int getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.get(Calendar.MONTH) + 1;
    }
    private int getLastMonthYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.get(Calendar.YEAR);
    }
    private int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }
    private int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    private String getInputOrExit(String prompt) {
        Optional<String> inputOpt = InputUtil.getInput(prompt);
        if (inputOpt.isEmpty()) {
            System.out.println("전 메뉴로 돌아갑니다.");
            System.exit(0);
        }
        return inputOpt.get();
    }

    private String getInput(String prompt) {
        return InputUtil.getInput(prompt).orElse("back");
    }

    private void displayOrderList(List<OrderVO> orders) {
        orders.forEach(order ->
                System.out.printf(" - 발주 ID: %-20s  발주일: %-10s  상태: %-15s%n",
                        order.getOrderId(), order.getOrderDate(), order.getOrderStatus())
        );
    }

    private void displayOrderDetails(String orderId) {
        OrderVO order = getOrder(orderId);
        if (order != null) {
            System.out.println("\n" + LINE);
            System.out.println("[발주 상세 내역]");
            System.out.println(LINE);
            System.out.printf("발주 ID: %-20s%n", order.getOrderId());
            System.out.printf("발주일 : %-10s%n", order.getOrderDate());
            System.out.printf("상태   : %-15s%n", order.getOrderStatus());
            List<OrderDetailVO> details = getOrderDetails(orderId);
            if (details != null && !details.isEmpty()) {
                details.forEach(detail ->
                        System.out.printf(" - 제품 ID: %-10s  수량: %-5d%n", detail.getProductId(), detail.getOrderQuantity())
                );
            } else {
                System.out.println("발주 상세 내역이 없습니다.");
            }
            System.out.println(LINE);
        } else {
            System.out.println(OrderErrorCode.ORDER_NOT_FOUND.getText());
        }
    }

    private void logout() {
        System.out.println("로그아웃합니다.");
        System.exit(0);
    }

    private void exitSystem() {
        System.out.println("시스템을 종료합니다.");
        System.exit(0);
    }

    private void showHeadquarterDashboard() {
        System.out.println("\n" + LINE);
        System.out.println("=== 본사 대시보드 ===");
        System.out.println(LINE);
        // 본사 대시보드 출력 로직 구현
    }

    private void headquarterCheckAndApproveOrders() {
        List<OrderVO> orders = getOrdersByStatus(OrderStatus.WAITING_FOR_APPROVAL.getStatus());
        if (orders.isEmpty()) {
            System.out.println("미승인 발주 요청이 없습니다.");
            return;
        }
        displayOrderList(orders);
        String orderId = getInputOrExit(OrderText.INPUT_ORDER_ID.getText());
        displayOrderDetails(orderId);
        String approve = getInput(OrderText.CONFIRM_APPROVAL.getText());
        if (approve.equalsIgnoreCase("y")) {
            approveOrder(orderId);
            System.out.println(OrderText.ORDER_APPROVE_SUCCESS.getText() + orderId);
        }
    }

    private void showWarehouseDashboard() {
        System.out.println("\n" + LINE);
        System.out.println("=== 창고 관리자 대시보드 ===");
        System.out.println(LINE);
        // 창고 관리자 대시보드 출력 로직 구현
    }

    private void warehouseDashboardRefresh() {
        showWarehouseDashboard();
    }

    private void warehouseOrderDetailInquiry() {
        String orderId = getInputOrExit(OrderText.INPUT_ORDER_ID.getText());
        displayOrderDetails(orderId);
    }

    private void pendingOrderInquiry() {
        System.out.println("\n" + LINE);
        System.out.println("[보류 건 조회]");
        List<OrderVO> pendingOrders = getOrdersByStatus(OrderStatus.HOLD.getStatus());
        if (pendingOrders.isEmpty()) {
            System.out.println("  없음");
        } else {
            displayOrderList(pendingOrders);
        }
        System.out.println(LINE);
    }
}
