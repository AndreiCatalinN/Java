package project.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Table_handle {


    private String path;
    private Connection c;
    private Statement s;
    //Constructors
    public Table_handle(){
        c = null;
        s = null;
        path = "jdbc:sqlite:F:\\College\\OOP\\Java\\Java Project\\database.db";
        create_table();
    }

    //Others
    //Create a table if it does not exist
    private void create_table(){
        //Structure of my table
        String crTable = "Create table is not exists investments(\n"
                + "proposal_id varchar2(20) PRIMARY KEY,\n"
                + "programme_name varchar2(100),\n"
                + "lead_applicant varchar2(100),\n"
                + "research_body varchar2(100),\n"
                + "title_of_research varchar2(200),\n"
                + "start_date date,\n"
                + "end_date date,\n"
                + "commitment number(9)" + ");";

        //Create connection and execute statement

        this.connect();
        this.command(crTable);
    }
    //Create a connection
    private Connection connect() {
        try {
            c = DriverManager.getConnection(path);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    //Create and execute a statement
    private Statement command(String querry) {
        try {
            this.connect();
            s = c.createStatement();
            s.execute(querry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
    private void insert(String proposalID, String progName, String leadApplicant,
                        String researchBody, String titleOfResearch, String startDate,
                        String endDate, String commitment){

    }
    //Populates the table
    public void populate_table(){

    }
}
