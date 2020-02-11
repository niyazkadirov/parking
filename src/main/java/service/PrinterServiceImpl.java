package service;

import lombok.SneakyThrows;
import model.Car;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrinterServiceImpl implements PrinterService {

    private MyThread thread = new MyThread();

    @Override
    public void printQuestionToConsole(ParkingServiceImpl parkingService) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size");
        try {
            parkingService.setParkingSize(parkingSize.nextInt());
            thread.start();
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            parkingService.setCheckEnter();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }
    }

    public void printParkingInfo(List<Car> cars){
        System.out.println("In the parking lot of "+ cars.size() +" cars");
        System.out.println("parking will be free in "+ cars.stream().sorted().findFirst());
    }

}






class MyThread extends Thread {
    private ParkingServiceImpl parkingService = new ParkingServiceImpl();

    @SneakyThrows
    @Override
    public void run() {
        parkingService.addFewCarsToListAndStopByEnter();
    }
}
