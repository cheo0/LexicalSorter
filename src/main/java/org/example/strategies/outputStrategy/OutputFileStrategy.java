package org.example.strategies.outputStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFileStrategy extends OutputStrategy {
    private final String filePath;

    public OutputFileStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String content) throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        Files.write(path, content.getBytes());
    }
}
