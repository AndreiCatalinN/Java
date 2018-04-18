/*
* Class description:
* Reads from file Open-Data-to-30-11-2016-for-Publication-v2.csv
* Cleans elements of starting and ending white spaces
* Cleans quotes from elements
* */

package Jproj;

//Imports
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class FileHandle {
    //Attributes
    private String fileName;
    private FileInputStream fi;

    //Constructor
    public FileHandle(){
        fileName = "Open-Data-to-30-11-2016-for-Publication-v2.csv";
    }

    //Opens the file
    private void fileOpen(){
        try{
            fi = new FileInputStream(fileName);
        }
        catch(FileNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    //writes to a temporary file from with i do another read
    public String readByCharacter(){
        char temp;
        StringBuilder build = new StringBuilder();

        this.fileOpen();
        try{
            //Cleans the commas from fields with quotes
            //Ex if i have "Andrei, here it is" it becomes "Andrei here it is"
            while (fi.available() > 0) {
                temp = (char) fi.read();
                build.append(temp);
                if(temp == '\"')
                    do{
                        temp = (char) fi.read();
                        if(temp == ','){
                            temp = (char) fi.read();
                        }
                        build.append(temp);
                    }while(temp != '\"');
                if(temp == '\n') {
                    temp = ',';
                    build.append(temp);
                }
            }

            //writes to a temporary file
            fi.close();
            return build.toString();

        }
        catch (IOException e){
            e.fillInStackTrace();
            return "A problem was encountered while reading the file";
        }
    }

    //Reads the file line by line
    public ArrayList <String> stringToArray(String temp){
        ArrayList <String> re = new ArrayList<>();
        int len = temp.length();
        //remove quotes
        for (int i =0 ; i< len;i++) {
            char ch = temp.charAt ( i );
            if(ch == '\"') {
                temp = temp.substring(0, i) + temp.substring(i + 1);
                len--;
            }
        }
        //Split the string into smaller strings that are put in a arrayList
        Collections.addAll(re, temp.split(","));
        return re;
    }
}