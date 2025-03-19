package controller.orderController;

import common.util.InputUtil;
import common.util.MenuUtil;
import dto.orderDTO.OrderDTO;
import dto.orderDTO.OrderItemDTO;
import dto.orderDTO.OrderStatisticsDTO;
import dto.orderDTO.PendingInventoryComparisonDTO;
import repository.InventoryRepo;
import repository.InventoryRepoImpl;
import repository.orderRepo.OrderRepo;
import repository.orderRepo.OrderRepoImpl;
import repository.outboundRepo.OutboundRepo;
import repository.outboundRepo.OutboundRepoImpl;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;
import service.outboundService.OutboundService;
import service.outboundService.OutboundServiceImpl;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

public class OrderMain {
    private static OrderController controller;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String LINE = "========================================";

    public static void main(String[] args) {
        initController();
        while (true) {
            showMainMenu();
        }
    }

    private static void initController() {
        // 의존성 주입을 통한 OrderService 생성
        OrderRepo orderRepo = new OrderRepoImpl();
        InventoryRepo inventoryRepo = new InventoryRepoImpl();
        OutboundRepo outboundRepo = new OutboundRepoImpl();
        OutboundService outboundService = new OutboundServiceImpl(inventoryRepo, orderRepo, outboundRepo);
        OrderService service = new OrderServiceImpl(orderRepo, inventoryRepo, outboundService);
        controller = new OrderControllerImpl(service);
    }

    private static void showMainMenu() {
        Map<Integer, Runnable> mainMenuActions = new HashMap<>();
        mainMenuActions.put(1, OrderMain::handleStoreManager);
        mainMenuActions.put(2, OrderMain::handleHeadquarters);
        mainMenuActions.put(3, OrderMain::handleWarehouseManager);
        mainMenuActions.put(4, () -> {
            System.out.println("시스템을 종료합니다.");
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
    private static void handleStoreManager() {
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, OrderMain::submitOrder);
        menuActions.put(2, OrderMain::storeManagerCheckOrders);
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

    private static int getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.get(Calendar.MONTH) + 1;
    }

    private static int getLastMonthYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.get(Calendar.YEAR);
    }

    private static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    private static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    private static void showStoreManagerDetailedStatistics(int year, int month) {
        String franchiseId = getInputOrExit("가맹점 아이디 입력: ");
        OrderStatisticsDTO stats = controller.getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
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

    private static void submitOrder() {
        String franchiseId = getInputOrExit("가맹점 아이디 입력: ");
        List<OrderItemDTO> items = buildOrderItems();
        if (items.isEmpty()) {
            System.out.println("등록된 상품이 없습니다. 발주 요청 제출을 취소합니다.");
            return;
        }
        OrderDTO dto = new OrderDTO(franchiseId, items);
        String orderId = controller.submitOrder(dto);
        System.out.printf("발주 요청 제출 완료. 발주 ID: %s%n", orderId);
    }

    private static List<OrderItemDTO> buildOrderItems() {
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

    private static OrderItemDTO findOrderItemByProductId(List<OrderItemDTO> items, String productId) {
        for (OrderItemDTO item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    private static void storeManagerCheckOrders() {
        String franchiseId = getInputOrExit("확인할 가맹점 아이디 입력: ");
        List<OrderVO> orders = controller.getOrdersByFranchiseId(franchiseId);
        if (orders.isEmpty()) {
            System.out.println("해당 가맹점의 발주 내역이 없습니다.");
            return;
        }
        displayOrderList(orders);
        String input = getInput("상세 조회할 발주 ID를 입력하거나 'back' 입력하여 메뉴로 돌아가세요: ");
        if (input.equalsIgnoreCase("back")) return;
        String orderId = input;
        displayOrderDetails(orderId);
    }

    /* ===== 본사 메뉴 ===== */
    private static void handleHeadquarters() {
        showHeadquarterDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, OrderMain::headquarterCheckAndApproveOrders);
        menuActions.put(2, () -> {}); // 로그아웃
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "--- 본사 메뉴 ---");
        System.out.println(LINE);
        System.out.printf("%-40s%n", "1. 미승인 발주 조회 및 승인");
        System.out.printf("%-40s%n", "2. 로그아웃");
        System.out.println(LINE);
        MenuUtil.handleMenuSelection("옵션 선택 (숫자 입력, 종료: exit): ", menuActions);
    }

    private static void headquarterCheckAndApproveOrders() {
        List<OrderVO> orders = controller.getOrdersByStatus("발주 승인 대기중");
        if (orders.isEmpty()) {
            System.out.println("미승인 발주 요청이 없습니다.");
            return;
        }
        displayOrderList(orders);
        String orderInput = getInputOrExit("상세 조회할 발주 ID 입력: ");
        String orderId = orderInput;
        displayOrderDetails(orderId);
        String approve = getInput("이 발주를 승인하시겠습니까? (y/n): ");
        if (approve.equalsIgnoreCase("y")) {
            controller.approveOrder(orderId);
        }
    }

    private static void showHeadquarterDashboard() {
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
            List<OrderVO> ordersPrevWeek = controller.getOrdersByDateRange(prevWeekStart, prevWeekEnd);
            if (ordersPrevWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersPrevWeek);
            }
            System.out.printf("%-40s%n", "[금주 (" + currentWeekStart + " ~ " + currentWeekEnd + ") 주문]");
            List<OrderVO> ordersCurrentWeek = controller.getOrdersByDateRange(currentWeekStart, currentWeekEnd);
            if (ordersCurrentWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersCurrentWeek);
            }
            System.out.printf("%-40s%n", "[금일 (" + todayStr + ") 주문]");
            List<OrderVO> ordersToday = controller.getOrdersByDate(todayStr);
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
    private static void handleWarehouseManager() {
        showWarehouseDashboard();
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(1, OrderMain::warehouseOrderDetailInquiry);
        menuActions.put(2, OrderMain::warehouseDashboardRefresh);
        menuActions.put(3, OrderMain::pendingOrderInquiry);
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

    private static void showWarehouseDashboard() {
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
            List<OrderVO> ordersPrevWeek = controller.getOrdersByDateRange(prevWeekStart, prevWeekEnd);
            if (ordersPrevWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersPrevWeek);
            }
            System.out.printf("%-40s%n", "[금주 (" + currentWeekStart + " ~ " + currentWeekEnd + ") 주문]");
            List<OrderVO> ordersCurrentWeek = controller.getOrdersByDateRange(currentWeekStart, currentWeekEnd);
            if (ordersCurrentWeek.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersCurrentWeek);
            }
            System.out.printf("%-40s%n", "[금일 (" + todayStr + ") 주문]");
            List<OrderVO> ordersToday = controller.getOrdersByDate(todayStr);
            if (ordersToday.isEmpty()) {
                System.out.printf("%-40s%n", "  없음");
            } else {
                displayOrderList(ordersToday);
            }
            System.out.println(LINE);
            System.out.printf("%-40s%n", "[재고 vs 발주 승인 대기 건 비교]");
            Map<String, PendingInventoryComparisonDTO> comparison = controller.getPendingInventoryComparisons();
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

    private static void warehouseDashboardRefresh() {
        showWarehouseDashboard();
    }

    private static void warehouseOrderDetailInquiry() {
        String orderInput = getInputOrExit("상세 조회할 발주 ID 입력: ");
        String orderId = orderInput;
        displayOrderDetails(orderId);
    }

    private static void pendingOrderInquiry() {
        System.out.println("\n" + LINE);
        System.out.printf("%-40s%n", "[보류 건 조회]");
        List<OrderVO> pendingOrders = controller.getOrdersByStatus("출고 보류");
        if (pendingOrders.isEmpty()) {
            System.out.printf("%-40s%n", "  없음");
        } else {
            displayOrderList(pendingOrders);
        }
        System.out.println(LINE);
    }

    private static String getInputOrExit(String prompt) {
        Optional<String> inputOpt = InputUtil.getInput(prompt);
        if (!inputOpt.isPresent()) {
            System.out.println("전 메뉴로 돌아갑니다.");
            System.exit(0);
        }
        return inputOpt.get();
    }

    private static String getInput(String prompt) {
        return InputUtil.getInput(prompt).orElse("back");
    }

    private static void displayOrderList(List<OrderVO> orders) {
        for (OrderVO order : orders) {
            System.out.printf(" - 발주 ID: %-20s  발주일: %-10s  상태: %-15s%n",
                    order.getOrderId(), order.getOrderDate(), order.getOrderStatus());
        }
    }

    private static void displayOrderDetails(String orderId) {
        OrderVO order = controller.getOrder(orderId);
        if (order != null) {
            System.out.println("\n" + LINE);
            System.out.printf("%-40s%n", "[발주 상세 내역]");
            System.out.println(LINE);
            System.out.printf("발주 ID: %-20s%n", order.getOrderId());
            System.out.printf("발주일 : %-10s%n", order.getOrderDate());
            System.out.printf("상태   : %-15s%n", order.getOrderStatus());
            List<OrderDetailVO> details = controller.getOrderDetails(orderId);
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
