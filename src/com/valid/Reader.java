package com.valid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private String fileName;

    public Reader(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public List<String> readContent() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        Scanner read = new Scanner(new BufferedReader(new FileReader(fileName)));

        while (read.hasNextLine()){
            String line = read.nextLine();
            list.add(line);
        }
        return list;
    }




}
