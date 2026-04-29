package se.kth.iv1350.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import se.kth.iv1350.model.RepairTask;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

public class RepairOrderTest {
    private String problemDescr = "broken brakes"; 
    private String phoneNumber = "123456789"; 
    private String bikeSerialNumber = "BIKE123"; 
    private RepairOrderRegistry repairOrderRegistry;
    private RepairOrder testRepairOrder;
    private String repairOrderId;
    private String diagTaskResult = "broken brakes indeed";

    @Test


    @Before
        public void setUp() {
            repairOrderRegistry= new RepairOrderRegistry();
            testRepairOrder = RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
            testRepairOrder.addDiagnosticReport(diagTaskResult);
    }
    
    @After
        public void tearDown() {
            repairOrderRegistry = null;
            testRepairOrder = null;
            repairOrderId = null;
            diagTaskResult = null;
}
    @Test
    public void  testCreateRepairOrderWithCorrectValues(){
        
        String repairOrderId = repairOrderRegistry.generateRepairOrderId();

        assertNotNull(testRepairOrder);
        assertNotNull(testRepairOrder.getRepairOrderId());
        assertEquals("broken brakes", testRepairOrder.getProblemDescr());
        assertEquals("123456789", testRepairOrder.getPhoneNumber());
        assertEquals("BIKE123", testRepairOrder.getBikeSerialNumber());

    }

    @Test
    public void testAddDiagnosticReportWithCorrectValues(){
        assertNotNull(testRepairOrder.getDiagnosticReport());
        assertEquals("broken brakes indeed", testRepairOrder.getDiagnosticReport());

    }

    @Test public void testAddRepairTasktWithCorrectValues(){




    }
   public void addRepairTask(RepairTask repairTask){
        if(nrOfRepairTasks < repairTasks.length){
            repairTasks[nrOfRepairTasks] = repairTask;
            nrOfRepairTasks++;
            totalCost = calculateTotalCost(repairTasks);
        }
    }
}




