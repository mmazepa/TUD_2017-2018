package domain;

public class Animal {

	private String name = "";
  private String gender = "";
	private int yob = 0;

  public Animal(String name, String gender, int yob) {
    this.name = name;
    this.gender = gender;
    this.yob = yob;
  }

  public Animal(){

  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
