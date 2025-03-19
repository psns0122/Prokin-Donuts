package config;

import controller.*;
import repository.*;
import repository.orderRepo.OrderRepo;
import repository.orderRepo.OrderRepoImpl;
import repository.outboundRepo.OutboundRepo;
import repository.outboundRepo.OutboundRepoImpl;
import service.*;
import service.orderService.OrderService;
import service.orderService.OrderServiceImpl;
import service.outboundService.OutboundService;
import service.outboundService.OutboundServiceImpl;

public class DiConfig {

    //입고 의존성 주입
    //repo
    private final InboundRepo inboundRepo = new InboundRepoImpl();

    //service
    private final InboundService inboundService = new InboundServiceImpl(inboundRepo);

    //controller
    private final InboundController inboundController = new InboundControllerImpl(inboundService);


    //가맹점 의존성 주입
    //repo
    private final FranchiseRepo franchiseRepo = new FranchiseRepoImpl();

    //service
    private final FranchiseService franchiseService = new FranchiseServiceImpl(franchiseRepo);

    //controller
    private final FranchiseController franchiseController = new FranchiseControllerImpl(franchiseService);


    //재고 의존성 주입
    //repo
    private final InventoryRepo inventoryRepo = new InventoryRepoImpl();

    //service
    private final InventoryService inventoryService = new InventoryServiceImpl(inventoryRepo);

    //controller
    private final InventoryController inventoryController = new InventoryControllerImpl(inventoryService);


    //회원 의존성 주입
    //repo
    private final MemberRepo memberRepo = new MemberRepoImpl();

    //service
    private final MemberService memberService = new MemberServiceImpl(memberRepo);

    //controller
    private final MemberController memberController = new MemberControllerImpl(memberService);


    // 발주 repository
    private final OrderRepo orderRepo = new OrderRepoImpl();
    // 출고 repository
    private final OutboundRepo outboundRepo = new OutboundRepoImpl();
    // 출고 service : inventoryRepo, orderRepo, outboundRepo 주입
    private final OutboundService outboundService = new OutboundServiceImpl(inventoryRepo, orderRepo, outboundRepo);
    // 발주 service : orderRepo, inventoryRepo, outboundService 주입
    private final OrderService orderService = new OrderServiceImpl(orderRepo, inventoryRepo, outboundService);
    // 발주 controller
    private final OrderController orderController = new OrderControllerImpl(orderService);
    // 출고 controller
    //private final OutboundController outboundController = new OutboundControllerImpl(outboundService);

    // 재고 의존성 주입
    //repo
    private final WarehouseRepo warehouseRepo = new WarehouseRepoImpl();

    //service
    private final WarehouseService warehouseService = new WarehouseServiceImpl(warehouseRepo);

    //controller
    private final WarehouseController warehouseController = new WarehouseControllerImpl(warehouseService);

    public MainController mainController() {
        return new MainController(
                inboundController,
                franchiseController,
                inventoryController,
                memberController,
                orderController,
                warehouseController
        );
    }

}