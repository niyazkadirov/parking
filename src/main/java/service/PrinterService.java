package service;

import model.Car;

import java.util.List;

public interface PrinterService {

    void printQuestionToConsole(ParkingServiceImpl parkingService);

    void printParkingInfo(List<Car> cars);
}
