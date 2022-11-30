import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) 
    {
        // read data file
        // create adjacency linkedlist
        // read request file
        // ^^in any order
        //args[0] - flight data
        //args[1] - request data
        //args[2] - output file name
        Scanner scan = null;
        try {
            String ff = "test.txt";//args[0]
            scan = new Scanner(new File(ff));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int numlines = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < numlines; i++)
        {
            String line = scan.nextLine();
            String[] breh = line.split("[|]");
            String origin = breh[0];
            String destination = breh[1];
            int cost = Integer.parseInt(breh[2]);
            int duration = Integer.parseInt(breh[3]);
            
        }
    }
}