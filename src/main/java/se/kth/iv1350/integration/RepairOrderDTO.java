package se.kth.iv1350.integration;
import se.kth.iv1350.model.RepairTask;

public class RepairOrderDTO {

    private String repairOrderId;
    private String problemDescr;
    private String phoneNumber;
    private String bikeSerialNumber;
    private String state;
    private double totalCost;
    private String diagnosticReport;
    private String estimatedCompletionDate;
    private final int nrOfRepairTasks;
    private final RepairTask[] repairTasks;

    /**
     * Constructs RepairOrderDTO class
     * @return reference to the class
     */
    public static RepairOrderDTO createRepairOrderDTO(){
        return new RepairOrderDTO();
    }

    
    public RepairOrderDTO() {
        this.nrOfRepairTasks = 0;
        this.repairTasks = new RepairTask[0];
        this.repairOrderId = "";
    }

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

    //getters
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
