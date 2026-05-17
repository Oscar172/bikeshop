package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a repair order state where the order has been created.
 */
public class CreatedState implements RepairOrderState {
    /**
     * Handles an attempt to accept the repair order.
     *
     * @param order The repair order whose state may be changed.
     */
    @Override
    public void accept(RepairOrder order){
        order.setState(new AcceptedState());
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
        //Created state cannot transition directly to paid.
        //No state change is made
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
        return "CREATED";
    }
}
