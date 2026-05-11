package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;


//ska vi låta rejected state kåta ordern gp vidare til andra tillstånd
public class RejectedState implements RepairOrderState{


    @Override
    public void accept(RepairOrder order){
        //Rejected state is final state, no state change is made.
    }

    @Override
    public void reject(RepairOrder order){
        //Rejected rejected, no state change is made.
    }

    @Override
    public void payed(RepairOrder order){
        ////Rejected rejected, no state change is made.
    }

    @Override
    public void readyForApproval(RepairOrder order){
        //Rejected state is final state, np state change is made.
    }

    @Override
    public String getStateName(){
        return "REJECTED";
    }
}
