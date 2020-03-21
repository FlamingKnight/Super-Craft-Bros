package com.galacticdiamond.magma.supercraftbros;

import com.google.common.io.Files;

import java.io.*;
import java.util.Objects;

public class ReadAndWriteFunctions {

    public void writeFile(File fileName, String toWrite) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for(int i = 0; i < toWrite.length(); i++) {
                fileWriter.write(toWrite.charAt(i));
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFolders(File sourceFolder, File destinationFolder) throws IOException {
        if(sourceFolder.isDirectory()) {
            if(!destinationFolder.exists()) {
                destinationFolder.mkdir();
            }
            for(String files : Objects.requireNonNull(sourceFolder.list())) {
                File srcFile = new File(sourceFolder, files);
                File destFile = new File(destinationFolder, files);

                copyFolders(srcFile, destFile);
            }
        } else {
            Files.copy(sourceFolder, destinationFolder);
        }
    }

    public String readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            StringBuilder builder = new StringBuilder();
            for(int ch; (ch = fileReader.read()) != -1;) {
                builder.appendCodePoint(ch);
            }
            String message = builder.toString();
            fileReader.close();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
