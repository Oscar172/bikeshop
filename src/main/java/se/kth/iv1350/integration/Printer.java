package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairOrder;

public class Printer {

    public Printer(){
    }

    public void print(String text){
        System.out.println(text);
    }

    //writes repairorder
    public void printRepairOrder(RepairOrder repairOrder){
        System.out.println("");
        System.out.println("This repair order: ");
        System.out.println("ID: " + repairOrder.getRepairOrderId());
        System.out.println("Problem: " + repairOrder.getProblemDescr());
        System.out.println("Bike Serial Number: " + repairOrder.getBikeSerialNumber());
        System.out.println("Status: " + repairOrder.getState());
        System.out.println("The total cost " + repairOrder.getTotalCost());
        System.out.println("");
    }
}