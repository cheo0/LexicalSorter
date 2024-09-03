package org.example;

import org.example.exceptions.FlagException;
import org.example.exceptions.SameFileException;
import org.example.managers.ArgumentsManager;
import org.example.strategies.inputStrategy.InputStrategy;
import org.example.strategies.outputStrategy.OutputStrategy;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Application {
    private final String[] arguments;

    public Application(String[] args) {
        arguments = args;
    }

    public void run() {
        try {
            final ArgumentsManager argumentsManager = new ArgumentsManager(arguments);
            InputStrategy input = argumentsManager.getInput();
            OutputStrategy output = argumentsManager.getOutput();

            input.read();

            final String normalizerInput = normalize(input.toString());
            final String sortedInput = sort(normalizerInput);

            output.write(sortedInput);
        } catch (java.io.IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (FlagException | SameFileException e) {
            System.out.println(e.getMessage());
        }
    }

    private String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    private String sort(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return Arrays.stream(input.split("\\s+")).sorted(Comparator.naturalOrder()).collect(Collectors.joining(" "));
    }
}
