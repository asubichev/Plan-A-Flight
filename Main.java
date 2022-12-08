import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.activity.InvalidActivityException;

public class Main {

    public static void main(String[] args) {
        // read data file
        // create adjacency linkedlist
        // read request file
        // ^^in any order
        // args[0] - flight data
        // args[1] - request data
        // args[2] - output file name
        Scanner scan = null;
        try {
            String ff = "test.txt"; // args[0]
            scan = new Scanner(new File(ff));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int numlines = scan.nextInt();
        scan.nextLine();
        ThaList list = new ThaList();
        for (int i = 0; i < numlines; i++) {
            String line = scan.nextLine();
            String[] breh = line.split("[|]");
            // Not sure if this is the proper exception to use
            if (breh.length != 4)
                throw new IllegalArgumentException("Incorrect input detected. Please check input file.");
            list.addPair(breh[0], breh[1], Double.parseDouble(breh[2]), Integer.parseInt(breh[3]));

        }
        System.out.println(list);
        String bee = list.findPath("Austin", "Houston", 1, true);
        System.out.println(bee);
        bee = list.findPath("Oklahoma City", "Philadelphia", 1, false);
        System.out.println(bee);
    }
}
