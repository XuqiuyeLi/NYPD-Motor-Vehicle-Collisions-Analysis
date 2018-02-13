package project5;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * This class is the runnable program containing the main method.
 * This class is responsible for parsing the command line argument,
 * reading the input file, creating the CollisionsData object based on the input file,
 * interacting with user, and producing the output.
 *
 *
 * @author Summer Li
 */

public class CollisionInfo {

    public static void main(String[] args) {
        // instantiate a new CollisionsData AVL tree object
        CollisionsData collisionsData = new CollisionsData();

        if(args.length <= 0 ){
            System.err.println("Error: missing name of the input file");
            System.exit(0);
        // read the file by using splitCSVLine method provided
        }else {
            try{
                for (String line : readFile(args[0])){
                ArrayList<String > entries = splitCSVLine(line);
                // only read the lines which have more than 24 entries
                if(entries.size() >= 24){
                    collisionsData.add(new Collision(entries));
                }else{
                    line = reader.readLine();
                } 
                }
            }
            catch (IOException e){
                    System.err.println("The file " + fileName
                        + " could not be opened");
                    System.exit(1);
            }
        }
        // prompt for inputs from users
        input = new Scanner(System.in);
        while(true){
            System.out.println("Enter a zip code (\"quit\" to exit):");
            String zip = input.nextLine();
            if(zip.equalsIgnoreCase("quit")){
                System.exit(2);
            }
            if(!zip.validZip()){
                System.out.println("Invalid zip code. Try again.");
            }
            System.out.prinln("Enter start date (MM/DD/YYYY):");
            Date dateBegin = input.nextLine();
            System.out.prinln("Enter end date (MM/DD/YYYY):");
            Date dateEnd = input.nextLine();
            if (!(startDate instanceof Date)|| !(endDate instanceof Date)){
                System.out.println("Invalid date format. Try again.");
            }
            if(dateBegin.compareTo(dateEnd)>0){
                System.out.println("Start date should be earlier than end date. Try again.");
            }
            String report = getReport(zip, dateBegin, dateEnd);
            System.out.println(report);

        }
        
    }

    /**
     * Read the file line by line.
     * 
     * 
     * @param fileName  the name of the file to be read
     * @return an Arraylist object containing all text lines in that file.
     */
    public static ArrayList<String > readFile(String fileName){
        ArrayList<String > fileLines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = reader.readLine();
            while (line!=null){
                fileLines.add(line);
                line = reader.readLine();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    /**
     * Splits the given line of a CSV file according to commas and double quotes
     * (double quotes are used to surround multi-word entries so that they may contain commas)
     * 
     * @param textLine  a line of text to be passed
     * @return an Arraylist object containing all individual entries found on that line
     */

    public static ArrayList<String> splitCSVLine(String textLine){

        ArrayList<String> entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes = false;
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) {
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

                // change insideQuotes flag when nextChar is a quote
                if (insideQuotes) {
                    insideQuotes = false;
                    insideEntry = false;
                }else {
                    insideQuotes = true;
                    insideEntry = true;
                }
            } else if (Character.isWhitespace(nextChar)) {
                if ( insideQuotes || insideEntry ) {
                    // add it to the current entry
                    nextWord.append( nextChar );
                }else { // skip all spaces between entries
                    continue;
                }
            } else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false;
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer();
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar);
                insideEntry = true;
            }

        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }

        return entries;
    }

}
