package org.example.strategies.inputStrategy;

import java.io.IOException;

public abstract class InputStrategy {
    protected String content;

    public abstract void read() throws IOException;

    @Override
    public String toString() {
        return content.trim();
    }
}
