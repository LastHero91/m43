package com.k381;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {
        randomAccessFile();
    }

    private static void randomAccessFile() {
        File file = new File("C:\\Users\\Никита\\Projects\\m43\\src\\main\\java\\com\\k381\\Text.txt");
        fileExpereance(file);

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file.getPath(), "rw")) {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                randomAccessFile.setLength(0);
                randomAccessFile.seek(0);
            }
            randomAccessFile.writeUTF(String.valueOf(file.getCanonicalFile().getName()) + ";\n");
            randomAccessFile.writeInt(1420);

            randomAccessFile.seek(0);
            System.out.println(randomAccessFile.readUTF());
            randomAccessFile.seek(randomAccessFile.length() - 4);
            System.out.println(randomAccessFile.readInt());

        } catch (IOException e) {
            log(e.getStackTrace(), e.getMessage());
        }
    }

    //Печать лога
    private static void log(StackTraceElement printStackTrace[], String message) {
        logger.info(message);
        for (StackTraceElement stackTraceElement : printStackTrace) {
            logger.log(Level.SEVERE, stackTraceElement.toString());
        }
    }


    //переписано с лекций
    private static void fileExpereance(File file) {
        if (file.exists()) {
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
        } else {
            System.out.println("File not found((");
            try {
                System.out.println(file.createNewFile());
                System.out.println(file.getFreeSpace() / (1024 * 1024));
                System.out.println(file.getTotalSpace() / (1024 * 1024));
                System.out.println(file.getUsableSpace() / (1024 * 1024));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
