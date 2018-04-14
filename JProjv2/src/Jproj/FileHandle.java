/*
* Class description:
* Reads from file Open-Data-to-30-11-2016-for-Publication-v2.csv
* Cleans elements of starting and ending white spaces
* Cleans quotes from elements
* */

package Jproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileHandle {

    private String fileName;
    private Scanner readF;

    //Constructor
    public FileHandle(){
        fileName = "Open-Data-to-30-11-2016-for-Publication-v2.csv";
        this.fileOpen();

        readF.close();
    }

    //Opens the file
    private void fileOpen(){
        try{
            readF = new Scanner(new File(fileName), "iso-8859-1");
        }
        catch(FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    //Reads the file line by line
    public ArrayList<String> readByLine(){
        String rec;

        this.fileOpen();
        ArrayList <String> re = new ArrayList<>();

        while (readF.hasNextLine())
        {
            rec = readF.nextLine();
            Collections.addAll(re, rec.split(","));
        }

        for(int i = 0 ;i<re.size();i++) {
            if (re.get(i).contains("\"")) {

                re.set(i, re.get(i) + re.get(i + 1));
                //For numbers like "8,123,123"
                if(re.get(i+2).contains("\"")) {
                    re.set(i, re.get(i) + re.get(i + 2));
                    re.remove(i+2);
                }
                re.remove(i + 1);
                re.set(i, this.clean(re.get(i)));
            }
            //System.out.println(re.get(i));
        }

        readF.close();
        return re;
    }

    //Returns the element after it deletes
    //the white spaces from the beginning and end
    //and deletes the quotes
    private String clean(String element){
        return element.replace("\"", "").trim();
    }

    //first attempt of reading the file, does not work
    // uses the readdata function
    private void readByElement(){
        //Nested declaration so i don't have to use any variables
        //I want to read from file Open-data
        this.fileOpen();
        String PID= "", name= "", mainResearcher= "", school= "";
        String title= "", startDate= "", endDate= "", Commitment= "";

        readF.useDelimiter(",");

        while( readF.hasNext() )
        {
            PID = readF.next();
            name= readF.next();
            mainResearcher= readF.next();
            school= readData();
            title= readData();
            startDate= readF.next();
            endDate= readF.next();
            Commitment= readData();
        }
        readF.close();
    }

    //Reads until it encounters a column
    //Reads 2 fields if it encounters a quote(")
    // Ex: "543,123" is read as one "field"
    private String readData(){
        String el = readF.next();

        if( el.contains("\"")) {
            el += readF.next();
        }
        return el;
    }
}
