package controller;

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
        loginController.mainMenu();


    }
}