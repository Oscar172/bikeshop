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
    }

    @Test
    public void testFindAllRepairOrdersReturnsAllMatchingRepairOrders() {
        controller.createRepairOrder("Change breaks", phoneNumber, "BIKE1");
        controller.createRepairOrder("Oil the chain", phoneNumber, "BIKE2");
        controller.createRepairOrder("Pump the tires", phoneNumber, "BIKE3");

        RepairOrderDTO[] foundOrders = controller.findAllRepairOrders(phoneNumber);

        assertNotNull(foundOrders);
        assertEquals(3, foundOrders.length);
        assertEquals(phoneNumber, foundOrders[0].getPhoneNumber());
        assertEquals(phoneNumber, foundOrders[1].getPhoneNumber());
        assertEquals(phoneNumber, foundOrders[2].getPhoneNumber());
        assertEquals("BIKE1", foundOrders[0].getBikeSerialNumber());
        assertEquals("BIKE2", foundOrders[1].getBikeSerialNumber());
        assertEquals("BIKE3", foundOrders[2].getBikeSerialNumber());
        assertEquals("Change breaks", foundOrders[0].getProblemDescr());
        assertEquals("Oil the chain", foundOrders[1].getProblemDescr());
        assertEquals("Pump the tires", foundOrders[2].getProblemDescr());
    }
}
