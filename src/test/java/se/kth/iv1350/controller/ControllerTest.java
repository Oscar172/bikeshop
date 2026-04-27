package se.kth.iv1350.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

public class ControllerTest {

    @Test
    public void testCreateRepairOrderCreatesRepairOrderWithCorrectValues(){
        CustomerRegistry customerRegistry = CustomerRegistry.customerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.repairOrderRegistry();
        Printer printer = Printer.createPrinter();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        RepairOrderDTO createdOrder = controller.createRepairOrder("broken brakes", "123456789", "BIKE123");

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.repairOrderId());
        assertEquals("broken brakes", createdOrder.problemDescr());
        assertEquals("123456789", createdOrder.phoneNumber());
        assertEquals("BIKE123", createdOrder.bikeSerialNumber());
        assertEquals("CREATED", createdOrder.state());
        assertEquals(0.0, createdOrder.totalCost(), 0.0);
    }

    @Test
    public void testFindRepairOrderReturnsLatestMatchingRepairOrder(){
        CustomerRegistry customerRegistry = CustomerRegistry.customerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.repairOrderRegistry();
        Printer printer = Printer.createPrinter();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        RepairOrderDTO latestOrder = controller.createRepairOrder("broken brakes", "123456789", "BIKE123");

        RepairOrderDTO foundOrder = controller.findRepairOrder("123456789");

        assertNotNull(foundOrder);
        assertEquals(latestOrder.repairOrderId(), foundOrder.repairOrderId());
        assertEquals("broken brakes", foundOrder.problemDescr());
        assertEquals("123456789", foundOrder.phoneNumber());
        assertEquals("BIKE123", foundOrder.bikeSerialNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder(){
        CustomerRegistry customerRegistry = CustomerRegistry.customerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.repairOrderRegistry();
        Printer printer = Printer.createPrinter();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        RepairOrderDTO createdOrder = controller.createRepairOrder("broken brakes", "123456789", "BIKE123");

        controller.addRepairTask(createdOrder.repairOrderId(), "Replace brake cable", 250.0);

        RepairOrderDTO updatedOrder = repairOrderRegistry.returnRepairOrderDTO(createdOrder.repairOrderId());

        assertNotNull(updatedOrder);
        assertEquals(createdOrder.repairOrderId(), updatedOrder.repairOrderId());
        assertEquals(250.0, updatedOrder.totalCost(), 0.0);
    }

    @Test
    public void testFindAllRepairOrdersReturnsAllMatchingRepairOrders(){
        CustomerRegistry customerRegistry = CustomerRegistry.customerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.repairOrderRegistry();
        Printer printer = Printer.createPrinter();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        controller.createRepairOrder("broken light", "123456789", "BIKE456");
        controller.createRepairOrder("broken frame", "123456789", "BIKE789");

        RepairOrderDTO[] foundOrders = controller.findAllRepairOrders("123456789");

        assertNotNull(foundOrders);
        assertEquals(3, foundOrders.length);
        assertEquals("123456789", foundOrders[0].phoneNumber());
        assertEquals("123456789", foundOrders[1].phoneNumber());
        assertEquals("123456789", foundOrders[2].phoneNumber());
        assertEquals("broken brakes", foundOrders[0].problemDescr());
        assertEquals("broken light", foundOrders[1].problemDescr());
        assertEquals("broken frame", foundOrders[2].problemDescr());
    }
}
