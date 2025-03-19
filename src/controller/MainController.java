package controller;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    OrderController orderController;
//    OutboundController outboundController;
    WarehouseController warehouseController;
    LoginController loginController;

    public MainController(InboundController inboundController, FranchiseController franchiseController, InventoryController inventoryController, MemberController memberController, OrderController orderController, WarehouseController warehouseController) {
        this.inboundController = inboundController;
        this.franchiseController = franchiseController;
        this.inventoryController = inventoryController;
        this.memberController = memberController;
        this.orderController = orderController;
//        this.outboundController = outboundController;
        this.warehouseController = warehouseController;
    }

    public void run() {


    }
}