package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerDTO;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.integration.exceptions.UserNotFoundException;
import se.kth.iv1350.util.Logger;

/**
 * Simulates user interaction with the system.
 */
public class View {
    private final Controller contr;
    private final Logger logger;

    /**
     * Creates a new View.
     * 
     * @param contr The controller used for all system operations.
     */
    public View(Controller contr, Logger logger) {
        this.contr = contr;
        this.logger = logger;
    }

    /**
    * Simulates a sample execution of the program.
    */
    public void runFakeExecution() {
        String phoneNumber = "1234";
        String phoneNumberDataFail = "0000";
        String phoneNumberNotFound = "1235";
        String repairOrderId = "RO-1";

        System.out.println("----- Sample Execution -----");
        System.out.println();

        System.out.println("----- Data Failure Exception Sample -----");
        try {
            contr.searchForCustomer(phoneNumberDataFail);
        } catch (UserNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Something went wrong when trying to reach server. Please try again later.");
            logger.log(e.getMessage());
        }

        System.out.println();

        System.out.println("----- User Not Found Exception Sample -----");
        try {
            contr.searchForCustomer(phoneNumberNotFound);    
        } catch (UserNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Something went wrong when trying to reach server. Please try again later.");
            logger.log(e.getMessage());
        }

        System.out.println();

        System.out.println("----- Customer Found Sample -----");
        try {
            CustomerDTO foundCustomer = contr.searchForCustomer(phoneNumber);
            System.out.println("Customer found: " + foundCustomer.getName() + " under phone number: " + phoneNumber);
            System.out.println("");

            contr.createRepairOrder("Flat tire and a broken front-light", phoneNumber, "BIKE-001");
            contr.addDiagnosticReport(repairOrderId, "Fix flat tire and other stuff");
            contr.addRepairTask(repairOrderId, "Change all brake wires", 249.90);
            contr.acceptRepairOrder(repairOrderId);
        } catch (UserNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Something went wrong when trying to reach server. Please try again later.");
            logger.log(e.getMessage());
        }    
    }    
}
