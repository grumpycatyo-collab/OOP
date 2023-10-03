package menu;

import java.util.Scanner;

public class DisplayMenu {

    public static char displayMainMenu(Scanner scanner) {
        System.out.println("Welcome to TUM's student management system!");
        System.out.println("What do you want to do?");
        System.out.println("g - General operations");
        System.out.println("f - Faculty operations");
        System.out.println("s - Student operations");
        System.out.println("q - Quit program");
        System.out.print("your input> ");
        return scanner.nextLine().charAt(0);
    }

    public static String displayGeneralOperationsMenu(Scanner scanner) {
        System.out.println("\nGeneral operations");
        System.out.println("What do you want to do?");
        System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
        System.out.println("ss/<student email> - search student and show faculty");
        System.out.println("df - display faculties");
        System.out.println("df/<field> - display all faculties of a field");
        System.out.println("b - Back");
        System.out.println("q - Quit Program");
        System.out.print("your input> ");
        return scanner.nextLine();
    }

    public static String displayFacultyOperationsMenu(Scanner scanner) {
        System.out.println("\nFaculty operations");
        System.out.println("What do you want to do?");
        System.out.println("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student");
        System.out.println("gs/<email> - graduate student");
        System.out.println("ds/<faculty abbreviation> - display enrolled students");
        System.out.println("dg/<faculty abbreviation> - display graduated students");
        System.out.println("bf/<faculty abbreviation>/<email> - check if student belongs to faculty");
        System.out.println("b - Back");
        System.out.println("q - Quit Program");
        System.out.print("your input> ");
        return scanner.nextLine();
    }
}
