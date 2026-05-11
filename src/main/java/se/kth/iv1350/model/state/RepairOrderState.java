package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public interface RepairOrderState {
    void accept(RepairOrder order);
    void reject(RepairOrder order);
    void payed(RepairOrder order);
    void readyForApproval(RepairOrder order);
    String getStateName();
}
