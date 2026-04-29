package se.kth.iv1350.controller;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

public class ControllerTest {
    private Controller controller;
    private RepairOrderRegistry repairOrderRegistry;

    private final String phoneNumber = "1234567";
    private final String description = "Broken breaks";
    private final String bikeSerialNumber = "BIKE123";
    private final double totalCost = 0.0;

    @Before
    public void setUp() {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        controller = new Controller(customerRegistry, repairOrderRegistry, printer);
    }

    @After
    public void tearDown() {
        controller = null;
        repairOrderRegistry = null;
    }

    @Test
    public void testCreateRepairOrderCreatesRepairOrderWithCorrectValues() {
        controller.createRepairOrder(description, phoneNumber, bikeSerialNumber);
        RepairOrderDTO createdOrder = controller.findRepairOrder(phoneNumber);

        assertNotNull("Order should have been created", createdOrder);
        assertNotNull(createdOrder.getRepairOrderId());
        assertEquals(description, createdOrder.getProblemDescr());
        assertEquals(phoneNumber, createdOrder.getPhoneNumber());
        assertEquals("CREATED", createdOrder.getState());
        assertEquals(totalCost, createdOrder.getTotalCost(), 0.0001);

        /* 
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
        */
    }

    @Test
    public void testFindRepairOrderReturnsLatestMatchingRepairOrder() {
        controller.createRepairOrder(description, phoneNumber, bikeSerialNumber);
        String anotherDescription = "broken chain";
        String anotherBikeSerialNumber = "BIKE456";
        controller.createRepairOrder(anotherDescription, phoneNumber, anotherBikeSerialNumber);

        RepairOrderDTO foundOrder = controller.findRepairOrder(phoneNumber);

        assertNotNull(foundOrder);
        assertEquals("Repair Order: 2", foundOrder.getRepairOrderId());
        assertEquals(anotherDescription, foundOrder.getProblemDescr());
        assertEquals(phoneNumber, foundOrder.getPhoneNumber());
        assertEquals(anotherBikeSerialNumber, foundOrder.getBikeSerialNumber());

        /* 
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
        */
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        controller.createRepairOrder(description, phoneNumber, bikeSerialNumber);
        RepairOrderDTO createdOrder = controller.findRepairOrder(phoneNumber);

        double taskCost = 150.0;
        String repairTaskDescr = "Replace cable";
        controller.addRepairTask(createdOrder.getRepairOrderId(), repairTaskDescr, taskCost);

        RepairOrderDTO updatedOrder = repairOrderRegistry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(updatedOrder);
        assertEquals(createdOrder.getRepairOrderId(), updatedOrder.getRepairOrderId());
        assertEquals(taskCost, updatedOrder.getTotalCost(), 0.0001);
        /* 
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
        */
    }

    @Test
    public void testFindAllRepairOrdersReturnsAllMatchingRepairOrders() {
        controller.createRepairOrder("Change breaks", phoneNumber, "BIKE1");
        controller.createRepairOrder("Oil the chain", phoneNumber, "BIKE2");
        controller.createRepairOrder("Pump the tires", phoneNumber, "BIKE3");

        RepairOrderDTO[] foundOrders = controller.findAllRepairOrder(phoneNumber);

        assertNotNull(foundOrders);
        assertEquals(3, foundOrders.length);
        assertEquals("Change breaks", foundOrders[0].getProblemDescr());
        assertEquals("Pump the tires", foundOrders[2].getProblemDescr());

        /* 
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
        */
    }
}
