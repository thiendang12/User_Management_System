package util;

import java.util.Scanner;

public class Library {
    private final Scanner scanner = new Scanner(System.in);

    public String getString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
public int getInt(String prompt, int min, int max, boolean checkFormat, int minFormatLength) {
    int value;
    while (true) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        if (checkFormat) {
            if (input.length() >= minFormatLength && !input.contains(" ")) {
                try {
                    value = Integer.parseInt(input);
                    if (value >= min && value <= max) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            } else {
                System.out.println("Invalid input format. You must enter at least " + minFormatLength + " characters, and no space.");
            }
        } else {
            try {
                value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
    return value;
}
}
