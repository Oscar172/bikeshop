package se.kth.iv1350.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import se.kth.iv1350.model.RepairTask;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

public class RepairOrderTest {
    private String problemDescr; 
    private String phoneNumber; 
    private String bikeSerialNumber; 
    private RepairOrderRegistry repairOrderRegistry;
    private RepairOrder testRepairOrder;
    private String diagTaskResult;
    private String repairOrderId;
    private RepairTask repairTask;
    private String repairTaskDescription;
    private double cost;


    private void createTestRepairTask(){
    repairTaskDescription = "replace brake wire";
            cost = 125;
            repairTask = RepairTask.createRepairTask(repairTaskDescription, cost);
    
    }

    @Before
        public void setUp() {
            problemDescr = "broken brakes"; 
            phoneNumber = "123456789"; 
            bikeSerialNumber = "BIKE123"; 
            diagTaskResult = "broken brakes indeed";
            repairOrderRegistry= new RepairOrderRegistry();
            testRepairOrder = RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
            repairOrderId = repairOrderRegistry.generateRepairOrderId();
            createTestRepairTask();
    }
    
    @After
        public void tearDown() {
            problemDescr = "broken brakes"; 
            phoneNumber = "123456789"; 
            bikeSerialNumber = "BIKE123"; 
            diagTaskResult = null;
            repairOrderRegistry = null;
            testRepairOrder = null;
            repairOrderId = null;
    }
    @Test
    public void  testCreateRepairOrderWithCorrectValues(){

        assertNotNull("There is no RepairOrder",testRepairOrder); //kör fler messages
        assertNotNull("There is no RepairOrderId",testRepairOrder.getRepairOrderId());
        assertEquals("Not the same RepairOrderId",repairOrderId, repairOrderRegistry.generateRepairOrderId());
        assertEquals("Wrong problemDescr","broken brakes", testRepairOrder.getProblemDescr());
        assertEquals("Wrong phoneNumber","123456789", testRepairOrder.getPhoneNumber());
        assertEquals("Wrong BikeSerialNumber","BIKE123", testRepairOrder.getBikeSerialNumber());

    }
    

    @Test
    public void testAddDiagnosticReportWithCorrectValues(){
        testRepairOrder.addDiagnosticReport(diagTaskResult);
        assertNotNull("There is no Diagnostic Report", testRepairOrder.getDiagnosticReport());
        assertEquals("Diagnotic Report not the same", "broken brakes indeed", testRepairOrder.getDiagnosticReport());

    }


    @Test public void testAddRepairTasktWithCorrectValues(){
        testRepairOrder.addRepairTask(repairTask);
        assertNotNull("There is no repairTask added", .getRepairTask());        //behöver göra getRepairTask
        assertEquals("Diagnotic Report not the same", "broken brakes indeed", testRepairOrder.getDiagnosticReport());

    

    }
   public void addRepairTask(RepairTask repairTask){
        if(nrOfRepairTasks < repairTasks.length){
            repairTasks[nrOfRepairTasks] = repairTask;
            nrOfRepairTasks++;
            totalCost = calculateTotalCost(repairTasks);
        }
    }

    @Test 
    public void testCalculateTotalCostBeforeAddRepairTask(){
        
        assertNull(testRepairOrder.calculateTotalCost());
    
    }

     @Test 
    public void testCalculateTotalCostAfterAddRepairTask(){
        
        assertNotNull(testRepairOrder.calculateTotalCost());
    
    }


     public double calculateTotalCost(RepairTask[] repairTasks){
        double sum = 0;

        for(int i = 0; i < repairTasks.length; i++){
            if(repairTasks[i] != null){
                sum += repairTasks[i].getCost();
            }
        }
        return sum;
    }
}




