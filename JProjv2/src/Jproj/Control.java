/*******************************************************
 * Program description
 * Author: Andrei Negura
 * IDE: IntelliJ
 * Date: 01/04/2018
 *
 *
 *
 *
 *
 * */
package Jproj;

import java.util.ArrayList;

public class Control {

    public static void main(String[] args) {
        //Screen MainScreen = new Screen("Data Expert");
        FileHandle mySet = new FileHandle();
        ArrayList <String> el= new ArrayList<>();


        el = mySet.readByLine();
        int len = el.size();
        for (int i = 0;i<len;i++)
            System.out.println(el.get(i));
        //mySet.insert("12", "asd","me",
        //      "dit", "stuff", "22/02/2017", "22/03/2018", "200000000");
    }
}
