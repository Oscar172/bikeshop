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
    public String repairOrderId(){
        return repairOrderId;
    }
    public String problemDescr(){
        return problemDescr;
    }
    public String phoneNumber(){
        return phoneNumber;
    }
    public String bikeSerialNumber(){
        return bikeSerialNumber;
    }
    public String state(){
        return state;
    }
    public double totalCost(){
        return totalCost;
    }
    public String diagnosticReport(){
        return diagnosticReport;
    }
    public String estimatedCompletionDate(){
        return estimatedCompletionDate;
    }


}
