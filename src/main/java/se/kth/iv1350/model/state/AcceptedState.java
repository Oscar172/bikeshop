package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a repair order state where the order has been accepted.
 */
public class AcceptedState implements RepairOrderState{
    /**
     * Handles an attempt to accept an already accepted repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void accept(RepairOrder order){
        //Already accepted, no state change is made.
    }

    /**
     * Handles an attempt to reject the repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void reject(RepairOrder order){
        order.setState(new RejectedState());
    }

    /**
     * Handles an attempt to mark the repair order as paid.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void paid(RepairOrder order){
        order.setState(new PaidState());
    }

    /**
     * Handles an attempt to mark the repair order as ready for approval.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void readyForApproval(RepairOrder order){
        order.setState(new ReadyForApproval());
    }

    /**
     * Returns the name of this state.
     *
     * @return The state name.
     */
    @Override
    public String getStateName(){
        return "ACCEPTED";
    }
}
