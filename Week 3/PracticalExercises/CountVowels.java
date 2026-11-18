import java.util.Scanner;

public class CountVowels {

    public static void main(String[] args) {

        int total = checkForVowels();
        showAnswers(total);
    }

    public static int checkForVowels() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String initword = scanner.nextLine().trim();
        System.out.println(initword);

        int vowelCounter = 0;
        String word = initword.toLowerCase();

        for (int i = 0; i <= word.length() - 1; i++) {
            char ch = word.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCounter++;
            }
        }

        scanner.close();
        return vowelCounter;
    }

    public static void showAnswers(int vowelCounter) {
        System.out.println(vowelCounter);
        System.out.println("There are " + vowelCounter + " vowels in your word");
    }
}