package Jproj;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Table_handle {
    //Attributes************************
    private String path;
    private Connection c;
    private Statement s;
    private ResultSet result;

    //Constructors**************************
    public Table_handle(){
        c = null;
        s = null;
        result = null;

        path = "jdbc:sqlite:F:\\College\\OOP\\Java\\Java Project\\database.db";
        create_table();
    }

    //Database connection***********************
    //Connects to database
    private Connection connect() {
        try {
            c = DriverManager.getConnection(path);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    //Create and execute a statement
    private void command(String querry) {
        ResultSet r;
        try {
            this.connect();
            s = c.createStatement();
            result = s.executeQuery(querry);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    //Returns data
    public void getData(){
        this.connect();

        try{
            while(result.next()){

            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Querries for the database*********************

    //Create a table if it does not exist
    private void create_table(){
        //Structure of my table
        String crTable = "Create table if not exists investments(\n"
                + "proposal_id varchar2(20) PRIMARY KEY,\n"
                + "programme_name varchar2(100),\n"
                + "lead_applicant varchar2(100),\n"
                + "research_body varchar2(200),\n"
                + "title_of_research varchar2(300),\n"
                + "start_date date,\n"
                + "end_date date,\n"
                + "commitment number(9)"
                + ");";

        //Create connection and execute statement
        this.connect();
        this.command(crTable);
    }

    public void populateTable(){

    }
    //Inserts data into database
    public void insert(String proposalID, String progName, String leadApplicant,
                       String researchBody, String titleOfResearch, String startDate,
                       String endDate, String commitment){

        String sqlInsert = "insert into investments values(\n"
                + "\'" + proposalID + "\'" + ",\n"
                + "\'" + progName + "\'" + ",\n"
                + "\'" + leadApplicant + "\'" + ",\n"
                + "\'" + researchBody + "\'" + ",\n"
                + "\'" + titleOfResearch + "\'" + ",\n"
                + "\'" + startDate + "\'" + ",\n"
                + "\'" + endDate + "\'" + ",\n"
                + commitment + ");";
        this.connect();
        this.command(sqlInsert);
    }

    //Gets the smallest value in the database
    public void getMin(){
        String s = "select min(commitment) from investments;";
        this.connect();
        this.command(s);
    }
    //Gets the biggest value in the database
    public void getMax(){
        String s = "select max(commitment) from investments;";
        this.connect();
        this.command(s);
    }
    //Gets the total fund for every college
    public void getCollegeSum(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    "group by research_body";
        this.connect();
        this.command(s);
    }

    //Gets the college that was funded the most
    public void getMaxCollege(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    //"group by research_body" +
                    "having max(sum(committment))";
        this.connect();
        this.command(s);
    }

    //Gets the college that was funded the least
    public void getMinCollege(){
        String s =  "select research_body ,sum(commitment) " +
                    "from investments " +
                    //"group by research_body" +
                    "having min(sum(committment))";
        this.connect();
        this.command(s);
    }

    //puts everything somewhere...
    public void hardCodedValues(){
        String minimum = "The smallest value is ";
        String maximum = "The biggest value is ";
    }

    public void createView(){

    }
}
