package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public class CreatedState implements RepairOrderState {


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
        order.setState(new OnHoldState());
    }

    @Override
    public String getStateName(){
        return "REJECTED";
    }
}

}
