package service;

import model.Car;
import model.Parking;
import model.ParkingPlace;

import java.util.List;

interface ParkingService {

    boolean validateInputFromConsole();

    void decrementAndRemoveCarList(List<ParkingPlace> carList);

    int getRandomNumber(Parking parking);

    void generateCarsAndAddToList(List<ParkingPlace> carList, final int randomNumber);

    boolean isHasFreePlace();

    void parkingHandler();

    void clearAll(List<ParkingPlace> carList);

    boolean clearByIndex(String command);

    void emptyMoveHandler();

    void printHelpCommands();
}
