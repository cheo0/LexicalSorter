package org.example.strategies.inputStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileStrategy extends InputStrategy {
    private final String filePath;

    public FileStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void read() throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }

            this.content = content.toString();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
