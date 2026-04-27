package se.kth.iv1350.integration;

public class RepairOrderDTO {

    private String repairOrderId;
    private String problemDescr;
    private String phoneNumber;
    private String bikeSerialNumber;
    private String state;
    private double totalCost;
    private String diagnosticReport;
    private String estimatedCompletionDate;

    //Skapar RepairOrderDTO
    public RepairOrderDTO(String repairOrderId, String problemDescr, String phoneNumber, String bikeSerialNumber, String state, double totalCost, String diagnosticReport, String estimatedCompletionDate){
        this.repairOrderId = repairOrderId;
        this.problemDescr = problemDescr;
        this. phoneNumber = phoneNumber;
        this.bikeSerialNumber = bikeSerialNumber;
        this.state = state;
        this.totalCost = totalCost;
        this.diagnosticReport = diagnosticReport;
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    //getters

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
}