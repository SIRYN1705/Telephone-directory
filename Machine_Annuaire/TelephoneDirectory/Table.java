package TelephoneDirectory;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Table extends JFrame {
	
    JTextField txtFirstName;
    JTextField txtLastName;
    JTextField txtDateOfBirth;
    JTextField txtAdress;
    
    private Modele modele = new Modele();
    private JTable tableau;
    
    private static JTextField id;
    private static JTextField name;
    private static JTextField number;
    
    public Table() {
    	super();
    	
    	setTitle("TelephoneDirectory");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	tableau = new JTable(modele);
    	getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
    	
    	JPanel boutons = new JPanel(); 
    	
    	JLabel idlabel=new JLabel("ID:");     
    	JLabel namelabel= new JLabel("Nom:");    
    	JLabel numberlabel= new JLabel("Numero:");
    	
    	id=new JTextField();
    	name = new JTextField();
    	number=new JTextField();
    	
    	    
    	id.setColumns(10);  
    	name.setColumns(10);   
    	number.setColumns(10);  
    	
    	boutons.add(idlabel);
    	boutons.add(namelabel); 
    	boutons.add(numberlabel);
    	
    	 
    	boutons.add(id);
    	boutons.add(name);
    	boutons.add(number);
    	
    	boutons.add(new JButton(new AddAction()));
    	boutons.add(new JButton(new RemoveAction()));
    	getContentPane().add(boutons, BorderLayout.SOUTH);
    	
    	pack();
    	
    }
    
    public static void main(String[] args) {
		
    	new Table().setVisible(true);	
    }
	
    private class AddAction extends AbstractAction {        
    	private AddAction() {            
    		super("Add");        
    	}
       
    	public void actionPerformed(ActionEvent e) {
        	
    		try {
                int row=modele.getRowCount();
                
                modele.AddClient(Integer.parseInt(id.getText()),name.getText(),Integer.parseInt(number.getText()),row);                
                id.setText("");
                name.setText("");
                number.setText("");        
    		} catch (SQLException ex) {               
    			throw new RuntimeException(ex);            
    		}   
    	}  
    }	
    private class RemoveAction extends AbstractAction {		
    	private RemoveAction() {			
    		super("Delete");			
    	}
					
    	public void actionPerformed(ActionEvent e) {					
    		int[] selection = tableau.getSelectedRows();			  		
    		for(int i = selection.length - 1; i >= 0; i--){				
    			try {
                    modele.removeAmi(i+1);                
    			} catch (SQLException ex) {
                    throw new RuntimeException(ex); 
    			}
    		}
    	}
    }
}