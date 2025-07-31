package org.example;

public class Statistics {
    private long integerCount = 0;
    private long integerSum = 0;
    private Long integerMin = null;
    private Long integerMax = null;

    private long floatCount = 0;
    private double floatSum = 0.0;
    private Double floatMin = null;
    private Double floatMax = null;

    private long stringCount = 0;
    private Integer stringMinLength = null;
    private Integer stringMaxLength = null;

    public void addInteger(long value) {
        integerCount++;
        integerSum += value;
        integerMin = (integerMin == null) ? value : Math.min(integerMin, value);
        integerMax = (integerMax == null) ? value : Math.max(integerMax, value);
    }

    public void addFloat(double value) {
        floatCount++;
        floatSum += value;
        floatMin = (floatMin == null) ? value : Math.min(floatMin, value);
        floatMax = (floatMax == null) ? value : Math.max(floatMax, value);
    }

    public void addString(String value) {
        stringCount++;
        int length = value.length();
        stringMinLength = (stringMinLength == null) ? length : Math.min(stringMinLength, length);
        stringMaxLength = (stringMaxLength == null) ? length : Math.max(stringMaxLength, length);
    }

    public void printStatistics(boolean full) {
        if (integerCount > 0) {
            System.out.println("Целые числа: " + integerCount);
            if (full) {
                System.out.println("  Минимум: " + integerMin);
                System.out.println("  Максимум: " + integerMax);
                System.out.println("  Сумма: " + integerSum);
                System.out.println("  Среднее: " + (double) integerSum / integerCount);
            }
        }

        if (floatCount > 0) {
            System.out.println("Вещественные числа: " + floatCount);
            if (full) {
                System.out.println("  Минимум: " + floatMin);
                System.out.println("  Максимум: " + floatMax);
                System.out.println("  Сумма: " + floatSum);
                System.out.println("  Среднее: " + floatSum / floatCount);
            }
        }

        if (stringCount > 0) {
            System.out.println("Строки: " + stringCount);
            if (full) {
                System.out.println("  Минимальная длина: " + stringMinLength);
                System.out.println("  Максимальная длина: " + stringMaxLength);
            }
        }
    }
}