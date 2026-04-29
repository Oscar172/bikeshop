package se.kth.iv1350.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

public class RepairOrderRegistryTest {

    @Test
    public void testReturnRepairOrderDTOReturnsStoredRepairOrder() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);
        RepairOrderDTO createdOrder = registry.findRepairOrder("123456789");

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(createdOrder.getRepairOrderId(), foundOrder.getRepairOrderId());
        assertEquals("broken brakes", foundOrder.getProblemDescr());
        assertEquals("123456789", foundOrder.getPhoneNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);
        RepairOrderDTO createdOrder = registry.findRepairOrder("123456789");

        RepairTask repairTask = RepairTask.createRepairTask("Replace brake cable", 250.0);
        registry.addRepairTask(createdOrder.getRepairOrderId(), repairTask);

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(createdOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(250.0, foundOrder.getTotalCost(), 0.0);
    }
}
