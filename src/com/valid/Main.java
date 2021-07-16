package com.valid;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    //TODO find the percentage of domains that does not exist

    static Writer writer;
    private Path path1;
    static {
        try {
            writer = new Writer("invalidDomains.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NotFoundException, IOException {


        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean stopProgram = false;
        while (stopProgram == false) {

            System.out.println(" Choose an option: \n" +
                    " \n" +
                    "1: Check domains manually \n" +
                    "2: Check domains from txt file \n" +
                    "3: Quit");

            input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println("Enter the domain e.g. protonmail.com, google.com, yahoo.com, gmail.com");
                    input = scanner.nextLine();
                    if (input.matches("[a-zA-Z-]+.[a-zA-z]{3}")) {
                        manualCheck(input);
                    } else {
                        System.out.println("");
                        System.out.println("Invalid domain...See example! \n");
                        System.out.println("Enter domain in the following form - protonmail.com, google.com, yahoo.com, gmail.com");
                        System.out.println("");
                    }
                    break;
                case "2":
                    System.out.println("Enter the path (e.g C:\\Users\\Pc\\Desktop\\dummy.txt): ");
                    input = scanner.nextLine();
                    checkDomainsFromTxt(input);
//                    System.out.println("DONE!\n");

                    break;
                case "3":
                    stopProgram = true;
                    break;


            }

        }

    }

    private static void checkDomainsFromTxt(String path) throws IOException, NotFoundException {



        if (path.matches("[A-Z]:\\\\[A-Za-z0-9\\\\]{0,200}.txt")) {

            Path path1 = FileSystems.getDefault().getPath(path).toAbsolutePath();
            File file = new File(String.valueOf(path1));
            if (path1.getFileName() == null || file.exists() == false) {
                System.out.println("File can't be found, check the path again and/or file name!");
            } else {
                System.out.println("File found\n");

                System.out.println("Domains should be written one domain per line. e.g: \n" +
                        "www.liam@protonmail.com\n" +
                        "www.noahgmail.com\n" +
                        "oliver@gmail.com\n" +
                        "www.elijah@something.com\n" +
                        "william@protonmail.com\n");

                //TODO check if the user wants to pass regex

                Reader reader = new Reader(file.toString());

                List<String> read = reader.readContent();
                checkHost(read);
                System.out.println("End " + LocalTime.now());
                writer.closeWriter();


            }


        } else {
            System.out.println("Invalid path and/or file!!! Restarting.");
        }

    }

    private static void manualCheck(String input) {
            //todo DONE


        try {
            InetAddress inetHost = InetAddress.getByName(input);

            String test = inetHost.getHostName();

            System.out.println("The host name was: " + test);
            System.out.println("The hosts IP address is: " + inetHost.getHostAddress());
            System.out.println("");

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void checkHost(List<String> list) throws NotFoundException, IOException {
        System.out.println("Start " + LocalTime.now());

        String test = "";
        for (int i = 0; i < list.size(); i++) {

            String getElement = list.get(i);
            if (getElement.contains("@")) {
                test = getElement.replaceFirst("^[a-zA-Z]*@"," ").strip();
            }else{
                test = getElement.replaceFirst("^[http://]*[www.]{4}"," ").strip();
            }



            String hostName = "";
            try {

                InetAddress inetHost = InetAddress.getByName(test);
                hostName = inetHost.getHostName();

                System.out.println("The host name was: " + hostName);
                System.out.println("The hosts IP address is: " + inetHost.getHostAddress());

            } catch (UnknownHostException ex) {
                String writeErr = ex.getMessage();
                writer.write(writeErr);

            }
        }
    }


}
