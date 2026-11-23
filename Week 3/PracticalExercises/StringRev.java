import java.util.Scanner;

public class StringRev {

    public static void main(String[] args) {
        reverseIt();
    }

    public static void reverseIt() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = scanner.nextLine().trim();
        String newWord = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            newWord += word.charAt(i);
        }

        System.out.println(newWord);

        scanner.close();
    }
}