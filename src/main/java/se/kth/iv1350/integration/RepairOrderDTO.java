package se.kth.iv1350.integration;
import se.kth.iv1350.model.RepairTask;

/**
 * Contains information about a repair order transferred between layers.
 */
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
     * Creates a new RepairOrderDTO containing repair order information.
     * 
     * @param repairOrderId The id of the reapir order.
     * @param problemDescr The description of the reported problem.
     * @param phoneNumber The customer's phone number
     * @param bikeSerialNumber The bike's serial number
     * @param state The current state of the repair order.
     * @param totalCost The total cost of the repair order.
     * @param diagnosticReport The diagnostic report for the repair order.
     * @param estimatedCompletionDate The estimated completion date.
     * @param nrOfRepairTasks The number of repair tasks in the order.
     * @param repairTasks The repair tasks included in the order.
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
     * Returns the repair order id.
     *
     * @return The repair order id.
     */
    public String getRepairOrderId() {
        return repairOrderId;
    }

    /**
     * Returns the problem description.
     *
     * @return The problem description.
     */
    public String getProblemDescr() {
        return problemDescr;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the bike's serial number.
     *
     * @return The bike's serial number.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * Returns the current state of the repair order.
     *
     * @return The current state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the total cost of the repair order.
     *
     * @return The total cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the diagnostic report.
     *
     * @return The diagnostic report.
     */
    public String getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * Returns the estimated completion date.
     *
     * @return The estimated completion date.
     */
    public String getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    /**
     * Returns the number of repair tasks.
     *
     * @return The number of repair tasks.
     */
    public int getNrOfRepairTasks() {
        return nrOfRepairTasks;
    }

    /**
     * Returns all repair tasks in the repair order.
     *
     * @return An array of repair tasks.
     */
    public RepairTask[] getRepairTasks() {
        return repairTasks;
    }
}
