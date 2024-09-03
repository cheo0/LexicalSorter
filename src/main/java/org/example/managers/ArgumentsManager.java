package org.example.managers;

import org.example.exceptions.FlagException;
import org.example.exceptions.SameFileException;
import org.example.strategies.inputStrategy.FileStrategy;
import org.example.strategies.inputStrategy.InputStrategy;
import org.example.strategies.inputStrategy.StandardInputStrategy;
import org.example.strategies.outputStrategy.OutputFileStrategy;
import org.example.strategies.outputStrategy.OutputStrategy;
import org.example.strategies.outputStrategy.StandardOutputStrategy;

public class ArgumentsManager {
    private final String[] arguments;
    private InputStrategy input;
    private OutputStrategy output;

    private static final String FLAG_STANDARD_INPUT = "-i";
    private static final String FLAG_FILE_OUTPUT = "-o";
    private static final String FLAG_FILE_INPUT = "-f";

    public ArgumentsManager(String[] args) throws FlagException, SameFileException {
        arguments = args;

        parseArguments();
    }

    private void parseArguments() throws FlagException, SameFileException {
        boolean useStandardInput = false;
        boolean useInputFile = false;
        boolean useOutputFile = false;

        String inputFile = null;
        String outputFile = null;

        if (arguments.length == 0) {
            throw new FlagException("No se le pasaron argumentos al programa.");
        }

        for (int i = 0; i < arguments.length; i++) {
            switch (arguments[i]) {
                case FLAG_STANDARD_INPUT:
                    if (i + 1 < arguments.length && !isArgument(arguments[i + 1])) {
                        throw new FlagException("La bandera -i no espera argumento");
                    }

                    input = new StandardInputStrategy();
                    useStandardInput = true;
                    break;
                case FLAG_FILE_OUTPUT:
                    String fileOutputPath = validateAndGetNext(i, "La bandera -o espera un argumento");

                    output = new OutputFileStrategy(fileOutputPath);
                    outputFile = fileOutputPath;
                    useOutputFile = true;
                    break;
                case FLAG_FILE_INPUT:
                    String fileInputPath = validateAndGetNext(i, "La bandera -f espera un argumento");

                    input = new FileStrategy(fileInputPath);
                    inputFile = fileInputPath;
                    useInputFile = true;
                    break;
            }
        }

        if (useStandardInput && useInputFile) {
            throw new FlagException("No se puede utilizar entrada estandar junto con un archivo de entrada");
        }

        if (inputFile != null && inputFile.equals(outputFile)) {
            throw new SameFileException();
        }

        if (!useOutputFile) {
            output = new StandardOutputStrategy();
        }
    }

    private boolean isArgument(String arg) {
        return arg.equals("-i") || arg.equals("-o") || arg.equals("-f");
    }

    private String validateAndGetNext(int index, String errorMessage) throws FlagException {
        if (index + 1 >= arguments.length || isArgument(arguments[index + 1])) {
            throw new FlagException(errorMessage);
        }

        return arguments[index + 1];
    }

    public InputStrategy getInput() {
        return input;
    }

    public OutputStrategy getOutput() {
        return output;
    }
}
