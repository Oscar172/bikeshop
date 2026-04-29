package se.kth.iv1350.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

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
    private RepairTask [] repairTasks;
    private String repairTaskDescription;
    private double cost;
    private RepairOrderDTO testDto;

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
            repairTasks = new RepairTask[10];
    }
    
    @After
        public void tearDown() {
            problemDescr = null; 
            phoneNumber = null; 
            bikeSerialNumber = null; 
            diagTaskResult = null;
            repairOrderRegistry = null;
            testRepairOrder = null;
            repairOrderId = null;
            repairTask = null;
            repairTasks = null;
            testDto = null;
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

        assertNotNull("There is no repairTask added", testRepairOrder.getRepairTasks());
        repairTasks [0] = repairTask;
        assertArrayEquals("RepairTask is not the same", repairTasks, testRepairOrder.getRepairTasks());
    }

    @Test 
    public void testCalculateTotalCost(){
        assertEquals("Total cost is not zero when repairTasks is empty",0.0, testRepairOrder.calculateTotalCost(repairTasks), 0.01);

        testRepairOrder.addRepairTask(repairTask);
        repairTasks [0] = repairTask;

        assertNotEquals("Totalcost is zero after adding repairTask", 0.0, testRepairOrder.calculateTotalCost(repairTasks));
        repairTasks [1] = repairTask;
        assertEquals("Totalcost is not 250",250,testRepairOrder.calculateTotalCost(repairTasks),0.01);
    }

    @Test
    public void testUpdateRepairOrderStatus(){
        assertEquals("Its not CREATED to begin with","CREATED", testRepairOrder.getState());

        testRepairOrder.updateRepairOrderStatus("REJECTED");

        assertEquals("Its not REJECTED","REJECTED", testRepairOrder.getState());
    }

    @Test
    public void testCreateRepairOrderDTO(){
        testRepairOrder.addDiagnosticReport(diagTaskResult);
        testRepairOrder.addRepairTask(repairTask);

        testDto = testRepairOrder.createRepairOrderDTO();

        assertEquals("RepairOrderId is not the same",testRepairOrder.getRepairOrderId(), testDto.getRepairOrderId());
        assertEquals("problemDescr is not the same",testRepairOrder.getProblemDescr(), testDto.getProblemDescr());
        assertEquals("phoneNumber is not the same",testRepairOrder.getBikeSerialNumber(), testDto.getBikeSerialNumber());
        assertEquals("bikeSerialNumber is not the same ",testRepairOrder.getProblemDescr(), testDto.getProblemDescr());
        assertEquals("state is not the same",testRepairOrder.getState(), testDto.getState());
        assertEquals("totalCost is not the same",testRepairOrder.getTotalCost(), testDto.getTotalCost(), 0.01);
        assertEquals("diagnosticReport is not the same",testRepairOrder.getDiagnosticReport(), testDto.getDiagnosticReport());
        assertEquals("estimatedCompletionDate is not the same",testRepairOrder.getEstimatedCompletionDate(), testDto.getEstimatedCompletionDate());
        assertEquals("nrOfRepairTasks is not the same",testRepairOrder.getNrOfRepairTasks(), testDto.getNrOfRepairTasks());
        assertArrayEquals("repairTasks is not the same",testRepairOrder.getRepairTasks(), testDto.getRepairTasks());
    }

}




