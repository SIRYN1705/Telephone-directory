package TelephoneDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Menu implements ActionListener{     
	
	static JMenuItem bdd ; 
	static JMenuItem Search;  
    
	public static void main(String args[]){
		JFrame frame = new JFrame("Home");
		frame.setSize(250,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		Menu obj = new Menu();
		Menu obj1 = new Menu();
		JMenu menu = new JMenu("Menu");
		bdd = new JMenuItem();	    
		bdd=new JMenuItem("View Database");
	    bdd.addActionListener(obj);	   
	    menu.add(bdd);
	    Search = new JMenuItem();
	    Search=new JMenuItem("Search a number by person's name");
	    Search.addActionListener(obj1);
	    menu.add(Search);
	    JMenuBar mb=new JMenuBar();
	    mb.add(menu);
	    frame.setJMenuBar(mb);
	    frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){ 
		if (e.getSource() == bdd) {
			Table CAL = new Table();
			CAL.setVisible(true);
		} else if (e.getSource() == Search) {
			Interface CAL = new Interface();
			CAL.setVisible(true);
		}
	}
}