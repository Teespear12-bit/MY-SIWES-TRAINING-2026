

import java.util.Scanner;

public class search {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] studentsNames = {"john", "james", "jack", "matthew", "elisa"};

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        String Name = name.toLowerCase();

        boolean found = false;
        for (int i = 0; i < studentsNames.length; i++) {
            if (studentsNames[i].equals(Name)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student Found");
        } else {
            System.out.println("Student NOT Found");
        }

        scanner.close();
    }
}