package project5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

/**
 * This class creates an oject which represents a single collision
 *
 * @param ArrayList<String>
 *
 * @author Summer Li
 */

public class Collision implements Comparable<Collision> {
    public String zip,key;
    public Date date;
    public int PersonsInjured,PedestriansInjured,CyclistInjured,MotoristsInjured,PersonsKilled,PedestriansKilled,CyclistKilled,MotoristsKilled;

    /**
     * Taking the arraylist entries to create a Collision object.
     * 
     * @param ArrayList<String>  the entries in the arraylist
     * 
     */
    public Collision(ArrayList<String> entries) throws IllegalArgumentException {
        // creating the Collision object with corresponding input data
        if(entries.get(0).validDate()== false){
            throw new IllegalArgumentException("Invalid Date Format");
        }
        this.date = new Date(entries.get(0));
        // check if the zip entry are 5 digits
        if(entries.get(3).validZip()== false){
            throw new IllegalArgumentException("Invalid Zip Format");
        }
        this.zip = entries.get(3);
        // the unique key has to be a non-empty string
        if(entries.get(23).validKey()== false){
            throw new IllegalArgumentException("Invalid Key Format");
        }
        this.key = entries.get(23);
        
        this.PersonsInjured = getNumber(entries.get(10));
        this.PersonsKilled = getNumber(entries.get(11));
        this.PedestriansInjured = getNumber(entries.get(12));
        this.PedestriansKilled = getNumber(entries.get(13));
        this.CyclistInjured = getNumber(entries.get(14));
        this.CyclistKilled = getNumber(entries.get(15));
        this.MotoristsInjured = getNumber(entries.get(16));
        this.MotoristsKilled = getNumber(entries.get(17));
    }

    /**
    * Validate the date entry from the input line
    * @return boolean true if the Date format is valid
    */
    private boolean validDate(String strDate){
        if(strDate == null){
            return false;
        }
        if(!(strDate instanceof Date)){
            return false;
        }
        return true;

    }

    /**
    * Validate the zip entry from the input line
    * @return boolean true if the Zip format is valid
    */
    private boolean validZip(String strZip){
        String regex = "\\d+";
        if(strZip.length()!=5){
            return false;
        }
        if(!strZip.matches(regex)){
            return false;
        }
        return true;
    }

    /**
    * Validate the key entry from the input line
    * @return boolean true if the Key format is valid
    */
    private boolean validKey(String strKey){
        if(strKey.equals("")){
            return false;
        }
        return true;
    }
    /**
     * Get the number of the string entries if the number is non-negative.
     * 
     * @param String the string contains the number
     * 
     */
    private int getNumber(String strNumber) throws NumberFormatException{
        int result = Integer.parseInt(strNumber);
        if(result >= 0){
            return result;
        }else {
            throw new IllegalArgumentException("Injured/Killed should be non-negative");
        }

    }

    /**
     * Get the zip of the string entries.
     * @return String zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Get the key of the string entries.
     * @return String key
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the date of the string entries.
     * @return Date the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the number of persons injured.
     * @return int the number of persons injured.
     */
    public int getPersonsInjured() {
        return PersonsInjured;
    }

    /**
     * Get the number of pedestrians injured.
     * @return int the number of pedestrains injured.
     */
    public int getPedestriansInjured() {
        return PedestriansInjured;
    }

    /**
     * Get the number of cyclists injured.
     * @return int the number of cyclists injured.
     */
    public int getCyclistInjured() {
        return CyclistInjured;
    }

    /**
     * Get the number of Motorists injured.
     * @return int the number of Motorists injured.
     */
    public int getMotoristsInjured() {
        return MotoristsInjured;
    }

    /**
     * Get the number of persons killed.
     * @return int the number of persons killed.
     */
    public int getPersonsKilled() {
        return PersonsKilled;
    }

    /**
     * Get the number of pedestrians killed.
     * @return int the number of pedestrains killed.
     */
    public int getPedestriansKilled() {
        return PedestriansKilled;
    }

    /**
     * Get the number of cyclists killed.
     * @return int the number of cyclists killed.
     */
    public int getCyclistKilled() {
        return CyclistKilled;
    }

    /**
     * Get the number of Motorists killed.
     * @return int the number of Motorists killed.
     */
    public int getMotoristsKilled() {
        return MotoristsKilled;
    }

    /**
     * Compare two Collision objects based on zip codes, dates and unique keys (in this order).
     * @return int the result of the comparison.
     */
    @Override
    public int compareTo(Collision o) {
        if(this.zip.compareTo(o.getZip()) == 0){
            if(this.date.compareTo(o.getDate()) == 0){
                return this.key.compareTo(o.getKey());
            }
            return this.date.compareTo(o.getDate());
        }
        return this.zip.compareTo(o.getZip());
    }
    /**
     * Check if two objects are equal.
     * @return boolean true if equal, false if not equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Collision))
            return false;
        final Collision other = (Collision) obj;
        if(!this.zip.equals(other.getKey())){
            return false;
        }else if(!this.key.equals(other.getKey())){
            return false;
        }else if(!this.date.equals(other.getDate())){
            return false;
        }
        return true;
    }
}
