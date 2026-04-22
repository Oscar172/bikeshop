package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.dto.RepairOrderDTO;

public class RepairOrderRegistry {

    //createRepairOrder
    public RepairOrder createRepairOrder(String problemDescr, String phoneNumber, String bikeSerialNumber){
        return RepairOrder.createRepairOrder(problemDescr, phoneNumber, bikeSerialNumber, this);
    }

    //<<create>> repairOrderRegistry()
    public static RepairOrderRegistry repairOrderRegistry(){
        return new RepairOrderRegistry();
    }

    //findAllRepairOrders
    public RepairOrderDTO[] findAllRepairOrders(String phoneNumber){
        return new RepairOrderDTO[0];
    }

    //addDiagnosticReport
    public void addDiagnosticReport(String repairOrderId, String diagRepairTask){

    }

    public void addRepairTask(String repairOrderId, String repairTask, double cost){

    }

    public RepairOrderDTO findRepairOrder(String phoneString){
        return null;
    }

    public void updateRepairOrder(RepairOrderDTO repairOrder){

    }

    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId){
        return null;
    }

    public void acceptRepairOrder(String repairOrderId){

    }

    public void rejectRepairOrder(String repairOrderId){
        
    }





}

