import java.util.*;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        System.out.println("Enter student grades (type -1 to stop):");
        
        while (true) {
            int grade = scanner.nextInt();
            if (grade == -1) {
                break;
            }
            grades.add(grade);
        }
        
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            int highest = Collections.max(grades);
            int lowest = Collections.min(grades);
            double average = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);

            System.out.println("Average Score: " + average);
            System.out.println("Highest Score: " + highest);
            System.out.println("Lowest Score: " + lowest);
        }
        
        scanner.close();
    }
}