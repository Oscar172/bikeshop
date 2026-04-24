package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairTask;
import se.kth.iv1350.model.RepairOrder;

public class RepairOrderRegistry {

    private RepairOrderDTO[] repairOrders;
    private int nrOfRepairOrders;
    
    private RepairOrderRegistry(){
        this.repairOrders = new RepairOrderDTO[100];
        this.nrOfRepairOrders = 0;
    }

    //<<create>> repairOrderRegistry()
    public static RepairOrderRegistry repairOrderRegistry(){
        return new RepairOrderRegistry();
    }

    //createRepairOrder
    public RepairOrderDTO createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber){
        RepairOrder repairOrder = RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, this);

        RepairOrderDTO repairOrderDTO = new RepairOrderDTO(repairOrder.getRepairOrderId(),
                                                           repairOrder.getProblemDescr(),
                                                           repairOrder.getPhoneNumber(),
                                                           repairOrder.getBikeSerialNumber(),
                                                           repairOrder.getState(),
                                                           repairOrder.getTotalCost(),
                                                           repairOrder.getDiagnosticReport(), "");
        updateRepairOrder(repairOrderDTO);
        return repairOrderDTO;
    }



    //findAllRepairOrders
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
    RepairOrderDTO[] foundRepairOrders = new RepairOrderDTO[nrOfRepairOrders];
    int nrOfFoundRepairOrders = 0;

    for(int i = 0; i < nrOfRepairOrders; i++){
        if(repairOrders[i].phoneNumber().equals(phoneNumber)){
            foundRepairOrders[nrOfFoundRepairOrders] = repairOrders[i];
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
    public void addDiagnosticReport(String repairOrderId, String diagRepairTask){

    }

    public void addRepairTask(String repairOrderId, String repairTask, double cost){

    }

    public RepairOrderDTO findRepairOrder(String phoneString){
        RepairOrderDTO repairOrder = null;

        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].phoneNumber().equals(phoneString)){
                repairOrder = repairOrders[i];
                break;
            }
        }
        return repairOrder;
    }

    /**
     * Saves a new or overwrites a repair order
     * @param repairOrder the specific repair order object
     */
    public void updateRepairOrder(RepairOrderDTO repairOrder){
        repairOrders[nrOfRepairOrders] = repairOrder;
        nrOfRepairOrders++;
    }

    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId){
        return null;
    }

    public void acceptRepairOrder(String repairOrderId){

    }

    public void rejectRepairOrder(String repairOrderId){
        
    }
}

