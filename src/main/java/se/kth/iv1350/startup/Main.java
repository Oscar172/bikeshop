package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.CustomerRegistry;
import se.kth.iv1350.integration.RepairOrderRegistry;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.view.View;

/**
 * Starts the application.
 */
public class Main 
{
    /**
     * Starts the entire application
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args )
    {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        Printer printer = new Printer();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

        Controller contr = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(contr);
        view.runFakeExecution();
    }
}

“Vår kod är uppdelad i olika delar så att varje klass har ett tydligt ansvar. Programmet startar i Main, där vi skapar de
viktigaste objekten: en kunddatakälla, en reparationsorder-datakälla, en controller och en view. Efter det kör View en fejkad
körning som visar hur systemet fungerar.

View fungerar som användaren. Den gör anrop som att söka kund, skapa repair order, lägga till diagnos, lägga till repair task och
acceptera ordern. Men View gör inte jobbet själv, utan skickar allt vidare till Controller.

Controller är mellanhanden i systemet. Den tar emot anrop från View och skickar dem vidare till rätt klass. Om vi vill hitta en
kund så går det till CustomerRegistry. Om vi vill skapa eller uppdatera en repair order så går det till RepairOrderRegistry.

Själva affärslogiken ligger mest i RepairOrder. Där sparas information om problemet, cykelns serienummer, kundens telefonnummer,
diagnos, repair tasks, status och total kostnad. När man lägger till en repair task så uppdateras totalpriset direkt i RepairOrder,
så logiken ligger i modellklassen och inte i view eller controller.

RepairOrderRegistry ansvarar för att spara och hitta ordrar. Den kan också acceptera eller rejecta en order genom att ändra dess
status. När en order accepteras så ber Controller Printer att skriva ut information om ordern.

Så kort sagt: Main startar, View anropar, Controller skickar vidare, registren hämtar eller sparar data, och RepairOrder innehåller
själva logiken och datan.”

Kort nödförklaring om de frågar “varför så här?”:

“Vi har delat upp det i lager för att minska koppling och göra koden tydligare. View pratar bara med controller, controller
samordnar, och modellen håller i själva datan och logiken.”