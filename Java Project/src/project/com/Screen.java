package project.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Screen extends JFrame implements ActionListener{

    JMenuBar bar;
    JMenu database, hardCodedQuerries;
    JMenuItem load, show, intOwnParams, home, about;

    public Screen(String title){
        super(title);
        setLayout(null);

        setSize(500, 500);
        bar = new JMenuBar();
        //Declare all the menu items
        load = new JMenuItem("Load");
        show = new JMenuItem("Show");
        intOwnParams = new JMenuItem("Search");
        home = new JMenuItem("Home");
        about = new JMenuItem("About");
        //Declare Menu's
        hardCodedQuerries = new JMenu("Queries");
        database = new JMenu("Database");
        database.add(load);
        database.add(show);
        database.add(intOwnParams);
        database.add(hardCodedQuerries);
        bar.add(home);
        bar.add(about);
        bar.add(database);

        setJMenuBar(bar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
