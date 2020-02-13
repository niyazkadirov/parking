import service.ParkingServiceImpl;

import static service.PrinterService.printQuestionToConsole;

public class ApplicationRunner {
    public static void main(String[] args) {
        ParkingServiceImpl parkingServiceImpl = new ParkingServiceImpl();

        printQuestionToConsole(parkingServiceImpl);
    }
}


