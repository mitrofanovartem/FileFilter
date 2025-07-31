package org.example;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {

            String outputPath = ".";
            String prefix = "";
            boolean appendMode = false;
            boolean shortStats = false;
            boolean fullStats = false;
            String[] inputFiles = {"in1.txt","in2.txt"};

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-o":
                        if (i + 1 < args.length) {
                            outputPath = args[++i];
                        } else {
                            System.err.println("Ошибка: для -o требуется указать путь");
                            return;
                        }
                        break;
                    case "-p":
                        if (i + 1 < args.length) {
                            prefix = args[++i];
                        } else {
                            System.err.println("Ошибка: для -p требуется указать префикс");
                            return;
                        }
                        break;
                    case "-a":
                        appendMode = true;
                        break;
                    case "-s":
                        shortStats = true;
                        break;
                    case "-f":
                        fullStats = true;
                        break;
                    default:
                        inputFiles = new String[args.length - i];
                        System.arraycopy(args, i, inputFiles, 0, args.length - i);
                        i = args.length;
                        break;
                }
            }

            if (inputFiles == null || inputFiles.length == 0) {
                System.err.println("Ошибка: не указаны входные файлы");
                return;
            }

            FileProcessor processor = new FileProcessor(outputPath, prefix, appendMode);
            Statistics stats = processor.processFiles(inputFiles);

            if (shortStats || fullStats) {
                stats.printStatistics(fullStats);
            }

        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}