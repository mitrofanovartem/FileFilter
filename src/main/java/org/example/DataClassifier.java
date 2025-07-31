package org.example;

public class DataClassifier {
    public enum DataType {
        INTEGER, FLOAT, STRING
    }

    public DataType classify(String line) {
        try {
            Long.parseLong(line);
            return DataType.INTEGER;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(line);
                return DataType.FLOAT;
            } catch (NumberFormatException e2) {
                return DataType.STRING;
            }
        }
    }
}