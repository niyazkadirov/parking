import java.util.Scanner;

public class Test {

    public static void main(String args[])
    {
        double numberOfStudents = 0;
        Scanner scnr = new Scanner(System.in);

        boolean readValue = false;            //Check if the valid input is received
        boolean shouldAskForNumber = true;    //Need to ask for number again? Case for Enter
        do {
            if (shouldAskForNumber) {
                System.out.print("Enter a number:");
                shouldAskForNumber = false;
            }

            if (scnr.hasNextDouble()) {
                numberOfStudents = scnr.nextDouble();
                readValue = true;
            } else {
                String token = scnr.next();
                if (!"".equals(token.trim())) {   //Check for Enter or space
                    shouldAskForNumber = true;
                }
            }
        } while (!readValue);

        System.out.printf("Value read is %.0f\n", numberOfStudents);
        System.out.println("You're outside the loop!");
    }
}
