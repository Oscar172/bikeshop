package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public class ReadyForApproval implements RepairOrderState{

    @Override
    public void accept(RepairOrder order){
        order.setState(new AcceptedState());
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
        return "READYFORAPPROVAL";
    }
}
