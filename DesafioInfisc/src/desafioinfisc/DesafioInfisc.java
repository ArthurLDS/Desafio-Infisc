/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc;

import desafioinfisc.Service.ClienteService;
import desafioinfisc.Service.FuncionarioService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Arthur
 */
public class DesafioInfisc {
    
    public static void main(String[] args) {
       getFileLines().forEach(System.out::println);
    } 
    
    public static List<String> getFileLines(){
        List<String> lines = new ArrayList<>();    
        try{
            Stream<String> stream = Files.lines(Paths.get("C:/Users/Arthur/Downloads/dados.txt"));
            stream.forEach(line -> lines.add(line));
        } catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }
    
    
    
}
