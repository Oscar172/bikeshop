package se.kth.iv1350.model;

public class RepairTask {
    private final double cost;
    private final String state;
    private final String description;

    /**
     * Creates a new RepairTask
     * 
     * @param description The description of the proposed repair task.
     * @param cost The cost of the proposed repair task.
     */
    private RepairTask(String description, double cost){
        this.description = description;
        this.cost = cost;
        this.state = "CREATED";
    }

    /**
     * Creates a repair task and returns it.
     * 
     * @param repairTaskDescription The description of the proposed repair task.
     * @param cost The cost of the proposed repair task.
     * @return The created repair task.
     */
    public static RepairTask createRepairTask(String repairTaskDescription, double cost){
        return new RepairTask(repairTaskDescription, cost);
    }

    /**
     * Returns the cost of the repair task.
     * 
     * @return The cost of the repair task.
     */
    public double getCost(){
        return cost;
    }
}

