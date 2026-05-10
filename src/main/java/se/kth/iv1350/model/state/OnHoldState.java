package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public class OnHoldState implements RepairOrderState {

    @Override
    public void accept(RepairOrder order){
        order.setState(new AcceptedState());
    }

    @Override
    public void reject(RepairOrder order){
        order.setState(new RejectedState());
    }

    @Override
    public void onHold(RepairOrder order){
        //Already ON-HOLD state, no change is made.
    }

    @Override
    public String getStateName(){
        return "ON-HOLD";
    }
}
