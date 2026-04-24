package se.kth.iv1350.model;

import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.integration.Printer;

public class RepairOrder {

    private String repairOrderId;
    private String date;
    private String problemDescr;
    private String state;
    private double totalCost;
    private String bikeSerialNumber;
    private String diagnosticReport;
    private String estimatedCompletionDate;
    private String phoneNumber;
    private String diagTaskResult;

    private RepairTask[] repairTasks;
    private int nrOfRepairTasks;
    
    private RepairOrder(String repairOrderId, String problemDescr, String phoneNumber, String bikeSerialNumber){
        this.repairOrderId = repairOrderId;
        this.problemDescr = problemDescr;
        this.phoneNumber = phoneNumber;
        this.bikeSerialNumber = bikeSerialNumber;
        this.totalCost = 0.0;
        this.diagnosticReport = "";
        this.repairTasks = new RepairTask[10];
        this.nrOfRepairTasks = 0;
        this.state = "CREATED";
    }
    
    //<<create>> repairOrder
    public static RepairOrder createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber, RepairOrderRegistry repairOrderRegistry){
        String repairOrderId = "RO1";
        return new RepairOrder(repairOrderId, problemDescr, phoneNumber, bikeSerialNumber);
    }

    //addDiagnosticReport
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult, RepairOrderRegistry repairOrderRegistry){
        this.diagnosticReport = diagTaskResult;
    }

    //addRepairTask
    public void addRepairTask(RepairTask repairTask, RepairOrderRegistry repairOrderRegistry){
        if(nrOfRepairTasks < repairTasks.length){
            repairTasks[nrOfRepairTasks] = repairTask;
            nrOfRepairTasks++;
            totalCost = calculateTotalCost(repairTasks);
        }
    }
    
    //calculateTotalCost
    public double calculateTotalCost(RepairTask[] repairTasks){
        double sum = 0;

        for(int i = 0; i < repairTasks.length; i++){
            if(repairTasks[i] != null){
                sum += repairTasks[i].getCost();
            }
        }
        return sum;
    }

    //updateRepairOrder
    public void updateRepairOrderStatus(String repairOrderId, String repairStatus, Printer printer){
        this.state = repairStatus;
        printer.printRepairOrder(this);
    }

    //getters för printer
    public String getRepairOrderId(){
        return repairOrderId;
    }
    public String getProblemDescr(){
        return problemDescr;
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
    public String getPhoneNumber(){
        return phoneNumber;
    }

}
