package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//implements säger, "följ samma observer-interface som RepairOrderView".

 /**
 * Writes updated repair orders to a log file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    
    private static final String LOG_REPAIR_ORDER = "logs/repair-order-log.txt";

    /**
     * Writes the updated repair order to the log file. 
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO){
                                                                                    //Append: true => vi "lägger till" istället för att vi skriver över innehållet typ
        try(PrintWriter logStream = new PrintWriter(new FileWriter(LOG_REPAIR_ORDER, true))){
            logStream.println("-----Repair Order Log-----");
            logStream.println("Repair order ID: " + repairOrderDTO.getRepairOrderId());
            logStream.println("Phone number: " + repairOrderDTO.getPhoneNumber());
            logStream.println("Problem Description: " + repairOrderDTO.getProblemDescr());
            logStream.println("Serial Number: " + repairOrderDTO.getBikeSerialNumber());
            logStream.println("Status: " + repairOrderDTO.getState());
            logStream.println("Total cost: " + repairOrderDTO.getTotalCost());
            logStream.println("Diagnostic report: " + repairOrderDTO.getDiagnosticReport());
            logStream.println("ETA: " + repairOrderDTO.getEstimatedCompletionDate());
            logStream.println();
        }
        catch (IOException e){
            System.out.println("Could not write repair order log file.");
        }
    }
}
