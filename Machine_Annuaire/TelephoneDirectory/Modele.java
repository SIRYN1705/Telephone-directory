package TelephoneDirectory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Modele extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final List<User> user = new ArrayList<User>();
	private ResultSet res = null;
	public Modele() {
		super();
		res = getSource();
		}
	private ResultSet getSource() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TelephoneDirectory", "root", "");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM User");
			return rs;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
					}
		return null;
		}
	
	public int getRowCount() {
		try {
			res.last();
			int pos = res.getRow();
			return pos;
			} catch (SQLException e) {
				e.printStackTrace();
				}
		return 0;
		}
	
	public int getColumnCount() {
		try {
			java.sql.ResultSetMetaData meta = res.getMetaData();
			return meta.getColumnCount();
			} catch (SQLException e) {
				e.printStackTrace();
				}
		return 0;
		}
	
	public String getColumnName(int columnIndex) {
		java.sql.ResultSetMetaData meta;
		try {
			meta = res.getMetaData();
			String nomColonne = meta.getColumnName(columnIndex+1);
			return nomColonne;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return "";
		
	}
	
	public Object getValueAt (int RowIndex, int columnIndex) {
		try {
			res.absolute(RowIndex+1);
			return res.getObject(columnIndex+1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
     return true;
     }
	
	public void setValueAt(Object aValue,int rowIndex,int columnIndex){
		if(aValue!=null){
         try {
             res.absolute(rowIndex+1);
             res.updateObject(columnIndex+1,aValue);
             res.updateRow();
         } catch (SQLException e){
             e.printStackTrace();
         }
     }
 }
	
	public void removeAmi(int rowIndex) throws SQLException {
     res.absolute(rowIndex);
     res.deleteRow();
     fireTableRowsDeleted(rowIndex,rowIndex);
     
	}

	public void AddClient(int id, String name, int number, int row) throws SQLException{

     res.moveToInsertRow();
     res.updateInt(1,id);
     res.updateString(2,name);
     res.updateInt(3,number);
     res.insertRow();
     fireTableRowsInserted(row,row);
     
	}

	public List<User> getNumber() {
	return user;
	}
}