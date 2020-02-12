import service.ParkingServiceImpl;
import service.PrinterService;
import service.PrinterServiceImpl;


public class ApplicationRunner {
    public static void main(String[] args) {
        PrinterService printerService = new PrinterServiceImpl();
        ParkingServiceImpl parkingService = new ParkingServiceImpl();



        printerService.printQuestionToConsole(parkingService);
    }
}


