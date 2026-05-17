package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a repair order state where the order has been rejected.
 * This is a final state and does not allow further state changes.
 */
public class RejectedState implements RepairOrderState{

    /**
     * Handles an attempt to accept a rejected repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void accept(RepairOrder order){
        // Rejected state is final, no state change is made.
    }

    /**
     * Handles an attempt to reject an already rejected repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void reject(RepairOrder order){
        // Rejected state is final, no state change is made.
    }

    /**
     * Handles an attempt to mark a rejected repair order as paid.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void paid(RepairOrder order){
        // Rejected state is final, no state change is made.
    }

    /**
     * Handles an attempt to mark a rejected repair order as ready for approval.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void readyForApproval(RepairOrder order){
        // Rejected state is final, no state change is made.
    }

    /**
     * Returns the name of this state.
     *
     * @return The state name.
     */
    @Override
    public String getStateName(){
        return "REJECTED";
    }
}
