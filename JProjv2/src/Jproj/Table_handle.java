package Jproj;

//Imports
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class Table_handle {


    //Attributes************************
    private String path;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedS;
    private ResultSet result;

    //Constructors**************************
    public Table_handle(){
        String fileName = "table.db";

        /*
         * Copyed code
         *   Path currentRelativePath = Paths.get("");
         *   String path = currentRelativePath.toAbsolutePath().toString();
         *
         * Source: https://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
         * */

        Path currentRelativePath = Paths.get("");
        String currentDir = currentRelativePath.toAbsolutePath().toString();
        this.path = "jdbc:sqlite:" + currentDir +"\\" +  fileName;
        statement = null;
        this.connect();
    }

    /*
    * copyed code
    *
    * Source: http://www.sqlitetutorial.net/sqlite-java/
    *
    * */
    //Database connection***********************

    //Closes database
    public void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //connect
    private void connect() {
        try {
            this.connection = DriverManager.getConnection(path);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Create and execute a statement
    private void command(String querry) {
        try {
            statement = connection.createStatement();
            statement.execute(querry);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Returns data
    public String[][] getData(){

        String sql = "Select * from investments;";
        try{
            String [][] selectAll = new String[5000][8];

            this.command(sql);
            result = statement.executeQuery(sql);

                // loop through the result set
            int i=0;
            while (result.next()) {
                selectAll[i][0] =  result.getString("proposal_id");
                selectAll[i][1] =  result.getString("programme_name");
                selectAll[i][2] =  result.getString("lead_applicant");
                selectAll[i][3] =  result.getString("research_body");
                selectAll[i][4] =  result.getString("title_of_research");
                selectAll[i][5] =  result.getString("start_date");
                selectAll[i][6] =  result.getString("end_date");
                selectAll[i][7] =  result.getString("commitment");
                i++;
            }
            return selectAll;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Querries for the database*********************
    //Insert into database
    public void insert(){
        String sql = "insert into investments (proposal_id, programme_name, lead_applicant," +
                " research_body, title_of_research, start_date, end_date,commitment)" +
                " values(?,?,?,?,?,?,?,?)";
        FileHandle f = new FileHandle();
        ArrayList <String> set= f.stringToArray(f.readByCharacter());
        int len = set.size();

        try {
            preparedS = connection.prepareStatement(sql);
            for (int i = 0 ; i < len; i +=8) {
                preparedS.setString(1, set.get(i).trim());
                preparedS.setString(2, set.get(i +1).trim());
                preparedS.setString(3, set.get(i +2).trim());
                preparedS.setString(4, set.get(i +3).trim());
                preparedS.setString(5, set.get(i +4).trim());
                preparedS.setString(6, set.get(i +5).trim());
                preparedS.setString(7, set.get(i +6).trim());
                preparedS.setString(8, set.get(i +7).trim());
                preparedS.executeUpdate();
            }
            connection.commit();
        }

        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Create a table if it does not exist
    public void createTable() {
        //Structure of my table
        String crTable = "Create table investments("
                + "proposal_id TEXT ,"
                + "programme_name TEXT not null,"
                + "lead_applicant TEXT not null, "
                + "research_body TEXT not null,"
                + "title_of_research TEXT not null,"
                + "start_date text not null,"
                + "end_date text not null,"
                + "commitment INTEGER not null);";
        this.command(crTable);
    }

    //Gets the smallest value in the database
    public void dropTable(){
        String drop = "Drop table investments;";
        this.command(drop);
    }

    public void getMin(){
        String s = "select min(commitment) from investments;";
        this.command(s);
    }

    //Gets the biggest value in the database
    public void getMax(){
        String s = "select max(commitment) from investments;";

        this.command(s);
    }

    //Gets the total fund for every college
    public void getCollegeSum(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    "group by research_body";

        this.command(s);
    }

    //Gets the college that was funded the most
    public void getMaxCollege(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    //"group by research_body" +
                    "having max(sum(committment))";

        this.command(s);
    }

    //Gets the college that was funded the least
    public void getMinCollege(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    //"group by research_body" +
                    "having min(sum(committment))";

        this.command(s);
    }

    //puts everything somewhere...
    public void hardCodedValues(){

        String minimum = "The smallest value is ";
        String maximum = "The biggest value is ";
    }

    public String countAll(){
        String count = "select count(*) \"number of rows \" from investments ";
        this.command(count);
        try {
            return result.getString("number of rows");
        }
        catch(SQLException e)
        {
            e.getErrorCode();
        }
        return "";
    }
}
