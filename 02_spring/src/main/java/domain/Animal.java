package domain;

public class Animal {

  private String species = "";
	private String name = "";
  private String gender = "";
	private int yob = 0;

  public Animal(String species, String name, String gender, int yob) {
    this.species = species;
    this.name = name;
    this.gender = gender;
    this.yob = yob;
  }

  public Animal(){

  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
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
