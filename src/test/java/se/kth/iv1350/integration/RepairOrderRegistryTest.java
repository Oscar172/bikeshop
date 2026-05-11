package se.kth.iv1350.integration;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.view.RepairOrderObserver;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private Controller contr;

    private final String phoneNumber = "123456789";
    private final String description = "Broken breaks";
    private final String bikeSerialNumber = "BIKE123";

    private void createTestOrder() {
        contr.createRepairOrder(description, phoneNumber, bikeSerialNumber);
    }
    @Before
    public void setUp() {
        registry = new RepairOrderRegistry();
        contr = new Controller(CustomerRegistry.getCustomerRegistry(), registry, new Printer());
        createTestOrder();
    }

    @After
    public void tearDown() {
        contr = null;
        registry = null;
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

        RepairOrder order = registry.findRepairOrderById("RO-1");

        assertFalse("Observer should not have been notified before update.", observer.updated);

        registry.notifyObservers(order);

        assertTrue("Observer should have been notified.", observer.updated);

    }

    // Nya av Tova
    @Test
    public void testFindRepairOrderById() {
        String repairOrderId = "RO-1";
        RepairOrder found = registry.findRepairOrderById(repairOrderId);

        assertNotNull("Should find order", found);
        assertEquals("ID should match", repairOrderId, found.getRepairOrderId());
    }

    @Test
    public void testGenerateRepairOrderId() {
        int currentCount = registry.getNrOfRepairOrders();
        String expectedId = "RO-" + (currentCount + 1);
        String nextId = registry.generateRepairOrderId();
        
        assertEquals("Next ID should be based on the current count + 1", expectedId, nextId);
    }

    @Test
    public void testFindAllRepairOrders() {
        contr.createRepairOrder("Flat tire", phoneNumber, "BIKE2");

        RepairOrderDTO[] results = registry.findAllRepairOrders(phoneNumber);
        assertEquals("Should find 2 orders", 2, results.length);
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
    public void testFindNonExistingOrderReturnsNull() {
        RepairOrderDTO result = registry.findRepairOrder("7890");
        assertNull("Searches for nonexisting number should return null", result);
    }
}