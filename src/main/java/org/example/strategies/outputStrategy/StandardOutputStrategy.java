package org.example.strategies.outputStrategy;

import java.io.IOException;

public class StandardOutputStrategy extends OutputStrategy {

    @Override
    public void write(String content) throws IOException {
        System.out.println(content);
    }
}
