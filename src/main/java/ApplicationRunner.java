import model.Parking;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class ApplicationRunner {

    public static void question(Parking parking){
        Scanner response = new Scanner(System.in);
        System.out.println("Добавить машину в очередь?");
        String userResponse = response.nextLine();

        if (userResponse.equals("да")){
            parking.addCarsToList();
        }else {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking();


        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Введите количество парковочных мест");
        parking.setParkingSize(parkingSize.nextInt());


        question(parking);


    }
}
