package com.k381;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Никита\\Projects\\m43\\src\\main\\java\\com\\k381\\Text.txt");

        //fileExpereance(file);

    }

    private static void fileExpereance(File file) {
        if(file.exists()) {
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                System.out.println(file.getCanonicalPath());

                System.out.println(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Arrays.asList(file.getParentFile().list(
                    ((dir, name) -> !name.endsWith(".txt"))
            )).stream().forEach(System.out::println);
            System.out.println(file.delete());
        }else{
            System.out.println("File not found((");
            try{
                System.out.println(file.createNewFile());
                System.out.println(file.getFreeSpace()/(1024*1024));
                System.out.println(file.getTotalSpace()/(1024*1024));
                System.out.println(file.getUsableSpace()/(1024*1024));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
