package org.example.strategies.outputStrategy;

import java.io.IOException;

public abstract class OutputStrategy {
    abstract public void write(String content) throws IOException;
}
