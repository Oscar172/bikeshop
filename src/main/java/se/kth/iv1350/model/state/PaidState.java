package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a repair order state where the order has been paid.
 */
public class PaidState implements RepairOrderState {
    /**
     * Handles an attempt to mark an already paid repair order as paid.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void paid(RepairOrder order){
        //Paid is final state, no state change is made.
    }

    /**
     * Handles an attempt to accept a paid repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void accept(RepairOrder order){
        //Paid is final state, no state change is made.
    }

    /**
     * Handles an attempt to reject a paid repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void reject(RepairOrder order){
        //Paid is final state, no state change is made.
    }

    /**
     * Handles an attempt to mark a paid repair order as ready for approval.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void readyForApproval(RepairOrder order){
        //Paid is final state, no state change is made.
    }

    /**
     * Returns the name of this state.
     *
     * @return The state name.
     */
    @Override
    public String getStateName(){
        return "PAID";
    }
}

