package controller;

import common.warehouse.WarehouseText;
import dto.WarehouseDTO;
import service.WarehouseService;

import java.util.Scanner;

public class WarehouseControllerImpl implements WarehouseController {
    private final WarehouseService warehouseService;
    private final Scanner scanner;

    public WarehouseControllerImpl(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showWarehouseMenu() {
        while (true) {
            System.out.println(WarehouseText.HQ_WAREHOUSE_MENU.getText());
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> insertWarehouse();
                case 2 -> updateWarehouse();
                case 3 -> deleteWarehouse();
                case 4 -> viewWarehouseMenu();
                case 5 -> {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    @Override
    public void insertWarehouse() {
        System.out.println("=== 창고 등록 ===");
        System.out.print("창고 이름: ");
        String name = scanner.nextLine();
        System.out.print("창고 소재지: ");
        String location = scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.insertWarehouse(warehouse)) {
            System.out.println("창고가 등록되었습니다.");
        }
    }

    @Override
    public void updateWarehouse() {
        System.out.println("=== 창고 수정 ===");
        System.out.print("수정할 창고 ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("새로운 창고 이름: ");
        String newName = scanner.nextLine();
        System.out.print("새로운 창고 소재지: ");
        String newLocation = scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.updateWarehouse(warehouse)) {
            System.out.println("창고 정보가 수정되었습니다.");
        }
    }

    @Override
    public void deleteWarehouse() {
        System.out.println("=== 창고 삭제 ===");
        System.out.print("삭제할 창고 ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.deleteWarehouse(warehouse)) {
            System.out.println("창고가 삭제되었습니다.");
        }
    }

    public void viewWarehouseMenu() {
        while (true) {
            System.out.println(WarehouseText.HQ_WAREHOUSE_VIEW_MENU.getText());
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> viewWarehouses();
                case 2 -> viewWarehousesByLocation();
                case 3 -> {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    @Override
    public void viewWarehouses() {
        System.out.println("=== 전체 창고 조회 ===");
        System.out.println("~~~~~~~~~");
        System.out.println("~~~~~~~~~");
        System.out.println("~~~~~~~~~");
        System.out.println("~~~~~~~~~");
        System.out.println("~~~~~~~~~");
        System.out.println("~~~~~~~~~");
        warehouseService.viewWarehouses();
    }

    @Override
    public void viewWarehousesByLocation() {
        System.out.println(WarehouseText.HQ_WAREHOUSE_VIEW_LOCATION_MENU.getText());
        System.out.print("소재지를 선택하세요: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String location = switch (choice) {
            case 1 -> "수도권";
            case 2 -> "비수도권";
            default -> {
                System.out.println("잘못된 입력입니다.");
                yield null;
            }
        };

        if (location != null) {
            System.out.println("~~~~~~~~~");
            System.out.println("~~~~~~~~~");
            System.out.println("~~~~~~~~~");
            System.out.println("~~~~~~~~~");
            System.out.println("~~~~~~~~~");
            System.out.println("~~~~~~~~~");
            warehouseService.viewWarehousesByLocation(location);
        }
    }
}
