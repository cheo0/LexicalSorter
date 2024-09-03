package org.example.strategies.inputStrategy;

import java.io.IOException;
import java.util.Scanner;

public class StandardInputStrategy extends InputStrategy {
    @Override
    public void read() throws IOException {
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        System.out.println("Enter a text: ");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("\\exit")) {
                break;
            }

            builder.append(line).append(System.lineSeparator());
        }

        this.content = builder.toString();
    }
}
