package com.marl0vv.study;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Service {
    private Storage storage;

    public Service(Storage storage) {
        this.storage = storage;
    }

    public void showEntry() {
        try {
            System.out.print("Enter id of the entry: ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();

            Entry entry = storage.findByIdOrNull(id);
            if (entry != null) {
                System.out.println("\n");
                entry.printEntry();
            } else {
                System.out.println("\nNo entry found with id " + id);
            }
        } catch (InputMismatchException exception) {
            System.err.println("Invalid input. Please enter a valid integer.");
        }
    }

    public void findEntry() {
        System.out.print("Enter name of the entry: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        List<Entry> matchingEntries = storage.findByName(name);
        if (!matchingEntries.isEmpty()) {
            System.out.println("\nMatching entries:");
            matchingEntries.forEach(Entry::printEntry);
        } else {
            System.out.println("\nNo matching entries found.");
        }
    }

    public void run(String... args) {
        System.out.println("Hello! Welcome to storage!");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Show entry by id");
            System.out.println("2 - Find entry by name");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");

            int optionChoice;
            if (sc.hasNextInt()) {
                optionChoice = Integer.parseInt(sc.nextLine());
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                sc.nextLine(); // Consume the invalid input
                continue;
            }
            switch (optionChoice) {
                case 1:
                    showEntry();
                    break;
                case 2:
                    findEntry();
                    break;
                case 0:
                    System.out.println("\nProgram is terminating. Thank you for using the storage. Bye!");
                    return;
                default:
                    System.out.println("\nError! Invalid option. Select valid option from the menu.");
            }
        }
    }
}
