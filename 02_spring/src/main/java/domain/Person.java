package domain;

public class Person {

	private String firstName = "";
  private String gender = "";
	private int yob = 0;

  public Person(String firstName, String gender, int yob) {
    this.firstName = firstName;
    this.gender = gender;
    this.yob = yob;
  }

  public Person(){

  }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

}
