package Jproj;

//Imports
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.CardLayout;

public class Screen extends JFrame implements ActionListener {
    private JMenuBar bar;
    private JMenu database, aboutMenu;
    private JMenuItem load, show, intOwnParams, about, interestingFacts, dropTable;
    private JTable text;

    private String [] header;
    private Table_handle myTable;

    public Screen(String title){
        super(title);
        setLayout(new CardLayout());
        myTable = new Table_handle();

        setSize(1200, 800);

        //Declare bar
        this.bar = new JMenuBar();
        setJMenuBar(bar);

        //Declare Menu's
        aboutMenu = addMenu("About", bar);
        database = addMenu("Database", bar);

        //Declare all the menu items
        load = addMenuItem( "Load", database);
        show = addMenuItem( "Show", database);
        intOwnParams =addMenuItem( "Search", database);
        interestingFacts = addMenuItem("Interesting Facts", database);
        dropTable = addMenuItem("Drop Table", database);
        about = addMenuItem( "About", aboutMenu);


        header = new String[]{"Proposal_id", "Programme name", "Lead Applicant",
                "College", "Title of Research", "Start Date", "End Date", "Commitment"};



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    private JMenuItem addMenuItem( String name, JMenu parent){
        JMenuItem tmp = new JMenuItem(name);
        parent.add(tmp);
        tmp.addActionListener(this);
        return tmp;
    }

    private JMenu addMenu( String name, JMenuBar parent){
        JMenu tmp = new JMenu(name);
        parent.add(tmp);
        tmp.addActionListener(this);
        return tmp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == about){
            JOptionPane.showMessageDialog(this,
                    "Author: Andrei Negura\n" +
                    "Student code: C16733435\n" +
                    "Lecturer : Susan McKeever\n" +
                    "Program Description: The purpose of the project is to take one dataset and build a tool " +
                    "that shows interesting\n facts from the dataset. " +
                    "The datasets are at : https://data" + ".gov.ie/data/search?res_format=CSV" +
                    "You  will need to LOAD the\n" + "dataset, or a subset of it, into a relational" +
                    " database yourself – and get a connection working" + " between\n your java code and the database.");
        }
        if(e.getSource() == load){
            JOptionPane.showMessageDialog(this,
                    "This should take around 30 seconds");
            //table.populateTable();
            myTable.createTable();
            myTable.insert();
            JOptionPane.showMessageDialog(this,
                    "Table has been loaded");

        }

        if(e.getSource() == show){
            /*PRINTS THE DATABASE*/

            JOptionPane.showMessageDialog(this,
                    "Also prints in the console");
            String [][] myData;
            myData = myTable.getData();
            text = new JTable(myData, header);
            text.setBounds(100, 100, 800, 800);
            this.add(text);
            text.doLayout();
            System.out.println("In the show button: ");
            for(int i = 0; i< 4487 ;i++)
            {
                for(int j = 0;j<8;j++)
                    System.out.println(myData[i][j]);
                System.out.println();
            }
            JScrollPane scr=new JScrollPane(text);
            add(scr);
            /*Create a scroll bar*/
        }
        if(e.getSource() == intOwnParams){

        }
        if (e.getSource() == dropTable){
            myTable.dropTable();

            JOptionPane.showMessageDialog(this,
                    "Table was droped");
        }
        if(e.getSource() == interestingFacts){
            JOptionPane.showMessageDialog(this,
                    "No of records: " + myTable.countAll());

        }
    }
}