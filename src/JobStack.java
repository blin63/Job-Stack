//Author: Reflow
//Version: 1.0.0
//Last updated: 2023-08-04

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class JobStack {
    public static void main(String[] args) throws Exception {
        //Variables to scan file from user
        String file;

        //Check if user put location of file in cmd line args
        if (args.length > 0) {
            file = args[0];
        } else {
            //else, prompt user to add location in program
            Scanner scan = new Scanner(System.in);

            //Prompt user to enter file location to be scanned
            System.out.println("Enter the location of the file to be read: ");
            file = scan.nextLine();

            //Close the scanner
            scan.close();
        }

        //Generate list items of jobs that have been applied to and skipped
        ArrayList<String[]> listItems = getListItems(file);
        printOutput(listItems);
    }

    //This method generates the list items from the file given from the scanner
    private static ArrayList<String[]> getListItems(String file) {
        ArrayList<String[]> listItems = new ArrayList<String[]>();
        final int ITEM_LIMIT = 3; // Number of cells in each list item

        try {
            File f = new File(file);
            Scanner scan = new Scanner(f);

            while (scan.hasNextLine()) {
                String data = scan.nextLine();

                if (!data.equalsIgnoreCase("")) {
                    String[] item = new String[ITEM_LIMIT];

                    item = data.split("-", ITEM_LIMIT);
                    listItems.add(item);
                }                
            }

            scan.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }

        return listItems;
    }

    private static void printOutput(ArrayList<String[]> listItems) {

        //Tally counters
        int appliedCounter = 0;
        int skippedCounter = 0;
        int jobCounter = 0;
        int appliedTotal = 0;
        int skippedTotal = 0;
        
        //List item max size constant
        final int MAX_LIST_ITEM_SIZE = 3;

        //Print output
        for (int i = 0; i < listItems.size(); i++) {
            //Check if current listItem is a job search section header
            if (listItems.get(i)[0].contains(":")) {

                //Print final tally if new job section found
                if (i != 0) {
                    System.out.println("\nNumber of jobs applied to: " + appliedCounter);
                    System.out.println("Number of jobs skipped: " + skippedCounter + "\n");

                    //Add applied and skipped counters to appropriate total counters
                    appliedTotal += appliedCounter;
                    skippedTotal += skippedCounter;
                }

                System.out.println(listItems.get(i)[0]);

                //Reset counter for next job search section
                appliedCounter = 0;
                skippedCounter = 0;

                //Header printed, get jobs in the current job search section
                continue;
            }

            System.out.println(listItems.get(i)[0] + " - " + listItems.get(i)[1]);

            //Increment applied counter if job has been applied to
            if (listItems.get(i).length == MAX_LIST_ITEM_SIZE && listItems.get(i)[2].contains("applied")) {
                appliedCounter++;
            }

            //Increment skipped counter if job has been skipped
            if (listItems.get(i).length == MAX_LIST_ITEM_SIZE && listItems.get(i)[2].contains("skipped")) {
                skippedCounter++;
            }

            //Increment the total number of jobs considered
            jobCounter++;
        }

        //Print and update final tally for final job search section
        System.out.println("\nNumber of jobs applied to: " + appliedCounter);
        System.out.println("Number of jobs skipped: " + skippedCounter);

        appliedTotal += appliedCounter;
        skippedTotal += skippedCounter;

        //Print total number of jobs from job stack
        System.out.println("\n\nStatistics from Job Stack: ");
        System.out.println("Total number of jobs considered: " + jobCounter);
        System.out.println("Total number of jobs applied to: " + appliedTotal);
        System.out.println("Total number of jobs skipped: " + skippedTotal + "\n");
    } 
}
