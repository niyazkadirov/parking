package service;

import model.Car;
import model.Parking;

import java.util.*;

import static service.PrinterService.*;


public class ParkingService {
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());
    private Parking parking = new Parking();
    private Scanner scanner = new Scanner(System.in);



    public boolean isFreePlace() {
        return carList.size() < parking.getParkingSize();
    }




    public void addCarsToList() {

        while (true) {
            String s = scanner.nextLine();
            if (!s.isEmpty()) {
                continue;
            }

            int randomNumber = getRandomNumber(parking);

            if (isFreePlace()) {
                for (int i = 1; i <= randomNumber; i++) {

                    if (!isFreePlace()) {
                        continue;
                    }
                    carList.add(new Car(random.nextInt(10)));
                }
            }


            decrementCarList(carList);

            carList.removeIf(car -> car.getRemainingIterate() <= 0);

            printNumberParkingSpace(parking, carList);

            int emptyPlace = randomNumber - (parking.getParkingSize() - carList.size());


            printNotPlaceInParking(emptyPlace);

            printParkingPlaceInfo(carList);
        }
    }

    public int getParkingSizeFromConsole(){
        Scanner parkingSize = new Scanner(System.in);
        int parkingSizeFromConsole = parkingSize.nextInt();
        parking.setParkingSize(parkingSizeFromConsole);
        return parkingSizeFromConsole;
    }

    public void decrementCarList(List<Car> carList){
        for (Car car : carList) {
            car.setRemainingIterate(car.getRemainingIterate() - 1);
        }
    }

    public int getRandomNumber(Parking parking){
        int randomNumber;
        if (parking.getParkingSize() <= 5) {
            randomNumber = 1;
        } else {
            int i = parking.getParkingSize() / 3;
            randomNumber = random.nextInt(i);
        }
        return randomNumber;
    }

    public void generateCarsForList1(List<Class>T, int randomNumber){
        if (isFreePlace()) {
            for (int i = 1; i <= randomNumber; i++) {

                if (!isFreePlace()) {
                    continue;
                }
                carList.add(new Car(random.nextInt(10)));
            }
        }
    }
}
