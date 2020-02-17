package service;

import model.Car;
import model.Parking;
import model.ParkingPlace;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static service.PrinterService.*;

public class ParkingServiceImpl implements ParkingService {
  private static final int UPPER_RANGE_RANDOM = 50;
  private final Random random = new Random(System.currentTimeMillis());
  private final Parking parking = new Parking();
  private final Scanner scanner = new Scanner(System.in);

  @Override
  public boolean isHasFreePlace() {
    return parking.getParkingSize() > parking.getParkingPlace().size();
  }

  @Override
  public void parkingHandler() {

    while (true) {

      String command = scanner.nextLine();
      switch (command) {
        case ("status"):
          printParkingPlaceInfo(parking.getParkingPlace());
          continue;

        case ("help"):
          printHelpCommands();
          continue;

        case (""):
          emptyMoveHandler();
          continue;

        case ("clear all"):
          clearAll(parking.getParkingPlace());
          continue;

        default:
          if (!clearByIndex(command)) {
            System.out.println("Invalid command");
          }
      }
    }
  }


  public int getParkingPlaceSizeNull(){
    return (int) parking.getParkingPlace().stream()
            .filter(parkingPlace -> parkingPlace.getCar() == null)
            .count();
  }



  @Override
  public void emptyMoveHandler() {
    int randomNumber = getRandomNumber(parking);
    ParkingServiceImpl parkingService = new ParkingServiceImpl();
    List<ParkingPlace> carList = parking.getParkingPlace();
    decrementAndRemoveCarList(carList);
    generateCarsAndAddToParkingPlace(carList, randomNumber);

    if (printNumberParkingSpace(parking, carList, getParkingPlaceSizeNull()) == 0) {
      printIterBeforeLeavingParking(carList);
      int emptyPlace = randomNumber - (parking.getParkingSize() - carList.size());
      printNotPlaceInParking(emptyPlace);
    }
  }

  @Override
  public boolean validateInputFromConsole() {
    try {
      int parkingSize = scanner.nextInt();
      parking.setParkingSize(parkingSize);

      if (parkingSize <= 0) {
        return false;
      }
    } catch (InputMismatchException ignored) {
      scanner.next();
      return false;
    }
    return true;
  }

  @Override
  public void decrementAndRemoveCarList(List<ParkingPlace> carList) {
    for (int i = 0; i <= carList.size() - 1; i++) {
      List<ParkingPlace> parkingPlace = parking.getParkingPlace();
      Car car = parkingPlace.get(i).getCar();

      if (car != null) {
        car.setRemainingIterate(car.getRemainingIterate() - 1);
        if (car.getRemainingIterate() <= 0) {
          parking.getParkingPlace().get(i).setCar(null);
        }
      }
    }
  }

  @Override
  public int getRandomNumber(Parking parking) {
    int numberAddedCars;
    if (parking.getParkingSize() <= 5) {
      numberAddedCars = 1;
    } else {
      int i = parking.getParkingSize() / 3;
      numberAddedCars = random.nextInt(i);
    }
    return numberAddedCars;
  }

  @Override
  public void generateCarsAndAddToParkingPlace(List<ParkingPlace> carList, final int randomNumber) {
    for (int i = 1; i <= randomNumber; i++) {
      int remainingIterate = random.nextInt(UPPER_RANGE_RANDOM);
      if (isHasFreePlace()) {
        List<ParkingPlace> parkingPlace = parking.getParkingPlace();
        parkingPlace.add(new ParkingPlace(new Car(remainingIterate <= 0 ? 1 : remainingIterate)));
      } else {
       parking.getParkingPlace().stream()
               .filter(parkingPlace -> parkingPlace.getCar() == null)
               .limit(randomNumber)
               .forEach(parkingPlace -> parkingPlace.setCar(new Car(remainingIterate <= 0 ? 1 : remainingIterate)));
      }
    }
  }

  @Override
  public void clearAll(List<ParkingPlace> carList) {
    carList.clear();
    System.out.println("parking successfully cleared");
    printParkingPlaceInfo(carList);
  }

  @Override
  public boolean clearByIndex(String index) {
    String[] arrCommand = index.split(" ");
    if (arrCommand[0].equals("clear")) {
      try {
        List<ParkingPlace> carList = parking.getParkingPlace();

        carList.get(Integer.parseInt(arrCommand[1])).setCar(null);
        System.out.println("Car under index " + arrCommand[1] + " was successfully deleted");
        printParkingPlaceInfo(carList);
        return true;
      } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
        System.out.println("Элемент не найден");
      }
    }
    return false;
  }

  @Override
  public void printHelpCommands() {
    System.out.println(
        " -------------------------------------------------------------------------------- \n"
            + "| <status> - Displays all current cars with their lifetime and parking number.   |\n"
            + "| <clear all> - completely cleans parking.                                       |\n"
            + "| <clear [index]> - cleans parking by index                                         |\n"
            + " --------------------------------------------------------------------------------");
  }
}
