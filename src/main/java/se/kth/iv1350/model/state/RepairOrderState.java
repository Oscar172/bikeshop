package se.kth.iv1350.model.state;

import se.kth.iv1350.model.RepairOrder;

public interface RepairOrderState {
    void accept(RepairOrder order);
    void reject(RepairOrder order);
    void onHold(RepairOrder order);
    String getStateName();
}
