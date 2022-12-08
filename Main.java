import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // read data file
        // create adjacency linkedlist
        // read request file
        // ^^in any order
        
        // args = new String[3];
        // args[0] = "flightdata.txt";
        // args[1] = "request.txt";
        // args[2] = "output.txt";


        // Create linked list-----------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------
        Scanner scan = null;
        try {
            String ff = args[0]; // "flightdata.txt"
            scan = new Scanner(new File(ff));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int numlines = scan.nextInt();
        scan.nextLine();
        ThaList list = new ThaList();
        for (int i = 0; i < numlines; i++) {
            String line = scan.nextLine();
            String[] data = line.split("[|]");
            // Not sure if this is the proper exception to use
            if (data.length != 4)
                throw new IllegalArgumentException("Incorrect input detected. Please check flight data file.");
            list.addPair(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]));

        }
        // Get possible paths------------------------------------------------------------------
        // ------------------------------------------------------------------------------------
        try {
            String ff = args[1]; // "request.txt"
            scan = new Scanner(new File(ff));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        numlines = scan.nextInt();
        scan.nextLine();
        String toFile = "";
        for (int i = 0; i < numlines; i++) {
            String line = scan.nextLine();
            String[] data = line.split("[|]");
            if (data.length != 3)
                throw new IllegalArgumentException("Incorrect input detected. Please check flight reqeust file.");
            boolean flag = false;
            if (data[2].equals("C"))
                flag = true;
            String toStdOut = list.findPath(data[0], data[1], i, flag);
            toFile += toStdOut + "\n";
        }
        // Write paths to file-----------------------------------------------------------------
        // ------------------------------------------------------------------------------------
        try {
            Files.write(Paths.get(args[2]), toFile.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
