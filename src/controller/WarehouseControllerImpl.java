package controller;

import common.warehouse.WarehouseErrorCode;
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
    public void showWarehouseMenu(int authorityId) {
        // TODO : 본사 / 창고관리자 로그인 권한 정보가 전달되어야함

        switch (authorityId) {
            case 1 -> headQuartersMenu();
            case 2 -> warehouseManagerMenu();
            default -> System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
        }
    }

    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////

    public void headQuartersMenu() {
        while (true) {
            System.out.print(WarehouseText.HQ_WAREHOUSE_MENU.getText());
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> insertWarehouse();
                case 2 -> updateWarehouse();
                case 3 -> deleteWarehouse();
                case 4 -> viewWarehouseMenu();
                case 5 -> {
                    System.out.println(WarehouseText.BACK_ACTION.getText());
                    return;
                }
                default -> System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
            }
        }
    }

    @Override
    public void insertWarehouse() {
        System.out.println(WarehouseText.HQ_INSERT_WAREHOUSE_HEADER.getText());

        System.out.print(WarehouseText.HQ_INSERT_WAREHOUSE_NAME.getText());
        String warehouseName = scanner.nextLine();
        System.out.print(WarehouseText.HQ_INSERT_WAREHOUSE_LOCATION.getText());
        String location = scanner.nextLine();
        System.out.print(WarehouseText.HQ_INSERT_WAREHOUSE_SIZE.getText());
        int size = scanner.nextInt();
        scanner.nextLine();
        System.out.print(WarehouseText.HQ_INSERT_WAREHOUSE_MANAGER_NAME.getText());
        String managerName = scanner.nextLine();
        System.out.print(WarehouseText.HQ_INSERT_WAREHOUSE_MANAGER_ID.getText());
        int managerID = scanner.nextInt();
        scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.insertWarehouse(warehouse)) {
            System.out.println(WarehouseText.HQ_INSERT_WAREHOUSE.getText());
        }
    }

    @Override
    public void updateWarehouse() {
        System.out.println(WarehouseText.HQ_UPDATE_WAREHOUSE_HEADER.getText());
        System.out.print(WarehouseText.HQ_UPDATE_WAREHOUSE_ID.getText());
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print(WarehouseText.HQ_UPDATE_WAREHOUSE_NAME.getText());
        String newName = scanner.nextLine();
        System.out.print(WarehouseText.HQ_UPDATE_WAREHOUSE_MANAGER_NAME.getText());
        String newManagerName = scanner.nextLine();
        System.out.print(WarehouseText.HQ_UPDATE_WAREHOUSE_MANAGER_ID.getText());
        int newManagerID = scanner.nextInt();
        scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.updateWarehouse(warehouse)) {
            System.out.println(WarehouseText.HQ_UPDATE_WAREHOUSE.getText());
        }
    }

    @Override
    public void deleteWarehouse() {
        System.out.println(WarehouseText.HQ_DELETE_WAREHOUSE_HEADER.getText());
        System.out.print(WarehouseText.HQ_DELETE_WAREHOUSE_ID.getText());
        int id = scanner.nextInt();
        scanner.nextLine();

        WarehouseDTO warehouse = new WarehouseDTO();
        if (warehouseService.deleteWarehouse(warehouse)) {
            System.out.println(WarehouseText.HQ_DELETE_WAREHOUSE.getText());
        }
    }

    public void viewWarehouseMenu() {
        while (true) {
            System.out.print(WarehouseText.HQ_WAREHOUSE_VIEW_MENU.getText());
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> viewWarehouses();
                case 2 -> viewWarehousesByLocation();
                case 3 -> {
                    System.out.println(WarehouseText.BACK_ACTION);
                    return;
                }
                default -> System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
            }
        }
    }

    @Override
    public void viewWarehouses() {
        System.out.println(WarehouseText.HQ_SHOW_WAREHOUSE_BY_ALL_HEADER.getText());
        warehouseService.viewWarehouses().forEach(System.out::println);
    }

    @Override
    public void viewWarehousesByLocation() {
        System.out.print(WarehouseText.HQ_WAREHOUSE_VIEW_LOCATION_MENU.getText());
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 3) {
            return;
        }

        String location = switch (choice) {
            case 1 -> "수도권";
            case 2 -> "비수도권";
            default -> {
                System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
                yield null;
            }
        };

        if (location != null) {
            System.out.println(WarehouseText.HQ_SHOW_WAREHOUSE_BY_LOCATION_HEADER.getText());
            warehouseService.viewWarehousesByLocation(location).forEach(System.out::println);
        }
    }

    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////

    public void warehouseManagerMenu() {
        while (true) {
            System.out.print(WarehouseText.WAREHOUSE_MANAGE_MENU.getText());
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> viewWarehousesByLogin();
                case 2 -> viewInventoryByLogin();
                case 3 -> {
                    System.out.println(WarehouseText.BACK_ACTION.getText());
                    return;
                }
                default -> System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
            }
        }
    }

    public void viewWarehousesByLogin() {
        System.out.println(WarehouseText.HQ_SHOW_WAREHOUSE_BY_LOGIN_HEADER.getText());
    }

    public void viewInventoryByLogin() {
        System.out.println(WarehouseText.HQ_SHOW_INVENTORY_BY_LOGIN_HEADER.getText());
    }

}
