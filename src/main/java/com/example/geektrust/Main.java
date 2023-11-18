package com.example.geektrust;


import com.example.geektrust.Config.Config;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        Config config = Config.getConfigInstance();
        config.register();
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String inputLine = sc.nextLine();
                List<String> tokens = Arrays.stream(inputLine.split(" ")).collect(Collectors.toList());
                config.invoker(tokens);
            }
            sc.close(); // closes the scanner
        } catch (Exception e) {
            System.out.println("INVALID_FILE_FORMAT");
        }

    }
}
