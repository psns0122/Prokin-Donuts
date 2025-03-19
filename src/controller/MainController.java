package controller;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    controller.orderController.OrderMain orderController;
//    OutboundController outboundController;
    WarehouseController warehouseController;

    public MainController(InboundController inboundController, FranchiseController franchiseController, InventoryController inventoryController, MemberController memberController, controller.orderController.OrderMain orderController, WarehouseController warehouseController) {
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