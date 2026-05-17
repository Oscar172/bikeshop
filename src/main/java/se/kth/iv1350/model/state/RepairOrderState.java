package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a state for a repair order and defines the allowed state transitions.
 */
public interface RepairOrderState {
    /**
     * Handles an attempt to accept the repair order.
     * 
     * @param order The repair order whose state may be changed.
     */
    void accept(RepairOrder order);

    /**
     * Handles an attempt to reject the repair order.
     * 
     * @param order The repair order whose state may be changed.
     */
    void reject(RepairOrder order);

    /**
     * Handles an attempt to mark the repair order as paid
     * 
     * @param order The repair order whose state may be changed.
     */
    void paid(RepairOrder order);

    /**
     * Handles an attempt to mark the repair order as ready for approval
     * 
     * @param order The repair order whose state may be changed.
     */
    void readyForApproval(RepairOrder order);

    /**
     * Returns the name of this state
     * 
     * @return The state name.
     */
    String getStateName();
}
