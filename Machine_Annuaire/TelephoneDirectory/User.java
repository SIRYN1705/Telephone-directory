package TelephoneDirectory;

public class User {
	private int Id;
	private String Name;
	private int Number;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	
	public User(int id, String name, int number) {
		 super();
		 this.Id = id;
		 this.Name = name;
		 this.Number=number;
		 }
}