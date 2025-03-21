package controller;

import common.login.LoginText;
import common.util.LoginUtil;
import common.util.MenuUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    OrderController orderController;
    WarehouseController warehouseController;
    LoginController loginController;

    Scanner scanner = new Scanner(System.in);

    public MainController(InboundController inboundController,
                          FranchiseController franchiseController,
                          InventoryController inventoryController,
                          MemberController memberController,
                          OrderController orderController,
                          WarehouseController warehouseController,
                          LoginController loginController) {
        this.inboundController = inboundController;
        this.franchiseController = franchiseController;
        this.inventoryController = inventoryController;
        this.memberController = memberController;
        this.orderController = orderController;
        this.warehouseController = warehouseController;
        this.loginController = loginController;
    }

    private void printHQMenu(int authority) {
        System.out.println(LoginText.HQ_MAINMENU.getText());
        System.out.print("메뉴 선택 : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> memberController.HQMenu();
            case 2 -> warehouseController.showWarehouseMenu(authority);
            case 3 -> inventoryController.showInventoryMenu(authority);
            case 4 -> franchiseController.showFranchiseMenu(authority);
            case 5 -> orderController.runHeadquartersMenu();
            case 6 -> inboundController.Headquarters();
            // 본사관리자
        }
    }
    private void printWMMenu(int authority) {
        System.out.println(LoginText.WM_MAINMENU.getText());
        System.out.print("메뉴 선택 : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> memberController.WMMenu();
            case 2 -> warehouseController.showWarehouseMenu(authority);
            case 3 -> inventoryController.showInventoryMenu(authority);
            case 4 -> orderController.runWarehouseMenu();
            case 5 -> inboundController.warehouseManager();
            // 본사관리자
        }
    }
    private void printFMMenu(int authority) {
        System.out.println(LoginText.FM_MAINMENU.getText());
        System.out.print("메뉴 선택 : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> memberController.FMMenu();
            case 2 -> orderController.runStoreManagerMenu();
            // 본사관리자
        }
    }

    public void run() {
        while (true) {
            // 로그인 메뉴
            loginController.loginPlay();

            // 로그인 후 권한 가져오기
            int authority = LoginUtil.getLoginMember().getAuthorityId();

            // 로그인 후 각 권한별 메뉴
            switch (authority) {
                case 1 -> printHQMenu(authority);
                case 2 -> printWMMenu(authority);
                case 3 -> printFMMenu(authority);
            }
        }
    }
}