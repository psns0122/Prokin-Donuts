package controller;

import common.inventory.InventoryText;
import common.warehouse.WarehouseErrorCode;
import common.warehouse.WarehouseText;
import service.InventoryService;

import java.util.Scanner;

public class InventoryControllerImpl implements InventoryController {
    InventoryService inventoryService;
    Scanner scanner = new Scanner(System.in);
    public InventoryControllerImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @Override
    public void showInventoryMenu(int amount) {
        System.out.println(InventoryText.MENU_HEADER.getText());
        System.out.println(InventoryText.SHOW_INVENTORY.getText());
        this.inventoryService.getAllInventory().forEach(System.out::println);
    }

}
