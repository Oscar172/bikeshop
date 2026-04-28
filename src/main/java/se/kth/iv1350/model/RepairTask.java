package se.kth.iv1350.model;

public class RepairTask {

    private final double cost;
    private final String state;
    private final String description;

    /**
     * Defines the objects properties.
     * @param description The description of the proposed repair task.
     * @param cost The cost of the proposed repair task.
     */
    private RepairTask(String description, double cost){
        this.description = description;
        this.cost = cost;
        this.state = "CREATED";
    }

    /**
     * Creates a repair task object and returns it.
     * @param repairTaskDescription The description of the proposed repair task.
     * @param cost The cost of the proposed repair task.
     * @return The new object.
     */
    public static RepairTask createRepairTask(String repairTaskDescription, double cost){
        return new RepairTask(repairTaskDescription, cost);
    }

    public double getCost(){
        return cost;
    }

}

