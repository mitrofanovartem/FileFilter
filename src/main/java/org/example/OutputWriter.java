package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class OutputWriter {
    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(String value) {
        integers.add(value);
    }

    public void addFloat(String value) {
        floats.add(value);
    }

    public void addString(String value) {
        strings.add(value);
    }

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getFloats() {
        return floats;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void writeToFile(String filePath, List<String> data, boolean append) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}