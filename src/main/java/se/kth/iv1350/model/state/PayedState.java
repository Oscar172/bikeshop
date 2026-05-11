package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public class PayedState implements RepairOrderState {


    @Override
    public void payed(RepairOrder order){
        //Payed is final state, no change is made.
    }

    @Override
    public void accept(RepairOrder order){
        //Payed is final state, no change is made.
    }

    @Override
    public void reject(RepairOrder order){
        //Payed is final state, no change is made.
    }

    @Override
    public void readyForApproval(RepairOrder order){
        order.setState(new ReadyForApproval());
    }

    @Override
    public String getStateName(){
        return "PAYED";
    }
}

