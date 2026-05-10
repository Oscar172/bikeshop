package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

 /**
 * Prints log messages to a specified file.
 */
public class FileLogger implements Logger{
    private final PrintWriter logStream;

    /**
    * Creates a new instance and opens the specified log file.
    * New log messages are appended to the existing file.
    */
    public FileLogger(String fileName) {
        PrintWriter stream;
        try {
            stream = new PrintWriter(new FileWriter(fileName, true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
            stream = new PrintWriter(System.err, true);
        }
        this.logStream = stream;
    }

    /**
    * Prints the specified string to the log file with a time stamp.
    *
    * @param message The string that will be printed to the log file.
    */
   @Override
    public void log(String message) {
        java.time.LocalDateTime timeStampDateTime = java.time.LocalDateTime.now();
        logStream.println(timeStampDateTime + " \n" + message);
    }
 }


