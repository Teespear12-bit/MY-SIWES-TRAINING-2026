import java.util.Scanner;

public class EvenOrOdd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        int check = number % 2;
    

        if (number < 1) {
            invalid(check);
        } else if (check == 0) {
            even(check);
        } else {
            odd(check);
        }

        scanner.close();
    }

    public static void invalid(int number) {
        System.out.println("Sorry, number must be greater than 0");
    }

    public static void even(int check) {
        System.out.println("Your number is even");
    }

    public static void odd(int check) {
        System.out.println("Your number is odd");
    }
}