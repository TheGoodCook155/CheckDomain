package com.valid;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {


    private String fileName;
    private BufferedWriter writer;

    public Writer(String fileName) throws IOException {
        this.fileName = fileName;
        writer = new BufferedWriter(new FileWriter(this.fileName));
    }

    public void write(String str) throws IOException {
            writer.write(str);
            writer.write("\n");
            writer.flush();
    }

    public void closeWriter() throws IOException {
        writer.close();
    }


}
