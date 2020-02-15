package service;

import model.Car;
import model.Parking;

import java.util.List;

public interface ParkingService {

    boolean validateInputFromConsole();

    void decrementAndRemoveCarList(List<Car> carList);

    int getRandomNumber(Parking parking);

    void generateCarsAndAddToList(List<Car> carList, int randomNumber);

    boolean isHasFreePlace();

    void parkingHandler();

    void clearAll(List<Car> carList);

    boolean clearByIndex(String command);

    void emptyMoveHandler();

    void printHelpCommands();
}
