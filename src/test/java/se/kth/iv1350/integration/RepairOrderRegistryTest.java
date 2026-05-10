package se.kth.iv1350.integration;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;
import se.kth.iv1350.view.RepairOrderObserver;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairOrderDTO order;

    private final String phoneNumber = "123456789";
    private final String description = "Broken breaks";
    private final String bikeSerialNumber = "BIKE123";
    private final String taskDescription = "Oil the chain";
    private final String diagResult = "Bad breaks";

    private void createTestOrder() {
        RepairOrder.createRepairOrder(description, phoneNumber, bikeSerialNumber, registry);
    }
    @Before
    public void setUp() {
        registry = new RepairOrderRegistry();
        createTestOrder();
        order = registry.findRepairOrder(phoneNumber);
    }

    @After
    public void tearDown() {
        registry = null;
        order = null;
    }

        private class TestObserver implements RepairOrderObserver{
        private boolean updated = false;

        @Override
        public void repairOrderUpdated(RepairOrderDTO repairOrderDTO){
            updated = true;
        }
    }

    //Test observer used to verufy that observers are notified whena  arepair order is updated.
    @Test
    public void testObserverIsNotifiedWhenRepairOrderIsUpdated(){
        TestObserver observer = new TestObserver();
        registry.addRepairOrderObserver(observer);

        assertFalse("Observer should not have been notified before update.", observer.updated);

        registry.addDiagnosticReport(order.getRepairOrderId(), "Flat tire");

        assertTrue("Observer should have been notified when repair order was updated.", observer.updated);

    }

    @Test
    public void testReturnRepairOrderDTOReturnsStoredRepairOrder() {
        RepairOrderDTO createdOrder = registry.findRepairOrder(phoneNumber);
        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull("Order should be found in registry", foundOrder);
        assertEquals("The repair order id does not match.", createdOrder.getRepairOrderId(), foundOrder.getRepairOrderId());
        assertEquals("The problem description does not match.", description, foundOrder.getProblemDescr());
        assertEquals("The phone number does not match.", phoneNumber, foundOrder.getPhoneNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        double taskCost = 250.0;
        RepairTask task = RepairTask. createRepairTask(taskDescription, taskCost);

        registry.addRepairTask(order.getRepairOrderId(), task);

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be found after updating.", updatedOrder);
        assertEquals("The cost does not match.", taskCost, updatedOrder.getTotalCost(), 0.0001);
    }

    @Test
    public void testAddDiagnosticReport() {
        registry.addDiagnosticReport(order.getRepairOrderId(), diagResult);

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be found after updating", updatedOrder);
        assertEquals("Diagnostic report not saved correctly", diagResult, updatedOrder.getDiagnosticReport());
    }

    @Test
    public void testAcceptRepairOrder() {
        registry.acceptRepairOrder(order.getRepairOrderId());

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be possible to find after changed status", updatedOrder);
        assertEquals("Order status should be ACCEPTED", "ACCEPTED", updatedOrder.getState());
    }

    @Test
    public void testRejectRepairOrder() {
        registry.rejectRepairOrder(order.getRepairOrderId());

        RepairOrderDTO updatedOrder = registry.returnRepairOrderDTO(order.getRepairOrderId());
        assertNotNull("Order should be possible to find after changed status", updatedOrder);
        assertEquals("Order status should be REJECTED", "REJECTED", updatedOrder.getState());
    }

    @Test
    public void testFindAllRepairOrders() {
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
