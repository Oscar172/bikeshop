package se.kth.iv1350.model;

import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.RepairOrderDTO;
import se.kth.iv1350.integration.RepairOrderRegistry;

public class RepairOrder {

    private RepairOrderRegistry repairOrderRegistry;
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
    
    private RepairOrder(String repairOrderId, String problemDescr, String phoneNumber, String bikeSerialNumber, RepairOrderRegistry repairOrderRegistry){
        this.repairOrderId = repairOrderId;
        this.problemDescr = problemDescr;
        this.phoneNumber = phoneNumber;
        this.bikeSerialNumber = bikeSerialNumber;
        this.repairOrderRegistry = repairOrderRegistry;

        this.totalCost = 0.0;
        this.diagnosticReport = "";
        this.estimatedCompletionDate = "";
        this.repairTasks = new RepairTask[10];
        this.nrOfRepairTasks = 0;
        this.state = "CREATED";
    }
    
    //<<create>> repairOrder
    public static RepairOrder createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber, RepairOrderRegistry repairOrderRegistry){
        String repairOrderId = repairOrderRegistry.generateRepairOrderId();
        RepairOrder repairOrder = new RepairOrder(repairOrderId, problemDescr, phoneNumber, bikeSerialNumber, repairOrderRegistry);
        repairOrderRegistry.updateRepairOrder(repairOrder.createRepairOrderDTO());
        return repairOrder;
    }

    //ev ha två olika create, en där nytt skapas och en där 
    // skapar med RepairTask och RepairOrderId som argument
    //övriga parametrar
     //<<create>> repairOrder med addrepairtask
    public static void addRepairTaskToRepairOrder(String repairOrderId, RepairTask repairTask, RepairOrderRegistry repairOrderRegistry){
        
        String repairOrderId = //hitta DTOn i registret med detta.

        //skriv över allt från dton
        //lägga till addRepairtask på en tom plats i vekorn, nästa lediga plats
        //spara DTOn på nytt.
        
        repairOrderRegistry.updateRepairOrder(repairOrder.createRepairOrderDTO());
    }

    //addDiagnosticReport
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        this.diagnosticReport = diagTaskResult;
    }

    //addRepairTask
    //need to call the repairOrderDTO from registry to get it here, calls with identifier repairOrderId.
    public void addRepairTask(String repairOrderId, RepairTask repairTask){
        
        RepairOrderDTO repairOrder = repairOrderRegistry.returnRepairOrderDTO(repairOrderId);

        //perhaps add a privatereadfunction to read DTOs
        //down below
        if(nrOfRepairTasks < repairTasks.length){
            repairTasks[nrOfRepairTasks] = repairTask;
            nrOfRepairTasks++;
            totalCost = calculateTotalCost(repairTasks);
        }

        repairOrderRegistry.updateRepairOrder(this.createRepairOrderDTO());
        //updates the DTO
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

    public RepairOrderDTO createRepairOrderDTO(){
        return new RepairOrderDTO(repairOrderId,
                                  problemDescr,
                                  phoneNumber,
                                  bikeSerialNumber,
                                  state,
                                  totalCost,
                                  diagnosticReport,
                                  estimatedCompletionDate);
    }

    //getters for printer
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
