package se.kth.iv1350.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class RepairTaskTest {
    private RepairTask taskToTest;
    private String description;
    private double cost;

    @Before
    public void setUp() {
        description = "Oil the chain";
        cost = 150.0;
        taskToTest = RepairTask.createRepairTask(description, cost);
    }
    
    @After
    public void tearDown() {
        taskToTest = null;
        description = null;
        cost = 0;
    }

    @Test
    public void testCreateRepairTaskNotNull() {
        assertNotNull("RepairTask should not be null.", taskToTest);
    }

    @Test
    public void testGetCost() {
        double actualCost = taskToTest.getCost();
        assertEquals("getCost did not return the same cost set in setUp", cost, actualCost, 0.0001);
    }
}
