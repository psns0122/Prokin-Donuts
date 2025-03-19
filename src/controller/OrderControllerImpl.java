package controller;

import common.util.InputUtil;
import common.util.MenuUtil;
import dto.orderDTO.OrderDTO;
import dto.orderDTO.OrderItemDTO;
import dto.orderDTO.OrderStatisticsDTO;
import dto.orderDTO.PendingInventoryComparisonDTO;
import service.OrderService;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderControllerImpl implements OrderController {
    private OrderService orderService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String LINE = "========================================";

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
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
    public OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId) {
        return orderService.getLastMonthOrderStatistics(franchiseId);
    }

    @Override
    public Map<String, PendingInventoryComparisonDTO> getPendingInventoryComparisons() {
        return orderService.getPendingInventoryComparisons();
    }

    // MainController 등에서 호출할 수 있도록 run() 메서드를 제공
    public void run() {
        // 예: 주문 관련 메뉴만 실행하는 경우
        runOrderManagementMenu();
    }

    private void runOrderManagementMenu() {
        while (true) {
            showMainMenu();
        }
    }

    private void showMainMenu() {
        Map<Integer, Runnable> mainMenuActions = new HashMap<>();
        mainMenuActions.put(1, this::handleStoreManager);
        mainMenuActions.put(2, this::handleHeadquarters);
        mainMenuActions.put(3, this::handleWarehouseManager);
        mainMenuActions.put(4, () -> {
            System.out.println("주문관리 시스템을 종료합니다.");
            System.exit(0);
        });
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "=== 역할 선택 ===");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "1. 점장 (발주 요청 제출/상태 확인, 통계)");
        System.out.printf("%-40s%n", "2. 본사 (미승인 발주 조회 및 승인, 대시보드)");
        System.out.printf("%-40s%n", "3. 창고 관리자 (출고 처리/보류, 대시보드)");
        System.out.printf("%-40s%n", "4. 종료");
        System.out.println(LINE);
        MenuUtil.handleMenuSelection("메뉴 선택 (숫자 입력, 종료: exit): ", mainMenuActions);
    }

    /* ===== 점장 메뉴 ===== */
    private void handleStoreManager() {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::submitOrderCLI);
        menuActions.put(2, this::storeManagerCheckOrders);
        menuActions.put(3, () -> showStoreManagerDetailedStatistics(getLastMonthYear(), getLastMonth()));
        menuActions.put(4, () -> showStoreManagerDetailedStatistics(getCurrentYear(), getCurrentMonth()));
        menuActions.put(5, () -> {}); // 로그아웃
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "--- 점장 메뉴 ---");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "1. 발주 요청 제출");
        System.out.printf("%-40s%n", "2. 발주 상태 확인");
        System.out.printf("%-40s%n", "3. 지난달 발주 통계 조회");
        System.out.printf("%-40s%n", "4. 이번달 발주 통계 조회");
        System.out.printf("%-40s%n", "5. 로그아웃");
        System.out.println(LINE);
        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
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

    private void showStoreManagerDetailedStatistics(int year, int month) {
        String franchiseId = getInputOrExit("가맹점 아이디 입력: ");
        OrderStatisticsDTO stats = getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", year + "년 " + month + "월 발주 통계");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "총 발주 요청 건수: " + stats.getTotalOrderRequests());
        System.out.println("상품별 발주 수량:");
        if (stats.getProductOrderQuantities().isEmpty()) {
            System.out.printf("%-40s%n", "  없음");
        } else {
            for (Map.Entry<String, Integer> entry : stats.getProductOrderQuantities().entrySet()) {
                System.out.printf("  제품 ID: %-10s 수량: %-5d%n", entry.getKey(), entry.getValue());
            }
        }
        System.out.println(LINE);
    }

    private void submitOrderCLI() {
        String franchiseId = getInputOrExit("가맹점 아이디 입력: ");
        List<OrderItemDTO> items = buildOrderItems();
        if (items.isEmpty()) {
            System.out.println("등록된 상품이 없습니다. 발주 요청 제출을 취소합니다.");
            return;
        }
        OrderDTO dto = new OrderDTO(franchiseId, items);
        String orderId = submitOrder(dto);
        System.out.printf("발주 요청 제출 완료. 발주 ID: %s%n", orderId);
    }

    private List<OrderItemDTO> buildOrderItems() {
        List<OrderItemDTO> items = new ArrayList<>();
        while (true) {
            String productInput = getInputOrExit("상품 아이디 입력 (종료하려면 'save' 입력): ");
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
                int quantity = Integer.parseInt(getInputOrExit("상품 발주 수량 입력: "));
                items.add(new OrderItemDTO(productInput, quantity));
            }
        }
        return items;
    }

    private OrderItemDTO findOrderItemByProductId(List<OrderItemDTO> items, String productId) {
        for (OrderItemDTO item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    private void storeManagerCheckOrders() {
        String franchiseId = getInputOrExit("확인할 가맹점 아이디 입력: ");
        List<OrderVO> orders = getOrdersByFranchiseId(franchiseId);
        if (orders.isEmpty()) {
            System.out.println("해당 가맹점의 발주 내역이 없습니다.");
            return;
        }
        displayOrderList(orders);
        String input = getInput("상세 조회할 발주 ID를 입력하거나 'back' 입력하여 메뉴로 돌아가세요: ");
        if (input.equalsIgnoreCase("back")) return;
        displayOrderDetails(input);
    }

    /* ===== 본사 메뉴 ===== */
    private void handleHeadquarters() {
        showHeadquarterDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::headquarterCheckAndApproveOrders);
        menuActions.put(2, () -> {}); // 로그아웃
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "--- 본사 메뉴 ---");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "1. 미승인 발주 조회 및 승인");
        System.out.printf("%-40s%n", "2. 로그아웃");
        System.out.println(LINE);
        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    private void headquarterCheckAndApproveOrders() {
        List<OrderVO> orders = getOrdersByStatus("발주 승인 대기중");
        if (orders.isEmpty()) {
            System.out.println("미승인 발주 요청이 없습니다.");
            return;
        }
        displayOrderList(orders);
        String orderId = getInputOrExit("상세 조회할 발주 ID 입력: ");
        displayOrderDetails(orderId);
        String approve = getInput("이 발주를 승인하시겠습니까? (y/n): ");
        if (approve.equalsIgnoreCase("y")) {
            approveOrder(orderId);
        }
    }

    private void showHeadquarterDashboard() {
        try {
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            String todayStr = sdf.format(today);
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String prevWeekStart = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String prevWeekEnd = sdf.format(cal.getTime());
            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String currentWeekStart = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String currentWeekEnd = sdf.format(cal.getTime());
            System.out.println("\n" + LINE);
            System.out.printf("%-40s%n", "=== 본사 대시보드 ===");
            System.out.println(LINE);
            System.out.printf("%-40s%n", "[전주 (" + prevWeekStart + " ~ " + prevWeekEnd + ") 주문]");
            List<OrderVO> ordersPrevWeek = getOrdersByDateRange(prevWeekStart, prevWeekEnd);
            if (ordersPrevWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersPrevWeek);
            }
            System.out.printf("%-40s%n", "[금주 (" + currentWeekStart + " ~ " + currentWeekEnd + ") 주문]");
            List<OrderVO> ordersCurrentWeek = getOrdersByDateRange(currentWeekStart, currentWeekEnd);
            if (ordersCurrentWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersCurrentWeek);
            }
            System.out.printf("%-40s%n", "[금일 (" + todayStr + ") 주문]");
            List<OrderVO> ordersToday = getOrdersByDate(todayStr);
            if (ordersToday.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersToday);
            }
            System.out.println(LINE);
        } catch (Exception e) {
            System.out.println("본사 대시보드 조회 중 오류: " + e.getMessage());
        }
    }

    /* ===== 창고 관리자 메뉴 ===== */
    private void handleWarehouseManager() {
        showWarehouseDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, this::warehouseOrderDetailInquiry);
        menuActions.put(2, this::warehouseDashboardRefresh);
        menuActions.put(3, this::pendingOrderInquiry);
        menuActions.put(4, () -> {}); // 로그아웃
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "--- 창고 관리자 메뉴 ---");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "1. 발주 상세조회");
        System.out.printf("%-40s%n", "2. 대시보드 (전주, 금주, 금일 주문 및 재고 비교)");
        System.out.printf("%-40s%n", "3. 보류 건 조회");
        System.out.printf("%-40s%n", "4. 로그아웃");
        System.out.println(LINE);
        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    private void showWarehouseDashboard() {
        try {
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            String todayStr = sdf.format(today);
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String prevWeekStart = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String prevWeekEnd = sdf.format(cal.getTime());
            cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String currentWeekStart = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String currentWeekEnd = sdf.format(cal.getTime());
            System.out.println("\n" + LINE);
            System.out.printf("%-40s%n", "=== 창고 관리자 대시보드 ===");
            System.out.println(LINE);
            System.out.printf("%-40s%n", "[전주 (" + prevWeekStart + " ~ " + prevWeekEnd + ") 주문]");
            List<OrderVO> ordersPrevWeek = getOrdersByDateRange(prevWeekStart, prevWeekEnd);
            if (ordersPrevWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersPrevWeek);
            }
            System.out.printf("%-40s%n", "[금주 (" + currentWeekStart + " ~ " + currentWeekEnd + ") 주문]");
            List<OrderVO> ordersCurrentWeek = getOrdersByDateRange(currentWeekStart, currentWeekEnd);
            if (ordersCurrentWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersCurrentWeek);
            }
            System.out.printf("%-40s%n", "[금일 (" + todayStr + ") 주문]");
            List<OrderVO> ordersToday = getOrdersByDate(todayStr);
            if (ordersToday.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersToday);
            }
            System.out.println(LINE);
            System.out.printf("%-40s%n", "[재고 vs 발주 승인 대기 건 비교]");
            Map<String, PendingInventoryComparisonDTO> comparison = getPendingInventoryComparisons();
            if (comparison.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                for (PendingInventoryComparisonDTO dto : comparison.values()) {
                    System.out.printf("  제품 ID: %-10s  재고: %-5d  대기: %-5d%n",
                            dto.getProductId(), dto.getInventoryQuantity(), dto.getPendingOrderQuantity());
                }
            }
            System.out.println(LINE);
        } catch (Exception e) {
            System.out.println("창고 관리자 대시보드 조회 중 오류: " + e.getMessage());
        }
    }

    private void warehouseDashboardRefresh() {
        showWarehouseDashboard();
    }

    private void warehouseOrderDetailInquiry() {
        String orderId = getInputOrExit("상세 조회할 발주 ID 입력: ");
        displayOrderDetails(orderId);
    }

    private void pendingOrderInquiry() {
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "[보류 건 조회]");
        List<OrderVO> pendingOrders = getOrdersByStatus("출고 보류");
        if (pendingOrders.isEmpty()) {
            System.out.printf("%-40s%n", "  없음");
        } else {
            displayOrderList(pendingOrders);
        }
        System.out.println(LINE);
    }

    private String getInputOrExit(String prompt) {
        Optional<String> inputOpt = InputUtil.getInput(prompt);
        if (!inputOpt.isPresent()) {
            System.out.println("전 메뉴로 돌아갑니다.");
            System.exit(0);
        }
        return inputOpt.get();
    }

    private String getInput(String prompt) {
        return InputUtil.getInput(prompt).orElse("back");
    }

    private void displayOrderList(List<OrderVO> orders) {
        for (OrderVO order : orders) {
            System.out.printf(" - 발주 ID: %-20s  발주일: %-10s  상태: %-15s%n",
                    order.getOrderId(), order.getOrderDate(), order.getOrderStatus());
        }
    }

    private void displayOrderDetails(String orderId) {
        OrderVO order = getOrder(orderId);
        if (order != null) {
            System.out.println("\n" + LINE);
            System.out.printf("%-40s%n", "[발주 상세 내역]");
            System.out.println(LINE);
            System.out.printf("발주 ID: %-20s%n", order.getOrderId());
            System.out.printf("발주일 : %-10s%n", order.getOrderDate());
            System.out.printf("상태   : %-15s%n", order.getOrderStatus());
            List<OrderDetailVO> details = getOrderDetails(orderId);
            if (details != null && !details.isEmpty()) {
                for (OrderDetailVO detail : details) {
                    System.out.printf(" - 제품 ID: %-10s  수량: %-5d%n", detail.getProductId(), detail.getOrderQuantity());
                }
            } else {
                System.out.printf("%-40s%n", "발주 상세 내역이 없습니다.");
            }
            System.out.println(LINE);
        } else {
            System.out.println("해당 발주를 찾을 수 없습니다.");
        }
    }


}
