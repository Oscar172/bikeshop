package se.kth.iv1350.model;

public class RepairTask {

    private double cost;
    private String state;
    private String description;

    //private constructor
    private RepairTask(String description, double cost){
        this.description = description;
        this.cost = cost;
        this.state = "CREATED";
    }

    //<<create>> createRepairTask
    public static RepairTask createRepairTask(String repairOrderId, String repairTask, double cost){
        return new RepairTask(repairTask, cost);
    }

    public double getCost(){
        return cost;
    }

}

