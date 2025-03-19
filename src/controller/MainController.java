package controller;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    OutboundController outboundController;
    WarehouseController warehouseController;
    public MainController(InboundController inboundController, FranchiseController franchiseController, InventoryController inventoryController, MemberController memberController, OutboundController outboundController, WarehouseController warehouseController) {
        this.inboundController = inboundController;
        this.franchiseController = franchiseController;
        this.inventoryController = inventoryController;
        this.memberController = memberController;
        this.outboundController = outboundController;
        this.warehouseController = warehouseController;
    }

    public void run() {


    }
}