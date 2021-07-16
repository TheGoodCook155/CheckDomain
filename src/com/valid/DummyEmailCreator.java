package com.valid;

import java.io.*;
import java.util.*;

public class DummyEmailCreator {

    public static void main(String[] args) throws IOException {
        String domain = "";

        BufferedWriter writer = new BufferedWriter(new FileWriter("dummy.txt"));
        Scanner read = new Scanner(new BufferedReader(new FileReader("testFiles\\names.txt")));


        while (read.hasNext()) {
            Random random = new Random();


            int index = random.nextInt(3) ;
            int index1 = random.nextInt(3);

            System.out.println("Index is " + index);


            String line = read.nextLine();
//            String[] arr = line.split(" ");
//            System.out.println(Arrays.toString(arr));



                domain = generateDomain(line);
//                System.out.println("Domain is " + domain);

            String save = line.toLowerCase(Locale.ROOT) + domain;
//            System.out.println("Save is " + save);

            writer.write(save);
            writer.write("\n");
            writer.flush();

        }

        read.close();
        writer.close();

    }


    //find first and last names

    //generate valid email

    //4 000 000 emails

    public static String generateDomain(String name) {
        String add = "@"+name.toLowerCase(Locale.ROOT)+".com";
        List<String> arr = Arrays.asList(
                "@gmail.com", "@yahoo.com", "@protonmail.com", "@outlook.com",add
        );


        Random random = new Random();
        int index = random.nextInt(arr.size());
        return arr.get(index);
    }


}
