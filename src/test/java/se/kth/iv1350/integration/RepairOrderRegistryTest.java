package se.kth.iv1350.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import se.kth.iv1350.model.RepairOrder;

public class RepairOrderRegistryTest {

    @Test
    public void testReturnRepairOrderDTOReturnsStoredRepairOrder() {
        RepairOrderRegistry registry = RepairOrderRegistry.repairOrderRegistry();
        RepairOrder repairOrder = RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(repairOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(repairOrder.getRepairOrderId(), foundOrder.repairOrderId());
        assertEquals("broken brakes", foundOrder.problemDescr());
        assertEquals("123456789", foundOrder.phoneNumber());
    }

    @Test
    public void testAddRepairTaskUpdatesExistingRepairOrder() {
        RepairOrderRegistry registry = RepairOrderRegistry.repairOrderRegistry();
        RepairOrder repairOrder = RepairOrder.createRepairOrder("broken brakes", "123456789", "BIKE123", registry);

        registry.addRepairTask(repairOrder.getRepairOrderId(), "Replace brake cable", 250.0);

        RepairOrderDTO foundOrder = registry.returnRepairOrderDTO(repairOrder.getRepairOrderId());

        assertNotNull(foundOrder);
        assertEquals(250.0, foundOrder.totalCost(), 0.0);
    }
}
