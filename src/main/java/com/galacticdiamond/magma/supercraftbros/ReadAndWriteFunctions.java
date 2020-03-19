package com.galacticdiamond.magma.supercraftbros;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import com.galacticdiamond.magma.supercraftbros.MagmaCore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

public class ReadAndWriteFunctions {

    public void writeFile(String fileName, String toWrite) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(" ");
            for(int i = 0; i < toWrite.length(); i++) {
                fileWriter.write(toWrite.charAt(i));
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            StringBuilder builder = new StringBuilder();
            for(int ch; (ch = fileReader.read()) != -1;) {
                builder.appendCodePoint(ch);
            }
            String message = builder.toString();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
