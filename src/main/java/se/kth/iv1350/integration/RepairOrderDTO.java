package se.kth.iv1350.integration;
import se.kth.iv1350.model.RepairTask;

public class RepairOrderDTO {

    private final String repairOrderId;
    private final String problemDescr;
    private final String phoneNumber;
    private final String bikeSerialNumber;
    private final String state;
    private final double totalCost;
    private final String diagnosticReport;
    private final String estimatedCompletionDate;
    private final int nrOfRepairTasks;
    private final RepairTask[] repairTasks;


    /**
     * Declares the RepairOrderDTO object.
     * @param repairOrderId
     * @param problemDescr
     * @param phoneNumber
     * @param bikeSerialNumber
     * @param state
     * @param totalCost
     * @param diagnosticReport
     * @param estimatedCompletionDate
     */
    public RepairOrderDTO(String repairOrderId, String problemDescr, String phoneNumber, String bikeSerialNumber, String state, double totalCost, String diagnosticReport, String estimatedCompletionDate, int nrOfRepairTasks, RepairTask[] repairTasks){
        this.repairOrderId = repairOrderId;
        this.problemDescr = problemDescr;
        this. phoneNumber = phoneNumber;
        this.bikeSerialNumber = bikeSerialNumber;
        this.state = state;
        this.totalCost = totalCost;
        this.diagnosticReport = diagnosticReport;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.nrOfRepairTasks = nrOfRepairTasks;
        this.repairTasks = repairTasks;
        
    }


    /**
     * A getter that returns the repairorder id.
     * @return The repairorder id.
     */
    public String getRepairOrderId(){
        return repairOrderId;
    }

    public String getProblemDescr(){
        return problemDescr;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getBikeSerialNumber(){
        return bikeSerialNumber;
    }
    public String getState(){
        return state;
    }
    public double getTotalCost(){
        return totalCost;
    }
    public String getDiagnosticReport(){
        return diagnosticReport;
    }
    public String getEstimatedCompletionDate(){
        return estimatedCompletionDate;
    }

    public int getNrOfRepairTasks(){
        return nrOfRepairTasks;
    }

     public RepairTask [] getRepairTasks(){
        return repairTasks;
    }

}
