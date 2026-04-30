package se.kth.iv1350.integration;

import se.kth.iv1350.model.RepairOrder;

/**
 * Represents a printer used to print text and repair order information.
 */
public class Printer {

    /**
     * Creates a new Printer.
     */
    public Printer(){
    }
    /**
     * Prints a text message.
     * 
     * @param text The text to print.
     */
    public void print(String text){
        System.out.println(text);
    }

    /**
     * Prints information about a repair order.
     * 
     * @param repairOrder The repair order to print.
     */
    public void printRepairOrder(RepairOrder repairOrder){
        System.out.println("");
        System.out.println("----- This Repair Order ----- ");
        System.out.println("ID: " + repairOrder.getRepairOrderId());
        System.out.println("Problem: " + repairOrder.getProblemDescr());
        System.out.println("Bike Serial Number: " + repairOrder.getBikeSerialNumber());
        System.out.println("Status: " + repairOrder.getState());
        System.out.println("The total cost " + repairOrder.getTotalCost());
        System.out.println("");
    }
}