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
            case 1 -> warehouseController.showWarehouseMenu(authority);
            case 2 -> franchiseController.showFranchiseMenu(authority);
            case 3 -> warehouseController.showWarehouseMenu(authority);
            case 4 -> warehouseController.showWarehouseMenu(authority);
            case 5 -> warehouseController.showWarehouseMenu(authority);
            case 6 -> warehouseController.showWarehouseMenu(authority);
            // 본사관리자
        }
    }
    private void printWMMenu(int authority) {
        System.out.println(LoginText.WM_MAINMENU.getText());
        warehouseController.showWarehouseMenu(authority);
        // 창고관리자
    }
    private void printFMMenu(int authority) {
        System.out.println(LoginText.FM_MAINMENU.getText());
        // 점주
    }

    public void run() {
        inventoryController.showInventoryMenu(1);

        while (true) {
            // 로그인 메뉴
            //memberController.MainMune();

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