import java.util.Scanner;

public class CalculateGrades {

    public static void main(String[] args) {
        gradingSystem();
    }

    public static void gradingSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Mathematics score: ");
        double Math = scanner.nextDouble();

        System.out.print("Enter English score: ");
        double English = scanner.nextDouble();

        System.out.print("Enter CRS score: ");
        double CRS = scanner.nextDouble();

        double Overall = (Math + English + CRS) / 3;

        String mathResult = "";
        String englishResult = "";
        String crsResult = "";
        String overallResult = "";

        System.out.println("Overall Score: " + Overall);

        if (Math < 0 || Math > 100) {
            mathResult = "INVALID SCORE (0-100)";
        } else if (Math >= 80) {
            mathResult = "MATHEMATICS: A";
        } else if (Math >= 70) {
            mathResult = "MATHEMATICS: B";
        } else if (Math >= 55) {
            mathResult = "MATHEMATICS: C";
        } else if (Math >= 50) {
            mathResult = "MATHEMATICS: D";
        } else if (Math >= 40) {
            mathResult = "MATHEMATICS: E";
        } else if (Math < 40) {
            mathResult = "MATHEMATICS: F";
        }

        if (English < 0 || English > 100) {
            englishResult = "INVALID SCORE (0-100)";
        } else if (English >= 80) {
            englishResult = "ENGLISH: A";
        } else if (English >= 70) {
            englishResult = "ENGLISH: B";
        } else if (English >= 55) {
            englishResult = "ENGLISH: C";
        } else if (English >= 50) {
            englishResult = "ENGLISH: D";
        } else if (English >= 40) {
            englishResult = "ENGLISH: E";
        } else if (English < 40) {
            englishResult = "ENGLISH: F";
        }

        if (CRS < 0 || CRS > 100) {
            crsResult = "INVALID SCORE (0-100)";
        } else if (CRS >= 80) {
            crsResult = "CRS: A";
        } else if (CRS >= 70) {
            crsResult = "CRS: B";
        } else if (CRS >= 55) {
            crsResult = "CRS: C";
        } else if (CRS >= 50) {
            crsResult = "CRS: D";
        } else if (CRS >= 40) {
            crsResult = "CRS: E";
        } else if (CRS < 40) {
            crsResult = "CRS: F";
        }

        if (Overall < 0 || Overall > 100) {
            overallResult = "INVALID SCORE (0-100)";
        } else if (Overall >= 80) {
            overallResult = "OVERALL GRADE: A";
        } else if (Overall >= 70) {
            overallResult = "OVERALL GRADE: B";
        } else if (Overall >= 55) {
            overallResult = "OVERALL GRADE: C";
        } else if (Overall >= 50) {
            overallResult = "OVERALL GRADE: D";
        } else if (Overall >= 40) {
            overallResult = "OVERALL GRADE: E";
        } else if (Overall < 40) {
            overallResult = "OVERALL GRADE: F";
        }

        System.out.println(mathResult);
        System.out.println(englishResult);
        System.out.println(crsResult);
        System.out.println(overallResult);

        scanner.close();
    }
}