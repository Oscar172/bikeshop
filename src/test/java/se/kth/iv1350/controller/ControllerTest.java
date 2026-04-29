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

        assertNotNull("Order should have been created.", createdOrder);
        assertNotNull("The repair order id should not be null after it has been created.", createdOrder.getRepairOrderId());
        assertEquals("The problem description in the order does not match the description entered at creation.", description, createdOrder.getProblemDescr());
        assertEquals("The phone number in the created order does not match the customer's phone number.", phoneNumber, createdOrder.getPhoneNumber());
        assertEquals("The order state should be 'CREATED' immediately after the order is created.", "CREATED", createdOrder.getState());
        assertEquals("The total cost of the order was not calculated correctly.", totalCost, createdOrder.getTotalCost(), 0.0001);
    }

    @Test
    public void testFindRepairOrderReturnsLatestMatchingRepairOrder() {
        controller.createRepairOrder(description, phoneNumber, bikeSerialNumber);
        String anotherDescription = "broken chain";
        String anotherBikeSerialNumber = "BIKE456";
        controller.createRepairOrder(anotherDescription, phoneNumber, anotherBikeSerialNumber);

        RepairOrderDTO foundOrder = controller.findRepairOrder(phoneNumber);

        assertNotNull("Search failed to return an order.", foundOrder);
        assertEquals("The found order does not have the expected id.", "Repair Order: 2", foundOrder.getRepairOrderId());
        assertEquals("The problem description in the found order does not match the expected description.", anotherDescription, foundOrder.getProblemDescr());
        assertEquals("The phone number in the found order does not match the customer's phone number.", phoneNumber, foundOrder.getPhoneNumber());
        assertEquals("The bike serial number in the found order does not match the expected serial number.", anotherBikeSerialNumber, foundOrder.getBikeSerialNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        controller.createRepairOrder(description, phoneNumber, bikeSerialNumber);
        RepairOrderDTO createdOrder = controller.findRepairOrder(phoneNumber);

        double taskCost = 150.0;
        String repairTaskDescr = "Replace cable";
        controller.addRepairTask(createdOrder.getRepairOrderId(), repairTaskDescr, taskCost);

        RepairOrderDTO updatedOrder = repairOrderRegistry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull("Updated order should not be null.", updatedOrder);
        assertEquals("The repair order id should remain the same after update.", createdOrder.getRepairOrderId(), updatedOrder.getRepairOrderId());
        assertEquals("The total cost in the updated order does not match the expected cost.", taskCost, updatedOrder.getTotalCost(), 0.0001);
    }

    @Test
    public void testFindAllRepairOrdersReturnsAllMatchingRepairOrders() {
        controller.createRepairOrder("Change breaks", phoneNumber, "BIKE1");
        controller.createRepairOrder("Oil the chain", phoneNumber, "BIKE2");
        controller.createRepairOrder("Pump the tires", phoneNumber, "BIKE3");

        RepairOrderDTO[] foundOrders = controller.findAllRepairOrders(phoneNumber);

        assertNotNull("The search result array should not be null.", foundOrders);
        assertEquals("The search should have returned exactly 3 orders.", 3, foundOrders.length);
        assertEquals("The phone number does not match at index 0.", phoneNumber, foundOrders[0].getPhoneNumber());
        assertEquals("The phone number does not match at index 1.", phoneNumber, foundOrders[1].getPhoneNumber());
        assertEquals("The phone number does not match at index 2.", phoneNumber, foundOrders[2].getPhoneNumber());
        assertEquals("The bike serial number does not match at index 0.", "BIKE1", foundOrders[0].getBikeSerialNumber());
        assertEquals("The bike serial number does not match at index 1.", "BIKE2", foundOrders[1].getBikeSerialNumber());
        assertEquals("The bike serial number does not match at index 2.", "BIKE3", foundOrders[2].getBikeSerialNumber());
        assertEquals("The problem description does not match at index 0.", "Change breaks", foundOrders[0].getProblemDescr());
        assertEquals("The problem description does not match at index 1.", "Oil the chain", foundOrders[1].getProblemDescr());
        assertEquals("The problem description does not match at index 2.", "Pump the tires", foundOrders[2].getProblemDescr());
    }
}
