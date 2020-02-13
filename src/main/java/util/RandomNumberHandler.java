package util;

import service.ParkingServiceImpl;

import java.util.Random;

public class RandomNumberHandler {



    public int getRandomNumber(Random random, int parkingSize) {
        int randomNumber;
        if (parkingSize <= 5) {
            randomNumber = 1;
        } else {
            int i = parkingSize / 3;

            randomNumber = random.nextInt(i);
            while (randomNumber == 0) {
                randomNumber = random.nextInt(i);
            }
        }
        return randomNumber;
    }
}
