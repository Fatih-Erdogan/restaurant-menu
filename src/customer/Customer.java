package customer;


public class Customer {

	private String name;
	private int age;
	private int numOfPerson;
	
	public Customer(String name, int age, int numOfPerson) {
		this.name = name;
		this.age = age;
		this.numOfPerson = numOfPerson;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getNumOfPerson() {
		return numOfPerson;
	}
	
	public void setNumOfPerson(int num) {
		numOfPerson = num;
	}

	
}
