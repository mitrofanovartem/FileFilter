package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

public class FileProcessor {
    private final String outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final DataClassifier classifier;
    private final OutputWriter writer;
    private final Statistics stats;

    public FileProcessor(String outputPath, String prefix, boolean appendMode) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.classifier = new DataClassifier();
        this.writer = new OutputWriter();
        this.stats = new Statistics();
    }

    public Statistics processFiles(String[] inputFiles) {
        for (String file : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        processLine(line);
                    }
                }
            } catch (Exception e) {
                System.err.println("Ошибка при обработке файла " + file + ": " + e.getMessage());
            }
        }
        writeResults();
        return stats;
    }

    private void processLine(String line) {
        DataClassifier.DataType type = classifier.classify(line);
        switch (type) {
            case INTEGER:
                stats.addInteger(Long.parseLong(line));
                writer.addInteger(line);
                break;
            case FLOAT:
                stats.addFloat(Double.parseDouble(line));
                writer.addFloat(line);
                break;
            case STRING:
                stats.addString(line);
                writer.addString(line);
                break;
        }
    }

    private void writeResults() {
        try {
            if (!writer.getIntegers().isEmpty()) {
                writer.writeToFile(Paths.get(outputPath, prefix + "integers.txt").toString(),
                        writer.getIntegers(), appendMode);
            }
            if (!writer.getFloats().isEmpty()) {
                writer.writeToFile(Paths.get(outputPath, prefix + "floats.txt").toString(),
                        writer.getFloats(), appendMode);
            }
            if (!writer.getStrings().isEmpty()) {
                writer.writeToFile(Paths.get(outputPath, prefix + "strings.txt").toString(),
                        writer.getStrings(), appendMode);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при записи выходных файлов: " + e.getMessage());
        }
    }
}