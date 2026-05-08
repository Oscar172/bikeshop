package se.kth.iv1350.view;

import se.kth.iv1350.integration.RepairOrderDTO;

import se.kth.iv1350.util.Logger;

//implements säger, "följ samma observer-interface som RepairOrderView".

 /**
 * Writes updated repair orders to a log file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    
    private final Logger logger;

    public RepairOrderLogger(Logger logger){
        this.logger = logger;
    }

    /**
     * Writes the updated repair order to the log file. 
     * @param repairOrderDTO The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrderDTO){
                                                                                    //Append: true => vi "lägger till" istället för att vi skriver över innehållet typ
        String message = 
            "-----Repair Order Log-----\n" +
              "Repair order ID: " + repairOrderDTO.getRepairOrderId() + "\n" +
              "Phone number: " + repairOrderDTO.getPhoneNumber() + "\n" +
              "Problem Description: " + repairOrderDTO.getProblemDescr() + "\n" +
              "Serial Number: " + repairOrderDTO.getBikeSerialNumber() + "\n" +
              "Status: " + repairOrderDTO.getState() + "\n" +
              "Total cost: " + repairOrderDTO.getTotalCost() + "\n" +
              "Diagnostic report: " + repairOrderDTO.getDiagnosticReport() + "\n" +
              "ETA: " + repairOrderDTO.getEstimatedCompletionDate() + "\n";
              logger.log(message);
    }
}
