import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    int age;
    String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

public class Stud_Management_System {
    private static final String FILE_NAME = "students.dat";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student Record");
            System.out.println("2. Edit Student Record");
            System.out.println("3. Search Student Record");
            System.out.println("4. Display All Students Record");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    editStudent(scanner);
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    saveStudents();
                    System.out.println("Exited Successfully.....");
                    scanner.close();
                    return;
                default:
                    System.out.println("You have entered an invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        
        students.add(new Student(id, name, age, course));
        System.out.println("Student added successfully!");
    }

    private static void editStudent(Scanner scanner) {
        System.out.print("Enter Student ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        
        for (Student student : students) {
            if (student.id == id) {
                System.out.print("Enter New Name: ");
                student.name = scanner.nextLine();
                System.out.print("Enter New Age: ");
                student.age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter New Course: ");
                student.course = scanner.nextLine();
                System.out.println("Student details updated successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        
        for (Student student : students) {
            if (student.id == id) {
                System.out.println("Student Found: " + student);
                return;
            }
        }
        System.out.println("**Student record not found.**");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("**Student record not found.**");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @SuppressWarnings("unchecked")
	private static void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    private static void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }
}
