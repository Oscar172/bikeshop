package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairTask;

public class RepairOrderRegistry {

    private RepairOrder[] repairOrders;
    private int nrOfRepairOrders;
    
    public RepairOrderRegistry(){
        this.repairOrders = new RepairOrder[100];
        this.nrOfRepairOrders = 0;

    }

    public String generateRepairOrderId(){
        return "Repair Order: " + (nrOfRepairOrders + 1);
    }

    //findAllRepairOrders
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        RepairOrderDTO[] foundRepairOrders = new RepairOrderDTO[nrOfRepairOrders];
        int nrOfFoundRepairOrders = 0;

        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].getPhoneNumber().equals(phoneNumber)){
                foundRepairOrders[nrOfFoundRepairOrders] = repairOrders[i].createRepairOrderDTO();
                nrOfFoundRepairOrders++;
            }
        }

        RepairOrderDTO[] repairOrders = new RepairOrderDTO[nrOfFoundRepairOrders];
        for(int i = 0; i < nrOfFoundRepairOrders; i++){
            repairOrders[i] = foundRepairOrders[i];
        }

        return repairOrders;
    }

    //addDiagnosticReport
    public void addDiagnosticReport(String repairOrderId, String diagTaskResult){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.addDiagnosticReport(diagTaskResult);
        }
    }

    public void addRepairTask(String repairOrderId, RepairTask repairTask){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.addRepairTask(repairTask);
        }
    }

    public RepairOrderDTO findRepairOrder(String phoneNumber){
        for(int i = nrOfRepairOrders - 1; i >= 0; i--){
            if(repairOrders[i].getPhoneNumber().equals(phoneNumber)){
                return repairOrders[i].createRepairOrderDTO();
            }
        }
        return null;
    }

    /**
     * Saves a new or overwrites a repair order
     * @param repairOrder the specific repair order object
     */
    public void updateRepairOrder(RepairOrder repairOrder){
        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].getRepairOrderId().equals(repairOrder.getRepairOrderId())){
                repairOrders[i] = repairOrder;
                return;
            }
        }

        repairOrders[nrOfRepairOrders] = repairOrder;
        nrOfRepairOrders++;
    }

    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            return repairOrder.createRepairOrderDTO();
        }
        return null;
    }

    public void acceptRepairOrder(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.updateRepairOrderStatus("ACCEPTED");
        }
    }

    public void rejectRepairOrder(String repairOrderId){
        RepairOrder repairOrder = findRepairOrderById(repairOrderId);
        if(repairOrder != null){
            repairOrder.updateRepairOrderStatus("REJECTED");
        }
    }

    private RepairOrder findRepairOrderById(String repairOrderId){
        for (int i = 0; i < nrOfRepairOrders; i++) {
            if (repairOrders[i].getRepairOrderId().equals(repairOrderId)) {
                return repairOrders[i];
            }
        }
        return null;
    }
}
