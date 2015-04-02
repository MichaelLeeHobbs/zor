package com.mlh;

import java.io.*;

class Main {

    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("Usage: fileName int(optional)");
            System.out.println("Example: java -jar zor.jar test.txt");
            System.out.println("Example: java -jar zor.jar test.txt 25");
            System.out.println("If optional int is used it must be used again to un-zor the file.");
            return;
        }
        int xorValue = 64;

        File file = new File(args[0]);

        if (args.length > 1) {
            xorValue = Integer.parseInt(args[1]);
        }
        int fileSize;
        if (file.length() < Integer.MAX_VALUE - 1) {
            fileSize = (int)file.length();
        } else {
            System.out.println("File to large!");
            return;
        }
        byte[] buffer = new byte[fileSize];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            // read fills buffer
            while((inputStream.read(buffer)) != -1) {}

            for (int i = 0; i < buffer.length; i++){
                buffer[i] ^= xorValue;
            }
        } catch (FileNotFoundException e) {
            System.out.println("While reading: File not found: '" + file.toString() + "'");
            return;
        } catch (IOException e) {
            System.out.println("While reading: Error reading file '" + file.toString() + "'");
            return;
        }

        try (FileOutputStream outputStream = new FileOutputStream(file.toString())) {
            for (byte aBuffer : buffer) {
                outputStream.write(aBuffer);
            }

        } catch (FileNotFoundException e) {
            System.out.println("While writing: File not found: '" + file.toString() + "'");
        } catch (IOException e) {
            System.out.println("While writing: Error writing file '" + file.toString() + "'");
        }

    }
}