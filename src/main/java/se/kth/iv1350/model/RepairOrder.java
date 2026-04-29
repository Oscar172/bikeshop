package se.kth.iv1350.model;

import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

/**
 * Represents a specific repair order and contains its business logic.
 */
public class RepairOrder {
    private final String repairOrderId;
    private final String problemDescr;
    private final String bikeSerialNumber;
    private final String estimatedCompletionDate;
    private final String phoneNumber;
    private final RepairTask[] repairTasks;
    private String state;
    private double totalCost;
    private String diagnosticReport;
    private int nrOfRepairTasks;
    
    /**
     * Creates a new repair order.
     * 
     * @param repairOrderId The id of the repair order.
     * @param problemDescr The description of the reported problem.
     * @param phoneNumber The customer's phone number.
     * @param bikeSerialNumber The bike's serial number.
     */
    private RepairOrder(String repairOrderId, String problemDescr, String phoneNumber, String bikeSerialNumber, RepairOrderRegistry repairOrderRegistry){
        this.repairOrderId = repairOrderId;
        this.problemDescr = problemDescr;
        this.phoneNumber = phoneNumber;
        this.bikeSerialNumber = bikeSerialNumber;
        this.totalCost = 0.0;
        this.diagnosticReport = "";
        this.estimatedCompletionDate = "One week after accepted offer"; 
        this.repairTasks = new RepairTask[10];
        this.nrOfRepairTasks = 0;
        this.state = "CREATED";
    }
    
    /**
     * Creates a repair order and stores it in the registry.
     * 
     * @param problemDescr The description of the reported problem.
     * @param phoneNumber The customer's phone number.
     * @param bikeSerialNumber The bike's serial number.
     * @param repairOrderRegistry The registry where the repair order is stored.
     */
    public static RepairOrder createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber, RepairOrderRegistry repairOrderRegistry){
        String repairOrderId = repairOrderRegistry.generateRepairOrderId();
        RepairOrder repairOrder = new RepairOrder(repairOrderId, problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
        repairOrderRegistry.updateRepairOrder(repairOrder);

        return repairOrder;
    }

    /**
     * Adds a diagnostic report to the repair order.
     * 
     * @param diagTaskResult The diagnostic result to add.
     */
    public void addDiagnosticReport(String diagTaskResult){
        this.diagnosticReport = diagTaskResult;
    }

    /**
     * Adds a repair task to the repair order.
     * 
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(RepairTask repairTask){
        if(nrOfRepairTasks < repairTasks.length){
            repairTasks[nrOfRepairTasks] = repairTask;
            nrOfRepairTasks++;
            totalCost = calculateTotalCost(repairTasks);
        }
    }
    
    /**
     * Calculates the total cost of all repait tasks.
     * 
     * @param repairTasks The total cost of the repair tasks.
     * @return The total cost of the reapir tasks.
     */
    public double calculateTotalCost(RepairTask[] repairTasks){
        double sum = 0;

        for(int i = 0; i < repairTasks.length; i++){
            if(repairTasks[i] != null){
                sum += repairTasks[i].getCost();
            }
        }
        return sum;
    }

    /**
     * Updates the status of the repair order.
     * 
     * @param repairStatus The new status of the reapir order.
     */
    public void updateRepairOrderStatus(String repairStatus){
        this.state = repairStatus;
    }

    /**
     * Creates a DTO containing the reapir order information.
     * 
     * @return A RepairOrderDTO representing this repair order.
     */
    public RepairOrderDTO createRepairOrderDTO(){
        return new RepairOrderDTO (repairOrderId,  
                                   problemDescr, 
                                   phoneNumber, 
                                   bikeSerialNumber, 
                                   state,
                                   totalCost, 
                                   diagnosticReport, 
                                   estimatedCompletionDate,
                                   nrOfRepairTasks,
                                   repairTasks);                             
    }

    /**
     * Returns the repair order id.
     *
     * @return The repair order id.
     */
    public String getRepairOrderId(){
        return repairOrderId;
    }

    /**
     * Returns the problem description.
     *
     * @return The problem description.
     */
    public String getProblemDescr(){
        return problemDescr;
    }

    /**
     * Returns the bike's serial number.
     *
     * @return The bike's serial number.
     */
    public String getBikeSerialNumber(){
        return bikeSerialNumber;
    }

    /**
     * Returns the current state of the repair order.
     *
     * @return The current state of the repair order.
     */
    public String getState(){
        return state;
    }

    /**
     * Returns the total cost of the repair order.
     *
     * @return The total cost of the repair order.
     */
    public double getTotalCost(){
        return totalCost;
    }

    /**
     * Returns the diagnostic report.
     *
     * @return The diagnostic report.
     */
    public String getDiagnosticReport(){
        return diagnosticReport;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

     /**
     * Returns the Repair Orders repair tasks.
     *
     * @return The repair tasks.
     */
    public RepairTask[] getRepairTasks(){
        return repairTasks;
    }
    
    public String getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }
}
