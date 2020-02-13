import model.Parking;
import service.ParkingService;
import static service.PrinterService.printQuestionToConsole;

public class ApplicationRunner {
    public static void main(String[] args) {
        ParkingService parkingService = new ParkingService();

        printQuestionToConsole(parkingService);
    }
}


