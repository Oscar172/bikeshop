package se.kth.iv1350.integration;

public class RepairOrderRegistry {

    private RepairOrderDTO[] repairOrders;
    private int nrOfRepairOrders;
    
    private RepairOrderRegistry(){
        this.repairOrders = new RepairOrderDTO[100];
        this.nrOfRepairOrders = 0;
    }

   
    /**
     * Constructs repairOrderRegistry class
     * @return reference to the class
     */
    public static RepairOrderRegistry repairOrderRegistry(){
        return new RepairOrderRegistry();
    }

    RepairOrderDTO repairOrderDTO = RepairOrderDTO.createRepairOrderDTO();

    public String generateRepairOrderId(){
        return "RO" + (nrOfRepairOrders + 1);
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

    public RepairOrderDTO getRepairOrderByRepairOrderId (String repairOrderID){
        for(int i = nrOfRepairOrders - 1; i >= 0; i--){
            if(repairOrders[i].repairOrderID().equals(repairOrderID)){
                return repairOrders[i];
            }
        }
        return null;
    }



    public RepairOrderDTO findRepairOrder(String phoneNumber){
        for(int i = nrOfRepairOrders - 1; i >= 0; i--){
            if(repairOrders[i].phoneNumber().equals(phoneNumber)){
                return repairOrders[i];
            }
        }
        return null;
    }

    /**
     * Saves a new or overwrites a repair order
     * @param repairOrder the specific repair order object
     */
    public void updateRepairOrder(RepairOrderDTO repairOrder){
        for(int i = 0; i < nrOfRepairOrders; i++){
            if(repairOrders[i].repairOrderId().equals(repairOrder.repairOrderId())){
                repairOrders[i] = repairOrder;
                return;
            }
        }

        repairOrders[nrOfRepairOrders] = repairOrder;
        nrOfRepairOrders++;
    }

    public RepairOrderDTO returnRepairOrderDTO(String repairOrderId){

        //reads repairOrderId for the DTO and then identifies
        //loop that searches all the DTOs, could be linear to simnplify
        return null;
    }

    public void acceptRepairOrder(String repairOrderId){

    }

    public void rejectRepairOrder(String repairOrderId){
        
    }
}
