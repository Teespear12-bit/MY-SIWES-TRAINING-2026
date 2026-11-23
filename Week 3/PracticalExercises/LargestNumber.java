import java.util.Arrays;
import java.util.Scanner;

public class LargestNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] list = new int[5];

        System.out.print("Enter number 1: ");
        int listOfNumbers1 = scanner.nextInt();

        System.out.print("Enter number 2: ");
        int listOfNumbers2 = scanner.nextInt();

        System.out.print("Enter number 3: ");
        int listOfNumbers3 = scanner.nextInt();

        System.out.print("Enter number 4: ");
        int listOfNumbers4 = scanner.nextInt();

        System.out.print("Enter number 5: ");
        int listOfNumbers5 = scanner.nextInt();

        list[0] = listOfNumbers1;
        list[1] = listOfNumbers2;
        list[2] = listOfNumbers3;
        list[3] = listOfNumbers4;
        list[4] = listOfNumbers5;



        int larNumber = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] > larNumber) {
                larNumber = list[i];
            }
        }



        showanswer(list, larNumber);

        scanner.close();
    }

    public static void showanswer(int[] list, int larNumber) {
        System.out.println("The largest number from the list " + Arrays.toString(list) + " is: " + larNumber);
    }
}

