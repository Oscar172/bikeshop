package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public class AcceptedState implements RepairOrderState{

    @Override
    public void accept(RepairOrder order){
        //Already accepted, no state change is made
    }

    @Override
    public void reject(RepairOrder order){
        order.setState(new RejectedState());
    }

    @Override
    public void payed(RepairOrder order){
        order.setState(new PayedState());
    }

    @Override
    public void readyForApproval(RepairOrder order){
        order.setState(new ReadyForApproval());
    }

    @Override
    public String getStateName(){
        return "ACCEPTED";
    }
}
