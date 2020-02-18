package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingPlace {
    private Car car;

    public ParkingPlace(Car car) {
        this.car = car;
    }
}


