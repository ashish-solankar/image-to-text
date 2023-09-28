package com.imagetotext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class TextFinder {

    private static final String defaultOutDir = Paths.get("").normalize().toAbsolutePath().toString();

    public static String findText(String inputFileWithAbsolutePath){
        return findText(inputFileWithAbsolutePath, null);
    }

    public static String findText(String inputFileWithAbsolutePath, String outPutDirWithAbsolutePath){
        if(outPutDirWithAbsolutePath == null){
            outPutDirWithAbsolutePath = defaultOutDir;
        }
        String returnString = "";
        try{
            
            File inputFile = new File(inputFileWithAbsolutePath);
            final String outputFilePath = Paths.get(defaultOutDir,inputFile.getName()).toString();
            executeBashCommand("tesseract "+ inputFileWithAbsolutePath + " " + outputFilePath);
            FileReader fileReader = new FileReader(outputFilePath + ".txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n"); // Add a newline character to separate lines
            }

            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();

            // Get the entire content as a single string
            returnString = stringBuilder.toString();



        }catch(Exception exception){
            exception.printStackTrace();
        }
        return returnString;
    }

    private static boolean executeBashCommand(String command) {
        boolean success = false;
        System.out.println("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        // Use bash -c so we can handle things like multi commands separated by ; and
        // things like quotes, $, |, and \. My tests show that command comes as
        // one argument to bash, so we do not need to quote it to make it one thing.
        // Also, exec may object if it does not have an executable file as the first thing,
        // so having bash here makes it happy provided bash is installed and in path.
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);
    
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
    
            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }
    
            b.close();
            success = true;
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }
    
}
