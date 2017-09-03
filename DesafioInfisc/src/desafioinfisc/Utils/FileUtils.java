/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author Arthur
 */
public class FileUtils {
    
    public static List<String> getFileLines(String path){
        List<String> lines = new ArrayList<>();    
        try{
            Stream<String> stream = Files.lines(Paths.get(path));
            stream.forEach(line -> lines.add(line));
        } catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }
}
