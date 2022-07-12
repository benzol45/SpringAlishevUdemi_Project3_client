package sensor_client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to sensor emulator.\n Press:\n1 for send 1000 random value\n2 for view diagram");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim();
        if (answer.equals("1")) {
            DataSender.sendRandomValues();
        } else if (answer.equals("2")) {
            Diagram.displayDiagram();
        } else {
            System.out.println("Incorrect input");
        }
    }
}
