package se.kth.iv1350.integration;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;

    private final String phoneNumber = "123456789";
    private final String description = "Broken breaks";
    private final String bikeSerialNumber = "BIKE123";
    private final String taskDescription = "Oil the chain";
    private final String diagResult = "Bad breaks";

    @Before
    public void setUp() {
        registry = new RepairOrderRegistry();
    }

    @After
    public void tearDown() {
        registry = null;
    }

    private void createTestOrder() {
        RepairOrder.createRepairOrder(description, phoneNumber, bikeSerialNumber, registry);
    }

    @Test
    public void testReturnRepairOrderDTOReturnsStoredRepairOrder() {
        createTestOrder();
        RepairOrderDTO createdOrder = registry.findRepairOrder(phoneNumber);
        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull("Order should be found in registry", foundOrder);
        assertEquals(createdOrder.getRepairOrderId(), foundOrder.getRepairOrderId());
        assertEquals(description, foundOrder.getProblemDescr());
        assertEquals(phoneNumber, foundOrder.getPhoneNumber());

        /* 
        RepairOrderRegistry registry = new RepairOrderRegistry();
        RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);
        RepairOrderDTO createdOrder = registry.findRepairOrder("123456789");

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(createdOrder.getRepairOrderId(), foundOrder.getRepairOrderId());
        assertEquals("broken brakes", foundOrder.getProblemDescr());
        assertEquals("123456789", foundOrder.getPhoneNumber());
        */
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        createTestOrder();
        RepairOrderDTO order = registry.findRepairOrder(phoneNumber);

        double taskCost = 250.0;
        RepairTask task = RepairTask. createRepairTask(taskDescription, taskCost);

        registry.addRepairTask(order.getRepairOrderId(), task);

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order could not be found after updating", updatedOrder);
        assertEquals(taskCost, updatedOrder.getTotalCost(), 0.0001);

        /* 
        RepairOrderRegistry registry = new RepairOrderRegistry();
        RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);
        RepairOrderDTO createdOrder = registry.findRepairOrder("123456789");

        RepairTask repairTask = RepairTask.createRepairTask("Replace brake cable", 250.0);
        registry.addRepairTask(createdOrder.getRepairOrderId(), repairTask);

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(250.0, foundOrder.getTotalCost(), 0.0);
        */
    }

    @Test
    public void testAddDiagnosticReport() {
        createTestOrder();
        RepairOrderDTO order = registry.findRepairOrder(phoneNumber);

        registry.addDiagnosticReport(order.getRepairOrderId(), diagResult);

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be found after updating", updatedOrder);
        assertEquals("Diagnostic report not saved correctly", diagResult, updatedOrder.getDiagnosticReport());
    }

    @Test
    public void testAcceptRepairOrder() {
        createTestOrder();
        RepairOrderDTO order = registry.findRepairOrder(phoneNumber);
        registry.acceptRepairOrder(order.getRepairOrderId());

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be possible to find after changed status", updatedOrder);
        assertEquals("Order status should be ACCEPTED", "ACCEPTED", updatedOrder.getState());
    }

    @Test
    public void testRejectRepairOrder() {
        createTestOrder();
        RepairOrderDTO order = registry.findRepairOrder(phoneNumber);
        registry.rejectRepairOrder(order.getRepairOrderId());

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be possible to find after changed status", updatedOrder);
        assertEquals("Order status should be REJECTED", "REJECTED", updatedOrder.getState());
    }

    @Test
    public void testFindAllRepairOrders() {
        createTestOrder();
        createTestOrder();

        RepairOrderDTO[] results = registry.findAllRepairOrders(phoneNumber);

        assertEquals("Should have found 2 orders under this phone number", 2, results.length);
    }

    @Test
    public void testFindNonExistingOrderReturnsNull() {
        RepairOrderDTO result = registry.findRepairOrder("7890");
        assertNull("Searches for nonexisting number should return null", result);
    }
}
