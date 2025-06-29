import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Fixed number of subjects
        final int numSubjects = 5;
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        
        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            
            // Validate marks
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks! Enter marks between 0-100 for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }
            totalMarks += marks[i];
        }
        
        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;
        
        // Determine grade
        String grade = calculateGrade(averagePercentage);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks + "/" + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
    
    // Method to calculate grade based on average percentage
    private static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}