package controller;

import common.util.LoginUtil;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    OrderController orderController;
    WarehouseController warehouseController;
    LoginController loginController;

    public MainController(InboundController inboundController, FranchiseController franchiseController, InventoryController inventoryController, MemberController memberController, OrderController orderController, WarehouseController warehouseController, LoginController loginController) {
        this.inboundController = inboundController;
        this.franchiseController = franchiseController;
        this.inventoryController = inventoryController;
        this.memberController = memberController;
        this.orderController = orderController;
        this.warehouseController = warehouseController;
        this.loginController = loginController;
    }

    public void run() {
        loginController.MainMenu();
        int authority = LoginUtil.getLoginMember().getAuthorityId();

        switch (authority) {
            case 1: // 본사관리자
                // inboundController.Headquarters();
                break;
            case 2:
                // 창고관리자
                break;
            case 3:
                // 점주
                break;
        }


    }
}