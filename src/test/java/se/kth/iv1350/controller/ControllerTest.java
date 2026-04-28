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
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        RepairOrderDTO createdOrder = controller.findRepairOrder("123456789");

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getRepairOrderId());
        assertEquals("broken brakes", createdOrder.getProblemDescr());
        assertEquals("123456789", createdOrder.getPhoneNumber());
        assertEquals("BIKE123", createdOrder.getBikeSerialNumber());
        assertEquals("CREATED", createdOrder.getState());
        assertEquals(0.0, createdOrder.getTotalCost(), 0.0);
    }

    @Test
    public void testFindRepairOrderReturnsLatestMatchingRepairOrder(){
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        controller.createRepairOrder("broken gears", "123456789", "BIKE456");

        RepairOrderDTO foundOrder = controller.findRepairOrder("123456789");

        assertNotNull(foundOrder);
        assertEquals("Repair Order: 2", foundOrder.getRepairOrderId());
        assertEquals("broken gears", foundOrder.getProblemDescr());
        assertEquals("123456789", foundOrder.getPhoneNumber());
        assertEquals("BIKE456", foundOrder.getBikeSerialNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder(){
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        RepairOrderDTO createdOrder = controller.findRepairOrder("123456789");

        controller.addRepairTask(createdOrder.getRepairOrderId(), "Replace brake cable", 250.0);

        RepairOrderDTO updatedOrder = repairOrderRegistry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(updatedOrder);
        assertEquals(createdOrder.getRepairOrderId(), updatedOrder.getRepairOrderId());
        assertEquals(250.0, updatedOrder.getTotalCost(), 0.0);
    }

    @Test
    public void testFindAllRepairOrdersReturnsAllMatchingRepairOrders(){
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);

        controller.createRepairOrder("broken brakes", "123456789", "BIKE123");
        controller.createRepairOrder("broken light", "123456789", "BIKE456");
        controller.createRepairOrder("broken frame", "123456789", "BIKE789");

        RepairOrderDTO[] foundOrders = controller.findAllRepairOrders("123456789");

        assertNotNull(foundOrders);
        assertEquals(3, foundOrders.length);
        assertEquals("123456789", foundOrders[0].getPhoneNumber());
        assertEquals("123456789", foundOrders[1].getPhoneNumber());
        assertEquals("123456789", foundOrders[2].getPhoneNumber());
        assertEquals("broken brakes", foundOrders[0].getProblemDescr());
        assertEquals("broken light", foundOrders[1].getProblemDescr());
        assertEquals("broken frame", foundOrders[2].getProblemDescr());
    }
}
