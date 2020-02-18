package service;

import model.Parking;
import model.ParkingPlace;

import java.util.List;

interface ParkingService {

    boolean validateInputFromConsole();

    void decrementAndRemoveParkingPlaceList(List<ParkingPlace> parkingPlaceList);

    int getRandomNumber(Parking parking);

    void generateCarsAndAddToParkingPlace(List<ParkingPlace> parkingPlaceList, final int randomNumber);

    boolean isHasFreePlace();

    void parkingHandler();

    void clearAll(List<ParkingPlace> parkingPlaceList);

    boolean clearByIndex(String command);

    void emptyMoveHandler();

    void printHelpCommands();

    int getEmptyParkingSpaces();
}
