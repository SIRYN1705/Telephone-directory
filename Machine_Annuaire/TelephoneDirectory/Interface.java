package TelephoneDirectory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Interface extends JFrame{
	
	private JPanel panel = new JPanel();
	 
    private JLabel Name = new JLabel("Name : ");
    private JLabel Number = new JLabel("Number : ");

    private JTextField nameTextField = new JTextField(40);
    private JTextField numberTextField = new JTextField(40);
   
    private JPanel buttons = new JPanel();
    private JButton btn = new JButton("Search");
    

    public Interface() {
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Annuaire téléphonique");
        btn.addActionListener(new btnAction());


        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setLayout(new GridLayout(6, 1));

        panel.add(Name);
        panel.add(nameTextField);
        
        panel.add(Number);
        panel.add(numberTextField);
        numberTextField.setEnabled(false);
        

        buttons.setLayout(new GridLayout(1, 1));
        buttons.add(btn);
        

        panel.add(new JLabel());
        panel.add(buttons);

        this.add(panel, BorderLayout.CENTER);
        this.toFront();
        this.requestFocus();
       	    }
    
	public static void main(String[] args) {
		new Interface().setVisible(true);	
	}
	
	private class btnAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
        	try { 
        		Socket s = new Socket("localhost",1717);
        		
        		BufferedReader in = new BufferedReader( new InputStreamReader(s.getInputStream()));
        		PrintWriter out = new PrintWriter( s.getOutputStream(),true);
        		
        		out.println(nameTextField.getText()); 	        	
        		
        		s.close();
        		
        	} catch (Exception ex) {
        			System.out.println(ex);	
        	}
        }
    }
}