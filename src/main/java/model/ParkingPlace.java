package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingPlace {
    private Car car;
    private int id;
    private boolean status;

    public ParkingPlace(Car car) {
        this.car = car;
    }
}


